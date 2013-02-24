package org.wr.face.common.page;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import org.apache.commons.lang.StringUtils;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.springframework.web.context.WebApplicationContext;
import org.wr.face.common.services.finders.NodeFinder;
import org.wr.face.common.services.finders.PageFinder;
import org.wr.face.request.SpringContextParser;
import org.wr.neo4j.meta.model.PageBean;
import org.wr.neo4j.meta.model.WidgetBean;
import org.wr.neo4j.meta.page.PageHandler;
import org.wr.neo4j.meta.page.PageHandlerRepository;

/**
 *
 * @author vorontsov
 */
public class CommonWebPageBuilder {

    protected Node node = null;
    protected PageBean pageBean = null;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final JspWriter out;
    private final WebApplicationContext context;

    public CommonWebPageBuilder(HttpServletRequest request, HttpServletResponse response, JspWriter out) {
        this.request = request;
        this.response = response;
        this.out = out;
        context = SpringContextParser.getSpringContext(request);
        NodeFinder nodeFinder = context.getBean(NodeFinder.class);
        PageFinder pageFinder = context.getBean(PageFinder.class);
        node = nodeFinder.getNode(request);
        pageBean = pageFinder.getPage(node, request);
    }

    public void initPage() {
        if (pageBean != null) {
            PageHandlerRepository pageHandlerRepository = context.getBean(PageHandlerRepository.class);
            PageHandler handler = pageHandlerRepository.get(pageBean.getProcessClass());
            if (handler != null) {
                handler.process(request, response);
            }
            checkProcessable();
        }
    }

    protected void checkProcessable() {
        try {
            node.getProperty("name");
        } catch (NotFoundException e) {
            node = null;
            pageBean = null;
        }
    }

    public Node getNode() {
        return node;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void renderWidgets() throws IOException, ServletException {
        if (null != pageBean) {
            for (WidgetBean widget : pageBean.getWidgets()) {
                org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response,
                        widget.getUrl() + "?id=" + node.getId() + (StringUtils.isEmpty(pageBean.getExtraParams()) ? "" : "&" + pageBean.getExtraParams()), out, true);
            }
        }
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public JspWriter getOut() {
        return out;
    }

    public WebApplicationContext getContext() {
        return context;
    }
}
