/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.builders.impl;

import javax.servlet.http.HttpServletRequest;
import org.wr.face.common.builders.AbstractWebToEntityBuilder;
import org.wr.face.common.builders.WebToEntityBuilder;
import org.wr.face.servlet.ParamExtendServletRequestWrapper;
import org.wr.neo4j.meta.model.ObjectTypeBean;

/**
 *
 * @author Vorontsov
 */
public class ObjectTypeWebToEntityBuilder extends AbstractWebToEntityBuilder implements WebToEntityBuilder<ObjectTypeBean>{

    @Override
    public ObjectTypeBean build(HttpServletRequest request) {
        ParamExtendServletRequestWrapper wrapper = createWrapper(request);
        
        ObjectTypeBean bean = createBean(wrapper);
        if(null == bean){
            return null;
        }
        bean.setName(wrapper.getParameter("name"));
        bean.setOrder(wrapper.getParameter("order", Long.class));
        bean.setParent(getParent(wrapper));
        return bean;
    }
    
    protected ParamExtendServletRequestWrapper createWrapper(HttpServletRequest request) {
        return new ParamExtendServletRequestWrapper(request);
    }

    private ObjectTypeBean createBean(ParamExtendServletRequestWrapper wrapper) {
        Long id = wrapper.getParameter("id", Long.class);
        if(null == id){
            return null;
        }
        return new ObjectTypeBean(id);
    }
    
}
