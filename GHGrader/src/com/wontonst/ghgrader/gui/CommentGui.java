/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.gui;

import com.wontonst.ghgformat.file.Comment;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author RoyZheng
 */
public class CommentGui extends JPanel {

    public CommentGui(Comment c) {
        this.add(new JLabel(c.getTitle()));
    }
}
