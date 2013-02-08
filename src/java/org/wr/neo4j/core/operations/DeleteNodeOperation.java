package org.wr.neo4j.core.operations;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.wr.neo4j.meta.BaseReationTypes;

/**
 *
 * @author vicwrc
 */
public class DeleteNodeOperation {
    
    public void delete(Node node){
        for(Relationship rel: node.getRelationships(Direction.INCOMING, BaseReationTypes.PARENT)){
            delete(rel.getStartNode());
        }
        for(Relationship rel: node.getRelationships(Direction.INCOMING, BaseReationTypes.OBJECT_TYPE)){
            delete(rel.getStartNode());
        }
        for(Relationship rel: node.getRelationships()){
            rel.delete();
        }
        node.delete();
    }
    
    
}
