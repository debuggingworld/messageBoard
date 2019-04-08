<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <!-- 全局js -->
    <script src="../../js/jquery.min.js?v=2.1.4"></script>
    <script src="../../js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <%--<script src="../js/content.js?v=1.0.0"></script>--%>

    <script type="text/javascript" src="../../js/sweetalert.min.js"></script>
   
   <%
     String msg=(String)request.getAttribute("msg");
     String err=(String)request.getAttribute("err");
     if(null!=msg)
     {
    	 out.println(" <script type=\"text/javascript\">swal('"+msg+"!','','success'); </script>");
    	
     }else if(null!=err)
     {
    	 out.println(" <script type=\"text/javascript\">swal('"+err+"!','','error'); </script>");
     }
   %>
