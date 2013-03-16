/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page.handlers.common;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.neo4j.graphdb.Node;
import org.wr.face.IdParser;
import org.wr.neo4j.core.Neo4jDBManager;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.core.operations.CommonNodeProvider;
import org.wr.neo4j.core.operations.DeleteNodeOperation;

/**
 *
 * @author vorontsov
 */
public class DeleteObjectPageHandler extends AbstractObjectPageHandler {

    private DeleteNodeOperation operation;
    private CommonNodeProvider nodeProvider;
    private Neo4jDBManager manager;

    @Override
    protected void initModels(HttpServletRequest request) {
        operation = this.getContext(request).getBean(DeleteNodeOperation.class);
        nodeProvider = this.getContext(request).getBean(CommonNodeProvider.class);
        manager = this.getContext(request).getBean(Neo4jDBManager.class);
    }

    @Override
    protected void processEvent(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        long id = IdParser.parseId(httpRequest);
        Node cur = manager.getDbService().getNodeById(id);
        Node parent = nodeProvider.getParent(cur);
        try (Neo4jTransaction tx = manager.createTransaction()) {
            operation.delete(cur);
            try {
                httpResponse.sendRedirect("index.jsp?id=" + parent.getId());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            tx.success();
        }
    }
}
