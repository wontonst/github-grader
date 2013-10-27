/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

import com.wontonst.ghg.file.Requirement;
import com.wontonst.ghg.file.Topic;
import com.wontonst.patterns.Builder;
import java.util.List;

/**
 *
 * @author RoyZheng
 */
public class TopicBuilder implements Builder<Topic> {

    String title;
    List<Requirement> requirements = new ArrayList<Requirement>();

    @Override
    public Topic build() {
        return new Topic(this);
    }

    @Override
    public List<String> check() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
