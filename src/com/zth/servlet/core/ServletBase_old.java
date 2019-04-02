package com.zth.servlet.core;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 封装对 servlet 的操作，一个servlet 多用途
 */
public abstract class ServletBase_old extends HttpServlet {

    public abstract void index(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException;


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Class[] params = new Class[]{HttpServletRequest.class,HttpServletResponse.class};
        Object[] realparams = new Object[]{req,resp};

        // 读一个请求参数 action
        String action = this.getString(req,"action");
        if ("".equals(action)){
            action = "index";
        }

        Class clazz = this.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(action,params);
            if (null!=method){
                method.invoke(this,realparams);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取参数
     */
    public String getString (HttpServletRequest request, String param){
        String re = null!= request.getParameter(param)?request.getParameter(param):"";
        return  re;
    }

    /**
     * 输出信息
     */
    public void rander(HttpServletResponse response,String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>"+msg+"</h1>");
        out.close();
    }
}
