/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.cache.builders;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.model.BaseBean;

/**
 *
 * @author vicwrc
 */
public interface CommonBuilder<T> {
    
    T build(Neo4jTransaction tx, BaseBean parent, Node node);
    
    boolean isAppropriate(Node node);
}
