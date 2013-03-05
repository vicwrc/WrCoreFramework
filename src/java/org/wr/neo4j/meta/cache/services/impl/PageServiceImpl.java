/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.cache.services.impl;

import java.util.List;
import java.util.Map;
import org.neo4j.graphdb.Node;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.NodeTypeCalculator;
import org.wr.neo4j.meta.cache.services.PageService;
import org.wr.neo4j.meta.model.PageBean;

/**
 *
 * @author vorontsov
 */
public class PageServiceImpl implements PageService {

    public static String CREATE_PAGE = "create";
    private NodeTypeCalculator nodeTypeCalculator;
    private Map<String, List<PageBean>> metaPages;

    public void setNodeTypeCalculator(NodeTypeCalculator nodeTypeCalculator) {
        this.nodeTypeCalculator = nodeTypeCalculator;
    }

    public void setMetaPages(Map<String, List<PageBean>> metaPages) {
        this.metaPages = metaPages;
    }

    @Override
    public List<PageBean> getPages(Node object, Node user) {
        MetaType type = nodeTypeCalculator.getType(object);
        if (MetaType.REGULAR.equals(type)) {
            return null; // todo : write logic here
        }

        return metaPages.get(type.toString());
    }

    @Override
    public PageBean getPage(String action, List<PageBean> pages) {
        for (PageBean page : pages) {
            if (action.equalsIgnoreCase(page.getAction())) {
                return page;
            }
        }
        return null;
    }

    @Override
    public PageBean getCreatePage(String objectType, Node user) {
        MetaType type = getMetaType(objectType);
        if (null != type /*&& !MetaType.REGULAR.equals(type)*/) {
            List<PageBean> pages = metaPages.get(type.toString());
            if (null != pages) {
                for (PageBean page : pages) {
                    if (CREATE_PAGE.equals(page.getAction())) {
                        return page;
                    }
                }
            }
        }
        // todo : add logic here later
        return null;
    }
    
    protected MetaType getMetaType(String objectType){
        try {
            return MetaType.valueOf(objectType);
        } catch (IllegalArgumentException e) {
            return MetaType.REGULAR;
        }
    }

    @Override
    public PageBean getPage(String action, Node object, Node user) {
        return getPage(action, getPages(object, user));
    }

    @Override
    public void remove(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void persist(PageBean bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PageBean> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PageBean getById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
