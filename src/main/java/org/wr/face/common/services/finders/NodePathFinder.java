/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.services.finders;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.operations.CommonNodeProvider;

/**
 *
 * @author vorontsov
 */
public class NodePathFinder {
    
    private CommonNodeProvider provider;

    public void setProvider(CommonNodeProvider provider) {
        this.provider = provider;
    }
    
    public List<Node> find(Node cur){
        List<Node> path = new LinkedList<>();
        path.add(cur);
        Node parent = provider.getParent(cur);
        while( null != parent ){
            path.add(parent);
            parent = provider.getParent(parent);
        }       
        Collections.reverse(path);
        return path;
    }
    
    
}
