<%@ page import="com.zth.pojo.Admin" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zth.pojo.Msg" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>留言板</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
</head>
<body>
<div style="height: 50px;"></div>
<div class="container">
    <div class="panel panel-default ">
        <div class="panel-heading ">
            <h3 class="panel-title ">
                <a href="#" class="btn btn-danger">首页</a>
                <a href="admin/msg?action=msg_add" class="btn btn-success">发布留言</a>
                <div style="float: right">
                    <%
                        Admin admin = (Admin)session.getAttribute("loged");
                        out.println(admin.getEmail());
                        out.println("<img src='../ups/"+admin.getPic()+"'  height=30");
                    %>
                </div>
            </h3>
        </div>
        <!-- 以下是表单 -->
        <table class="table table-hover table-striped table-responsive">
            <tr><th>序号</th><th>标题</th><th>作者</th><th>时间</th></tr>
            <%
                List<Msg> msgs = ( List<Msg>)request.getAttribute("allmsg");

                if (null != msgs && msgs.size()>0){
                    int index = 1;
                    for (Msg msg:msgs) {
                        %>

            <tr><td>01</td><td>这是标题</td><td>作者</td><td>2019-4-7 09:47</td></tr>
                        <%
                    }
                }
            %>


            <tr><td>01</td><td>这是标题</td><td>作者</td><td>2019-4-7 09:47</td></tr>
            <tr><td>01</td><td>这是标题</td><td>作者</td><td>2019-4-7 09:47</td></tr>
            <tr><td>01</td><td>这是标题</td><td>作者</td><td>2019-4-7 09:47</td></tr>
            <tr><td>01</td><td>这是标题</td><td>作者</td><td>2019-4-7 09:47</td></tr>
            <tr><td>01</td><td>这是标题</td><td>作者</td><td>2019-4-7 09:47</td></tr>
        </table>


        <div>
            <div class="pull-left">
                <nav >
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>

            <div class="pull-right ">
                当前1页，总共5条
            </div>
        </div>

        <!-- 结束 -->

    </div>
</div>
<%@include file="booter2.jsp" %>
</body>
</html>

