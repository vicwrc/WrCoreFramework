/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page.handlers.meta.attribute;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.wr.face.common.WebSpringAware;
import org.wr.face.common.builders.impl.AttributeWebToEntityBuilder;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.neo4j.meta.page.PageHandler;
import org.wr.neo4j.meta.cache.services.AttributeService;

/**
 *
 * @author vorontsov
 */
public class PersistAttributePageHandler extends WebSpringAware implements PageHandler {

    protected AttributeWebToEntityBuilder builder;
    protected AttributeService attributeService;
    protected boolean isInited = false;

    public void setBuilder(AttributeWebToEntityBuilder builder) {
        this.builder = builder;
    }

    protected void init(HttpServletRequest httpRequest) {
        if (!isInited) {
            setBuilder(this.getContext(httpRequest).getBean(AttributeWebToEntityBuilder.class));
            attributeService = this.getContext(httpRequest).getBean(AttributeService.class);
            isInited = true;
        }
    }

    @Override
    public void process(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        init(httpRequest);
        AttributeBean bean = builder.build(httpRequest);
        if (null != bean) { 
            System.out.println("attr to store : "+bean);
            attributeService.persist(bean);
            try {
                httpResponse.sendRedirect("index.jsp?id=" + bean.getId());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
