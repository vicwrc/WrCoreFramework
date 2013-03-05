package org.wr.face.common.components.view.tiles;

import org.apache.commons.lang.StringUtils;
import org.neo4j.graphdb.Node;
import org.wr.face.common.components.WebComponent;
import org.wr.neo4j.meta.model.PageBean;

/**
 *
 * @author vorontsov
 */
public class Tile extends WebComponent {

    private final PageBean page;
    private final Node node;

    public Tile(PageBean page, Node node) {
        this.page = page;
        this.node = node;
    }

    @Override
    public String renderHtml() {
        return "<div class=\"tile" + getColor() +getType() + "\" " + getJSAction() + ">\n"
                + "                        <div class=\"tile-content\" >\n"
                + "                            " + getContentValue() + "\n"
                + "                        </div>\n"
                + "<div class=\"brand\"> "
                + "<span class=\"name\">" + page.getName() + "</span> "
                + "</div>"
                + "                    </div>";
    }

    protected String getType() {
        if (StringUtils.isEmpty(page.getPathToImage())) {
            return "";
        } else {
            return " icon";
        }
    }

    protected String getContentValue() {
        if (StringUtils.isEmpty(page.getPathToImage())) {
            return  page.getName();
        }
        if (page.getPathToImage().contains("icon:")) {
            return "<i class=\""+page.getPathToImage().substring(5) +"\"></i>";
        } else {
            return "<img src=\""+page.getPathToImage()+"\">";
        }
    }

    protected String getColor() {
        if (StringUtils.isEmpty(page.getColor())) {
            return "";
        } else {
            return " " + page.getColor();
        }
    }

    protected String getJSAction() {
        return "onClick=\"document.location.href = 'index.jsp?id=" + node.getId() + "&action=" + page.getAction()
                + (StringUtils.isEmpty(page.getExtraParams()) ? "" : "&" + page.getExtraParams()) + "'\"";
    }
}
