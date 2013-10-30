/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgformat.parser;

import com.wontonst.ghgformat.file.GHGFile;
import com.wontonst.ghgformat.exceptions.IncompleteGHGFileException;
import com.wontonst.ghgformat.exceptions.IncompleteRequirementException;
import com.wontonst.ghgformat.exceptions.IncompleteTopicException;
import com.wontonst.ghgformat.exceptions.IncompleteVariablesException;
import com.wontonst.ghgformat.exceptions.MalformedGHGFileException;
import com.wontonst.ghgformat.file.Comment;
import com.wontonst.ghgformat.file.Format;
import com.wontonst.patterns.Singleton;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @brief loads a .ghg file and converts it into a GHGFile object
 * @author RoyZheng
 */
public class GHGLoader extends Singleton {

    public enum Block {

        TOPIC, REQUIREMENT, COMMENT
    }

    public static GHGFile load(File file) throws FileNotFoundException, MalformedGHGFileException, IncompleteGHGFileException {
        GHGScanner sc = new GHGScanner(file);
        return GHGLoader.load(sc);
    }

    public static GHGFile load(String path) throws FileNotFoundException, IncompleteGHGFileException, MalformedGHGFileException {
        GHGScanner sc = new GHGScanner(path);
        return GHGLoader.load(sc);
    }

    private static GHGFile load(GHGScanner sc) throws MalformedGHGFileException, IncompleteGHGFileException {
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
                //System.out.println("processing " + line);
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
                        //System.out.println("adding " + line);
                        builder.addRequirement(line.trim());
                        state = Block.COMMENT;
                        grab = true;
                        break;
                    case COMMENT:
                        if (line.charAt(0) != '\t') {
                            state = Block.TOPIC;
                            break;
                        }
                        if (!Comment.isComment(line)) {
                            //System.out.println(line + "isnt");
                            state = Block.REQUIREMENT;
                            break;
                        }
                        //System.out.println(line + "is");
                        builder.addComment(line.trim());
                        grab = true;
                        break;
                }
            }
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
            GHGFile f = GHGLoader.load("sample.ghg");
            System.out.println("---GHG---");
            System.out.println(f.toString(Format.GHG));
            System.out.println("---MD---");
            System.out.println(f.toString(Format.MD));
            System.out.println("---HTML---");
            System.out.println(f.toString(Format.HTML));
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
