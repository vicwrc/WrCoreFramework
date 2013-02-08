
package org.wr.neo4j.core.dbhandlers.dbinstance.impl;

import org.wr.neo4j.core.Neo4jDBManager;
import org.wr.neo4j.core.Neo4jTransactionExecutor;
import org.wr.neo4j.core.dbhandlers.dbinstance.DbStartStopHandler;
import org.wr.neo4j.meta.structure.StructureCreatorDirector;

/**
 *
 * @author vicwrc
 */
public class DefaultDbStartStopHandler implements DbStartStopHandler{

    public void onStart(Neo4jDBManager manager) {
        try {
            new Neo4jTransactionExecutor(manager).perform(new StructureCreatorDirector()) ;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onStop(Neo4jDBManager manager) {
        
    }
    
}
