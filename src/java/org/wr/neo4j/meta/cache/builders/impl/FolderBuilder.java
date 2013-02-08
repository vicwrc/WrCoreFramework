/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.cache.builders.impl;

import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.core.operations.CommonNodeProvider;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.cache.builders.BuilderFactory;
import org.wr.neo4j.meta.cache.builders.CommonBuilder;

/**
 *
 * @author Vorontsov
 */
public class FolderBuilder implements CommonBuilder<BaseBean> {

    private CommonNodeProvider provider;
    private BuilderFactory builderFactory;

    public void setProvider(CommonNodeProvider provider) {
        this.provider = provider;
    }

    public void setBuilderFactory(BuilderFactory builderFactory) {
        this.builderFactory = builderFactory;
    }

    @Override
    public BaseBean build(Neo4jTransaction tx, BaseBean parent, Node node) {
        BaseBean bean = create(tx, parent, node);

        bean.setParent(parent);
        bean.setName((String) node.getProperty(MetaDataConstants.ALL_NAME));
        bean.setOrder(getOrder(node));

        List<BaseBean> children = new LinkedList<>();
        bean.setChildren(children);
        for (Node child : provider.getChildren(node)) {
            BaseBean childBean = builderFactory.build(tx, bean, child);
            if (childBean != null) {
                children.add(childBean);
            }
        }
        
        return bean;
    }

    protected long getOrder(Node node) {
        try {
            return (long) node.getProperty(MetaDataConstants.ALL_ORDER);
        } catch (NotFoundException e) {
            return node.getId();
        }
    }

    protected BaseBean create(Neo4jTransaction tx, BaseBean parent, Node node) {
        return new BaseBean(node.getId());
    }

    @Override
    public boolean isAppropriate(Node node) {
        return MetaType.FOLDER.equals(MetaType.valueOf((String) node.getProperty(MetaDataConstants.ALL_META_TYPE)));
    }
}
