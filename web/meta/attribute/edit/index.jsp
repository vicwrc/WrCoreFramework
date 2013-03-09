
<%@page import="org.wr.face.common.components.edit.input.SimpleListComponent"%>
<%@page import="org.wr.face.common.components.edit.input.SingleTextComponent"%>
<%@page import="org.wr.face.common.components.edit.HiddenField"%>
<%@page import="org.wr.face.common.components.edit.Html5InputType"%>
<%@page import="org.wr.neo4j.meta.attribute.AttributeType"%>
<%@page import="org.wr.face.common.components.view.NestedTableRow"%>
<%@page import="org.wr.face.common.components.view.SimpleTableComponent"%>
<%@page import="org.wr.face.common.components.edit.Form"%>
<%@page import="org.wr.neo4j.meta.model.BaseBean"%>
<%@page import="org.wr.neo4j.meta.model.AttributeBean"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.wr.neo4j.meta.cache.services.AttributeService"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.wr.face.request.SpringContextParser"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
         WebApplicationContext context = SpringContextParser.getSpringContext(request);
         long id = -1;
         String nodeid = request.getParameter("id");
         String parentId = request.getParameter("parentId");
         String objectType = "ATTRIBUTE";
         String action = request.getParameter("action");
         AttributeBean bean = null;
         if(!StringUtils.isEmpty(nodeid)){
            id = Long.parseLong(nodeid); 
            AttributeService service = context.getBean(AttributeService.class);

            bean = service.getById(id);
         }
         if(null == bean){
             bean = new AttributeBean(-1);             
         }
         
         out.print(new Form()
                 .addChild(new SimpleTableComponent()
                 .addChild(new NestedTableRow("Attribute name").addChild(new SingleTextComponent("name").setDefaultValue(bean.getName())))
                 .addChild(new NestedTableRow("Public name").addChild(new SingleTextComponent("publicName").setDefaultValue(bean.getPublicName())))
                 
                 .addChild(new NestedTableRow("Order").addChild(
                           new SingleTextComponent("order").setDefaultValue(String.valueOf(bean.getOrder())).setType(Html5InputType.NUMBER)
                 ))
                 .addChild(new NestedTableRow("Max Entries").addChild(
                           new SingleTextComponent("maxEntries").setDefaultValue(String.valueOf(bean.getMaxEntries())).setType(Html5InputType.NUMBER)
                 ))
                 .addChild(new NestedTableRow("is Required").
                           addChild(new SimpleListComponent(new String[]{"Yes","No"}, "isRequred").setDeafult(bean.isRequired()?"Yes":"No")))
                 .addChild(new NestedTableRow("Type")
                          .addChild(new SimpleListComponent(AttributeType.values(),"type").setDeafult(bean.getType().toString()))
                 )
                 ).addChild(new HiddenField("id", String.valueOf(bean.getId())))
                 .addChild(new HiddenField("parentId", parentId))
                 .addChild(new HiddenField("objectType", objectType))
                 .addChild(new HiddenField("action", "edit".equals(action)?"submit":"create"))
                 .render());
         
        %>