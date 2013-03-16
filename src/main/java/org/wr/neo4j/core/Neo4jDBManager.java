package org.wr.neo4j.core;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
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

    public Neo4jDBManager(GraphDatabaseService dbService, DbStartStopHandler startStopHandler) {
        this.dbService = dbService;
        this.startStopHandler = startStopHandler;
    }

    public Neo4jDBManager(GraphDatabaseService dbService) {
        this(dbService, null);
    }

    public void open() {
        if (null != startStopHandler) {
            startStopHandler.onStart(this);
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
            dbService = null;
        }
    }

    private void finishAllTransactions() {
        for (Transaction transaction : transactions) {
            transaction.finish();
        }
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
