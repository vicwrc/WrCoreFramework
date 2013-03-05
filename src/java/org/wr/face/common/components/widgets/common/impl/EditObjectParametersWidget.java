
package org.wr.face.common.components.widgets.common.impl;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.edit.HiddenField;
import org.wr.face.common.components.view.SimpleTableComponent;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.neo4j.meta.model.ObjectTypeBean;
import org.wr.neo4j.meta.model.attribute.ReferenceAttribute;

/**
 *
 * @author vorontsov
 */
public class EditObjectParametersWidget extends SimpleTableComponent {

    private String parentId;
    private final ObjectTypeBean objectType;
    private Node node;

    public EditObjectParametersWidget(ObjectTypeBean objectType, Node node) {
        this.objectType = objectType;
        this.node = node;
        addChild(new HiddenField("id",String.valueOf(node.getId())));
        initChildren();
    }

    public EditObjectParametersWidget(ObjectTypeBean objectType, String parentId) {
        this.objectType = objectType;
        this.parentId = parentId;
        addChild(new HiddenField("parentId", parentId));
        addChild(new HiddenField("objectType", String.valueOf(objectType.getId())));
        initChildren();
    }

    private void initChildren() {
        addChild(new HiddenField("action", "submit"));
        for (AttributeBean attr : objectType.getAllAttributes()) {
            if (isParameter(attr)) {
                addChild(new ParameterObjectRowComponent(attr, node));
            }
        }
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
