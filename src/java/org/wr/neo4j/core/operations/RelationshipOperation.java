/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.core.operations;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.wr.neo4j.meta.BaseReationTypes;

/**
 *
 * @author Vorontsov
 */
public class RelationshipOperation {

    public void setParent(Node node, Node parent) {
        Relationship rel = node.getSingleRelationship(BaseReationTypes.PARENT, Direction.OUTGOING);
        if (null == rel || null == rel.getEndNode()) {
            node.createRelationshipTo(parent, BaseReationTypes.PARENT);
        }else if (!parent.equals(rel.getEndNode())) {
            rel.delete();
            node.createRelationshipTo(parent, BaseReationTypes.PARENT);
        }
    }
    
    public void setReferenceOTtoAttribute(Node ot, Node attribute){
        for(Relationship rel : ot.getRelationships(BaseReationTypes.ATTRIBUTES, Direction.OUTGOING)){
            if(rel.getEndNode().getId() == attribute.getId()){
                return;
            }            
        }
        ot.createRelationshipTo(attribute, BaseReationTypes.ATTRIBUTES);
    }
}
