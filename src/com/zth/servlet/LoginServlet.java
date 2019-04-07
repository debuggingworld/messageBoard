package com.zth.servlet;

import com.zth.db.Db;
import com.zth.pojo.Admin;
import com.zth.servlet.core.ServletBase;
import com.zth.utils.Md5Encrypt;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.annotation.WebServlet;


@WebServlet("/login")
public class LoginServlet extends ServletBase {
    @Override
    public void index(Mapping mapping) throws Exception {

    }

    public void checkLogin(Mapping mapping) throws Exception {
        String srand = (String)mapping.getRequest().getSession().getAttribute("randomCode");

        String rand = mapping.getString("rand");

        if (rand.equals(srand)) {
            String email = mapping.getString("email");
            String pwd = mapping.getString("upwd");

            String sql = "select * from admin where email = ? and upwd = ?";

            Admin admin = Db.query(sql,new BeanHandler<Admin>(Admin.class),email, Md5Encrypt.md5(pwd));

            if (null != admin){
                mapping.setSesstionAttr("loged",admin);
                mapping.setAttr("msg","登录成功");
                mapping.forward("page/main.jsp");
            }else {
                mapping.setAttr("err","用户名或密码不正确！");
                mapping.forward("page/login.jsp");
            }

        }else {
            mapping.setAttr("err","验证码不正确！");
            mapping.forward("page/login.jsp");
        }
    }

}
