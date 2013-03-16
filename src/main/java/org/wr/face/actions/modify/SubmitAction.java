/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face.actions.modify;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.neo4j.graphdb.Node;
import org.wr.face.actions.AbstractWebAction;
import org.wr.neo4j.core.Neo4jTransactionExecutor;
import org.wr.neo4j.handlers.GetByIdTransaction;
import org.wr.neo4j.handlers.UpdateNodePropertiesTransaction;

/**
 *
 * @author vorontsov
 */
public class SubmitAction extends AbstractWebAction{

    private Neo4jTransactionExecutor executor;

    public void setExecutor(Neo4jTransactionExecutor executor) {
        this.executor = executor;
    }

    public Object perform(HttpServletRequest request, String action) throws Exception {
        Long id = (Long)pageRequestParsers.get("id").parseParams(request);
        Node node = null;
        if(null != id){
            node = (Node)executor.perform(new GetByIdTransaction(id));
        }
        return executor.perform(
                new UpdateNodePropertiesTransaction(
                        node,
                        (Map<String, Object>)pageRequestParsers.get("params").parseParams(request)));
    }

}
