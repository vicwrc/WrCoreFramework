/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page.handlers.meta.attribute;

import javax.servlet.http.HttpServletRequest;
import org.wr.face.common.page.handlers.meta.DeleteMetaPageHandler;
import org.wr.neo4j.meta.cache.services.AttributeService;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author Vorontsov
 */
public class DeleteAttributePageHandler extends DeleteMetaPageHandler<AttributeBean>{

    @Override
    protected void initModels(HttpServletRequest request) {
        setService(this.getContext(request).getBean(AttributeService.class));
    }
    
}
