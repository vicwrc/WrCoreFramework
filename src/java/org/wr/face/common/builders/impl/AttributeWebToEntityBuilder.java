/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.builders.impl;

import javax.servlet.http.HttpServletRequest;
import org.wr.face.common.builders.AbstractWebToEntityBuilder;
import org.wr.face.common.builders.WebToEntityBuilder;
import org.wr.face.servlet.ParamExtendServletRequestWrapper;
import org.wr.face.servlet.ParamExtendServletRequestWrapper.ValueParser;
import org.wr.neo4j.meta.attribute.AttributeType;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class AttributeWebToEntityBuilder extends AbstractWebToEntityBuilder implements WebToEntityBuilder<AttributeBean>{

    @Override
    public AttributeBean build(HttpServletRequest request) {
        ParamExtendServletRequestWrapper wrapper = createWrapper(request);
        
        AttributeBean bean = createAttribute(wrapper);
        if(null == bean){
            return null;
        }
        bean.setMaxEntries(wrapper.getParameter("maxEntries", Integer.class));
        bean.setName(wrapper.getParameter("name"));
        bean.setOrder(wrapper.getParameter("order", Long.class));
        bean.setRequired(wrapper.getParameter("isRequred", Boolean.class));
        bean.setType(wrapper.getParameter("type", AttributeType.class));
        bean.setParent(getParent(wrapper));
        return bean;
    }
    
    protected AttributeBean createAttribute(ParamExtendServletRequestWrapper wrapper){
        Long id = wrapper.getParameter("id", Long.class);
        if(null == id){
            return null;
        }
        return new AttributeBean(id);
    }
    
    protected ParamExtendServletRequestWrapper createWrapper(HttpServletRequest request){
        ParamExtendServletRequestWrapper wrapper = new ParamExtendServletRequestWrapper(request);
        
        wrapper.getParsers().put(AttributeType.class, new ValueParser<AttributeType>(){

            @Override
            protected AttributeType parseNonEmptyValue(String value) {
                return AttributeType.valueOf(value);
            }

            @Override
            protected AttributeType[] arrayCreation(int length) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        
        return wrapper;
    }
    
}
