package org.wr.neo4j.meta.cache.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.Neo4jDBManager;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.core.operations.DeleteNodeOperation;
import org.wr.neo4j.core.operations.RelationshipOperation;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.model.ObjectTypeBean;
import org.wr.neo4j.meta.cache.services.MetaCacheService;
import org.wr.neo4j.meta.cache.services.ObjectTypeService;

/**
 *
 * @author vicwrc
 */
public class ObjectTypeServiceImpl implements ObjectTypeService {

    private Map<Long, ObjectTypeBean> objectTypes = new HashMap<>();
    private Neo4jDBManager manager;
    private DeleteNodeOperation deleteNodeOperation;
    private MetaCacheService metaCacheService;
    private RelationshipOperation relationshipOperation;

    public void setMetaCacheService(MetaCacheService metaCacheService) {
        this.metaCacheService = metaCacheService;
    }

    public void setManager(Neo4jDBManager manager) {
        this.manager = manager;
    }

    public void setDeleteNodeOperation(DeleteNodeOperation deleteNodeOperation) {
        this.deleteNodeOperation = deleteNodeOperation;
    }

    public void setRelationshipOperation(RelationshipOperation relationshipOperation) {
        this.relationshipOperation = relationshipOperation;
    }

    public void init() throws Exception {
        BaseBean root = metaCacheService.getRootBean();
        BaseBean metaRoot = root.getChildById(metaCacheService.getMetaDataRoot().getId());
        ObjectTypeBean rootOT = (ObjectTypeBean) metaRoot.getChildById(metaCacheService.getBaseObjectType().getId());
        loadOtRecursively(rootOT);
    }

    protected void loadOtRecursively(ObjectTypeBean ot) {
        objectTypes.put(ot.getId(), ot);
        for (ObjectTypeBean child : ot.getChildObjectTypes()) {
            loadOtRecursively(child);
        }
    }

    @Override
    public List<ObjectTypeBean> getAll() {
        return Collections.unmodifiableList(new LinkedList(objectTypes.values()));
    }

    @Override
    public ObjectTypeBean getById(long id) {
        return objectTypes.get(id);
    }

    @Override
    public void remove(long id) {
        try (Neo4jTransaction tx = manager.createTransaction()){
            ObjectTypeBean bean = getById(id);
            deleteNodeOperation.delete(manager.getDbService().getNodeById(id));
            bean.remove();
            objectTypes.remove(id);
            tx.success();
        } 
    }

    @Override
    public void persist(ObjectTypeBean bean) {
        try (Neo4jTransaction tx = manager.createTransaction()){
            ObjectTypeBean existBean = this.getById(bean.getId());
            if (null != existBean) {
                persist(bean, modify(bean, existBean));
            } else {
                persist(bean, create(bean));
            }
            tx.success();
        } 
    }

    protected void persist(ObjectTypeBean bean, Node node) {
        node.setProperty(MetaDataConstants.ALL_NAME, bean.getName());
        node.setProperty(MetaDataConstants.ALL_ORDER, bean.getOrder());
        node.setProperty(MetaDataConstants.ALL_META_TYPE, MetaType.OBJECT_TYPE.toString());
    }

    protected Node create(ObjectTypeBean bean) {
        Node node = manager.getDbService().createNode();
        relationshipOperation.setParent(node, manager.getDbService().getNodeById(bean.getParent().getId()));

        BaseBean parentBean = this.getById(bean.getParent().getId());
        if (null == parentBean) {
            parentBean = metaCacheService.getRootBean().getChildById(metaCacheService.getMetaDataRoot().getId());
        }

        bean.setId(node.getId());
        bean.setParent(parentBean);
        parentBean.getChildren().add(bean);

        objectTypes.put(bean.getId(), bean);
        return node;
    }

    protected Node modify(ObjectTypeBean bean, ObjectTypeBean existBean) {
        existBean.setName(bean.getName());
        existBean.setOrder(bean.getOrder());
        return manager.getDbService().getNodeById(bean.getId());
    }
}
