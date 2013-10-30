/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.gui;

import com.wontonst.ghg.file.GHGFile;
import com.wontonst.ghg.parser.GHGLoader;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author RoyZheng
 */
public class GUICore extends JFrame implements ActionListener {

    public static int FRAME_DIM_X = 400;
    public static int FRAME_DIM_Y = 300;
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected JFileChooser file_chooser = new JFileChooser();
    protected Menu menu;
    
    protected GHGFile ghg;

    public GUICore() {
        this.menu = new Menu(this);
        this.setJMenuBar(this.menu);

        this.setSize(FRAME_DIM_X, FRAME_DIM_Y);
        this.setLocation(this.screenSize.width / 2 - FRAME_DIM_X / 2, this.screenSize.height / 2 - FRAME_DIM_Y / 2);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action performed");
        if (this.menu.objIsQuit(e.getSource())) {
            int returnv = this.file_chooser.showOpenDialog(this);
            if (returnv == JFileChooser.APPROVE_OPTION) {
                File f = this.file_chooser.getSelectedFile();
                GHGFile file = GHGLoader.load(f);
            }
            return;
        }
        if (this.menu.objIsSave(e.getSource())) {

            return;
        }
        if (this.menu.objIsOpen(e.getSource())) {
            int returnv = this.file_chooser.showOpenDialog(this);
            if (returnv == JFileChooser.APPROVE_OPTION) {
                File f = this.file_chooser.getSelectedFile();
            }
            return;
        }
    }
}
