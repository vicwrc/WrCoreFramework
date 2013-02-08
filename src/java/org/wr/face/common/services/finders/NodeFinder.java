/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.services.finders;

import javax.servlet.http.HttpServletRequest;
import org.neo4j.graphdb.Node;
import org.springframework.web.context.WebApplicationContext;
import org.wr.face.IdParser;
import org.wr.face.request.SpringContextParser;
import org.wr.neo4j.core.Neo4jDBManager;

/**
 *
 * @author vorontsov
 */
public class NodeFinder {
    
    public Node getNode(HttpServletRequest request){
        WebApplicationContext context = SpringContextParser.getSpringContext(request);
        long id = IdParser.parseId(request);
        if (-1 != id) {
            Neo4jDBManager manager = context.getBean(Neo4jDBManager.class);
            return manager.getDbService().getNodeById(id);
        }
        return null;
    }
    
    public Node getParentNode(HttpServletRequest request){
        WebApplicationContext context = SpringContextParser.getSpringContext(request);
        long id = IdParser.parseParentId(request);
        if (-1 != id) {
            Neo4jDBManager manager = context.getBean(Neo4jDBManager.class);
            return manager.getDbService().getNodeById(id);
        }
        return null;
    }
    
}
