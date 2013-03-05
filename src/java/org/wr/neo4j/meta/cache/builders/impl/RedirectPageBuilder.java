/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.cache.builders.impl;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.model.PageBean;
import org.wr.neo4j.meta.model.page.RedirectPageBean;

/**
 *
 * @author vorontsov
 */
public class RedirectPageBuilder extends PageBuilder {

    @Override
    public RedirectPageBean build(Neo4jTransaction tx, BaseBean parent, Node node) {
        RedirectPageBean bean = (RedirectPageBean) super.build(tx, parent, node);

        bean.setPageToRedirect((String) node.getProperty("pageToRedirect"));
        return bean;
    }

    @Override
    protected PageBean create(Neo4jTransaction tx, BaseBean parent, Node node) {
        return new RedirectPageBean(node.getId(), (String) node.getProperty("action"));        
    }

    @Override
    public boolean isAppropriate(Node node) {
        try {
            return MetaType.PAGE.equals(MetaType.valueOf((String) node.getProperty(MetaDataConstants.ALL_META_TYPE)))
                    && RedirectPageBean.PROCESS_CLASS_NAME.equals(node.getProperty("processClass"));
        } catch (NotFoundException ex) {
            return false;
        }
    }
}
