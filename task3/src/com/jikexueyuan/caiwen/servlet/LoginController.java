package com.jikexueyuan.caiwen.servlet;

import com.jikexueyuan.caiwen.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by caiwen on 2016/6/10.
 */
public class LoginController extends HttpServlet{
    private User loginUser;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("username").length() > 0 && req.getParameter("password").length() > 0) {
            loginUser = new User();
            loginUser.setUsername(req.getParameter("username"));
            loginUser.setPassword(req.getParameter("password"));
            req.getSession().setAttribute("loginUser", loginUser);
            resp.sendRedirect("main.jsp");
        } else {
            req.setAttribute("errMsg","请输入用户名和密码");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }
}
