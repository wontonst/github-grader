/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg;

import com.wontonst.ghg.file.GHGFile;
import com.wontonst.ghg.exceptions.IncompleteGHGFileException;
import com.wontonst.ghg.exceptions.MalformedGHGFileException;
import com.wontonst.patterns.Singleton;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;

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
            builder.addVariable(me);;
        }
        
        
        
        /*
         ---
         title: Restaurant v2.1
         allow-negative: false
         organization: usc-csci201-fall2013
         required-for-each: 1: Clear documentation
         ---
         Milestone v2.1A - Fulfill the Requirements of v2
         2: One customer, one waiter
         2: Multiple customers, one waiter
         $$(required-for-each)
         Milestone v2.1B &ndash; Full Design of All 6 Agents (Cook, Waiter, Host, Customer, Cashier, Market)
         1: You are to develop an interaction diagram for the normative scenario.
         1: The interaction diagram should include message numbers, parameters, good message names.
         2: Full Design document for all the agents. The document must include:
         $$(required-for-each)
         Deductions
         -10: Not using the agent methodology correctly:
         Shouldn't access fields/values of another agent.
         Shouldn't pass pointers in messages (other than agent pointers).
         -5: Runtime errors other than concurrent modification errors, which we ignore for now.
         */
        return builder.build();
    }
}
