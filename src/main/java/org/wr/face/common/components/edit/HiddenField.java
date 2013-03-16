/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.edit;

import org.wr.face.common.components.WebComponent;

/**
 *
 * @author Vorontsov
 */
public class HiddenField extends WebComponent{

    private final String name;
    private final String value;

    public HiddenField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
    
    @Override
    public String renderHtml() {
        return "<input type=\"hidden\" name=\""+name+"\" value=\""+value+"\">";
    }
    
}
