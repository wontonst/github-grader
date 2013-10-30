/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgformat.exceptions;

/**
 *
 * @author RoyZheng
 */
public class IncompleteRequirementException extends GHGException {

    String requirement;

    public IncompleteRequirementException(String requirement, String msg) {
        super(msg);
        this.requirement = requirement;
    }

    public String getRequirement() {
        return requirement == null || requirement.isEmpty() ? "[REQUIREMENT TITLE NOT SET]" : this.requirement;
    }
}
