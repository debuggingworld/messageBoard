package com.zth.servlet;

import com.zth.db.Db;
import com.zth.pojo.Admin;
import com.zth.servlet.core.ServletBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/regist")
public class RegistServlet extends ServletBase {
    @Override
    public void index(Mapping mapping) throws Exception {
        mapping.forward("page/regist.jsp");
    }
    public void regist(Mapping mapping)throws ServletException, IOException{

        Admin admin = new Admin();
        try {
            mapping.getBean(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        admin.setUpur("01000");

        String sql = "insert into admin(email,upwd,upur,name,pic) values(?,?,?,?,?)";

        try {
            Db.update(sql,admin.getEmail(),admin.getUpwd(),admin.getUpur(),admin.getName(),admin.getPic());

            mapping.setAttr("msg","注册成功");

        } catch (SQLException e) {
            try {
                mapping.setAttr("msg","注册成功");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        mapping.redirect("login");
    }
}
