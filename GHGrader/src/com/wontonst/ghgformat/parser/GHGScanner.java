/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgformat.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author RoyZheng
 */
public class GHGScanner {

    protected int line_number = 0;
    protected Scanner scanner;

    /**
     * Customer file scanner to keep track of line number.
     *
     * @param f
     */
    public GHGScanner(String f) throws FileNotFoundException {
        this.scanner = new Scanner(new File(f));
    }

    public GHGScanner(File f) throws FileNotFoundException {
        this.scanner = new Scanner(f);
    }

    public int getLineNumber() {
        return this.line_number;
    }

    public boolean hasNextLine() {
        return this.scanner.hasNextLine();
    }

    public String nextLine() {
        line_number++;
        return this.scanner.nextLine();
    }
}
