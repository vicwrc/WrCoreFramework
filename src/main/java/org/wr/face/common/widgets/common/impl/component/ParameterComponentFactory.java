
package org.wr.face.common.widgets.common.impl.component;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.WebComponent;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public interface ParameterComponentFactory {

    public WebComponent createComponent(AttributeBean attr, Node node) ;
    
}
