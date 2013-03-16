/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.structure;

/**
 *
 * @author vicwrc
 */
public class NodeCreationException extends RuntimeException{

    public NodeCreationException() {
    }

    public NodeCreationException(String message) {
        super(message);
    }

    public NodeCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeCreationException(Throwable cause) {
        super(cause);
    }

    public NodeCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
