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
public class Topic extends Component {

    int value = 0;
    List<Requirement> requirements;

    public Topic(TopicBuilder builder) throws IncompleteTopicException {
        List<String> c = builder.check();
        if (c != null && !c.isEmpty()) {
            throw new IncompleteTopicException(builder.getTitle(), BuildString.Build(c, " "));
        }
        this.title = builder.getTitle();
        this.requirements = builder.getRequirements();
        this.value = builder.getValue();
    }
    public void toString(StringBuilder b){
           b.append(this.title);
           for(Requirement r : this.requirements){
               b.append("\n");
               r.toString(b);
           }
    }
}
