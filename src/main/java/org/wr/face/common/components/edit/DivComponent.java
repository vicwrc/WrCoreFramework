package org.wr.face.common.components.edit;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.wr.face.common.components.WebComponent;
import org.wr.face.common.components.conditions.ComponentJoinCondition;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author Vorontsov
 */
public class DivComponent extends InputComponent{

    public DivComponent(String id) {
        super(id);
    }

    protected List<WebComponent> children = new LinkedList<>();

    protected String renderStartBlock(){
        return "<div id=\""+getId()+"\" "+getHidden()+">";
    }

    protected String renderEndBlock(){
        return "</div>";
    }

    public DivComponent addChild(WebComponent component) {
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
