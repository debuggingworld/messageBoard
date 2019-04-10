<%@ page import="com.zth.pojo.Msg" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/10 0010
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
    <title>留言详情</title>
</head>
<body>

<%
    Msg msg = (Msg)request.getAttribute("msg");
%>

<div style="height: 80px;"></div>
<div class="container">
    <div class="panel panel-info col-md-8 col-md-offset-2">
        <%
            if ( null != msg){
                %>
        <div class="panel-heading" style="text-align: center;"><%=msg.getTitle()%></div>
        <div class="panel-body" style="text-align: center;">
            <%=msg.getContent()%>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
