/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.exceptions;

/**
 *
 * @author RoyZheng
 */
public class MalformedGHGFileException extends GHGException {

    public MalformedGHGFileException(String error, String line, int line_number) {
        super(error + " has occurred on line " + line_number + ": " + line);
    }
}