package com.zth.servlet.core;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet("/hello")
public class HelloServlet extends ServletBase {
    @Override
    public void index(Mapping mapping) throws ServletException, IOException {
        mapping.rander("index 方法");
    }

    public void add(Mapping mapping) throws ServletException, IOException {
        mapping.rander("add 方法");
    }

    public void delete(Mapping mapping) throws ServletException, IOException {
        mapping.rander("delete 方法");
    }

    public void update(Mapping mapping) throws ServletException, IOException {
        mapping.rander("update 方法");
}

}
