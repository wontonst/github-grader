/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

import com.wontonst.ghg.exceptions.IncompleteRequirementException;
import com.wontonst.ghg.exceptions.IncompleteTopicException;
import com.wontonst.ghg.file.Requirement;
import com.wontonst.ghg.file.Topic;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RoyZheng
 */
public class TopicBuilder extends BuilderBase<Topic> {

    List<Requirement> requirements = new ArrayList<Requirement>();
    RequirementBuilder current_requirement = null;
    int value = 0;

    public void addRequirement(String s) throws IncompleteRequirementException {
        if (current_requirement != null) {
            this.requirements.add(this.current_requirement.build());
        }
        this.current_requirement = new RequirementBuilder();
        this.current_requirement.addTitle(s);
    }

    public int getValue() {
        int v = 0;
        for (Requirement r : requirements) {
            v += r.getValue();
        }
        return v;
    }

    public void addComment(String s) {
        this.current_requirement.addComment(s);
    }

    public List<Requirement> getRequirements() {
        return this.requirements;
    }

    @Override
    public Topic build() throws IncompleteTopicException, IncompleteRequirementException {
        this.requirements.add(this.current_requirement.build());
        return new Topic(this);
    }

    @Override
    public List<String> check() {
        List<String> errors = new ArrayList<String>();
        if (this.title.isEmpty()) {
            errors.add("Topic does not contain a title");
        }
        if (this.requirements.isEmpty()) {
            errors.add("Topic does not contain any requirements.");
        }
        return errors;
    }

    public int numRequirements() {
        return this.requirements.size() + (this.current_requirement == null ? 0 : 1);
    }
}
