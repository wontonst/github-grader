/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

import com.wontonst.ghg.exceptions.IncompleteRequirementException;
import com.wontonst.ghg.file.Comment;
import com.wontonst.ghg.file.Requirement;
import com.wontonst.patterns.Builder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RoyZheng
 */
public class RequirementBuilder extends BuilderBase<Requirement> {

    int value;

    public int getValue() {
        return this.value;
    }
    List<Comment> comments = new ArrayList<Comment>();

    public RequirementBuilder() {
    }

    public void addComment(String s) {
        this.comments.add(new Comment(s));
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    @Override
    public Requirement build() throws IncompleteRequirementException {
        if (!RequirementBuilder.isRequirement(this.getTitle())) {
            throw new IncompleteRequirementException(this.getTitle(), this.getTitle() + " is not a valid requirement format.");
        }
        this.value = Integer.parseInt(this.title.substring(0, this.title.indexOf(":")));
        this.title = this.title.substring(this.title.indexOf(":")+1);
        return new Requirement(this);
    }

    @Override
    public List<String> check() {
        List<String> errors = new ArrayList<String>();
        if (this.title == null || this.title.isEmpty()) {
            errors.add("title");
        }
        return errors;
    }

    public static boolean isRequirement(String s) {
        String s2 = s.trim();
        if (s2.indexOf(":") == -1) {
            return false;
        }
        try {
            Integer.parseInt(s2.substring(0, s2.indexOf(":")));
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
