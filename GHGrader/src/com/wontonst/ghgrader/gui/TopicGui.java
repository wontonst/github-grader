/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgrader.gui;

import com.wontonst.ghgformat.file.Topic;
import java.awt.GridBagConstraints;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author RoyZheng
 */
public class TopicGui extends JPanel {

    public TopicGui(Topic topic, List<RequirementGui> reqs) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new JLabel(topic.getTitle()), gbc);
        gbc.gridy = 1;
        gbc.gridx = 1;
        for (RequirementGui r : reqs) {
            this.add(r);
            gbc.gridy = gbc.gridy + 1;
        }
    }
}
