
<%@page import="org.wr.face.common.components.view.attribute.AttributeTable"%>
<%@page import="org.wr.neo4j.meta.model.AttributeBean"%>
<%@page import="java.util.List"%>
<%@page import="org.wr.neo4j.meta.cache.MetaCacheController"%>
<%@page import="org.wr.neo4j.meta.cache.services.AttributeService"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.wr.face.SpringContextParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
         WebApplicationContext context = SpringContextParser.getSpringContext(request);
         long id = -1;
         String nodeid = request.getParameter("id");
         if(!StringUtils.isEmpty(nodeid)){
            id = Long.parseLong(nodeid); 
            MetaCacheController controller = context.getBean(MetaCacheController.class);
            //AttributeBean bean = service.getById(id);
            List<AttributeBean> attributes = null;
            if(id == controller.getMetaCacheService().getAttributeRoot().getId()){
               attributes = controller.getAttributeService().getAll(); 
            }
            if(null != controller.getObjectTypeService().getById(id) ){
               attributes = controller.getObjectTypeService().getById(id).getCurrentAttributes(); 
            }
            if(null != attributes){
                out.print(new AttributeTable(attributes).render());
            }
            
         }
        %>
        