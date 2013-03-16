
package org.wr.face.common.components.edit.input;

/**
 *
 * @author vorontsov
 */
public class SingleDateComponent extends SingleTextComponent{

    public SingleDateComponent(String id) {
        super(id);
    }
    
    @Override
    protected String getExtraAttributes() {
        return " onfocus=\"showCalendarControl(this);\" ";
    }
    
}
