
package org.wr.face.common.widgets.common.impl;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.WebComponent;
import org.wr.face.common.components.edit.HiddenField;
import org.wr.face.common.components.view.SimpleTableComponent;
import org.wr.face.common.widgets.common.impl.component.ParameterComponentFactory;
import org.wr.face.common.widgets.common.impl.component.ParameterObjectRowComponent;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.neo4j.meta.model.ObjectTypeBean;
import org.wr.neo4j.meta.model.attribute.ReferenceAttribute;

/**
 *
 * @author vorontsov
 */
public class ObjectParametersWidget extends SimpleTableComponent {

    protected String parentId;
    protected final ObjectTypeBean objectType;
    protected Node node;
    protected final ParameterComponentFactory parameterComponentFactory; 

    
    
    public ObjectParametersWidget(ObjectTypeBean objectType, Node node ,ParameterComponentFactory parameterComponentFactory) {
        this.parameterComponentFactory = parameterComponentFactory;
        this.objectType = objectType;
        this.node = node;
        addChild(new HiddenField("id",String.valueOf(node.getId())));
        initChildren();
    }
    
    public ObjectParametersWidget(ObjectTypeBean objectType, String parentId, ParameterComponentFactory parameterComponentFactory) {
        this.objectType = objectType;
        this.parentId = parentId;
        this.parameterComponentFactory = parameterComponentFactory;
        addChild(new HiddenField("parentId", parentId));
        addChild(new HiddenField("objectType", String.valueOf(objectType.getId())));
        initChildren();
    }
    
    private void initChildren() {
        addChild(new HiddenField("action", "submit"));
        for (AttributeBean attr : objectType.getAllAttributes()) {
            if (isParameter(attr)) {
                addChild(createComponentByAttrId(attr));
            }
        }
    }

    protected WebComponent createComponentByAttrId(AttributeBean attr) {
        ParameterObjectRowComponent component = new ParameterObjectRowComponent(attr, node);
        component.setComponentFactory(parameterComponentFactory);
        return component;
    }
    
    protected boolean isParameter(AttributeBean attr) {
        if (attr instanceof ReferenceAttribute) {
            return attr.getMaxEntries() < 2;
        }
        return true;
    }

    public String getParentId() {
        return parentId;
    }

    public ObjectTypeBean getObjectType() {
        return objectType;
    }

    public Node getNode() {
        return node;
    }
    
    
}
