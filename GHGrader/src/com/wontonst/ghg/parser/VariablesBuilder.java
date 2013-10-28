/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

import com.wontonst.ghg.file.Variables;
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
    public Variables build() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> check() {

        List<String> missing = new ArrayList<String>();
        for (String s : this.mandatory_fields) {
            missing.add(s);
        }
        for (String key : this.variables.keySet()) {
            if (missing.contains(key) && !this.variables.get(key).isEmpty()) {
                missing.remove(key);
            }
        }
        return missing;
    }

    public Map<String, String> getVariables() {
        return this.variables;
    }
}
