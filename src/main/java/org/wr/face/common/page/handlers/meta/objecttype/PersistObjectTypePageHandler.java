/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page.handlers.meta.objecttype;

import javax.servlet.http.HttpServletRequest;
import org.wr.face.common.builders.impl.ObjectTypeWebToEntityBuilder;
import org.wr.face.common.page.handlers.meta.PersistMetaPageHandler;
import org.wr.neo4j.meta.cache.services.ObjectTypeService;


/**
 *
 * @author Vorontsov
 */
public class PersistObjectTypePageHandler<ObjectTypeBean> extends PersistMetaPageHandler{

    @Override
    protected void initModels(HttpServletRequest request) {
        setBuilder(this.getContext(request).getBean(ObjectTypeWebToEntityBuilder.class));
        setService(this.getContext(request).getBean(ObjectTypeService.class));
    }
    
}
