package org.wr.neo4j.core.operations;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.wr.neo4j.meta.BaseReationTypes;

/**
 *
 * @author vicwrc
 */
public class CommonNodeProvider {
    
    public Node getParent(Node cur){
        Relationship rel = cur.getSingleRelationship(BaseReationTypes.PARENT, Direction.OUTGOING);
        if(null == rel){
            return null;
        }
        return rel.getEndNode();
    }
    
    public List<Node> getChildren(Node node){
        List<Node> children = new LinkedList<>();
        for(Relationship rel : node.getRelationships(Direction.INCOMING,BaseReationTypes.PARENT)){
            children.add(rel.getStartNode());
        }
        Collections.sort(children, new Comparator<Node>(){

            @Override
            public int compare(Node o1, Node o2) {
                return (int)(o1.getId() - o2.getId());
            }

           
        });
        return children;
    }
    
    public Node getObjectType(Node node){
        Relationship ref2ot = node.getSingleRelationship(BaseReationTypes.OBJECT_TYPE, Direction.OUTGOING);
        return ref2ot != null ? ref2ot.getEndNode(): null;
    }
    
}
