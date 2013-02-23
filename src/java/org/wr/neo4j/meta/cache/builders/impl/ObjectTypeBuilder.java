package org.wr.neo4j.meta.cache.builders.impl;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.BaseReationTypes;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.model.ObjectTypeBean;




/**
 *
 * @author vicwrc
 */
public class ObjectTypeBuilder extends FolderBuilder{
    
    private Map<Long,AttributeBean> attributes;
    
    @Override
    public ObjectTypeBean build(Neo4jTransaction tx, BaseBean parent, Node node){
        ObjectTypeBean bean = (ObjectTypeBean)super.build(tx, parent, node);
        
        List<AttributeBean> attrs = new LinkedList<>();
        for(Relationship attrRel : node.getRelationships(BaseReationTypes.ATTRIBUTES)){
            Node attr = attrRel.getEndNode();
            AttributeBean attrBean = getAttributes(bean).get(attr.getId());
            attrs.add(attrBean);
            attrBean.getObjectTypes().add(bean);
        }
        bean.setCurrentAttributes(attrs);
        
        return bean;
    }
    
    protected BaseBean getMetaDataTop(BaseBean cur){
        BaseBean parent = cur.getParent();
        while(parent instanceof ObjectTypeBean){
            parent = parent.getParent();
        }
        return parent;
    }

    public Map<Long, AttributeBean> getAttributes(BaseBean cur) {
        if(null == attributes){
            createAttributesMap(cur);
        }
        return attributes;
    }
    
    protected void createAttributesMap(BaseBean curOt){
        BaseBean metaDataRoot = getMetaDataTop(curOt);
        BaseBean attrsRoot = null;
        System.out.println("Children of metaRoot : "+metaDataRoot.getChildren());
        for(BaseBean bean : metaDataRoot.getChildren()){
            if("Attributes Root".equals(bean.getName())){
                attrsRoot = bean;
                break;
            }
        }
        attributes = new HashMap<>();
        for(BaseBean attr: attrsRoot.getChildren()){
            if(attr instanceof AttributeBean){
                attributes.put(attr.getId(), (AttributeBean)attr);
            }
        }
    }
        
    @Override
    public boolean isAppropriate(Node node) {
        return MetaType.OBJECT_TYPE.equals(MetaType.valueOf((String)node.getProperty(MetaDataConstants.ALL_META_TYPE)));
    }
    
    @Override
    protected ObjectTypeBean create(Neo4jTransaction tx, BaseBean parent, Node node) {
        return new ObjectTypeBean(node.getId()); 
    }
}
