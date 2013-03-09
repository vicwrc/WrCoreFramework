/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.widgets.common.impl.component.edit;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.edit.input.multiple.MultipleNumberInput;
import org.wr.face.common.widgets.common.impl.component.AttrIdCreator;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class MultipleNumberAttrComponent extends MultipleNumberInput{

    private final AttributeBean attr;
    private final Node node;

    public MultipleNumberAttrComponent(AttributeBean attr, Node node) {
        super(AttrIdCreator.createAttrId(attr));
        this.attr = attr;
        this.node = node;
        if(null != node){
            this.setListValues(toStringArray((int[])node.getProperty(attr.getName())));
        }
    }
    
    public static String[] toStringArray(int[] value) {
        String[] out = new String[value.length];
        for(int i=0; i< value.length; i++) {
            out[i] = String.valueOf(value[i]);
        }
        return out;
    }
    
}
