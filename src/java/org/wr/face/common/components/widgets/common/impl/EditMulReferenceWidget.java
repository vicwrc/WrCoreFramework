/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.widgets.common.impl;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.NestedComponent;
import org.wr.neo4j.meta.model.ObjectTypeBean;

/**
 *
 * @author vorontsov
 */
public class EditMulReferenceWidget extends NestedComponent{

    private String parentId;
    private final ObjectTypeBean objectType;
    private Node node;
            
    
    public EditMulReferenceWidget(ObjectTypeBean objectType, Node node){
        this.objectType = objectType;
        this.node = node;
    }
    
    public EditMulReferenceWidget(ObjectTypeBean objectType, String parentId){
        this.objectType = objectType;
        this.parentId = parentId;
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
