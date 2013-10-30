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

    int value = 0, input_value;
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

    @Override
    public void toString(Format f, StringBuilder b) {
        switch (f) {
            case MD:
            case HTML:
            case JEKYLL_HTML:
            case JEKYLL_MD:
                b.append("###");
                b.append(this.title);
                b.append(" [");
                b.append(this.value);
                b.append(this.value == 1 ? " point" : " points");
                b.append("]");
                break;
            case GHG:
                b.append(this.title);
                break;
        }
        for (Requirement r : this.requirements) {
            b.append("\n");
            r.toString(f, b);
        }
    }
}
