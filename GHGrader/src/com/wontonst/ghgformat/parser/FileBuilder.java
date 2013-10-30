/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgformat.parser;

import com.wontonst.ghgformat.file.GHGFile;
import com.wontonst.ghgformat.exceptions.IncompleteGHGFileException;
import com.wontonst.ghgformat.exceptions.IncompleteRequirementException;
import com.wontonst.ghgformat.exceptions.IncompleteTopicException;
import com.wontonst.ghgformat.exceptions.IncompleteVariablesException;
import com.wontonst.ghgformat.file.Topic;
import com.wontonst.ghgformat.file.Variables;
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
    protected List<Topic> topics = new ArrayList<Topic>();
    protected TopicBuilder current_topic = null;

    public FileBuilder() {
    }

    public void finalizeVariables() throws IncompleteVariablesException {
        this.variables = this.variables_builder.build();
    }

    public void addTopic(String s) throws IncompleteTopicException, IncompleteRequirementException {
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

    public GHGFile build() throws IncompleteGHGFileException, IncompleteTopicException, IncompleteRequirementException {
        if (this.variables == null) {
            throw new IncompleteGHGFileException("Variables object not initialized - this is a programmer error.");
        }
        this.topics.add(this.current_topic.build());
        return new GHGFile(this);
    }

    public Variables getVariables() {
        return this.variables;
    }

    public List<Topic> getTopics() {
        return this.topics;
    }
}
