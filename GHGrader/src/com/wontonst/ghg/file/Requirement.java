/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.file;

import com.wontonst.ghg.exceptions.IncompleteRequirementException;
import com.wontonst.ghg.parser.RequirementBuilder;
import com.wontonst.util.BuildString;
import java.util.List;

/**
 *
 * @author RoyZheng
 */
public class Requirement extends Component {

    int value;
    List<Comment> comments;

    public Requirement(RequirementBuilder builder) throws IncompleteRequirementException {
        List<String> c = builder.check();
        if (c != null && !c.isEmpty()) {
            throw new IncompleteRequirementException(builder.getTitle(), "Missing mandatory field [" + BuildString.Build(c, " ") + "]");
        }
        this.title = builder.getTitle();
        this.comments = builder.getComments();
        this.value = builder.getValue();
    }

    public int getValue() {
        return this.value;
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
            c.toString(f,b);
        }
    }
}
