/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.widgets.common;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.NestedComponent;
import org.wr.face.common.widgets.common.impl.ObjectParametersWidget;
import org.wr.face.common.widgets.common.impl.ViewChildrenWidget;
import org.wr.face.common.widgets.common.impl.ViewMulReferenceWidget;
import org.wr.face.common.widgets.common.impl.component.componentfactory.ViewParameterComponentFactory;
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
        addChild(new ObjectParametersWidget(objectType, node, new ViewParameterComponentFactory()));
        addChild(new ViewMulReferenceWidget(objectType, node));
        addChild(new ViewChildrenWidget(objectType, node));        
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
