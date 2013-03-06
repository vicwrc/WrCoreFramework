/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.widgets.common;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.NestedComponent;
import org.wr.neo4j.meta.model.ObjectTypeBean;

/**
 *
 * @author vorontsov
 */
public class ViewObjectWidget extends NestedComponent{

    private final ObjectTypeBean objectType;
    private final Node node;

    public ViewObjectWidget(ObjectTypeBean objectType, Node node) {
        this.objectType = objectType;
        this.node = node;
    }
    
    @Override
    protected String renderStartBlock() {
        return "";
    }

    @Override
    protected String renderEndBlock() {
        return "";
    }
    
}
