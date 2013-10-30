/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.gui;

import com.wontonst.ghgformat.file.GHGFile;
import com.wontonst.ghgrader.model.Project;
import com.wontonst.ghgrader.model.Student;
import com.wontonst.ghgrader.model.StudentInfo;
import com.wontonst.patterns.Builder;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
            this.username = new JTextField(username);
            this.repository = new JTextField(repo);
        }
    }
    public static int FRAME_DIM_X = 500;
    public static int FRAME_DIM_Y = 600;
    public static Dimension DIM = new Dimension(FRAME_DIM_X, FRAME_DIM_Y);
    GuiCore core;
    JScrollPane scroll;
    JPanel scroll_view = new JPanel();
    List<InputFields> input = new ArrayList<InputFields>();
    JButton done = new JButton("DONE");
    JButton add = new JButton("ADD ROW");
    String organization;
    JPanel panel;///<panel that holds everything inside frame
    GridBagConstraints gbc;
    GHGFile ghgfile;

    public ProjectBuilder(GuiCore core, GHGFile file) {
        this.ghgfile = file;
        this.organization = ghgfile.getVariables().get("organization");
        this.gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 30;
        gbc.weightx = 1;

        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.organization = organization;
        this.setSize(FRAME_DIM_X, FRAME_DIM_Y);
        this.setLocation(GuiCore.screenSize.width / 2 - FRAME_DIM_X / 2, GuiCore.screenSize.height / 2 - FRAME_DIM_Y / 2);
        this.core = core;
        this.setUndecorated(true);

        Dimension scroll_dim = new Dimension(FRAME_DIM_X, FRAME_DIM_Y * 3 / 4);
        // this.scroll_view.setSize(FRAME_DIM_X, 400);//FRAME_DIM_Y * 2 / 3);
        this.scroll_view.setLayout(new GridBagLayout());
        this.scroll = new JScrollPane(this.scroll_view);
        this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //this.scroll.setSize(FRAME_DIM_X, FRAME_DIM_Y * 2 / 3);
        this.scroll.setPreferredSize(scroll_dim);
        this.scroll.setMinimumSize(scroll_dim);

        this.done.addActionListener(this);
        this.add.addActionListener(this);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        this.panel.add(this.scroll, constraints);
        constraints.gridy = 1;
        this.panel.add(this.add, constraints);
        constraints.gridy = 2;
        this.panel.add(this.done, constraints);
        this.add(this.panel);
        this.setVisible(true);
    }

    public void finish() {
        this.setVisible(false);
    }

    @Override
    public Project build() throws Exception {
        List<Student> st = new ArrayList<Student>();
        for (InputFields f : this.input) {
            if (!f.username.getText().isEmpty() && !f.repository.getText().isEmpty()) {
                st.add(new Student(this.ghgfile, new StudentInfo(f.username.getText(), f.repository.getText())));
            }
        }
        Project p = new Project(this, st);
        return p;
    }

    public GHGFile getGHGFile() {
        return this.ghgfile;
    }

    @Override
    public List<String> check() {
        List<String> errors = new ArrayList<String>();
        if (this.input.isEmpty()) {
            errors.add("No usernames inputted.");
        }
        return errors;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.done) {

            this.core.projectBuildDone();
            return;
        }
        if (e.getSource() == this.add) {
            InputFields ifs = new InputFields(this.organization == null ? "" : this.organization, "");
            this.scroll_view.add(ifs.username, this.gbc);
            this.gbc.gridx = 1;
            this.scroll_view.add(ifs.repository, this.gbc);
            this.gbc.gridx = 0;
            this.gbc.gridy = this.gbc.gridy + 1;
            if (this.organization != null) {
                ifs.username.setEditable(false);
            }// else {
            //     ifs.username.setColumns(20);
            //  }
            //ifs.repository.setColumns(20);
            this.input.add(ifs);
            this.revalidate();
            return;
        }
    }
}
