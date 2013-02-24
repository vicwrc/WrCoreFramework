package org.wr.face.common.page.handlers.meta;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.wr.face.common.WebSpringAware;
import org.wr.face.common.builders.WebToEntityBuilder;
import org.wr.neo4j.meta.cache.services.MetadataPersistenceService;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.page.PageHandler;

/**
 *
 * @author Vorontsov
 */
public abstract class PersistMetaPageHandler<T extends BaseBean> extends WebSpringAware implements PageHandler {

    protected WebToEntityBuilder<T> builder;
    protected MetadataPersistenceService<T> service;
    protected boolean isInited = false;

    public void setBuilder(WebToEntityBuilder builder) {
        this.builder = builder;
    }

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
        T bean = builder.build(httpRequest);
        if (null != bean) { 
            service.persist(bean);
            try {
                httpResponse.sendRedirect("index.jsp?id=" + bean.getId());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
}
