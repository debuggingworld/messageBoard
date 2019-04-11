package com.zth.filter;


import com.zth.pojo.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证用户登录状态，过滤非法登录
 */
@WebFilter("/admin/*")
public class CheckLoginFilter  implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        HttpSession session = request.getSession();

        Admin admin = (Admin)session.getAttribute("loged");
        if (null != admin){
            filterChain.doFilter(request,response);
        }else {
            request.setAttribute("err","请先登录");

            response.sendRedirect("../login");
            ///request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request,response);

        }
    }
}
