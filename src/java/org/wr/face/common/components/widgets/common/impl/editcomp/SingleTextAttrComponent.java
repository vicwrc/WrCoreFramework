package org.wr.face.common.components.widgets.common.impl.editcomp;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.edit.SingleTextComponent;
import org.wr.face.common.components.widgets.common.impl.AttrIdCreator;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class SingleTextAttrComponent extends SingleTextComponent{

    private final AttributeBean attr;
    private final Node node;

    public SingleTextAttrComponent(AttributeBean attr, Node node) {
        super(AttrIdCreator.createAttrId(attr));
        this.attr = attr;
        this.node = node;
        if(null != node){
            this.setDefaultValue((String)node.getProperty(attr.getName()));
        }
    }
    
    
}
