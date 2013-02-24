/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page.handlers.meta.objecttype;

import javax.servlet.http.HttpServletRequest;
import org.wr.face.common.page.handlers.meta.DeleteMetaPageHandler;
import org.wr.neo4j.meta.cache.services.ObjectTypeService;
import org.wr.neo4j.meta.model.ObjectTypeBean;

/**
 *
 * @author Vorontsov
 */
public class DeleteObjectTypePageHandler extends DeleteMetaPageHandler<ObjectTypeBean>{

    @Override
    protected void initModels(HttpServletRequest request) {
        setService(this.getContext(request).getBean(ObjectTypeService.class));
    }
    
}
