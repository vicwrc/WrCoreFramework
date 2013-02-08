/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.structure.creators;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.BaseReationTypes;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.structure.NodeCreationException;

/**
 *
 * @author vicwrc
 */
public class BaseObjectTypeCreator extends NamedObjectCreator{
   
    private final Node nameAttr;

    public BaseObjectTypeCreator(Node nameAttr) {
        this.nameAttr = nameAttr;
    }
    
    

    @Override
    public Node createNode(Neo4jTransaction tx, Node parent) {
        Node node = super.createNode(tx, parent);
        
        node.setProperty(MetaDataConstants.ALL_ORDER, (long)1);
        node.setProperty(MetaDataConstants.ALL_META_TYPE, MetaType.OBJECT_TYPE.toString());
        if(null == nameAttr){
            throw new NodeCreationException("Name Attribute mst be specified!");
        }
        node.createRelationshipTo(nameAttr, BaseReationTypes.ATTRIBUTES);
        
        return node;
    }
    
    @Override
    protected String getName() {
        return "Base Object Type";
    }
    
}
