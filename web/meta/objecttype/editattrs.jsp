<%@page import="org.wr.face.common.components.edit.input.SingleTextComponent"%>
<%@page import="org.wr.face.common.components.edit.HiddenField"%>
<%@page import="org.wr.face.common.components.edit.Html5InputType"%>
<%@page import="org.wr.face.common.components.view.NestedTableRow"%>
<%@page import="org.wr.face.common.components.view.SimpleTableComponent"%>
<%@page import="org.wr.face.common.components.edit.Form"%>
<%@page import="org.wr.neo4j.meta.model.ObjectTypeBean"%>
<%@page import="org.wr.neo4j.meta.cache.services.ObjectTypeService"%>
<%@page import="org.wr.face.request.SpringContextParser"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

         WebApplicationContext context = SpringContextParser.getSpringContext(request);
         long id = -1;
         ObjectTypeBean bean = null;
         String nodeid = request.getParameter("id");
         String parentId = request.getParameter("parentId");
         String objectType = "OBJECT_TYPE";
         String action = request.getParameter("action");
         
         if(!StringUtils.isEmpty(nodeid)){
            id = Long.parseLong(nodeid); 
            ObjectTypeService service = context.getBean(ObjectTypeService.class);
            bean = service.getById(id);
         } 
         if(null == bean) {
            bean = new ObjectTypeBean(-1);
         }
         
         out.print(new Form()
                 .addChild(new SimpleTableComponent()
                 .addChild(new NestedTableRow("Object Type name").addChild(new SingleTextComponent("name").setDefaultValue(bean.getName())))
                 .addChild(new NestedTableRow("Order").addChild(
                           new SingleTextComponent("order").setDefaultValue(String.valueOf(bean.getOrder())).setType(Html5InputType.NUMBER)
                 ))
                 
                 ).addChild(new HiddenField("id", String.valueOf(bean.getId())))
                 .addChild(new HiddenField("parentId", parentId))
                 .addChild(new HiddenField("objectType", objectType))
                 .addChild(new HiddenField("action", "edit".equals(action)?"submit":"create"))
                 .render());
        %>