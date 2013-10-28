/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

import com.wontonst.ghg.file.GHGFile;
import com.wontonst.ghg.exceptions.IncompleteGHGFileException;
import com.wontonst.ghg.exceptions.IncompleteRequirementException;
import com.wontonst.ghg.exceptions.IncompleteTopicException;
import com.wontonst.ghg.exceptions.IncompleteVariablesException;
import com.wontonst.ghg.file.Topic;
import com.wontonst.ghg.file.Variables;
import com.wontonst.patterns.Builder;
import java.util.ArrayList;
import java.util.List;

/**
 * Build a GHGFile object.
 *
 * @author RoyZheng
 */
public class FileBuilder implements Builder<GHGFile> {

    protected Variables variables;
    protected VariablesBuilder variables_builder = new VariablesBuilder();
    protected List<Topic> topics;
    protected TopicBuilder current_topic = null;

    public FileBuilder() {
    }

    public void finalizeVariables() throws IncompleteVariablesException {
        this.variables = new Variables(variables_builder);
    }

    public void addTopic(String s) throws IncompleteTopicException {
        if (current_topic != null) {
            this.topics.add(this.current_topic.build());
        }
        this.current_topic = new TopicBuilder();
        this.current_topic.addTitle(s);
    }

    public void addRequirement(String s) throws IncompleteRequirementException {
        this.current_topic.addRequirement(s);
    }

    public void addComment(String s) {
        this.current_topic.addComment(s);
    }

    public void addVariable(MapEntry me) {
        this.addVariable(me.key, me.value);
    }

    public void addVariable(String name, String variable) {
        this.variables_builder.addVariable(name, variable);
    }

    /**
     * @brief checks for missing mandatory fields and returns the ones missing
     * @return missing mandatory fields
     */
    public List<String> check() {
        List<String> errors = new ArrayList<String>();
        if (this.topics.isEmpty()) {
            errors.add("No topics were found in file.");
        }
        return errors;
    }

    public GHGFile build() throws IncompleteGHGFileException {
        if (this.variables == null) {
            throw new IncompleteGHGFileException("Variables object not initialized - this is a programmer error.");
        }
        return new GHGFile(this);
    }

    public Variables getVariables() {
        return this.variables;
    }

    public List<Topic> getTopics() {
        return this.topics;
    }
}
