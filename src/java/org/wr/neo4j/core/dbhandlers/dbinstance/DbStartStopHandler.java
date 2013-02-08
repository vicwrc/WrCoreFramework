package org.wr.neo4j.core.dbhandlers.dbinstance;

import org.wr.neo4j.core.Neo4jDBManager;

/**
 *
 * @author vicwrc
 */
public interface DbStartStopHandler {
    
    void onStart(Neo4jDBManager manager); 
    
    void onStop(Neo4jDBManager manager);
    
}
