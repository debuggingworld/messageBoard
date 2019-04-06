package com.zth.servlet.core;

import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginServlet extends ServletBase {
    @Override
    public void index(Mapping mapping) throws Exception {
        mapping.forward("pages/login.jsp");
    }
}
