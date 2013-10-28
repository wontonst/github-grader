/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg.exceptions;

/**
 *
 * @author RoyZheng
 */
public class IncompleteTopicException extends GHGException {

    String topic;

    public IncompleteTopicException(String topic, String msg) {
        super(msg);
        this.topic = topic;
    }

    public String getTopic() {
        return topic == null || topic.isEmpty() ? "[TOPIC TITLE NOT SET]" : topic;
    }
}
