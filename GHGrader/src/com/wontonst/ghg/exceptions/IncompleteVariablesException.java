/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.exceptions;

import java.util.List;

/**
 *
 * @author RoyZheng
 */
public class IncompleteVariablesException extends GHGException {

    List<String> values_missing;

    public IncompleteVariablesException(String error, List<String> missing) {
        super(error);
        this.values_missing = missing;
    }
}
