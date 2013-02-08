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
import org.wr.neo4j.meta.structure.ObjectCreator;

/**
 *
 * @author vicwrc
 */
public abstract class NamedObjectCreator implements ObjectCreator{

    @Override
    public Node createNode(Neo4jTransaction tx, Node parent) {
        Node node = tx.getDbManager().getDbService().createNode();
        node.setProperty(MetaDataConstants.ALL_NAME, getName());
        if(null != parent){
           node.createRelationshipTo(parent, BaseReationTypes.PARENT) ;       
        }
        node.setProperty(MetaDataConstants.ALL_META_TYPE, MetaType.FOLDER.toString());
        return node;
    }
    
    
    protected abstract String getName();
    
}
