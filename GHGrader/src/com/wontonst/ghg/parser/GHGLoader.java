/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

import com.wontonst.ghg.file.GHGFile;
import com.wontonst.ghg.exceptions.IncompleteGHGFileException;
import com.wontonst.ghg.exceptions.IncompleteRequirementException;
import com.wontonst.ghg.exceptions.IncompleteTopicException;
import com.wontonst.ghg.exceptions.IncompleteVariablesException;
import com.wontonst.ghg.exceptions.MalformedGHGFileException;
import com.wontonst.ghg.file.Comment;
import com.wontonst.patterns.Singleton;
import java.io.FileNotFoundException;

/**
 * @brief loads a .ghg file and converts it into a GHGFile object
 * @author RoyZheng
 */
public class GHGLoader extends Singleton {

    public enum Block {

        TOPIC, REQUIREMENT, COMMENT
    }

    public static GHGFile load(String path) throws FileNotFoundException, IncompleteGHGFileException, MalformedGHGFileException {
        GHGScanner sc = new GHGScanner(path);
        FileBuilder builder = new FileBuilder();

        String line = sc.nextLine();
        try {
            if (!line.equals("---")) {
                throw new MalformedGHGFileException("File does not start with proper yaml-styled variables declaration", line, sc.getLineNumber());
            }
            while (true) {//while we're still in the variables header block
                line = sc.nextLine();
                if (line.equals("---")) {
                    break;
                }
                MapEntry me = FilePatterns.getHeaderVariable(line);
                if (me == null) {
                    throw new MalformedGHGFileException("Yaml-styled variable declaration contains an error", line, sc.getLineNumber());
                }
                builder.addVariable(me);
            }
            try {
                builder.finalizeVariables();
            } catch (IncompleteVariablesException ex) {
                throw new MalformedGHGFileException("Variables declaration incomplete. " + ex.getMessage(), line, sc.getLineNumber());
            }

            Block state = Block.TOPIC;
            boolean grab = true;
            while (true) {
                if (grab) {
                    if (!sc.hasNextLine()) {
                        break;
                    }
                    line = sc.nextLine();
                }
                grab = false;
                switch (state) {
                    case TOPIC:
                        if (line.charAt(0) == '\t') {
                            throw new MalformedGHGFileException("Topic " + line + " cannot start with a tab.", line, sc.getLineNumber());
                        }
                        builder.addTopic(line);
                        state = Block.REQUIREMENT;
                        grab = true;
                        break;
                    case REQUIREMENT:
                        if (line.charAt(0) != '\t') {
                            state = Block.TOPIC;
                            break;
                        }
                        builder.addRequirement(line.trim());
                        state = Block.COMMENT;
                        grab = true;
                    case COMMENT:
                        if (line.charAt(0) != '\t') {
                            state = Block.TOPIC;
                            break;
                        }
                        if (!Comment.isComment(line)) {
                            state = Block.REQUIREMENT;
                            break;
                        }
                        builder.addComment(line.trim());
                        grab = true;
                }
            }
            /*
             while (true) {//grab topic
             //          if (line.isEmpty() || !sc.hasNextLine()) {
             //              break;
             //           }
             //             line = sc.nextLine();
             //            if (line.charAt(0) == '\t') {
             //                throw new MalformedGHGFileException("Topic " + line + " cannot start with a tab.", line, sc.getLineNumber());
             //           }
             //          builder.addTopic(line.trim());
             while (true) {//get requirements
             //           line = sc.nextLine();
             //             if (line.charAt(0) != '\t' || line.isEmpty()) {
             //                 break;
             //             }
             //               builder.addRequirement(line.trim());

             while (sc.hasNextLine()) {//get comments
             //            line = sc.nextLine();
             //        if (line.isEmpty() || line.charAt(0) != '\t' || !Comment.isComment(line)) {
             //             break;
             //         }
             builder.addComment(line.trim());
             line = null;
             }
             }
             }*/
            return builder.build();
        } catch (IncompleteTopicException ex) {
            throw new MalformedGHGFileException("Topic \"" + ex.getTopic() + "\" has an error: " + ex.getMessage(), line, sc.getLineNumber());
        } catch (IncompleteRequirementException ex) {
            throw new MalformedGHGFileException("Requirement \"" + ex.getRequirement() + "\" has an error: " + ex.getMessage(), line, sc.getLineNumber());
        }
    }

    public static void main(String[] args) {
        System.out.println("Working from " + System.getProperty("user.dir"));
        try {
            System.out.println(GHGLoader.load("sample.ghg"));
        } catch (FileNotFoundException ex) {
            System.out.println("file not found: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IncompleteGHGFileException ex) {
            System.out.println("Incomplete GHGFile: " + ex.getMessage());
        } catch (MalformedGHGFileException ex) {
            System.out.println("MalformedGHGFile: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
