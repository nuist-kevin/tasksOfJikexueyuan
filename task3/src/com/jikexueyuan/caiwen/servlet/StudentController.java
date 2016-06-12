package com.jikexueyuan.caiwen.servlet;

import com.jikexueyuan.caiwen.domain.Student;
import com.jikexueyuan.caiwen.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by caiwen on 2016/6/11.
 */
public class StudentController extends HttpServlet{

    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().endsWith("list")) {
            List<Student> students = studentService.getAllStudents();
            req.setAttribute("students", students);
            req.getRequestDispatcher("/jsp/student/list.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
