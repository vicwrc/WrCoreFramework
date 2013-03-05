package org.wr.face.common.components.widgets.common;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.edit.Form;
import org.wr.face.common.components.widgets.common.impl.EditMulReferenceWidget;
import org.wr.face.common.components.widgets.common.impl.EditObjectParametersWidget;
import org.wr.neo4j.meta.model.ObjectTypeBean;

/**
 *
 * @author vorontsov
 */
public class EditObjectWidget extends Form{
    
    private String parentId;
    private final ObjectTypeBean objectType;
    private Node node;
            
    
    public EditObjectWidget(ObjectTypeBean objectType, Node node){
        this.objectType = objectType;
        this.node = node;
        addChild(new EditObjectParametersWidget(objectType, node));
        addChild(new EditMulReferenceWidget(objectType, node));
    }
    
    public EditObjectWidget(ObjectTypeBean objectType, String parentId){
        this.objectType = objectType;
        this.parentId = parentId;
        addChild(new EditObjectParametersWidget(objectType, parentId));
        addChild(new EditMulReferenceWidget(objectType, parentId));
    }
    
    
    
}
