package org.wr.face.common.components;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.wr.face.common.components.conditions.ComponentJoinCondition;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author vorontsov
 */
public abstract class NestedComponent extends WebComponent {

    protected List<WebComponent> children = new LinkedList<>();

    protected abstract String renderStartBlock();

    protected abstract String renderEndBlock();

    public NestedComponent addChild(WebComponent component) {
        if (null != component) {
            children.add(component);
            if (null != this.getRequest()) {
                component.setRequest(request);
            }
        }
        return this;
    }

    @Override
    public WebComponent setRequest(HttpServletRequest request) {
        this.request = request;
        for (WebComponent component : children) {
            component.setRequest(request);
        }
        return this;
    }

    private String renderAllChildren() {
        return WrCollections.aggregate(children, ComponentJoinCondition.getInstance(), new StringBuilder()).toString();
    }

    @Override
    public String renderHtml() {
        return renderStartBlock() + renderAllChildren() + renderEndBlock();
    }
}
