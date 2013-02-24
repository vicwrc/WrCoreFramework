/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.cache.services;

import java.util.List;
import org.wr.neo4j.meta.model.BaseBean;

/**
 *
 * @author Vorontsov
 */
public interface MetadataPersistenceService<T extends BaseBean> {
    
    List<T> getAll();
    
    T getById(long id);
    
    void remove(long id);
    
    void persist(T bean);
}
