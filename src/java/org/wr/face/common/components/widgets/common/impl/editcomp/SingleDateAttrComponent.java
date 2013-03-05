package org.wr.face.common.components.widgets.common.impl.editcomp;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.edit.SingleDateComponent;
import org.wr.face.common.components.widgets.common.impl.AttrIdCreator;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class SingleDateAttrComponent extends SingleDateComponent{

    private final AttributeBean attr;
    private final Node node;

    public SingleDateAttrComponent(AttributeBean attr, Node node) {
        super(AttrIdCreator.createAttrId(attr));
        this.attr = attr;
        this.node = node;
        if(null != node){
            this.setDefaultValue(String.valueOf(node.getProperty(attr.getName())));
        }
    }
    
    
}
