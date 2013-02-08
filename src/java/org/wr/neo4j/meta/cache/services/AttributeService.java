package org.wr.neo4j.meta.cache.services;

import java.util.List;
import org.neo4j.graphdb.Node;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vicwrc
 */
public interface AttributeService {
    
    List<AttributeBean> getAll();
    
    AttributeBean getById(long id);
    
    void remove(long id);
    
    void persist(AttributeBean attribute);
    
    List<AttributeBean> getByNodes(List<Node> nodes);
    
}
