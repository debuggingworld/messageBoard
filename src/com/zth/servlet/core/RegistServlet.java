package com.zth.servlet.core;

import com.zth.pojo.Admin;

import javax.servlet.annotation.WebServlet;

@WebServlet("/regist")
public class RegistServlet extends ServletBase {
    @Override
    public void index(Mapping mapping) throws Exception {

        Admin admin = new Admin();
        mapping.getBean(admin); // 完成自动封装

        mapping.rander(admin.getId()+"");
        mapping.rander(admin.getEmail());
        mapping.rander(admin.getUpwd());
        mapping.rander(admin.getUpur());

    }
}
