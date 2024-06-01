package com.javafx_database.model.DAO;

import com.javafx_database.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO implements IDataAccessObject<Teacher> {
    private DATABASE1 database = DATABASE1.getDatabase();
    private Connection connection = database.getConnection();


    @Override
    public Teacher getById(int id) {
        Teacher teacher = new Teacher();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from teacher where teacher_id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                teacher.setTeacher_name(resultSet.getString("teacher_name"));
                teacher.setTeacher_id(resultSet.getInt("teacher_id"));
                teacher.setTeacher_age(resultSet.getInt("teacher_age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teacher;
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> teacherList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from teacher");
            resultSet = preparedStatement.executeQuery();
            Teacher teacher;
            while (resultSet.next()){
                teacher = new Teacher();
                teacher.setTeacher_id(resultSet.getInt(1));
                teacher.setTeacher_name(resultSet.getString(2));
                teacher.setTeacher_age(resultSet.getInt(3));
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherList;

    }

    @Override
    public boolean add(Teacher teacher) {
        PreparedStatement preparedStatement;
        int sonuc;
        try {
            preparedStatement = connection.prepareStatement("insert into teacher( teacher_name, teacher_age) values (?,?)");
            preparedStatement.setString(1 , teacher.getTeacher_name());
            preparedStatement.setInt(2,teacher.getTeacher_age());
            sonuc = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (sonuc>0)
            return true;
        return false;


    }

    @Override
    public boolean delete(Teacher teacher) {
        PreparedStatement preparedStatement;
        int sonuc;
        try {
            preparedStatement = connection.prepareStatement("delete from teacher where teacher_id=?");
            preparedStatement.setInt(1,teacher.getTeacher_id());
            sonuc= preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (sonuc>0)
            return true;
        return false;
    }

}
