<%@page import="org.wr.face.common.components.view.navigation.NodePath"%>
<%@page import="org.wr.face.common.services.finders.NodePathFinder"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.wr.face.request.SpringContextParser"%>
<%@page import="org.wr.face.IdParser"%>
<%@page import="org.neo4j.graphdb.Node"%>
<%@page import="org.wr.neo4j.core.Neo4jDBManager"%>
<%@page import="org.wr.neo4j.meta.MetaType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    WebApplicationContext context = SpringContextParser.getSpringContext(request);
    long id = IdParser.parseId(request);
    Node node = null;

    if (-1 != id) {
        Neo4jDBManager manager = context.getBean(Neo4jDBManager.class);
        node = manager.getDbService().getNodeById(id);
        NodePathFinder finder = context.getBean(NodePathFinder.class);
        
        out.print(new NodePath(finder.find(node)).render());
    }
%>

