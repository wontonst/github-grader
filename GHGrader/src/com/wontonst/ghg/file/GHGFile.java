/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.file;

import com.wontonst.ghg.exceptions.IncompleteGHGFileException;
import com.wontonst.ghg.parser.FileBuilder;
import com.wontonst.util.BuildString;
import java.util.List;
import java.util.Map;

/**
 * @brief object representation of a .ghg file
 * @author RoyZheng
 */
public class GHGFile {

    Variables variables;
    List<Topic> topics;

    public GHGFile(FileBuilder builder) throws IncompleteGHGFileException {
        StringBuilder sb = new StringBuilder();
        List<String> checked = builder.check();
        if (!checked.isEmpty()) {
            throw new IncompleteGHGFileException(checked, "Errors found: [" + BuildString.Build(checked, " ") + "]");
        }

        this.variables = builder.getVariables();
        this.topics = builder.getTopics();
    }
}
