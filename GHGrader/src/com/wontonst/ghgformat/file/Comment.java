/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgformat.file;

import com.wontonst.ghgformat.parser.RequirementBuilder;
import com.wontonst.ghgrader.gui.CommentGui;
import javax.swing.JPanel;

/**
 *
 * @author RoyZheng
 */
public class Comment extends Component {

    private Comment() {
    }

    public Comment(String s) {
        this.title = s;
    }

    public static boolean isComment(String s) {
        String s2 = s.trim();
        if (s2.indexOf(":") == -1) {
            return true;
        }
        return !RequirementBuilder.isRequirement(s);
    }

    @Override
    public void toString(Format f, StringBuilder b) {
        switch (f) {
            case MD:
                b.append("    + ");
                break;
            case GHG:
                b.append("\t\t");
                break;
        }
        b.append(this.title);
    }

    public Comment deepClone() {
        return new Comment(this.title);
    }

    @Override
    public CommentGui toPanel() {
        return new CommentGui(this);
    }
}
