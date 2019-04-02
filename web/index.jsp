
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>用户注册</title>
  </head>
  <body>
  <center>
    <form action="regist" method="post">
      用户名：<input type="email" name="email"/><br/>
      密&nbsp;码：<input type="password" name="upwd"/><br/>
      权 &nbsp;限：<select name="upur">
      <option value="10000">管理员</option>
      <option value="01000">普通管理员</option>
      <option value="00100">一般用户</option>
    </select><br/>
      <input type="submit" value="注册">
    </form>
  </center>
  </body>
</html>
