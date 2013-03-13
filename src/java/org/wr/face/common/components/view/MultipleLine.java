/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view;

import org.wr.face.common.components.WebComponent;

/**
 *
 * @author Vorontsov
 */
public class MultipleLine extends WebComponent{

    private final String[] lines;

    public MultipleLine(String[] lines) {
        this.lines = lines;
    }
    
    @Override
    public String renderHtml() {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for(String value : lines){
            if(isFirst) {
                isFirst = false;
            }else{
                sb.append("<br>");
            }
            sb.append(value);
        }
        
        return sb.toString();
    }
    
    
    
}
