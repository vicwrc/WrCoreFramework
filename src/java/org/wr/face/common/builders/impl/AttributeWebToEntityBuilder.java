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
import org.wr.neo4j.meta.model.attribute.ListAttribute;
import org.wr.neo4j.meta.model.attribute.ReferenceAttribute;

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
        bean.setPublicName(wrapper.getParameter("publicName"));
        //bean.setAdditionalParameters(MultipleTextInput.);
        bean.setOrder(wrapper.getParameter("order", Long.class));
        bean.setRequired(wrapper.getParameter("isRequred", Boolean.class));
        bean.setType(wrapper.getParameter("type", AttributeType.class));
        bean.setParent(getParent(wrapper));
        return bean;
    }
    
    protected AttributeBean createAttribute(ParamExtendServletRequestWrapper wrapper){
        Long id = wrapper.getParameter("id", Long.class);
        AttributeType type = wrapper.getParameter("type", AttributeType.class);
        if(null == id){
            return null;
        }
        if(AttributeType.CHILD.equals(type) || AttributeType.REFERENCE.equals(type)) {
            ReferenceAttribute attr = new ReferenceAttribute(id);
            attr.setReferenceTo(wrapper.getParameter("referenceTo"));
            return attr;
        }
        if (AttributeType.LIST.equals(type)) {
            ListAttribute attr =  new ListAttribute(id);
            attr.setValues(wrapper.getParameterValues("listValues"));
            return attr;
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
