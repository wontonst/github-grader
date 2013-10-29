/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author RoyZheng
 */
public class GUICore extends JFrame implements ActionListener {
    
    public static int FRAME_DIM_X = 400;
    public static int FRAME_DIM_Y = 300;
    
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    protected Menu menu;
    
    public GUICore() {
        this.menu = new Menu();
        this.setJMenuBar(this.menu);
        
        this.setSize(FRAME_DIM_X, FRAME_DIM_Y);
        this.setLocation(this.screenSize.width/2-FRAME_DIM_X/2, this.screenSize.height/2-FRAME_DIM_Y/2);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.menu.objIsQuit(e.getSource())){
            
            return;
        }
        if(this.menu.objIsSave(e.getSource())){
            
            return;
        }
        if(this.menu.objIsOpen(e.getSource())){
            
            return;
        }
    }
}
