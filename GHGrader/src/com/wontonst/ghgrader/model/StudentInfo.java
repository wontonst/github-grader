/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.model;

/**
 *
 * @author RoyZheng
 */
public class StudentInfo {

    String repo_name, username;

    public StudentInfo(String repository, String usr) {
        this.repo_name = repository;
        this.username = usr;
    }
}
