/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.structure.creators;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.structure.NodeCreationException;
import org.wr.neo4j.meta.structure.ObjectCreator;

/**
 *
 * @author vicwrc
 */
public class RootCreator implements ObjectCreator{

    public static final long EXPECTED_ID = 1L;
    
    
    @Override
    public Node createNode(Neo4jTransaction tx, Node parent) {
        Node node = tx.getDbManager().getDbService().createNode();
        if(EXPECTED_ID != node.getId()){
            throw new NodeCreationException("Root node must have id = 1!");
        }
        node.setProperty(MetaDataConstants.ALL_NAME, "ROOT");
        node.setProperty(MetaDataConstants.ALL_META_TYPE, MetaType.FOLDER.toString());
        return node;
    }
    
}
