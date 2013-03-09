<%@page import="org.wr.face.common.components.view.SimpleTableComponent"%>
<%@page import="org.wr.face.common.components.view.TextWebComponent"%>
<%@page import="org.wr.neo4j.meta.model.AttributeBean"%>
<%@page import="org.wr.neo4j.meta.cache.services.AttributeService"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.wr.face.request.SpringContextParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%
         WebApplicationContext context = SpringContextParser.getSpringContext(request);
         long id = -1;
         String nodeid = request.getParameter("id");
         
         if(!StringUtils.isEmpty(nodeid)){
            id = Long.parseLong(nodeid); 
            AttributeService service = context.getBean(AttributeService.class);

            AttributeBean bean = service.getById(id);
   
        %>
        <h3>Attribute <b><%=bean.getName()%></b></h3>
        <div class="grid">
           <%
            out.print(new SimpleTableComponent()
                    .addChild(new TextWebComponent("Attribute Name", bean.getName()))
                    .addChild(new TextWebComponent("Public Name", bean.getPublicName())) 
                    .addChild(new TextWebComponent("Type", bean.getType().toString()))
                    .addChild(new TextWebComponent("Is Required", bean.isRequired()?"Yes":"No"))
                    .addChild(new TextWebComponent("Order", String.valueOf(bean.getOrder())))
                    .addChild(new TextWebComponent("MaxEntries", Integer.valueOf(bean.getMaxEntries()).toString()))
                    .render()
                    );
           
            
%>

        </div>
       <%
       }
%> 
    
