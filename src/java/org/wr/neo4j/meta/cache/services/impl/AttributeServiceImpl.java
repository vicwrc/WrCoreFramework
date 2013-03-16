package org.wr.neo4j.meta.cache.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.Neo4jDBManager;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.core.operations.DeleteNodeOperation;
import org.wr.neo4j.core.operations.RelationshipOperation;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.attribute.AttributeType;
import org.wr.neo4j.meta.cache.services.AttributeService;
import org.wr.neo4j.meta.cache.services.MetaCacheService;
import org.wr.neo4j.meta.cache.services.ObjectTypeService;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.model.ObjectTypeBean;
import org.wr.neo4j.meta.model.attribute.ListAttribute;
import org.wr.neo4j.meta.model.attribute.ReferenceAttribute;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author vicwrc
 */
public class AttributeServiceImpl implements AttributeService {

    private Map<Long, AttributeBean> attributes = new HashMap<>();
    private MetaCacheService metaCacheService;
    private Neo4jDBManager manager;
    private DeleteNodeOperation deleteNodeOperation;
    private RelationshipOperation relationshipOperation;
    private BaseBean attrRoot;
    private ObjectTypeService objectTypeService;

    public void setObjectTypeService(ObjectTypeService objectTypeService) {
        this.objectTypeService = objectTypeService;
    }

    public void setRelationshipOperation(RelationshipOperation relationshipOperation) {
        this.relationshipOperation = relationshipOperation;
    }

    public void setMetaCacheService(MetaCacheService metaCacheService) {
        this.metaCacheService = metaCacheService;
    }

    public void setManager(Neo4jDBManager manager) {
        this.manager = manager;
    }

    public void setDeleteNodeOperation(DeleteNodeOperation deleteNodeOperation) {
        this.deleteNodeOperation = deleteNodeOperation;
    }

    public BaseBean getAttrRoot() {
        return attrRoot;
    }

    public void init() throws Exception {
        BaseBean root = metaCacheService.getRootBean();
        BaseBean metaRoot = root.getChildById(metaCacheService.getMetaDataRoot().getId());
        attrRoot = metaRoot.getChildById(metaCacheService.getAttributeRoot().getId());

        for (BaseBean attr : attrRoot.getChildren()) {
            if (attr instanceof AttributeBean) {
                attributes.put(attr.getId(), (AttributeBean) attr);
            }
        }
    }

    @Override
    public List<AttributeBean> getAll() {
        return Collections.unmodifiableList(new LinkedList(attributes.values()));
    }

    @Override
    public AttributeBean getById(long id) {
        return attributes.get(id);
    }

    @Override
    public void remove(long id) {
        try (Neo4jTransaction tx = manager.createTransaction()){
            AttributeBean bean = getById(id);
            deleteNodeOperation.delete(manager.getDbService().getNodeById(id));
            attributes.remove(id);
            attrRoot.getChildren().remove(bean);
            for (ObjectTypeBean ot : bean.getObjectTypes()) {
                ot.getCurrentAttributes().remove(bean);
                ot.rebuildAttributesRecursive();
            }
            tx.success();
        } 
    }

    @Override
    public List<AttributeBean> getByNodes(List<Node> nodes) {
        return WrCollections.aggregate(nodes, new WrCollections.AggregateCondition<Node, LinkedList<AttributeBean>>() {
            @Override
            public LinkedList<AttributeBean> aggregateItem(Node item, LinkedList<AttributeBean> currentResult) {
                currentResult.add(getById(item.getId()));
                return currentResult;
            }
        }, new LinkedList<AttributeBean>());
    }

    @Override
    public void persist(AttributeBean bean) {
        try (Neo4jTransaction tx = manager.createTransaction()){
            AttributeBean existBean = this.getById(bean.getId());
            if (null != existBean) {
                persist(bean, modify(bean, existBean));
            } else {
                persist(bean, create(bean));
            }
            tx.success();
        } 
    }

    protected void persist(AttributeBean bean, Node node) {
        node.setProperty(MetaDataConstants.ALL_NAME, bean.getName());
        node.setProperty(MetaDataConstants.ALL_ORDER, bean.getOrder());
        node.setProperty(MetaDataConstants.ALL_META_TYPE, MetaType.ATTRIBUTE.toString());
        node.setProperty(MetaDataConstants.ATTRIBUTE_IS_REQUIRED, bean.isRequired() ? 1 : 0);
        node.setProperty(MetaDataConstants.ATTRIBUTE_TYPE, bean.getType().toString());
        node.setProperty(MetaDataConstants.ATTRIBUTE_MAX_ENTRIES, bean.getMaxEntries());
        if(StringUtils.isEmpty(bean.getPublicName())){
            node.removeProperty(MetaDataConstants.ATTRIBUTE_PUBLIC_NAME);
        }else {
            node.setProperty(MetaDataConstants.ATTRIBUTE_PUBLIC_NAME, bean.getPublicName());
        }
        if(AttributeType.CHILD.equals(bean.getType()) || AttributeType.REFERENCE.equals(bean.getType())) {
            ReferenceAttribute refAttr = (ReferenceAttribute)bean;
            node.setProperty(MetaDataConstants.ATTRIBUTE_REFERENCE_TO, refAttr.getReferenceTo());
        }
        if (AttributeType.LIST.equals(bean.getType())) { 
            ListAttribute listAttr = (ListAttribute)bean;
            node.setProperty(MetaDataConstants.ATTRIBUTE_LIST_VALUES, listAttr.getValues());
        }
        if(bean.getAdditionalParameters().length == 0) {
            node.removeProperty(MetaDataConstants.ATTRIBUTE_ADDITIONAL_PARAMETERS);
        } else {
            node.setProperty(MetaDataConstants.ATTRIBUTE_ADDITIONAL_PARAMETERS, bean.getAdditionalParameters());
        }
    }

    protected Node create(AttributeBean bean) {
        BaseBean parentOtCandidate = bean.getParent();
        Node node = manager.getDbService().createNode();
        relationshipOperation.setParent(node, manager.getDbService().getNodeById(attrRoot.getId()));

        bean.setId(node.getId());
        bean.setParent(attrRoot);
        attrRoot.getChildren().add(bean);

        attributes.put(bean.getId(), bean);
        /* object types apply*/
        ObjectTypeBean otBean = objectTypeService.getById(parentOtCandidate.getId());
        if (null != otBean) {
            Node otNode = manager.getDbService().getNodeById(otBean.getId());
            relationshipOperation.setReferenceOTtoAttribute(otNode, node);
            otBean.getCurrentAttributes().add(bean);
            otBean.rebuildAttributesRecursive();
            bean.getObjectTypes().add(otBean);
        }
        return manager.getDbService().getNodeById(bean.getId());
    }

    protected Node modify(AttributeBean bean, AttributeBean existBean) {
        bean.setId(existBean.getId());
        bean.setParent(existBean.getParent());
        bean.getParent().getChildren().remove(existBean);
        bean.getParent().getChildren().add(bean);
        
        bean.setChildren(existBean.getChildren());
        for(BaseBean childBean : bean.getChildren()) {
            childBean.setParent(bean);
        }
        
        attributes.put(bean.getId(), bean);
        
        
        bean.getObjectTypes().addAll(existBean.getObjectTypes());
        for (ObjectTypeBean ot : bean.getObjectTypes()) {
            ot.getCurrentAttributes().remove(existBean);
            ot.getCurrentAttributes().add(bean);
            ot.rebuildAttributesRecursive();
        }
        
        return manager.getDbService().getNodeById(bean.getId());
    }
}
