package com.zth.servlet;

import com.zth.servlet.core.ServletBase;

import javax.servlet.annotation.WebServlet;

@WebServlet("/logout")
public class LogoutServlet extends ServletBase {
    @Override
    public void index(Mapping mapping) throws Exception {

        mapping.getRequest().getSession().invalidate();

        mapping.getResponse().sendRedirect("login");
        //mapping.forward("login");
    }
}
