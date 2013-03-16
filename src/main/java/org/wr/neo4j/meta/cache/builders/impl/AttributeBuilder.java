package org.wr.neo4j.meta.cache.builders.impl;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.attribute.AttributeType;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.utils.WrArrays;


/**
 *
 * @author vicwrc
 */
public class AttributeBuilder extends FolderBuilder{

    @Override
    public AttributeBean build(Neo4jTransaction tx, BaseBean parent, Node node) {
        AttributeBean bean = (AttributeBean)super.build(tx, parent, node);
        
        bean.setMaxEntries((int)node.getProperty(MetaDataConstants.ATTRIBUTE_MAX_ENTRIES));
        bean.setType(AttributeType.valueOf((String)node.getProperty(MetaDataConstants.ATTRIBUTE_TYPE)));
        bean.setRequired(1 == (int)node.getProperty(MetaDataConstants.ATTRIBUTE_IS_REQUIRED));
        bean.setPublicName(getPublicName(node));
        bean.setAdditionalParameters((String[])node.getProperty(MetaDataConstants.ATTRIBUTE_ADDITIONAL_PARAMETERS,WrArrays.EMPTY_STRING_ARRAY));
        return bean;
    }
    
    protected String getPublicName(Node node){
        try{
            return (String)node.getProperty(MetaDataConstants.ATTRIBUTE_PUBLIC_NAME);
        }catch(NotFoundException ex){
            return null;
        }
    }
    
    @Override
    protected AttributeBean create(Neo4jTransaction tx, BaseBean parent, Node node) {
        return new AttributeBean(node.getId()); 
    }
    
    @Override
    public boolean isAppropriate(Node node) {
        return MetaType.ATTRIBUTE.equals(MetaType.valueOf((String)node.getProperty(MetaDataConstants.ALL_META_TYPE)));
    }
}
