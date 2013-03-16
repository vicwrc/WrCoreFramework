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
public class GetByIdTransaction implements DoInNewTransacton<Node>{

    private long id;

    public GetByIdTransaction(long id) {
        this.id = id;
    }

    public Node perform(Neo4jTransaction tx) throws Exception {
        return tx.getDbManager().getDbService().getNodeById(id);
    }

}
