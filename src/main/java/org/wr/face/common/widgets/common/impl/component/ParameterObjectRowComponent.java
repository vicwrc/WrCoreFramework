package org.wr.face.common.widgets.common.impl.component;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.view.NestedTableRow;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class ParameterObjectRowComponent extends NestedTableRow{
    
    private final AttributeBean attr;
    private final Node node;
    private ParameterComponentFactory componentFactory;

    public ParameterObjectRowComponent(AttributeBean attr, Node node) {
        super(attr.getName());
        this.attr = attr;
        this.node = node;        
    }

    public void setComponentFactory(ParameterComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
    }

    @Override
    public String renderHtml() {
        this.addChild(componentFactory.createComponent(attr, node));
        return super.renderHtml();
    }
    
    
}
