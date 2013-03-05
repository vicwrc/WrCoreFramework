
package org.wr.face.common.components.widgets.common.impl;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.WebComponent;
import org.wr.face.common.components.widgets.common.impl.editcomp.*;
import org.wr.neo4j.meta.attribute.AttributeType;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class ParameterComponentFactory {

    public WebComponent createComponent(AttributeBean attr, Node node) {
        if (AttributeType.TEXT.equals(attr.getType())) {
            if (attr.getMaxEntries() == 1) {
                return new SingleTextAttrComponent(attr, node);
            }
        }
        if (AttributeType.NUMBER.equals(attr.getType())) {
            if (attr.getMaxEntries() == 1) {
                return new SingleNumberAttrComponent(attr, node);
            }
        }
        if (AttributeType.DATE.equals(attr.getType())) {
            if (attr.getMaxEntries() == 1) {
                return new SingleDateAttrComponent(attr, node);
            }
        }
        return null;
    }
}
