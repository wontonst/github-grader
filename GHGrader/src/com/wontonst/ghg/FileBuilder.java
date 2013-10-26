/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg;

import com.wontonst.ghg.file.GHGFile;
import com.wontonst.ghg.exceptions.IncompleteGHGFileException;
import com.wontonst.ghg.file.Requirement;
import com.wontonst.ghg.file.Topic;
import com.wontonst.ghg.file.Variables;
import com.wontonst.util.BuildString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RoyZheng
 */
public class FileBuilder {

    Variables v = new Variables();
    List<Topic> topics;
    List<Requirement> requirements;
    String[] mandatory_fields = {"TITLE"};

    public FileBuilder() {
    }

    public void adTopic(String s) {
        this.topics.add(new Topic(s));
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
        StringBuilder sb = new StringBuilder();
        List<String> checked = this.check();
        if (!checked.isEmpty()) {
            throw new IncompleteGHGFileException(checked, "Missing mandatory field [" + BuildString.Build(checked, " ") + "]");
        }
        return new GHGFile(this);
    }
}
