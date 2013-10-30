/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.model;

import com.wontonst.ghgformat.file.GHGFile;

/**
 *
 * @author RoyZheng
 */
public class Student {

    GHGFile file;
    protected String repo_name, username;

    public Student(GHGFile f, String repository, String usr) {
        this.repo_name = repository;
        this.username = usr;
    }
}
