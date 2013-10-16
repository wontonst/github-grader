/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.patterns;

import java.util.Map;

/**
 *
 * @author RoyZheng
 */
    public class Singleton {

        private static Map<Class<? extends Singleton>,Singleton> INSTANCES_MAP = new java.util.HashMap<Class<? extends Singleton>, Singleton>();

        public synchronized static <E extends Singleton> E getInstance(Class<E> instanceClass) throws InstantiationException, IllegalAccessException {
            if(INSTANCES_MAP.containsKey(instanceClass)) {
                return (E) INSTANCES_MAP.get(instanceClass);
            } else {
                E instance = instanceClass.newInstance();
                INSTANCES_MAP.put(instanceClass, instance);
                return instance;
            }
        }
    }
