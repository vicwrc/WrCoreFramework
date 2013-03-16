/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.neo4j.core;

/**
 *
 * @author vorontsov
 */
public interface DoInNewTransacton<T> {

    public T perform(Neo4jTransaction transaction)throws Exception;

}
