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
public class StudentController extends HttpServlet {

    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String action = req.getParameter("action");
        List<Student> students;
        if (action == null) {
            students= studentService.getAllStudents();
            req.setAttribute("students", students);
            req.getRequestDispatcher("student/list.jsp").forward(req, resp);
        } else
            if ("get".equals(action)) {
                String startAge = req.getParameter("startAge");
                students = studentService.getStudents(startAge);
                req.setAttribute("students", students);
                req.getRequestDispatcher("student/list.jsp").forward(req, resp);
            } else
                if ("delete".equals(action))
                {
                    String gender = req.getParameter("gender");
                    studentService.deleteAllMales(gender);
                } else
                    if ("add".equals(action))
                    {
                        studentService.add10Students();
                    }

    }


}
