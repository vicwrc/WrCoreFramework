
<%@page import="org.wr.neo4j.meta.model.attribute.ReferenceAttribute"%>
<%@page import="org.wr.neo4j.meta.model.attribute.ListAttribute"%>
<%@page import="org.wr.face.common.widgets.meta.ot.ObjectTypeSelectList"%>
<%@page import="org.wr.face.common.components.edit.input.multiple.MultipleTextInput"%>
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
    if (!StringUtils.isEmpty(nodeid)) {
        id = Long.parseLong(nodeid);
        AttributeService service = context.getBean(AttributeService.class);

        bean = service.getById(id);
    }
    if (null == bean) {
        bean = new AttributeBean(-1);
    }

    Form form = new Form();
    form.addChild(new SimpleTableComponent()
            .addChild(new NestedTableRow("Attribute name").addChild(new SingleTextComponent("name").setDefaultValue(bean.getName())))
            .addChild(new NestedTableRow("Public name").addChild(new SingleTextComponent("publicName").setDefaultValue(bean.getPublicName())))
            .addChild(new NestedTableRow("Order").addChild(
            new SingleTextComponent("order").setDefaultValue(String.valueOf(bean.getOrder())).setType(Html5InputType.NUMBER)))
            .addChild(new NestedTableRow("Max Entries").addChild(
            new SingleTextComponent("maxEntries").setDefaultValue(String.valueOf(bean.getMaxEntries())).setType(Html5InputType.NUMBER)))
            .addChild(new NestedTableRow("is Required").
            addChild(new SimpleListComponent(new String[]{"Yes", "No"}, "isRequred").setDeafult(bean.isRequired() ? "Yes" : "No")))
            .addChild(new NestedTableRow("Type")
            .addChild(new SimpleListComponent(AttributeType.values(), "type"){
            
        protected String putAttributes(){
            return " onChange=\"new TypeChangeAction(this).apply();\"";            
        }

    }.setDeafult(bean.getType().toString())))
            .addChild(new NestedTableRow("Additional Parameters").
            addChild(new MultipleTextInput(bean.getAdditionalParameters(), "additionalParameters")))
            .addChild(new NestedTableRow("Reference To")
            .setHidden(!(AttributeType.CHILD.equals(bean.getType()) || AttributeType.REFERENCE.equals(bean.getType())))
            .setId("referenceToTag")
            .addChild(new ObjectTypeSelectList(
            AttributeType.CHILD.equals(bean.getType()) || AttributeType.REFERENCE.equals(bean.getType())
            ? ((ReferenceAttribute) bean).getReferenceTo() : "", "referenceTo").setRequest(request)))
            .addChild(new NestedTableRow("List Values")
            .setHidden(!AttributeType.LIST.equals(bean.getType()))
            .setId("listValuesTag")
            .addChild(new MultipleTextInput(AttributeType.LIST.equals(bean.getType()) ? ((ListAttribute) bean).getValues() : null, "listValues"))))
            .addChild(new HiddenField("id", String.valueOf(bean.getId())))
            .addChild(new HiddenField("parentId", parentId))
            .addChild(new HiddenField("objectType", objectType))
            .addChild(new HiddenField("action", "edit".equals(action) ? "submit" : "create"));


    out.print(form.render());

%>

<script language="javascript">
            
    function TypeChangeAction(typeComp){
        this.typeComp = typeComp;
        this.value = typeComp.value;
        this.referenceToRow = document.getElementById('referenceToTag');
        this.listValuesRow = document.getElementById('listValuesTag');
    }
       
    TypeChangeAction.prototype = {
                
        apply : function() {
            if(this.value.toString() == 'REFERENCE'.toString() || 
                this.value.toString() == 'CHILD'.toString() ) {
                this.setVisibility(this.referenceToRow, null);
                this.setVisibility(this.listValuesRow, 'none');
                return;
            } 
            if(this.value.toString() == 'LIST'.toString()  ) {
                this.setVisibility(this.referenceToRow, 'none');
                this.setVisibility(this.listValuesRow, null);
                return;
            } 
            this.setVisibility(this.referenceToRow, 'none');
                this.setVisibility(this.listValuesRow, 'none');
        },
        
        setVisibility: function(component, value) {
            component.style.display = value;            
        }
        
    }    
       
            
            
</script>