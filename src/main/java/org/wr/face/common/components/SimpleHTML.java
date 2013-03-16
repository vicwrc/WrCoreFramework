/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components;

/**
 *
 * @author vorontsov
 */
public class SimpleHTML extends WebComponent{

    private String html;

    public SimpleHTML(String html) {
        this.html = html;
    }
    
    @Override
    public String renderHtml() {
        return html;
    }
    
}
