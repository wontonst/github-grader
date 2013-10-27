/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

import com.wontonst.ghg.file.GHGFile;
import com.wontonst.ghg.exceptions.IncompleteGHGFileException;
import com.wontonst.ghg.file.Topic;
import com.wontonst.ghg.file.Variables;
import com.wontonst.patterns.Builder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RoyZheng
 */
public class FileBuilder implements Builder<GHGFile> {

    Variables v = new Variables();
    List<Topic> topics;
    
    TopicBuilder current_topic = null;
    
    String[] mandatory_fields = {"TITLE"};

    public FileBuilder() {
    }

    public void addTopic(String s) {
       if(current_topic != null){
           this.topics.add(this.current_topic.build());
       }
           this.current_topic = new TopicBuilder();
           this.current_topic.addTitle(s);
    }

    public void addRequirement(String s) {
    }

    public void addComment(String s) {
    }

    public void addVariable(MapEntry me) {
        this.addVariable(me.key, me.value);
    }

    public void addVariable(String name, String variable) {
        this.v.add(name, variable);
    }

    /**
     * @brief checks for missing mandatory fields and returns the ones missing
     * @return missing mandatory fields
     */
    public List<String> check() {
        List<String> missing = new ArrayList<String>();
        for (String s : this.mandatory_fields) {
            missing.add(s);
        }
        for (String key : this.v.keySet()) {
            if (missing.contains(key) && !this.v.get(key).isEmpty()) {
                missing.remove(key);
            }
        }
        return missing;
    }

    public GHGFile build() throws IncompleteGHGFileException {
        return new GHGFile(this);
    }
}
