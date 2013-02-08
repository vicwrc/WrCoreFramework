/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page.handlers;


import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.neo4j.graphdb.Node;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.context.WebApplicationContext;
import org.wr.face.common.services.finders.NodeFinder;
import org.wr.face.common.services.finders.PageFinder;
import org.wr.face.request.SpringContextParser;
import org.wr.neo4j.meta.page.PageHandler;
import org.wr.neo4j.meta.model.page.RedirectPageBean;

/**
 *
 * @author vorontsov
 */
public class RedirectPageHandler implements PageHandler{

    
    
    @Override
    public void process(ServletRequest inputRequest, ServletResponse inputResponse) {
        HttpServletRequest request = (HttpServletRequest)inputRequest;
        HttpServletResponse response = (HttpServletResponse)inputResponse;
        WebApplicationContext context = SpringContextParser.getSpringContext(request);
        NodeFinder nodeFinder = context.getBean(NodeFinder.class);
        PageFinder pageFinder = context.getBean(PageFinder.class);
        Node node = nodeFinder.getNode(request);
        RedirectPageBean redirectPage = (RedirectPageBean) pageFinder.getPage(node, request);
        sendRedirect(response, node, redirectPage);
    }

    protected void sendRedirect(HttpServletResponse response, Node node, RedirectPageBean redirectPage) {
        try {
            response.sendRedirect(createRedirectUrl(node,redirectPage));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    protected String createRedirectUrl(Node node, RedirectPageBean redirectPage){
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(redirectPage.getPageToRedirect());
        return exp.getValue(node, String.class);
    }
    
}
