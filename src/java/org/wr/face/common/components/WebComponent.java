/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author vorontsov
 */
public abstract class WebComponent {
    
    protected boolean singleRenderable = true;
    protected HttpServletRequest request;
    

    public boolean isSingleRenderable() {
        return singleRenderable;
    }

    public WebApplicationContext getContext(){
        return WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()) ;
    }

    public WebComponent setRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    public HttpServletRequest getRequest() {
        return request;
    }
    
    protected WebComponent setSingleRenderable(boolean singleRenderable) {
        this.singleRenderable = singleRenderable;
        return this;
    }
    
    public String render(){
        if(singleRenderable){
            return renderHtml();
        }
        throw new WebComponentRenderError();
    }
    
    public abstract String  renderHtml();
}
