<%-- 
    Document   : query
    Created on : 07.11.2012, 19:01:58
    Author     : vicwrc
--%>

<%@page import="java.io.IOException"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.neo4j.cypher.javacompat.ExecutionEngine"%>
<%@page import="org.neo4j.cypher.javacompat.ExecutionResult"%>
<%@page import="org.wr.face.request.SpringContextParser"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Neo4j Query Runner</h3>
        
        <form method="post">
            <textarea name="query"><%= null==request.getParameter("query")?"":request.getParameter("query") %></textarea>
            <br><input type="submit" value="execute"/>
        </form>
        <%
        QueryRunner runner = new QueryRunner(request.getParameter("query"), out,SpringContextParser.getSpringContext(request));
        runner.printResult();
%>    
    </body>
</html>
<%!

public class QueryRunner{
    
    private String query;
    private JspWriter out;
    private WebApplicationContext context;
    
    public QueryRunner(String query,
                       JspWriter out, 
                       WebApplicationContext context){
        this.query = query;
        this.out = out;
        this.context = context;
    }

    public void printResult() throws IOException{
        if(StringUtils.isEmpty(query)){
            return;
        }
        ExecutionResult result = ((ExecutionEngine)context.getBean("executionEngine")).execute(query);
        out.print("<table border=1 width=\"100%\">");
        out.print("</table>");
    }
    
}    

%>
