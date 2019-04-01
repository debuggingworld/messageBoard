package com.zth.servlet.core;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends ServletBase {
    @Override
    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.rander(resp,"index 方法");
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.rander(resp,"add 方法");
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.rander(resp,"delete 方法");
}

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.rander(resp,"update 方法");
    }

}
