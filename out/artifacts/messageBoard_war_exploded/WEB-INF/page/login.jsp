<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <style type="text/css">
        #head{
            height: 100px;
        }
    </style>
</head>
<body>
<div id="head"></div>
<div class="container">
    <div class="row">
        <div class=" col-xs-10 col-xs-offset-1 col-sm-offset-2 col-md-6 col-md-offset-3" >
            <div class="panel panel-primary" >
                <div class="panel-heading">
                    <h3 class="panel-title">用户登录</h3>
                </div>
                <div class="panel-body">
                    <!--表单 -->
                    <form action="login" method="post">
                        <input type="hidden" name="action" value="checkLogin"/>
                        <div class="form-group">
                            <label>邮箱：</label>
                            <input type="email" class="form-control" placeholder="Email" name="email" required>
                        </div>
                        <div class="form-group">
                            <label >密码：</label>
                            <input type="password" name="upwd" class="form-control" required placeholder="Password">
                        </div>
                        <label >验证码：</label>
                        <div class="form-group">

                            <input type="text" name="rand" class="form-control pull-left" required  style="width: 80px;" >
                            <img src="randomImage" onclick="this.src = 'randomImage?aaa = '+Math.random()" style="width: 100px; cursor:pointer;"/>
                        </div>
                        <button type="submit" class="btn btn-primary" style="margin-right: 30px;">登录</button>
                        <a href="regist" class="btn btn-danger">注册</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<%@include file="booter.jsp" %>--%>
<%@include file="booter.jsp" %>

</body>
</html>

