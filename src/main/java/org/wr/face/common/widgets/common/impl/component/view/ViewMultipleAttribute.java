package org.wr.face.common.widgets.common.impl.component.view;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.view.MultipleLine;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.utils.WrArrays;

/**
 *
 * @author vorontsov
 */
public class ViewMultipleAttribute extends MultipleLine{

    private final AttributeBean attr;
    private final Node node;
    
    public ViewMultipleAttribute(AttributeBean attr, Node node) {
        super(getValues(node.getProperty(attr.getName())));
        this.attr = attr;
        this.node = node;        
    }

    public static String[] getValues(Object o){
        if(o instanceof String[]) {
            return (String[])o;
        }
        if(o instanceof int[]){
            return WrArrays.toStringArray((int[])o);
        }
        return null;
    }
   
}
