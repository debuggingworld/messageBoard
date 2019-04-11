
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>走丢了</title>
    <%
        String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";

    %>
    <link rel="stylesheet" href="<%=path%>css/bootstrap.min.css" />
</head>
<body>
<div class="container ">
    <div>
        <div style="height: 50px;"></div>
        <div class="col-md-8 col-md-offset-2"  >
            <img src="<%=path%>res/404.jpg" style="max-width: 100%; height: auto;"/>
            <div style="height: 50px;"></div>
            <div>
                <a href="<%=path%>admin/msg" class="btn btn-success pull-right">返回</a>
            </div>
        </div>

    </div>
</div>
</body>
</html>
