/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

import java.util.Scanner;

/**
 *
 * @author RoyZheng
 */
public class GHGScanner {

    protected int line_number = 0;
    protected Scanner scanner;

    public GHGScanner(String f) {
        this.scanner = new Scanner(f);
    }

    public int getLineNumber() {
        return this.line_number;
    }

    public String nextLine() {
        line_number++;
        return this.scanner.nextLine();
    }
}
