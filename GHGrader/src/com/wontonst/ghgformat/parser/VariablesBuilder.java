/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgformat.parser;

import com.wontonst.ghgformat.exceptions.IncompleteVariablesException;
import com.wontonst.ghgformat.file.Variables;
import com.wontonst.patterns.Builder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RoyZheng
 */
public class VariablesBuilder implements Builder<Variables> {

    Map<String, String> variables = new HashMap<String, String>();
    protected String[] mandatory_fields = {"TITLE"};

    public VariablesBuilder() {
    }

    public void addVariable(String s, String v) {
        this.variables.put(s, v);
    }

    @Override
    public Variables build() throws IncompleteVariablesException {
        return new Variables(this);
    }

    @Override
    public List<String> check() {
        List<String> missing = new ArrayList<String>();
        for (String s : this.mandatory_fields) {
            missing.add(s);
        }
        for (String key : this.variables.keySet()) {
            if (missing.contains(key.toUpperCase()) && !this.variables.get(key).isEmpty()) {
                missing.remove(key.toUpperCase());
            }
        }
        return missing;
    }

    public Map<String, String> getVariables() {
        return this.variables;
    }
}
