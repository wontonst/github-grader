/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.file;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author RoyZheng
 */
public class Variables {

    Map<String, String> custom_variables = new HashMap<String, String>();

    public String get(String k){
        return this.custom_variables.get(k);
    }
    public void add(String k, String v) {
        this.custom_variables.put(k, v);
    }
    public Set<String> keySet(){
        return this.custom_variables.keySet();
    }
}
