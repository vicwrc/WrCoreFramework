package org.wr.face.common.widgets.common.impl.component.componentfactory;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.WebComponent;
import org.wr.face.common.widgets.common.impl.component.ParameterComponentFactory;
import org.wr.face.common.widgets.common.impl.component.view.ViewMultipleAttribute;
import org.wr.face.common.widgets.common.impl.component.view.ViewSingleAttribute;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class ViewParameterComponentFactory implements ParameterComponentFactory {

    @Override
    public WebComponent createComponent(AttributeBean attr, Node node) {
        if (attr.getMaxEntries() == 1) {
            return new ViewSingleAttribute(attr, node);
        } else {
            return new ViewMultipleAttribute(attr, node);
        }
    }
}
