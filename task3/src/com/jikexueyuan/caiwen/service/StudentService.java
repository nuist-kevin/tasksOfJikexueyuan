package com.jikexueyuan.caiwen.service;

import com.jikexueyuan.caiwen.db.ConnectionFactory;
import com.jikexueyuan.caiwen.domain.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiwen on 2016/6/11.
 */
public class StudentService {

    private Connection connection;
    private ResultSet result;

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        connection = ConnectionFactory.getInstance().getConnection();
        try {
            result = connection.createStatement().executeQuery(" select * FROM t_students; ");
            while (result.next()) {
                Student student = new Student();
                student.setId(result.getInt("stu_id"));
                student.setName(result.getString("stu_name"));
                student.setAge(result.getInt("stu_age"));
                student.setGender(result.getString("stu_gender"));
                student.setAddress(result.getString("stu_address"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void add10Students() {
        String sql = " insert into t_students (stu_id, stu_name, stu_age, stu_gender, stu_adress) values " + " ";
        connection = ConnectionFactory.getInstance().getConnection();
        try {
            result = connection.createStatement().executeQuery(" select * FROM t_students; ");
        } catch (SQLException e1) {
            e1.printStackTrace();

        }
    }
}
