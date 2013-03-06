/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.widgets.common.impl.editcomp;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.edit.input.SingleNumberComponent;
import org.wr.face.common.widgets.common.impl.AttrIdCreator;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class SingleNumberAttrComponent  extends SingleNumberComponent{

    private final AttributeBean attr;
    private final Node node;

    public SingleNumberAttrComponent(AttributeBean attr, Node node) {
        super(AttrIdCreator.createAttrId(attr));
        this.attr = attr;
        this.node = node;
        if(null != node){
            this.setDefaultValue(String.valueOf(node.getProperty(attr.getName())));
        }
    }
    
}
