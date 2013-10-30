/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.gui;

import com.wontonst.ghgrader.model.Project;
import com.wontonst.ghgrader.model.StudentInfo;
import com.wontonst.patterns.Builder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * GUI to build a Project object.
 *
 * @author RoyZheng
 */
public class ProjectBuilder extends JFrame implements Builder<Project>, ActionListener {

    class InputFields {

        JTextField username;
        JTextField repository;

        public InputFields(String username, String repo) {
            this.username = username;
            this.repository = repo;
        }
    }
    public static int FRAME_DIM_X = 400;
    public static int FRAME_DIM_Y = 500;
    GuiCore core;
    JScrollPane scroll = new JScrollPane();
    JPanel scroll_view = new JPanel();
    List<InputFields> input;
    JButton done = new JButton("DONE");
    JButton add = new JButton("ADD ROW");
    String organization;

    public ProjectBuilder(GuiCore core, String organization) {
        this.organization = organization;
        this.setLocation(GuiCore.screenSize.width / 2 - FRAME_DIM_X / 2, GuiCore.screenSize.height / 2 - FRAME_DIM_Y / 2);
        this.core = core;
        this.setUndecorated(true);

        this.scroll.add(this.scroll_view);
        this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.done.addActionListener(this);
        this.add.addActionListener(this);


    }

    @Override
    public Project build() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> check() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.done) {
            this.core.projectBuildDone();
            return;
        }
        if (e.getSource() == this.add) {
            InputFields ifs = new InputFields(this.organization == null ? "" : this.organization, "");
            this.scroll_view.add(ifs.username);
            this.scroll_view.add(ifs.repository);
            if (this.organization != null) {
                ifs.username.setEditable(false);
            }
            this.input.add(ifs);
            return;
        }
    }
}
