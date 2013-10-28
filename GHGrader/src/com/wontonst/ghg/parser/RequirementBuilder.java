/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

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
    public Requirement build() throws Exception {
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
}
