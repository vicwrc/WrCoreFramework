/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.edit.input.multiple;

import org.wr.face.common.components.edit.input.SingleNumberComponent;
import org.wr.utils.WrArrays;

/**
 *
 * @author vorontsov
 */
public class MultipleNumberInput extends MultipleTextInput{

    public MultipleNumberInput(String id) {
        super(id);
        this.setDataInputField( new SingleNumberComponent(id+"_fake"));
    }

    public MultipleNumberInput(Object[] listValues, String id) {
        super(listValues, id);
        this.setDataInputField( new SingleNumberComponent(id+"_fake"));
    }
    
    
}
