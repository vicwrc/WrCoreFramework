<%-- 
    Document   : query
    Created on : 07.11.2012, 19:01:58
    Author     : vicwrc
--%>

<%@page import="org.neo4j.graphdb.Transaction"%>
<%@page import="org.wr.neo4j.core.Neo4jDBManager"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.IOException"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.neo4j.cypher.javacompat.ExecutionEngine"%>
<%@page import="org.neo4j.cypher.javacompat.ExecutionResult"%>
<%@page import="org.wr.face.SpringContextParser"%>
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
            <textarea rows="25" cols="120"  name="query"><%= null == request.getParameter("query") ? "" : request.getParameter("query")%></textarea>
            <br><input type="submit" value="execute"/>
        </form>
        <%            
            QueryRunner runner = new QueryRunner(request.getParameter("query"), out, SpringContextParser.getSpringContext(request));
            runner.printResult();
        %>    
    </body>
</html>
<%!    
    public class QueryRunner {
        
        private String query;
        private JspWriter out;
        private WebApplicationContext context;
        
        public QueryRunner(String query,
                JspWriter out,
                WebApplicationContext context) {
            this.query = query;
            this.out = out;
            this.context = context;
        }
        
        public void printResult() throws IOException {
            if (StringUtils.isEmpty(query)) {
                return;
            }
            Neo4jDBManager manager = context.getBean(Neo4jDBManager.class);
            Transaction tx = manager.createTransaction();
            try {
                
                
                ExecutionResult result = ((ExecutionEngine) context.getBean("executionEngine")).execute(query);
                out.print("<table border=1 width=\"100%\">");
                out.print("<thead>");
                out.print("<tr>");
                for (String colName : result.columns()) {
                    out.print("<td>" + colName + "</td>");
                }
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");
                for (Map<String, Object> row : result) {
                    out.print("<tr>");
                    for (Entry<String, Object> column : row.entrySet()) {
                        out.print("<td>" + column.getValue() + "</td> ");
                    }
                    out.print("</tr>");
                }
                out.print("</tbody>");
                out.print("</table>");
                tx.success();
            } catch (Exception e) {
                out.print(e.getMessage());
            } finally {
                tx.finish();
            }
        }
    }
    
%>
