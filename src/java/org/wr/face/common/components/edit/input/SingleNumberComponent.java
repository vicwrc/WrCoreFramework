/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.edit.input;

/**
 *
 * @author vorontsov
 */
public class SingleNumberComponent extends SingleTextComponent{

    public SingleNumberComponent(String id) {
        super(id);
    }
    
    @Override
    protected String getExtraAttributes() {
        return " onkeypress='validateNumber(event)' ";
    }
}
