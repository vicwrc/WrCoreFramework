/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.services.finders;

import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Node;
import org.wr.neo4j.meta.MetaType;
import org.wr.neo4j.meta.NodeTypeCalculator;

/**
 *
 * @author vorontsov
 */
// todo : rewrite it later
public class ContentUrlFinder {
    
    private NodeTypeCalculator calculator;

    public void setCalculator(NodeTypeCalculator calculator) {
        this.calculator = calculator;
    }
    
    public List<String> findContentUrls(Node node){
        return findContentUrls(node,"view");
    }
    
    public List<String> findContentUrls(Node node, String action){
        List<String> urls = new LinkedList<>();
        if("tiles".equals(action)){
            urls.add("widgets/tiles/index.jsp");
            return urls;
        }
        MetaType type = calculator.getType(node);
        
        if (MetaType.ATTRIBUTE.equals(type) && "view".equals(action)) {
            urls.add("meta/attribute/view/index.jsp");
        }
        urls.add("widgets/childCloud.jsp");
        urls.add("meta/attribute/view/table/index.jsp");
        return urls;
    }
    
}
