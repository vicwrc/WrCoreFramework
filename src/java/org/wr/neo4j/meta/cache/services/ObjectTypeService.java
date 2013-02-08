package org.wr.neo4j.meta.cache.services;

import java.util.List;
import org.wr.neo4j.meta.model.ObjectTypeBean;

/**
 *
 * @author vicwrc
 */
public interface ObjectTypeService {
    
    List<ObjectTypeBean> getAll();
    
    ObjectTypeBean getById(long id);
    
    void remove(long id);
    
    ObjectTypeBean persist(ObjectTypeBean objectType);
    
}
