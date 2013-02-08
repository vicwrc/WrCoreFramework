
<%@page import="org.wr.face.common.components.view.ChildCloud"%>
<%@page import="org.wr.neo4j.core.operations.CommonNodeProvider"%>
<%@page import="org.wr.neo4j.core.Neo4jDBManager"%>
<%@page import="org.wr.face.IdParser"%>
<%@page import="java.util.List"%>
<%@page import="org.neo4j.graphdb.Node"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.wr.face.request.SpringContextParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    WebApplicationContext context = SpringContextParser.getSpringContext(request);
    long id = IdParser.parseId(request);
    Node node = null;
    

    if (-1 != id) {
        Neo4jDBManager manager = context.getBean(Neo4jDBManager.class);
        CommonNodeProvider nodeProvider = context.getBean(CommonNodeProvider.class);
        node = manager.getDbService().getNodeById(id);
        List<Node> children = nodeProvider.getChildren(node);
        out.print(new ChildCloud(children).render());
    }
%>
