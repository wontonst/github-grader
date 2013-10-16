/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.util;

import java.util.List;

/**
 *
 * @author RoyZheng
 */
public class BuildString {

    public static String Build(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String Build(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        for(String s : strings){
        sb.append(s);
        }return sb.toString();
    }

    public static String Build(List<String> strings, String delimit) {
        if(strings.isEmpty())return "";
        StringBuilder sb = new StringBuilder();
        sb.append(strings.get(0));
        for(int i = 1; i  != strings.size(); i++){
            sb.append(delimit);
            sb.append(strings.get(i));
        }
        return sb.toString();
    }
}
