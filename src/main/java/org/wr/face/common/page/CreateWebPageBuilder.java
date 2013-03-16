/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import org.apache.commons.lang.StringUtils;
import org.wr.face.common.services.finders.NodeFinder;
import org.wr.face.common.services.finders.PageFinder;
import org.wr.neo4j.meta.model.WidgetBean;

/**
 *
 * @author vorontsov
 */
public class CreateWebPageBuilder extends CommonWebPageBuilder {

    public CreateWebPageBuilder(HttpServletRequest request, HttpServletResponse response, JspWriter out) {
        super(request, response, out);
        NodeFinder nodeFinder = getContext().getBean(NodeFinder.class);
        PageFinder pageFinder = getContext().getBean(PageFinder.class);
        node = nodeFinder.getParentNode(request);
        pageBean = pageFinder.getCreatePage(request);
    }
    
    public void renderWidgets() throws IOException, ServletException{
        if (null != pageBean) {
            for (WidgetBean widget : pageBean.getWidgets()) {
                org.apache.jasper.runtime.JspRuntimeLibrary.include(getRequest(), getResponse(),
                        widget.getUrl() + "?parentId=" + node.getId() + (StringUtils.isEmpty(pageBean.getExtraParams()) ? "" : "&" + pageBean.getExtraParams()), getOut(), true);
            }
        }
    }

    
}
