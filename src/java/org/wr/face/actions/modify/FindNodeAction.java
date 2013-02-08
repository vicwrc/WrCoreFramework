/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face.actions.modify;

import javax.servlet.http.HttpServletRequest;
import org.wr.face.actions.AbstractWebAction;
import org.wr.neo4j.core.Neo4jTransactionExecutor;
import org.wr.neo4j.handlers.GetByIdTransaction;

/**
 *
 * @author vorontsov
 */
public class FindNodeAction extends AbstractWebAction{

    private Neo4jTransactionExecutor executor;

    public void setExecutor(Neo4jTransactionExecutor executor) {
        this.executor = executor;
    }

    public Object perform(HttpServletRequest request, String action) throws Exception {
        Long id = (Long)pageRequestParsers.get("id").parseParams(request);
        return executor.perform(new GetByIdTransaction(id));
    }

}
