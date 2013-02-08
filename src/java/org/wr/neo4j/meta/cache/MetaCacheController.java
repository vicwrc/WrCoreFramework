/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.cache;

import org.wr.neo4j.meta.cache.services.AttributeService;
import org.wr.neo4j.meta.cache.services.MetaCacheService;
import org.wr.neo4j.meta.cache.services.ObjectTypeService;
import org.wr.neo4j.meta.cache.services.PageService;


/**
 *
 * @author vorontsov
 */
public class MetaCacheController {
    
    private AttributeService attributeService;
    private ObjectTypeService objectTypeService;
    private MetaCacheService metaCacheService;
    private PageService pageService;

    public AttributeService getAttributeService() {
        return attributeService;
    }

    public void setAttributeService(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    public ObjectTypeService getObjectTypeService() {
        return objectTypeService;
    }

    public void setObjectTypeService(ObjectTypeService objectTypeService) {
        this.objectTypeService = objectTypeService;
    }

    public MetaCacheService getMetaCacheService() {
        return metaCacheService;
    }

    public void setMetaCacheService(MetaCacheService metaCacheService) {
        this.metaCacheService = metaCacheService;
    }

    public PageService getPageService() {
        return pageService;
    }

    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }
    
}
