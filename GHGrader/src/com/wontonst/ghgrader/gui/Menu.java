/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.gui;

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
    protected JMenuItem open, save, quit;

    public Menu() {
        super();
        this.file = new JMenu("File");
        this.file.setMnemonic(KeyEvent.VK_F);

        this.open = new JMenuItem("Open", KeyEvent.VK_O);
        this.quit = new JMenuItem("Quit", KeyEvent.VK_Q);
        this.save = new JMenuItem("Save", KeyEvent.VK_S);

        this.file.add(this.open);
        this.file.add(this.save);
        this.file.add(this.quit);

        this.add(this.file);

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
}
