<%@page import="org.wr.face.common.page.CreateWebPageBuilder"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    CreateWebPageBuilder pageBuilder = new CreateWebPageBuilder(request, response, out);
    pageBuilder.initPage();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta charset="utf-8">

        <link href="css/modern.css" rel="stylesheet">
        <link href="css/modern-responsive.css" rel="stylesheet">
        <link href="css/site.css" rel="stylesheet" type="text/css">
        <link href="js/google-code-prettify/prettify.css" rel="stylesheet" type="text/css">


        <title>Wr Core main page</title>
    </head>
    <body class="modern-ui" onload="prettyPrint()">

        <div class="page">

        </div>
        <div class="page secondary">
            <div class="page-header">
                <div class="page-header-content">
                    <h1><%= pageBuilder.getPageBean() == null ? "not found" : pageBuilder.getPageBean().getName()%>
                        <small>
                            <jsp:include page="/widgets/common/navigationPath.jsp">
                                <jsp:param name="id" value="<%=pageBuilder.getNode().getId()%>" />
                            </jsp:include> 
                        </small></h1>
                    
                </div>
            </div>

            <div class="page-region">
                <div class="page-region-content">
                    <%
                        pageBuilder.renderWidgets();
                    %>



                </div>
            </div>
        </div>

        <div class="page">
            <div class="nav-bar">
                <div class="nav-bar-inner padding10">
                    <span class="element">
                        2013, WR Core &copy; by <a href="mailto:victor.vorontsov86@gmail.com">Victor Vorontsov</a>
                    </span>
                </div>
            </div>
        </div>
    </body>
</html>
