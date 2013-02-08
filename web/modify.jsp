<%-- 
    Document   : modify
    Created on : Oct 29, 2012, 3:09:14 PM
    Author     : vorontsov
--%>

<%@page import="org.wr.face.actions.ActionManager"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.wr.face.request.SpringContextParser"%>
<%@page import="org.wr.face.ErrorHelper"%>
<%@page import="org.neo4j.graphdb.Transaction"%>
<%@page import="org.wr.neo4j.core.Neo4jDBManager"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.wr.neo4j.handlers.GetByIdTransaction"%>
<%@page import="org.wr.neo4j.core.Neo4jTransactionExecutor"%>
<%@page import="org.neo4j.graphdb.Node"%>
<%@page import="org.wr.face.IdParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
          <script language='javascript'>

            function addProperty(){
                var table = document.getElementById('paramsTable');
                var rows = table.getElementsByTagName('tr');

                var index = rows.length;
                var newRow = document.createElement('tr');
                var cell1 = document.createElement('td');
                var cell2 = document.createElement('td');
                var cell3 = document.createElement('td');

                cell1.appendChild(document.createTextNode('Property'));

                inputMA = document.createElement('input');
                inputMA.setAttribute('name', 'keyname' + index);
                inputMA.setAttribute('size', '20');
                cell2.appendChild(inputMA);

                inputDN = document.createElement('input');
                inputDN.setAttribute('name', 'keyvalue' + index);
                inputDN.setAttribute('size', '30');
                cell3.appendChild(inputDN);


                newRow.appendChild(cell1);
                newRow.appendChild(cell2);
                newRow.appendChild(cell3);

                table.appendChild(newRow);
            }


          </script>

    </head>
    <body>
        <h1>Modify Node Action</h1>
        <%
        WebApplicationContext context = SpringContextParser.getSpringContext(request);
        String nodeid = request.getParameter("nodeid");
        Object retValue = null;
        Node node = null;
        String errorMessage="";
        try{
            retValue = //ConfigHolder.getInstance().getActionManager().performAction(request);
                       context.getBean(ActionManager.class).performAction(request);
            if(retValue instanceof Node){
                node = (Node)retValue;
                nodeid = String.valueOf(node.getId());
            }
        }catch(Throwable e ){
            errorMessage = ErrorHelper.getHtmlError(e);
        }
%>
        <form name="modifyForm">
            <input type="hidden" id="action" name="action"/>
            ID : <input type="text" id="nodeid" name="nodeid" value="<%= nodeid %>"/><input type="button" value="Add property" onclick="addProperty()"/>
            <table id="paramsTable">
<%
     
            if(null != node){
                int i = 0;
                for(String key : node.getPropertyKeys()){
                    Object value = node.getProperty(key);
                    %>
                    <tr>
                        <td>
                            Property
                        </td>
                        <td>
                            <input type="text" name="keyname<%=i%>" value="<%= key %>">
                        </td>
                        <td>
                            <input type="text" name="keyvalue<%=i%>" value="<%= value %>">
                        </td>
                    </tr>
                    <%
                    i++;
                }

            }else{
                out.print("<br> Node deleted sucessfully!");
            }
        

%>
            </table>
            <input type='submit' value='Submit'
                onclick="javascript:document.getElementById('action').setAttribute('value','submit');"/>
            <input type='submit' value='Find'
                onclick="javascript:document.getElementById('action').setAttribute('value','find');"/>
            <input type='submit' value='Delete'
                onclick="javascript:document.getElementById('action').setAttribute('value','delete');"/>
<%
           out.print(errorMessage);
%>
        </form>
    </body>
</html>
