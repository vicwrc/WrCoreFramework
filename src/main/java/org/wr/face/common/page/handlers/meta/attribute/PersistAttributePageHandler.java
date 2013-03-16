/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page.handlers.meta.attribute;

import javax.servlet.http.HttpServletRequest;
import org.wr.face.common.builders.impl.AttributeWebToEntityBuilder;
import org.wr.face.common.page.handlers.meta.PersistMetaPageHandler;
import org.wr.neo4j.meta.cache.services.AttributeService;

/**
 *
 * @author vorontsov
 */
public class PersistAttributePageHandler<AttributeBean> extends PersistMetaPageHandler {

    @Override
    protected void initModels(HttpServletRequest request) {
        setBuilder(this.getContext(request).getBean(AttributeWebToEntityBuilder.class));
        setService(this.getContext(request).getBean(AttributeService.class));
    }

    
}
