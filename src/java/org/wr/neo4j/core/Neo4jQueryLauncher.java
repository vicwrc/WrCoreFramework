package org.wr.neo4j.core;

import java.util.Iterator;
import java.util.Map;
import javax.xml.soap.Node;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;

/**
 *
 * @author vorontsov
 */
public class Neo4jQueryLauncher {

    private static final Log LOGGER = LogFactory.getLog(Neo4jQueryLauncher.class);

    private ExecutionEngine engine;

    public Neo4jQueryLauncher(ExecutionEngine engine) {
        this.engine = engine;
    }

    public Iterator<Node> performQuery(String query, Map<String, Object> params){
        ExecutionResult result = null == params? engine.execute( query ):engine.execute( query, params );
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug( result );
        }
        String firstColumn = result.columns().iterator().next();

        return result.columnAs(firstColumn);
    }

    public Iterator<Node> performQuery(String query){
        return performQuery(query, null);
    }
}
