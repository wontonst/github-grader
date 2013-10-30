/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.gui;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author RoyZheng
 */
public class Menu extends JMenuBar {

    protected JMenu file;
    protected JMenuItem open, save, save_as, newp, quit;
    protected JMenu perform;
    protected JMenuItem login, upload, editp;
    protected JMenu statistics;
    protected JMenuItem general, deductions;
    protected ActionListener listener;

    public Menu(ActionListener listener) {
        super();
        this.listener = listener;

        this.file = new JMenu("File");
        this.file.setMnemonic(KeyEvent.VK_F);

        this.newp = new JMenuItem("New", KeyEvent.VK_N);
        this.open = new JMenuItem("Open", KeyEvent.VK_O);
        this.save = new JMenuItem("Save", KeyEvent.VK_S);
        this.save_as = new JMenuItem("Save as", KeyEvent.VK_A);
        this.quit = new JMenuItem("Quit", KeyEvent.VK_Q);

        this.newp.setToolTipText("Create a new project from an existing ghg file");
        this.open.setToolTipText("Open a previous ghg grading project");

        this.newp.addActionListener(this.listener);
        this.open.addActionListener(this.listener);
        this.save.addActionListener(this.listener);
        this.save_as.addActionListener(this.listener);
        this.quit.addActionListener(this.listener);

        this.file.add(this.newp);
        this.file.add(this.open);
        this.file.add(this.save);
        this.file.add(this.save_as);
        this.file.add(this.quit);

        this.add(this.file);

        this.perform = new JMenu("Github");
        this.perform.setMnemonic(KeyEvent.VK_G);

        this.login = new JMenuItem("Login", KeyEvent.VK_L);
        this.upload = new JMenuItem("Upload", KeyEvent.VK_U);
        this.editp = new JMenuItem("Edit project", KeyEvent.VK_E);

        this.login.addActionListener(this.listener);
        this.upload.addActionListener(this.listener);
        this.editp.addActionListener(this.listener);

        this.perform.add(this.login);
        this.perform.add(this.upload);
        this.perform.add(this.editp);

        this.add(this.perform);

        this.statistics = new JMenu("Statistics");
        this.statistics.setMnemonic(KeyEvent.VK_S);

        this.general = new JMenuItem("General", KeyEvent.VK_G);
        this.deductions = new JMenuItem("Deductions", KeyEvent.VK_D);

        this.general.addActionListener(this.listener);
        this.deductions.addActionListener(this.listener);

        this.statistics.add(this.general);
        this.statistics.add(this.deductions);

        this.add(this.statistics);
    }

    public boolean objIsNew(Object o) {
        return o == this.newp;
    }

    public boolean objIsOpen(Object o) {
        return o == this.open;
    }

    public boolean objIsSave(Object o) {
        return o == this.save;
    }

    public boolean objIsQuit(Object o) {
        return o == this.quit;
    }

    public boolean objIsSaveAs(Object o) {
        return o == this.save_as;
    }

    public boolean objIsEditProject(Object o) {
        return o == this.upload;
    }

    public boolean objIsUpload(Object o) {
        return o == this.upload;
    }

    public boolean objIsLogin(Object o) {
        return o == this.login;
    }

    public boolean objIsStatsGeneral(Object o) {
        return o == this.general;
    }

    public boolean objIsStatsDeduction(Object o) {
        return o == this.deductions;
    }
}
