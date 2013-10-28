/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

import com.wontonst.ghg.parser.FileBuilder;
import com.wontonst.ghg.file.GHGFile;
import com.wontonst.ghg.exceptions.IncompleteGHGFileException;
import com.wontonst.ghg.exceptions.MalformedGHGFileException;
import com.wontonst.patterns.Singleton;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @brief loads a .ghg file and converts it into a GHGFile object
 * @author RoyZheng
 */
public class GHGLoader extends Singleton {

    public static GHGFile load(String path) throws FileNotFoundException, IncompleteGHGFileException, MalformedGHGFileException {
        Scanner sc = new Scanner(path);
        FileBuilder builder = new FileBuilder();

        int line_number = 1;
        String line = sc.nextLine();
        if (!line.equals("---")) {
            throw new MalformedGHGFileException("File does not start with proper yaml-styled variables declaration", line, line_number);
        }
        while (true) {
            line = sc.nextLine();
            if (line.equals("---")) {
                break;
            }
            MapEntry me = FilePatterns.getHeaderVariable(line);
            if (me == null) {
                throw new MalformedGHGFileException("Yaml-styled variable declaration contains an error", line, line_number);
            }
            builder.addVariable(me);
        }

        return builder.build();
    }
}
