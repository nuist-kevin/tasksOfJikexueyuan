package com.jikexueyuan.caiwen.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by caiwen on 2016/6/12.
 */
public class LogoutServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loginUser") == null) {
            req.setAttribute("errMsg","您尚未登录，无需登出");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        } else {
            req.getSession().removeAttribute("loginUser");
            resp.sendRedirect("login.jsp");
        }
    }
}
