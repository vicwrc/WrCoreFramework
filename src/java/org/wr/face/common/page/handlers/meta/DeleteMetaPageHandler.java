/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page.handlers.meta;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.wr.face.IdParser;
import org.wr.face.common.WebSpringAware;
import org.wr.neo4j.meta.cache.services.MetadataPersistenceService;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.page.PageHandler;

/**
 *
 * @author Vorontsov
 */
public abstract class DeleteMetaPageHandler<T extends BaseBean> extends WebSpringAware implements PageHandler {
    
    protected MetadataPersistenceService<T> service;
    protected boolean isInited = false;
    
    public void setService(MetadataPersistenceService<T> service) {
        this.service = service;
    }
    
    protected void init(HttpServletRequest httpRequest) {
        if (!isInited) {
            initModels(httpRequest);
            isInited = true;
        }
    }
    
    protected abstract void initModels(HttpServletRequest request);

    @Override
    public void process(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        init(httpRequest);
        T bean = service.getById(IdParser.parseId(httpRequest));
        if (null != bean) { 
            service.remove(bean.getId());
            try {
                httpResponse.sendRedirect("index.jsp?id=" + bean.getParent().getId());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
