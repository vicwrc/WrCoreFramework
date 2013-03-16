
package org.wr.neo4j.meta.cache.builders.impl;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.model.WidgetBean;

/**
 *
 * @author vicwrc
 */
public class WidgetBuilder extends FolderBuilder{

    
    @Override
    protected WidgetBean create(Neo4jTransaction tx, BaseBean parent, Node node) {
        return new WidgetBean(node.getId(), (String)node.getProperty(MetaDataConstants.WIDGET_URL)); 
    }

    @Override
    public boolean isAppropriate(Node node) {
        return MetaType.WIDGET.equals(MetaType.valueOf((String)node.getProperty(MetaDataConstants.ALL_META_TYPE)));
    }
    
}
