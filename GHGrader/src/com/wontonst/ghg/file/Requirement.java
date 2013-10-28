/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.file;

import com.wontonst.ghg.parser.RequirementBuilder;
import java.util.List;

/**
 *
 * @author RoyZheng
 */
public class Requirement extends Component{
    List<Comment> comments;
    public Requirement(RequirementBuilder builder){
        this.title = builder.getTitle();
        this.comments = builder.getComments();
    }
}
