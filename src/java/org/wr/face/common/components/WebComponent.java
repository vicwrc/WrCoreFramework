/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components;

/**
 *
 * @author vorontsov
 */
public abstract class WebComponent {
    
    protected boolean singleRenderable = true;

    public boolean isSingleRenderable() {
        return singleRenderable;
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
