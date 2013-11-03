/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.model;

import com.wontonst.ghgformat.file.GHGFile;
import com.wontonst.ghgrader.gui.StudentGui;

/**
 * Abstraction of a single student.
 * @author RoyZheng
 */
public class Student {

    GHGFile file;
    protected StudentInfo info;

    public Student(GHGFile f, StudentInfo info) {
        this.file = f.deepClone();
        this.info = info;
    }
    public StudentGui toPanel(){
        return new StudentGui(this, file.getTopicGuis());
    }
}
