/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.gui;

import com.wontonst.ghgformat.exceptions.IncompleteGHGFileException;
import com.wontonst.ghgformat.exceptions.MalformedGHGFileException;
import com.wontonst.ghgformat.file.GHGFile;
import com.wontonst.ghgformat.parser.GHGLoader;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
        if (this.menu.objIsNew(e.getSource())) {
            int returnv = this.file_chooser.showOpenDialog(this);
            if (returnv == JFileChooser.APPROVE_OPTION) {
                File f = this.file_chooser.getSelectedFile();
                try {
                    GHGFile file = GHGLoader.load(f);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "File could not be found!");
                } catch (MalformedGHGFileException ex) {
                    JOptionPane.showMessageDialog(this, "Malformed .ghg file: " + ex.getMessage());
                } catch (IncompleteGHGFileException ex) {
                    JOptionPane.showMessageDialog(this, "CRITICAL ERROR! " + ex.getMessage());
                }
            }
            return;
        }
        if (this.menu.objIsQuit(e.getSource())) {
            return;
        }
        if (this.menu.objIsSave(e.getSource())) {

            return;
        }
        if (this.menu.objIsOpen(e.getSource())) {
            int returnv = this.file_chooser.showOpenDialog(this);
            if (returnv == JFileChooser.APPROVE_OPTION) {
                File f = this.file_chooser.getSelectedFile();
                //parse ghd file
            }
            return;
        }
    }
}
