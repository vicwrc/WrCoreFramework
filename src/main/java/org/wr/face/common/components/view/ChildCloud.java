/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view;

import java.util.List;
import org.neo4j.graphdb.Node;
import org.wr.face.common.components.WebComponent;
import org.wr.neo4j.meta.MetaDataConstants;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author vorontsov
 * // todo : transfer to widgets
 */
public class ChildCloud extends WebComponent {

    public static ChildEntryCreator childEntryCreator = new ChildEntryCreator();
    private final List<Node> children;

    public ChildCloud(List<Node> children) {
        this.children = children;
    }

    @Override
    public String renderHtml() {
        return children.isEmpty() ? "" : "<h3>Children Cloud:</h3>" + WrCollections.aggregate(children, childEntryCreator, new StringBuilder()).toString();
    }
    
    public static class ChildEntryCreator implements WrCollections.AggregateCondition<Node, StringBuilder> {

        @Override
        public StringBuilder aggregateItem(Node item, StringBuilder currentResult) {
            return currentResult.append("<button onClick=\"document.location.href = 'index.jsp?id=").append(item.getId()).append("'\">").append(item.getProperty(MetaDataConstants.ALL_NAME)).append("</button> ");
        }
    }
}
