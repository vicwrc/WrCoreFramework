/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.neo4j.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.DoInNewTransacton;
import org.wr.neo4j.core.Neo4jTransaction;

/**
 *
 * @author vorontsov
 */
public class UpdateNodePropertiesTransaction  implements DoInNewTransacton<Node>{

    private Node node;
    private Map<String, Object> params;

    public UpdateNodePropertiesTransaction(Node node, Map<String, Object> params) {
        this.node = node;
        this.params = params;
    }
    
    public Node perform(Neo4jTransaction tx) throws Exception {
        if(null == node){
            node = tx.getDbManager().getDbService().createNode();
        }
        if(null == params){
            params = new HashMap<String, Object>();
        }

        applyParameters(node, params);

        return node;
    }

    protected void applyParameters(Node node, Map<String, Object> params){
        // add new
        for(Entry<String, Object> param : params.entrySet()){
            node.setProperty(param.getKey(), param.getValue());
        }
        for(String key : node.getPropertyKeys()){
            if(null == params.get(key)){
                node.removeProperty(key);
            }
        }
    }

}
