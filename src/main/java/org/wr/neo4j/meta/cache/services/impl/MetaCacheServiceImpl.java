/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.cache.services.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.Neo4jDBManager;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.cache.builders.BuilderFactory;
import org.wr.neo4j.meta.cache.services.MetaCacheService;

/**
 *
 * @author vicwrc
 */
public class MetaCacheServiceImpl implements MetaCacheService {

    protected Neo4jDBManager manager;
    protected Map<String, Node> metadata = new ConcurrentHashMap<>();
    protected BuilderFactory factory;
    protected BaseBean root;

    public void setManager(Neo4jDBManager manager) {
        this.manager = manager;
    }

    public void setFactory(BuilderFactory factory) {
        this.factory = factory;
    }

    public void init() {
        metadata.clear();
        metadata.put("root", manager.getDbService().getNodeById(1));
        metadata.put("dataroot", manager.getDbService().getNodeById(2));
        metadata.put("metadataroot", manager.getDbService().getNodeById(3));

        metadata.put("attrroot", manager.getDbService().getNodeById(5));
        metadata.put("widgetroot", manager.getDbService().getNodeById(4));
        metadata.put("baseot", manager.getDbService().getNodeById(7));
        Neo4jTransaction tx = manager.createTransaction();
        try {
            root = factory.build(tx, null, getRootNode());
            tx.success();
        } finally {
            tx.finish();
        }
    }

    @Override
    public BaseBean getRootBean() {
        return root;
    }

    @Override
    public Node getRootNode() {
        return metadata.get("root");
    }

    @Override
    public Node getDataRoot() {
        return metadata.get("dataroot");
    }

    @Override
    public Node getMetaDataRoot() {
        return metadata.get("metadataroot");
    }

    @Override
    public Node getAttributeRoot() {
        return metadata.get("attrroot");
    }

    @Override
    public Node getWidgetRoot() {
        return metadata.get("widgetroot");
    }

    @Override
    public Node getBaseObjectType() {
        return metadata.get("baseot");
    }
}
