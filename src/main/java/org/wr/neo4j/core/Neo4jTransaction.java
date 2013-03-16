/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.neo4j.core;

import org.neo4j.graphdb.Lock;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.graphdb.Transaction;

/**
 *
 * @author vorontsov
 */
public class Neo4jTransaction implements Transaction,  AutoCloseable {

    private boolean active = true;
    private Transaction transaction;
    private Neo4jDBManager dbManager;

    public Neo4jTransaction(Transaction transaction, Neo4jDBManager dbManager) {
        this.transaction = transaction;
        this.dbManager = dbManager;
    }

    public boolean isActive() {
        return active;
    }

    public Neo4jDBManager getDbManager() {
        return dbManager;
    }

    protected void finishCurrentTransaction(){
        active = false;
        dbManager.finishTransaction(this);
    }

    @Override
    public void failure() {
        transaction.failure();
    }

    @Override
    public void success() {
        transaction.success();
    }

    @Override
    public void finish() {
        transaction.finish();
        finishCurrentTransaction();
    }

    @Override
    public Lock acquireWriteLock(PropertyContainer pc) {
        return transaction.acquireWriteLock(pc);
    }

    @Override
    public Lock acquireReadLock(PropertyContainer pc) {
        return transaction.acquireReadLock(pc);
    }

    @Override
    public void close() {
        finish();
    }

}
