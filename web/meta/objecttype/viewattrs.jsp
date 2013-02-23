
<%@page import="org.wr.neo4j.meta.model.ObjectTypeBean"%>
<%@page import="org.wr.neo4j.meta.cache.services.ObjectTypeService"%>
<%@page import="org.wr.face.common.components.view.TextWebComponent"%>
<%@page import="org.wr.face.common.components.view.SimpleTableComponent"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.wr.face.request.SpringContextParser"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
         WebApplicationContext context = SpringContextParser.getSpringContext(request);
         long id = -1;
         String nodeid = request.getParameter("id");
         
         if(!StringUtils.isEmpty(nodeid)){
            id = Long.parseLong(nodeid); 
            ObjectTypeService service = context.getBean(ObjectTypeService.class);
            ObjectTypeBean bean = service.getById(id);
            
             out.print(new SimpleTableComponent()
                    .addChild(new TextWebComponent("Attribute Name", bean.getName()))
                    .addChild(new TextWebComponent("Order", String.valueOf(bean.getOrder())))
                    .render()
                    );
               }
        %>