/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.widgets.common.impl.component.componentfactory;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.WebComponent;
import org.wr.face.common.widgets.common.impl.component.ParameterComponentFactory;
import org.wr.face.common.widgets.common.impl.component.edit.MultipleDateAttrComponent;
import org.wr.face.common.widgets.common.impl.component.edit.MultipleNumberAttrComponent;
import org.wr.face.common.widgets.common.impl.component.edit.MultipleTextAttrComponent;
import org.wr.face.common.widgets.common.impl.component.edit.SingleDateAttrComponent;
import org.wr.face.common.widgets.common.impl.component.edit.SingleNumberAttrComponent;
import org.wr.face.common.widgets.common.impl.component.edit.SingleTextAttrComponent;
import org.wr.neo4j.meta.attribute.AttributeType;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class EditParameterComponentFactory implements ParameterComponentFactory{
 
    @Override
     public WebComponent createComponent(AttributeBean attr, Node node) {
        if (AttributeType.TEXT.equals(attr.getType())) {
            if (attr.getMaxEntries() == 1) {
                return new SingleTextAttrComponent(attr, node);
            } else {
                return new MultipleTextAttrComponent(attr, node);
            }
        }
        if (AttributeType.NUMBER.equals(attr.getType())) {
            if (attr.getMaxEntries() == 1) {
                return new SingleNumberAttrComponent(attr, node);
            } else {
                return new MultipleNumberAttrComponent(attr, node);
            }
        }
        if (AttributeType.DATE.equals(attr.getType())) {
            if (attr.getMaxEntries() == 1) {
                return new SingleDateAttrComponent(attr, node);
            } else {
                return new MultipleDateAttrComponent(attr, node);
            }
        }
        return null;
    }
     
}
