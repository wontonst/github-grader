/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.gui;

import com.wontonst.ghgformat.file.Requirement;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author RoyZheng
 */
public class RequirementGui extends JPanel implements ActionListener {

    protected JButton add_all;
    protected JButton add_one;
    protected JButton remove_one;
    protected JButton remove_all;
    protected JLabel field;

    public RequirementGui(Requirement r) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.setLayout(new GridBagLayout());

        this.add(new JLabel(r.getTitle() + "(" + r.getValue() + (r.getValue() <= 1 ? " point)" : " points)")), gbc);


        gbc.gridy = 1;
        this.add_all = new JButton("++");
        this.add_all.addActionListener(this);
        this.add(this.add_all, gbc);

        gbc.gridy = 2;
        this.add_one = new JButton("+");
        this.add_one.addActionListener(this);
        this.add(this.add_one, gbc);

        gbc.gridy = 3;
        this.field = new JLabel("x");
        this.add(this.field, gbc);

        gbc.gridy = 4;
        this.remove_one = new JButton("-");
        this.remove_one.addActionListener(this);
        this.add(this.remove_one, gbc);

        gbc.gridy = 5;
        this.remove_all = new JButton("--");
        this.remove_all.addActionListener(this);
        this.add(this.remove_all, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
