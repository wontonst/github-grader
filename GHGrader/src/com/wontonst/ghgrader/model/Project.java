/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.model;

import com.wontonst.ghgformat.file.GHGFile;
import com.wontonst.ghgrader.gui.ProjectBuilder;
import java.util.List;

/**
 * Abstraction of a Github grading project. Contains a rubric and a list of
 * students with scores.
 *
 * @author RoyZheng
 */
public class Project {

    GHGFile file;
    List<Student> students;

    public Project(ProjectBuilder builder, List<Student> st) {
        this.file = builder.getGHGFile();
        this.students = st;
    }
}
