/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components;

/**
 *
 * @author vicwrc
 */
public class WebComponentRenderError extends RuntimeException{

    public WebComponentRenderError() {
    }

    public WebComponentRenderError(String message) {
        super(message);
    }

    public WebComponentRenderError(String message, Throwable cause) {
        super(message, cause);
    }

    public WebComponentRenderError(Throwable cause) {
        super(cause);
    }

    public WebComponentRenderError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
