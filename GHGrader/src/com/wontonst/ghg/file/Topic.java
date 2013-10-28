/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.file;

import com.wontonst.ghg.exceptions.IncompleteTopicException;
import com.wontonst.ghg.parser.TopicBuilder;
import com.wontonst.util.BuildString;
import java.util.List;

/**
 *
 * @author RoyZheng
 */
public class Topic extends Component{
    List<Requirement> requirements;
    
    public Topic(TopicBuilder builder) throws IncompleteTopicException{
        List<String> c = builder.check();
        if(c != null && !c.isEmpty())throw new IncompleteTopicException("Missing mandatory field [" + BuildString.Build(c, " ") + "]");
        this.title = builder.getTitle();
        this.requirements = builder.getRequirements();
    }
}
