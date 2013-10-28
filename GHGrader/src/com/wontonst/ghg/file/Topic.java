/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.file;

import com.wontonst.ghg.parser.TopicBuilder;
import java.util.List;

/**
 *
 * @author RoyZheng
 */
public class Topic extends Component{
    List<Requirement> requirements;
    
    public Topic(TopicBuilder builder){
        this.title = builder.getTitle();
        this.requirements = builder.getRequirements();
    }
}
