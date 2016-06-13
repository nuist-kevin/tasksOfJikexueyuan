package com.jikexueyuan.caiwen.service;

import com.jikexueyuan.caiwen.db.ConnectionFactory;
import com.jikexueyuan.caiwen.domain.Student;

import java.sql.*;
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
        connection = ConnectionFactory.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate(" insert into t_students " +
                    " (stu_id, stu_name, stu_age, stu_gender,stu_address) values " +
                    " (1, '马云', 16, 'M', '浙江'); ");
            statement.executeUpdate(" insert into t_students " +
                    " (stu_id, stu_name, stu_age, stu_gender,stu_address) values " +
                    " (2, '马化腾', 15, 'M', '广东'); ");
            statement.executeUpdate(" insert into t_students " +
                    " (stu_id, stu_name, stu_age, stu_gender,stu_address) values " +
                    " (3, '李彦宏', 14, 'M', '山西'); ");
            statement.executeUpdate(" insert into t_students " +
                    " (stu_id, stu_name, stu_age, stu_gender,stu_address) values " +
                    " (4, '刘强东', 18, 'M', '江苏'); ");
            statement.executeUpdate(" insert into t_students " +
                    " (stu_id, stu_name, stu_age, stu_gender,stu_address) values " +
                    " (5, '孙亚芳', 14, 'F', '武汉'); ");
            statement.executeUpdate(" insert into t_students " +
                    " (stu_id, stu_name, stu_age, stu_gender,stu_address) values " +
                    " (6, '罗永浩', 19, 'M', '吉林'); ");
            statement.executeUpdate(" insert into t_students " +
                    " (stu_id, stu_name, stu_age, stu_gender,stu_address) values " +
                    " (7, '贾跃亭', 14, 'M', '山西'); ");
            statement.executeUpdate(" insert into t_students " +
                    " (stu_id, stu_name, stu_age, stu_gender,stu_address) values " +
                    " (8, '任正非', 20, 'M', '贵州'); ");
            statement.executeUpdate(" insert into t_students " +
                    " (stu_id, stu_name, stu_age, stu_gender,stu_address) values " +
                    " (9, '雷军', 15, 'M', '湖北'); ");
            statement.executeUpdate(" insert into t_students " +
                    " (stu_id, stu_name, stu_age, stu_gender,stu_address) values " +
                    "(10, '董明珠', 16, 'F', '江苏'); ");
            connection.commit();
        } catch (Exception e1) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            e1.printStackTrace();
        }
    }

    public void deleteAllMales(String gender) {

        connection = ConnectionFactory.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(" delete FROM t_students WHERE stu_gender = ? ");
            preparedStatement.setString(1, gender);
            preparedStatement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
