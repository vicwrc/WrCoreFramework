/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view.navigation;

import java.util.List;
import org.neo4j.graphdb.Node;
import org.wr.face.common.components.WebComponent;
import org.wr.neo4j.meta.MetaDataConstants;

/**
 *
 * @author vorontsov
 */
public class NodePath extends WebComponent {

    public static String PATH_SEPARATOR = " &rsaquo; ";
    private final List<Node> nodePath;

    public NodePath(List<Node> nodePath) {
        this.nodePath = nodePath;
    }

    @Override
    public String renderHtml() {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (Node node : nodePath) {
            if (!isFirst) {
                sb.append(PATH_SEPARATOR);
            } else {
                isFirst = false;
            }
            sb.append(getNodeUrl(node));
        }

        return sb.toString();
    }

    protected String getNodeUrl(Node node) {
        return "<a href=\"index.jsp?id=" + node.getId() + "\">" + node.getProperty(MetaDataConstants.ALL_NAME) + "</a>";
    }
}
