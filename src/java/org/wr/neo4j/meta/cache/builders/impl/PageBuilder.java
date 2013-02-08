/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.cache.builders.impl;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.model.PageBean;
import org.wr.neo4j.meta.model.page.RedirectPageBean;


/**
 *
 * @author Vorontsov
 */
public class PageBuilder extends FolderBuilder{

    @Override
    public PageBean build(Neo4jTransaction tx, BaseBean parent, Node node) {
        PageBean bean = (PageBean)super.build(tx, parent, node);
       
        bean.setColor((String)node.getProperty("color"));
        bean.setHidden("true".equals(node.getProperty("hidden")));
        bean.setExtendable("true".equals(node.getProperty("extendable")));
        bean.setExtraParams((String)node.getProperty("extraParams"));
        bean.setProcessClass((String)node.getProperty("processClass"));
        return bean;
    }
    
    @Override
    protected PageBean create(Neo4jTransaction tx, BaseBean parent, Node node) {
        if(RedirectPageBean.PROCESS_CLASS_NAME.equals(node.getProperty("processClass"))){
            RedirectPageBean pageBean = new RedirectPageBean(node.getId(),(String)node.getProperty("action"));
            pageBean.setPageToRedirect((String)node.getProperty("pageToRedirect"));
            return pageBean; 
        }
        return new PageBean(node.getId(),(String)node.getProperty("action")); 
    }
    
    @Override
    public boolean isAppropriate(Node node) {
        return MetaType.PAGE.equals(MetaType.valueOf((String)node.getProperty(MetaDataConstants.ALL_META_TYPE)));
    }
    
    
}
