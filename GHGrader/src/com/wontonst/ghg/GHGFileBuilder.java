/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg;

import com.wontonst.ghg.exceptions.IncompleteGHGFileException;
import com.wontonst.util.BuildString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RoyZheng
 */
public class GHGFileBuilder {

    Map<String, String> custom_variables = new HashMap<String, String>();
    String[] mandatory_fields = {"TITLE"};

    public GHGFileBuilder() {
    }

    public void addVariable(String name, String variable) {
        this.custom_variables.put(name, variable);
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
        for (String key : this.custom_variables.keySet()) {
            if (missing.contains(key) && !this.custom_variables.get(key).isEmpty()) {
                missing.remove(key);
            }
        }
        return missing;
    }

    public GHGFile build() throws IncompleteGHGFileException {
        StringBuilder sb = new StringBuilder();
        List<String> checked = this.check();
        if (!checked.isEmpty()) {
            throw new IncompleteGHGFileException(checked,"Missing mandatory field [" + BuildString.Build(checked, " ") + "]");
        }
        return new GHGFile(this);
    }
}
