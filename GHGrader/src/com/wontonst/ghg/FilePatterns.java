/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg;

import java.util.regex.Pattern;

/**
 *
 * @author RoyZheng
 */
public class FilePatterns {

    public static Pattern header_marker = Pattern.compile("[-]{3}+");
    //  public static Pattern header_title = Pattern.compile("title:[.]++");
    public static Pattern header_variable = Pattern.compile("^\\w++:\\w$");

    public static MapEntry getHeaderVariable(String line) {
        String key = line.substring(0, line.indexOf(":"));
        String value = line.substring(line.indexOf(":")).trim();
        if (key.indexOf(" ") != -1) {
            return null;
        }
      //  if (value.indexOf(" ") != -1) {
        //    return null;
      //  }
        return new MapEntry(key, value);
    }
}
