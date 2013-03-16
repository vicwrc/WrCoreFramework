/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.widgets.common.impl.component.view;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.WebComponent;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class ViewSingleAttribute extends WebComponent{

    private final AttributeBean attr;
    private final Node node;
    
    public ViewSingleAttribute(AttributeBean attr, Node node) {
        this.attr = attr;
        this.node = node;        
    }

    @Override
    public String renderHtml() {
        return String.valueOf(node.getProperty(attr.getName()));
    }
    
    
    
}
