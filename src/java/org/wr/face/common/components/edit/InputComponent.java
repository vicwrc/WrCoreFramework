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
public abstract class InputComponent extends WebComponent{
    
    private final String id;
    private boolean disabled = false;
    private boolean hidden = false;

    public InputComponent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public InputComponent setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }
    
    protected String getDisabled(){
        return disabled? " disabled=\"\"":"";
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    
    public String getHidden(){
        return hidden ? " style=\"display: none;\" " : "";
    }
    
}
