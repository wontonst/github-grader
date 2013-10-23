/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg;

import com.wontonst.ghg.exceptions.IncompleteGHGFileException;
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
public class GHGLoader extends Singleton{
    
    public static GHGFile load(String path) throws FileNotFoundException, IncompleteGHGFileException {
        Scanner sc = new Scanner(new File(path));
        GHGFileBuilder builder = new GHGFileBuilder();
        
        String delimit = sc.nextLine();
        
        
        return builder.build();
    }
    
}
