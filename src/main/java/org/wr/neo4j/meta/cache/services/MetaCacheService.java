package org.wr.neo4j.meta.cache.services;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.meta.model.BaseBean;

/**
 *
 * @author vicwrc
 */
public interface MetaCacheService {
    
    BaseBean getRootBean();
    
    Node getRootNode();
    
    Node getDataRoot();
    
    Node getMetaDataRoot();
    
    Node getAttributeRoot();
    
    Node getWidgetRoot();
    
    Node getBaseObjectType();
    
}
