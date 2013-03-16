package org.wr.face.common.components.edit.input.multiple;

import org.wr.face.common.components.edit.input.SingleDateComponent;

/**
 *
 * @author vorontsov
 */
public class MultipleDateInput extends MultipleTextInput{

    public MultipleDateInput(String id) {
        super(id);
        this.setDataInputField( new SingleDateComponent(id+"_fake"));
    }

    public MultipleDateInput(Object[] listValues, String id) {
        super(listValues, id);
        this.setDataInputField( new SingleDateComponent(id+"_fake"));
    }
    
}
