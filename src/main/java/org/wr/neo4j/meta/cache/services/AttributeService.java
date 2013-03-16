package org.wr.neo4j.meta.cache.services;

import java.util.List;
import org.neo4j.graphdb.Node;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vicwrc
 */
public interface AttributeService extends MetadataPersistenceService<AttributeBean>{
    
    List<AttributeBean> getByNodes(List<Node> nodes);
    
}
