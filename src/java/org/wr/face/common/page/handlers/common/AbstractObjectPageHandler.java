/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page.handlers.common;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.wr.face.common.WebSpringAware;
import org.wr.neo4j.meta.page.PageHandler;

/**
 *
 * @author vorontsov
 */
public abstract class AbstractObjectPageHandler extends WebSpringAware implements PageHandler {

    protected boolean isInited = false;
    
    protected void init(HttpServletRequest httpRequest) {
        if (!isInited) {
            initModels(httpRequest);
            isInited = true;
        }
    }
    
    protected abstract void initModels(HttpServletRequest request);

    @Override
    public void process(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        init(httpRequest);
        processEvent(httpRequest, httpResponse);
    }

    protected abstract void processEvent(HttpServletRequest httpRequest, HttpServletResponse httpResponse) ;
}
