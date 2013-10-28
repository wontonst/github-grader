/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

import com.wontonst.ghg.parser.FileBuilder;
import com.wontonst.ghg.file.GHGFile;
import com.wontonst.ghg.exceptions.IncompleteGHGFileException;
import com.wontonst.ghg.exceptions.IncompleteTopicException;
import com.wontonst.ghg.exceptions.MalformedGHGFileException;
import com.wontonst.patterns.Singleton;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @brief loads a .ghg file and converts it into a GHGFile object
 * @author RoyZheng
 */
public class GHGLoader extends Singleton {

    public static GHGFile load(String path) throws FileNotFoundException, IncompleteGHGFileException, MalformedGHGFileException {
        GHGScanner sc = new GHGScanner(path);
        FileBuilder builder = new FileBuilder();

        String line = sc.nextLine();
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
        line = sc.nextLine();
        while (true) {//grab topic/requirement/comments
            if (line.isEmpty()) {
                break;
            }
            if (line.charAt(0) == '\t') {
                throw new MalformedGHGFileException("Topic " + line + " cannot start with a tab.", line, sc.getLineNumber());
            }
            try {
                builder.addTopic(line.trim());
            } catch (IncompleteTopicException ex) {
                throw new MalformedGHGFileException(ex.getTopic() + " has an error: " + ex.getMessage(), line, sc.getLineNumber());
            }

        }
        return builder.build();
    }
}
