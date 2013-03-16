
<%@page import="org.wr.face.common.components.view.tiles.Tiles"%>
<%@page import="org.neo4j.graphdb.Node"%>
<%@page import="org.wr.neo4j.core.Neo4jDBManager"%>
<%@page import="org.wr.neo4j.meta.model.PageBean"%>
<%@page import="java.util.List"%>
<%@page import="org.wr.neo4j.meta.cache.MetaCacheController"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.wr.face.SpringContextParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
         WebApplicationContext context = SpringContextParser.getSpringContext(request);
         long id = -1;
         String nodeid = request.getParameter("id");
         if(!StringUtils.isEmpty(nodeid)){
            id = Long.parseLong(nodeid); 
            MetaCacheController controller = context.getBean(MetaCacheController.class);
            Neo4jDBManager manager = context.getBean(Neo4jDBManager.class);
            Node node = manager.getDbService().getNodeById(id);
            
            List<PageBean> tiles = controller.getPageService().getPages(node, null);
            
            out.print(new Tiles(tiles,node).render());        
            
         }
        %>
