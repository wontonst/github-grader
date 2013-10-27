/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.patterns;

import java.util.List;

/**
 *
 * @author RoyZheng
 */
public interface Builder<T> {
    /**
     * Builds the object.
     * @return an instance of T
     * @throws Exception if check() does not pass
     */
    public abstract T build() throws Exception;
    /**
     * Checks if the object is ready to be built.
     * @return list of errors
     */
    public abstract List<String> check();
}
