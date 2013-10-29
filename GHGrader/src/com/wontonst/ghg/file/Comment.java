/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.file;

import com.wontonst.ghg.parser.RequirementBuilder;

/**
 *
 * @author RoyZheng
 */
public class Comment extends Component {

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


}
