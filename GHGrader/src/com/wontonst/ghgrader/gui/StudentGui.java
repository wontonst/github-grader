/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.gui;

import com.wontonst.ghgrader.model.Student;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author RoyZheng
 */
public class StudentGui extends JPanel {

    Student student;

    public StudentGui(Student s, List<TopicGui> topics) {
        this.student = s;
        this.setLayout(new GridLayout(0, 1));
        for (TopicGui g : topics) {
            this.add(g);
        }
    }
}
