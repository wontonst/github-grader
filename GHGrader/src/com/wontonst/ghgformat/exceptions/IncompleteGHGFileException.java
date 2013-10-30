/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgformat.exceptions;

import java.util.List;

/**
 * Exception that should only get thrown if the programmer of this program made an error.
 * @author RoyZheng
 */
public class IncompleteGHGFileException extends GHGException {

    List<String> missing_fields;

    public IncompleteGHGFileException(List<String> missing, String s) {
        super(s);
        this.missing_fields = missing;
    }

    public IncompleteGHGFileException(String s) {
        super(s);
    }

    public List<String> getMissingFields() {
        return this.missing_fields;
    }
}
