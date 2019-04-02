package com.zth.servlet.core;

import com.zth.pojo.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;

/**
 * 封装 servlet 的操作，一个 servlet 多用途
 */

public abstract class ServletBase extends HttpServlet {

    public abstract void index(Mapping mapping)throws Exception;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        // 获取参数 action
        String action = null!= req.getParameter("action")?req.getParameter("action"):"index";

        Class clazz = this.getClass();

        try {
            Method method = clazz.getDeclaredMethod(action, Mapping.class);
            if (null != method) {

                Mapping mapping = new Mapping(req,resp);
                method.invoke(this,mapping);
                mapping =null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取参数
     */
    public static String getString(HttpServletRequest request,String param){

        String res = null!= request.getParameter(param)?request.getParameter(param):"";
        return res;
    }

    /**
     * 内部类
     */
    public class Mapping{


        private HttpServletRequest request;
        private HttpServletResponse response;
        public Mapping(){}

        public Mapping(HttpServletRequest request,HttpServletResponse response){
            this.request = request;
            this.response = response;
        }

        public HttpServletRequest getRequest() {
            return request;
        }

        public void setRequest(HttpServletRequest request) {
            this.request = request;
        }

        public HttpServletResponse getResponse() {
            return response;
        }

        public void setResponse(HttpServletResponse response) {
            this.response = response;
        }

        /**
         * 获取参数
         */

        public String getString(String param) throws Exception{
            String res = null!= request.getParameter(param)?request.getParameter(param):"";
            return  res;
        }

        /**
         * 获取字符数组
         * @return 可能返回 null
         */
        public String[] getStringArray(String param) throws Exception{
            return request.getParameterValues(param);
        }

        /**
         * 直接获取 int 型数字
         */
        public int getInt(String param) throws Exception{
            int res = -1;
            if (this.getString(param).matches("\\d+")){
                res = Integer.parseInt(this.getString(param));
            }

            return res;
        }
        /**
         * 直接获取 long 型数字
         */
        public long getLong(String param) throws Exception{
            long res = -1;
            if (this.getString(param).matches("\\d+")){
                res = Long.parseLong(this.getString(param));
            }

            return res;
        }

        /**
         * 输出信息
         */
        public void rander(String msg) throws IOException {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<h1>"+msg+"</h1>");
            out.flush();
        }

        /**
         * 用请求参数的值，对应填充 java d对象
         * @param
         */
        public void getBean(Object bean) throws Exception{
            Class clazz = bean.getClass();
            Field[] all = clazz.getDeclaredFields();

            if (null!= all && all.length>0){

                for (Field field:all) {
                    field.setAccessible(true);
                    String fname = field.getName();  // 成员名
                    String readParam = this.getString(fname);

                    if (readParam.length()>0){
                        if (field.getType() == Integer.class || field.getType() == int.class){
                            field.set(bean,this.getInt(fname));
                        }else if (field.getType() == Long.class || field.getType() == long.class) {
                            field.set(bean,this.getLong(fname));
                        }else if (field.getType() == String.class){
                            field.set(bean,this.getString(fname));
                        }else if (field.getType() == Date.class){
                            field.set(bean,new Date());
                        }

                    }
                }
            }
        }

        public void setAttr(String key,String value)throws IOException, SQLException {
            request.setAttribute(key,value);
        }

        public void setSesstionAttr(String key,String value)throws IOException, SQLException{
            request.getSession().setAttribute(key,value);
        }

        public void forward(String path) throws IOException, SQLException, ServletException {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/"+path);
            rd.forward(request,response);
        }

        public void redirect(String path) throws ServletException,IOException{
            response.sendRedirect(path);
        }
    }
}


















