/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgformat.file;

import com.wontonst.ghgformat.exceptions.IncompleteGHGFileException;
import com.wontonst.ghgformat.parser.FileBuilder;
import com.wontonst.ghgrader.gui.TopicGui;
import com.wontonst.util.BuildString;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @brief object representation of a .ghg file
 * @author RoyZheng
 */
public class GHGFile {

    Variables variables;
    List<Topic> topics;

    private GHGFile() {
    }

    public GHGFile(FileBuilder builder) throws IncompleteGHGFileException {
        StringBuilder sb = new StringBuilder();
        List<String> checked = builder.check();
        if (!checked.isEmpty()) {
            throw new IncompleteGHGFileException(checked, "Errors found: [" + BuildString.Build(checked, " ") + "]");
        }

        this.variables = builder.getVariables();
        this.topics = builder.getTopics();
    }

    public Variables getVariables() {
        return this.variables;
    }

    public String toString(Format f) {
        StringBuilder b = new StringBuilder();
        String header = "";
        String body;
        switch (f) {
            case GHG:
            case JEKYLL_MD:
            case JEKYLL_HTML:
                this.variables.toString(f, b);
                header = b.toString();
                b = new StringBuilder();
                break;
        }
        for (Topic t : this.topics) {
            b.append("\n");
            t.toString(f, b);
        }
        body = b.toString();
        switch (f) {
            case HTML:
            case JEKYLL_HTML:
                body = body;//TODO: CONVERT TO HTML HERE!!!!
        }

        switch (f) {
            case GHG:
            case JEKYLL_MD:
            case JEKYLL_HTML:
                return header + body;
            default:
                return body;
        }
    }

    public List<TopicGui> getTopicGuis() {
        List<TopicGui> t = new ArrayList<TopicGui>();
        for (Topic top : this.topics) {
            t.add(top.toPanel());
        }
        return t;
    }

    public GHGFile deepClone() {
        GHGFile f = new GHGFile();
        List<Topic> top = new ArrayList<Topic>();
        for (Topic t : this.topics) {
            top.add(t.deepClone());
        }
        f.topics = top;
        return f;
    }
}
