package org.wr.neo4j.meta.cache.services;

import java.util.List;
import org.neo4j.graphdb.Node;
import org.wr.neo4j.meta.model.PageBean;

/**
 *
 * @author vorontsov
 */
public interface PageService extends MetadataPersistenceService<PageBean>{
    
    List<PageBean> getPages(Node object, Node user);
    
    PageBean getPage(String action, List<PageBean> pages);
    
    PageBean getPage(String action, Node object, Node user);
    
    PageBean getCreatePage(String objectType, Node user);
    
}
