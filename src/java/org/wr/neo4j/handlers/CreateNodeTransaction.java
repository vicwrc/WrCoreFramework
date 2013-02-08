/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.neo4j.handlers;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.DoInNewTransacton;
import org.wr.neo4j.core.Neo4jTransaction;

/**
 *
 * @author vorontsov
 */
public class CreateNodeTransaction implements DoInNewTransacton<Node>{

    public Node perform(Neo4jTransaction tx) throws Exception {
        return tx.getDbManager().getDbService().createNode();
    }

}
