/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.cache.builders.impl.attribute;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.attribute.AttributeType;
import org.wr.neo4j.meta.cache.builders.impl.AttributeBuilder;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.model.attribute.ReferenceAttribute;

/**
 *
 * @author vorontsov
 */
public class ReferenceAttributeBuilder extends AttributeBuilder{
    
    @Override
    public ReferenceAttribute build(Neo4jTransaction tx, BaseBean parent, Node node) {
        ReferenceAttribute bean = (ReferenceAttribute)super.build(tx, parent, node);
        
        bean.setReferenceTo((String)node.getProperty("referenceTo"));
        
        return bean;
    }
    
    @Override
    protected ReferenceAttribute create(Neo4jTransaction tx, BaseBean parent, Node node) {
        return new ReferenceAttribute(node.getId()); 
    }
    
    @Override
    public boolean isAppropriate(Node node) {
        return MetaType.ATTRIBUTE.equals(MetaType.valueOf((String)node.getProperty(MetaDataConstants.ALL_META_TYPE))) &&
               ( AttributeType.CHILD.toString().equals((String)node.getProperty(MetaDataConstants.ATTRIBUTE_TYPE)) ||
                AttributeType.REFERENCE.toString().equals((String)node.getProperty(MetaDataConstants.ATTRIBUTE_TYPE)));
    }
    
}
