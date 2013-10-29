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

    public String toString(Format f) {
        StringBuilder b = new StringBuilder();

        switch (f) {
            case MD:
            case HTML:
            case JEKYLL_MD:
            case JEKYLL_HTML:
        }
        this.variables.toString(b);

        for (Topic t : this.topics) {
            b.append("\n");
            t.toString(b);
        }
        return b.toString();
    }

    public String toMd() {
        StringBuilder b = new StringBuilder();
        for (Topic t : this.topics) {
            b.append(t.toMd());
            b.append("\n");
        }
        return b.toString();
    }

    public String toJson() {
    }

    public String toHtml() {
    }

    public String toJekyllMd() {
    }

    public String toJekyllHtml() {
    }
}
