/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.services.finders;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.neo4j.graphdb.Node;
import org.springframework.web.context.WebApplicationContext;
import org.wr.face.request.SpringContextParser;
import org.wr.neo4j.meta.cache.MetaCacheController;
import org.wr.neo4j.meta.model.PageBean;


/**
 *
 * @author vorontsov
 */
public class PageFinder {

    public PageBean getPage(Node curNode, HttpServletRequest request) {
        if (null == curNode) {
            return null;
        }
        WebApplicationContext context = SpringContextParser.getSpringContext(request);
        String action = request.getParameter("action");
        action = StringUtils.isEmpty(action) ? "view" : action;
        MetaCacheController cacheController = context.getBean(MetaCacheController.class);
        return cacheController.getPageService().getPage(action, curNode, null);
    }
    
    public PageBean getCreatePage(HttpServletRequest request) {
        WebApplicationContext context = SpringContextParser.getSpringContext(request);
        String objectType = request.getParameter("objectType");
        
        MetaCacheController cacheController = context.getBean(MetaCacheController.class);
        return cacheController.getPageService().getCreatePage(objectType, null);
    }
}
