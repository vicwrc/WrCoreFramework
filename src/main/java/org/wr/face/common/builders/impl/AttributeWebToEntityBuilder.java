/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.builders.impl;

import javax.servlet.http.HttpServletRequest;
import org.neo4j.graphdb.Node;
import org.wr.face.common.builders.AbstractWebToEntityBuilder;
import org.wr.face.common.builders.WebToEntityBuilder;
import org.wr.face.common.components.edit.input.multiple.MultipleTextInput;
import org.wr.face.servlet.ParamExtendServletRequestWrapper;
import org.wr.face.servlet.ParamExtendServletRequestWrapper.ValueParser;
import org.wr.neo4j.core.Neo4jDBManager;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.attribute.AttributeType;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.neo4j.meta.model.attribute.ListAttribute;
import org.wr.neo4j.meta.model.attribute.ReferenceAttribute;
import org.wr.utils.WrArrays;
import org.wr.utils.WrMath;

/**
 *
 * @author vorontsov
 */
public class AttributeWebToEntityBuilder extends AbstractWebToEntityBuilder implements WebToEntityBuilder<AttributeBean> {

    private Neo4jDBManager manager;

    public void setManager(Neo4jDBManager manager) {
        this.manager = manager;
    }

    @Override
    public AttributeBean build(HttpServletRequest request) {
        ParamExtendServletRequestWrapper wrapper = createWrapper(request);

        AttributeBean bean = createAttribute(wrapper);
        if (null == bean) {
            return null;
        }
        bean.setMaxEntries(wrapper.getParameter("maxEntries", Integer.class));
        bean.setName(wrapper.getParameter("name"));
        bean.setPublicName(wrapper.getParameter("publicName"));
        bean.setOrder(wrapper.getParameter("order", Long.class));
        bean.setRequired(wrapper.getParameter("isRequred", Boolean.class));
        bean.setType(wrapper.getParameter("type", AttributeType.class));
        bean.setParent(getParent(wrapper));
        return bean;
    }

    protected AttributeBean createAttribute(ParamExtendServletRequestWrapper wrapper) {
        Long id = wrapper.getParameter("id", Long.class);
        AttributeType type = wrapper.getParameter("type", AttributeType.class);
        if (null == id) {
            return null;
        }
        AttributeBean bean;
        Node node = -1 != id ? manager.getDbService().getNodeById(id) : null;
        if (AttributeType.CHILD.equals(type) || AttributeType.REFERENCE.equals(type)) {
            ReferenceAttribute attr = new ReferenceAttribute(id);
            attr.setReferenceTo(wrapper.getParameter("referenceTo"));
            bean = attr;
        } else if (AttributeType.LIST.equals(type)) {
            ListAttribute attr = new ListAttribute(id);
            if (null == node) {
                attr.setValues(wrapper.getParameterValues("listValues_add"));
            } else {
                attr.setValues(MultipleTextInput.mergeValues(
                        MultipleTextInput.parseValues(node.getProperty(MetaDataConstants.ATTRIBUTE_LIST_VALUES)),
                        wrapper.getParameterValues("listValues_add"),
                        wrapper.getParameterValues("listValues_remove")));
            }
            bean = attr;
        } else {
            bean = new AttributeBean(id);
        }

        if (null == node) {
            bean.setAdditionalParameters(WrMath.nvl(wrapper.getParameterValues("additionalParameters_add"), WrArrays.EMPTY_STRING_ARRAY));
        } else {
            bean.setAdditionalParameters(MultipleTextInput.mergeValues(
                    MultipleTextInput.parseValues(node.getProperty(MetaDataConstants.ATTRIBUTE_ADDITIONAL_PARAMETERS)),
                    wrapper.getParameterValues("additionalParameters_add"),
                    wrapper.getParameterValues("additionalParameters_remove")));
        }
        return bean;
    }

    protected ParamExtendServletRequestWrapper createWrapper(HttpServletRequest request) {
        ParamExtendServletRequestWrapper wrapper = new ParamExtendServletRequestWrapper(request);

        wrapper.getParsers().put(AttributeType.class, new ValueParser<AttributeType>() {
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
