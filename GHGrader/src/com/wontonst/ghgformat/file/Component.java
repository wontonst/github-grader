/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghgformat.file;

import javax.swing.JPanel;

/**
 *
 * @author RoyZheng
 */
public abstract class Component {

    protected String title;

    public abstract void toString(Format f, StringBuilder b);

    public abstract Component deepClone();

    public abstract JPanel toPanel();

    public String getTitle() {
        return this.title;
    }
}
