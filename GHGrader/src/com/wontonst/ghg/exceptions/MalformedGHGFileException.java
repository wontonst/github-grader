/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.exceptions;

/**
 * Error thrown when user did not provide a validly formatted GHG file.
 * @author RoyZheng
 */
public class MalformedGHGFileException extends GHGException {

    public MalformedGHGFileException(String error, String line, int line_number) {
        super("Line " + line_number + "("+line+"): "+error);
    }
}
