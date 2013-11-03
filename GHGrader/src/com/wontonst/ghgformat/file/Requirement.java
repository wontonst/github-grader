/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgformat.file;

import com.wontonst.ghgformat.exceptions.IncompleteRequirementException;
import com.wontonst.ghgformat.parser.RequirementBuilder;
import com.wontonst.ghgrader.gui.CommentGui;
import com.wontonst.ghgrader.gui.RequirementGui;
import com.wontonst.util.BuildString;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RoyZheng
 */
public class Requirement extends Component {
    
    int value, input_value;
    List<Comment> comments;
    
    private Requirement() {
    }
    
    public Requirement(RequirementBuilder builder) throws IncompleteRequirementException {
        List<String> c = builder.check();
        if (c != null && !c.isEmpty()) {
            throw new IncompleteRequirementException(builder.getTitle(), "Missing mandatory field [" + BuildString.Build(c, " ") + "]");
        }
        this.title = builder.getTitle();
        this.comments = builder.getComments();
        this.value = builder.getValue();
    }
    
    public void setInputValue(int i) {
        this.input_value = i;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public RequirementGui toPanel() {
        List<CommentGui> c = new ArrayList<CommentGui>();
        for (Comment cm : this.comments) {
            c.add(cm.toPanel());
        }
        return new RequirementGui(this,c);
    }
    
    @Override
    public void toString(Format f, StringBuilder b) {
        switch (f) {
            case MD:
            case JEKYLL_MD:
            case JEKYLL_HTML:
            case HTML:
                b.append("  - _**(");
                b.append(this.value);
                b.append(this.value == 1 ? " point" : " points");
                b.append("**_)");
                break;
            case GHG:
                b.append("\t");
                b.append(this.value);
                b.append(": ");
        }
        b.append(this.title);
        // b.append("\n");
        for (Comment c : this.comments) {
            b.append("\n");
            c.toString(f, b);
        }
    }
    
    public Requirement deepClone() {
        Requirement r = new Requirement();
        r.value = this.value;
        r.input_value = this.input_value;
        List<Comment> comm = new ArrayList<Comment>();
        for (Comment c : this.comments) {
            comm.add(c.deepClone());
        }
        r.comments = comm;
        return r;
    }
}
