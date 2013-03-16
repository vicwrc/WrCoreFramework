package org.wr.neo4j.meta.structure;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.Neo4jTransaction;

/**
 *
 * @author vicwrc
 */
public interface ObjectCreator {
    
    
    Node createNode(Neo4jTransaction tx, Node parent);
    
}
