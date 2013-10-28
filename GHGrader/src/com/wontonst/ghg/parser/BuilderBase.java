/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.parser;

import com.wontonst.patterns.Builder;

/**
 *
 * @author RoyZheng
 */
public abstract class BuilderBase<T> implements Builder<T> {

    String title;

    public String getTitle() {
        return this.title;
    }

    public void addTitle(String t) {
        this.title = t;
    }
}
