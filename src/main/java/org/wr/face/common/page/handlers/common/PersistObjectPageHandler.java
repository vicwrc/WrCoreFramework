/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page.handlers.common;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.neo4j.graphdb.Node;
import org.wr.face.common.services.finders.NodeFinder;
import org.wr.face.common.services.persistence.AttributePersistenceService;
import org.wr.face.common.widgets.common.impl.component.AttrIdCreator;
import org.wr.neo4j.core.Neo4jDBManager;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.core.operations.CommonNodeProvider;
import org.wr.neo4j.core.operations.RelationshipOperation;
import org.wr.neo4j.meta.cache.services.ObjectTypeService;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.neo4j.meta.model.ObjectTypeBean;
import org.wr.utils.WrMath;

/**
 *
 * @author vorontsov
 */
public class PersistObjectPageHandler extends AbstractObjectPageHandler {

    private NodeFinder nodeFinder;
    private CommonNodeProvider nodeProvider;
    private RelationshipOperation relationshipOperation;
    private ObjectTypeService otService;
    private Neo4jDBManager manager;
    private AttributePersistenceService attributePersistenceService = new AttributePersistenceService();

    @Override
    protected void initModels(HttpServletRequest request) {
        nodeFinder = this.getContext(request).getBean(NodeFinder.class);
        nodeProvider = this.getContext(request).getBean(CommonNodeProvider.class);
        otService = this.getContext(request).getBean(ObjectTypeService.class);
        manager = this.getContext(request).getBean(Neo4jDBManager.class);
        relationshipOperation = this.getContext(request).getBean(RelationshipOperation.class);
    }

    @Override
    protected void processEvent(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        Node cur = nodeFinder.getNode(httpRequest);
        Node parent = nodeFinder.getParentNode(httpRequest);
        try (Neo4jTransaction tx = manager.createTransaction()) {
            if (null == cur) {
                cur = create(tx, parent, httpRequest, httpResponse);
            } else {
                update(tx, cur, httpRequest, httpResponse);
            }
            try {
                httpResponse.sendRedirect("index.jsp?id=" + cur.getId());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            tx.success();
            
        }
    }

    protected Node create(Neo4jTransaction tx, Node parent, HttpServletRequest request, HttpServletResponse response) {
        long otId = Long.parseLong(request.getParameter("objectType"));
        ObjectTypeBean ot = otService.getById(otId);
        Node cur = create(parent, ot);
        persistParameters(cur, ot, request, response);
        return cur;
    }

    protected void update(Neo4jTransaction tx, Node cur, HttpServletRequest request, HttpServletResponse response) {
        Node objectType = nodeProvider.getObjectType(cur);
        ObjectTypeBean ot = otService.getById(objectType.getId());
        persistParameters(cur, ot, request, response);
    }

    
    
    protected Node create(Node parent, ObjectTypeBean ot) {
        Node cur = manager.getDbService().createNode();
        Node objectType = manager.getDbService().getNodeById(ot.getId());
        relationshipOperation.setParent(cur, parent);
        relationshipOperation.setObjectType(cur, objectType);
        return cur;
    }

    protected void persistParameters(Node cur, ObjectTypeBean ot, HttpServletRequest request, HttpServletResponse response) {
        for(AttributeBean attr : ot.getAllAttributes()) {
            persistAttribute(cur, attr, request, response);
        }
    }

    private void persistAttribute(Node cur, AttributeBean attr, HttpServletRequest request, HttpServletResponse response) {
        String attrId = AttrIdCreator.createAttrId(attr);
        if(attr.getMaxEntries() > 1 ){
            // multiple
            String[] valuesToAdd = WrMath.nvl(request.getParameterValues(attrId+"_add"), new String[0]);
            String[] valuesToRemove = WrMath.nvl(request.getParameterValues(attrId+"_remove"), new String[0]);
            attributePersistenceService.persist(cur, attr, valuesToAdd, valuesToRemove);
        } else {
            // single
            String newValue = request.getParameter(attrId);
            attributePersistenceService.persist(cur, attr, newValue);
        }
    }
}
