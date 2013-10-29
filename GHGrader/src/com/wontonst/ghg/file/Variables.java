/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.file;

import com.wontonst.ghg.exceptions.IncompleteVariablesException;
import com.wontonst.ghg.parser.VariablesBuilder;
import com.wontonst.util.BuildString;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author RoyZheng
 */
public class Variables {

    Map<String, String> custom_variables = new HashMap<String, String>();

    public Variables(VariablesBuilder builder) throws IncompleteVariablesException {
        List<String> c = builder.check();
        if (c != null && !c.isEmpty()) {
            throw new IncompleteVariablesException("Missing mandatory field [" + BuildString.Build(c, " ") + "]", c);
        }
        this.custom_variables = builder.getVariables();
    }

    public String get(String k) {
        return this.custom_variables.get(k);
    }

    public Set<String> keySet() {
        return this.custom_variables.keySet();
    }

    public void toString(StringBuilder b) {
        for (String k : this.custom_variables.keySet()) {
            b.append(k + "=");
            b.append(this.custom_variables.get(k));
            b.append("\n");
        }
    }
}
