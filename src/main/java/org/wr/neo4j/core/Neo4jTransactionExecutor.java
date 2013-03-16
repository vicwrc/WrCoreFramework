/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.core;

/**
 *
 * @author vorontsov
 */
public class Neo4jTransactionExecutor {

    private Neo4jDBManager dbManager;

    public Neo4jTransactionExecutor(Neo4jDBManager dbManager) {
        this.dbManager = dbManager;
    }

    public <T> T perform(DoInNewTransacton<T> transactionAction) throws Exception {
        Neo4jTransaction tx = null;
        try {
            tx = dbManager.createTransaction();
            T result = transactionAction.perform(tx);
            tx.success();
            return result;
        } finally {
            if (null != tx) {
                tx.finish();
            }
        }
    }
}
