/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.structure.creators;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.attribute.AttributeType;

/**
 *
 * @author vicwrc
 */
public class NameAttributeCreator extends NamedObjectCreator{

    @Override
    public Node createNode(Neo4jTransaction tx, Node parent) {
        Node node = super.createNode(tx, parent);
        
        node.setProperty(MetaDataConstants.ALL_ORDER, (long)1);
        node.setProperty(MetaDataConstants.ATTRIBUTE_TYPE, AttributeType.TEXT.toString());
        node.setProperty(MetaDataConstants.ATTRIBUTE_MAX_ENTRIES, 1);
        node.setProperty(MetaDataConstants.ATTRIBUTE_IS_REQUIRED, 1);
        node.setProperty(MetaDataConstants.ALL_META_TYPE, MetaType.ATTRIBUTE.toString());
        
        return node;
    }
    
    @Override
    protected String getName() {
        return "Name";
    }
    
}
