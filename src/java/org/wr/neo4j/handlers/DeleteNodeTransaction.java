/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.neo4j.handlers;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.DoInNewTransacton;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.core.operations.DeleteNodeOperation;

/**
 *
 * @author vorontsov
 */
public class DeleteNodeTransaction implements DoInNewTransacton<Node>{

    private DeleteNodeOperation deleteNodeOperation = new DeleteNodeOperation();
    private long id;

    public DeleteNodeTransaction(long id) {
        this.id = id;
    }

    public Node perform(Neo4jTransaction tx) throws Exception {
        Node node2delete = tx.getDbManager().getDbService().getNodeById(id);
        deleteNodeOperation.delete(node2delete);
        return node2delete;
    }


}
