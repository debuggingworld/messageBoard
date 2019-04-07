package com.zth.servlet.core;

import com.zth.pojo.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/regist")
public class RegistServlet extends ServletBase {
    @Override
    public void index(Mapping mapping) throws ServletException, IOException {

        Admin admin = new Admin();
        try {
            mapping.getBean(admin); // 完成自动封装
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapping.rander(admin.getId()+"");
        mapping.rander(admin.getEmail());
        mapping.rander(admin.getUpwd());
        mapping.rander(admin.getUpur());

    }
}
