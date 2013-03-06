
<%@page import="org.wr.face.common.widgets.common.ViewObjectWidget"%>
<%@page import="org.wr.neo4j.core.operations.CommonNodeProvider"%>
<%@page import="org.wr.neo4j.core.Neo4jDBManager"%>
<%@page import="org.neo4j.graphdb.Node"%>
<%@page import="org.wr.neo4j.meta.model.ObjectTypeBean"%>
<%@page import="org.wr.neo4j.meta.cache.services.ObjectTypeService"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.wr.face.request.SpringContextParser"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%    
    
    WebApplicationContext context = SpringContextParser.getSpringContext(request);
    long id = -1;
    ObjectTypeService service = context.getBean(ObjectTypeService.class);
    ObjectTypeBean bean = null;
    String nodeid = request.getParameter("id");
    
    if (!StringUtils.isEmpty(nodeid)) {
        id = Long.parseLong(nodeid);        
        Node node = context.getBean(Neo4jDBManager.class).getDbService().getNodeById(id);
        bean = service.getById(context.getBean(CommonNodeProvider.class).getObjectType(node).getId());
        out.print(new ViewObjectWidget(bean, node).render());
    }  
    
    
    
%>