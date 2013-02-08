/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.core;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.wr.neo4j.core.dbhandlers.dbinstance.DbStartStopHandler;

/**
 *
 * @author vorontsov
 */
public class Neo4jDBManager {

    private GraphDatabaseService dbService;
    private String dbName;
    private String properties;
    private List<Neo4jTransaction> transactions = new ArrayList<>();
    private DbStartStopHandler startStopHandler;

    /* system stuff */
    private boolean finishState = false;

    public Neo4jDBManager(String dbName, String properties, DbStartStopHandler startStopHandler) {
        this.dbName = dbName;
        this.properties = properties;
        this.startStopHandler = startStopHandler;
    }

    public Neo4jDBManager(String dbName, String properties) {
        this(dbName, properties, null);
    }

    public void open() {
        if (null == dbService) {
            dbService = openDB(dbName, properties);
            if (null != startStopHandler) {
                startStopHandler.onStart(this);
            }
        }
    }

    public GraphDatabaseService getDbService() {
        return dbService;
    }

    public void close() {
        if (null != dbService) {
            if (null != startStopHandler) {
                try {
                    startStopHandler.onStop(this);
                } catch (Throwable e) {
                    // close DB anyway
                    e.printStackTrace();
                }
            }
            finishState = true;
            finishAllTransactions();
            registerShutdownHook(dbService);
            dbService = null;
        }
    }

    private void finishAllTransactions() {
        for (Transaction transaction : transactions) {
            transaction.finish();
        }
    }

    private static void registerShutdownHook(final GraphDatabaseService graphDb) {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running example before it's completed)
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Neo4j Database was sucessfully shutted down.");
                graphDb.shutdown();
            }
        });
    }

    protected GraphDatabaseService openDB(String dbName, String properties) {
        return new GraphDatabaseFactory().
                newEmbeddedDatabaseBuilder(dbName).
                loadPropertiesFromFile(properties).newGraphDatabase();
    }

    public List<Neo4jTransaction> getTransactions() {
        return transactions;
    }

    public Neo4jTransaction createTransaction() {
        Transaction tr = dbService.beginTx();
        Neo4jTransaction transaction = new Neo4jTransaction(tr, this);
        transactions.add(transaction);
        return transaction;
    }

    public void finishTransaction(Neo4jTransaction transaction) {
        if (!transaction.isActive() && !finishState) {
            transactions.remove(transaction);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
