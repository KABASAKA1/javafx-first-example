package com.javafx_database.model.DAO;

import com.javafx_database.model.Course;
import com.javafx_database.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO implements IDataAccessObject<Course> {
    private DATABASE1 database = DATABASE1.getDatabase();
    private Connection connection = database.getConnection();
    private Teacher teacher = new Teacher();


    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();

        List<Course> courses = courseDAO.getAll();
        for (Course course : courses) {
            System.out.println(course.getCourse_id() + " " + course.getCourse_name() + " " + course.getTeacher().getTeacher_id() + " " + course.getTeacher().getTeacher_name() + " " + course.getTeacher().getTeacher_age());

        }
    }

    @Override
    public Course getById(int id) {
        Course course = new Course();
        Teacher teacher;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from course where course_id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                course.setCourse_name(resultSet.getString("course_name"));
                course.setCourse_id(resultSet.getInt("course_id"));
                teacher = getTeacherById(resultSet.getInt("teacher_id"));
                course.setTeacher(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;

    }

    @Override
    public List<Course> getAll() {
        List<Course> courseList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from course");
            resultSet = preparedStatement.executeQuery();
            Course course;
            Teacher teacher;
            while (resultSet.next()){
                course = new Course();
                course.setCourse_id(resultSet.getInt(1));
                course.setCourse_name(resultSet.getString(2));
                teacher = getTeacherById(resultSet.getInt(3));
                course.setTeacher(teacher);
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;

    }

    public Teacher getTeacherById(int id) {
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
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public boolean add(Course course) {
        PreparedStatement preparedStatement;
        int sonuc=-1;
        try {
            preparedStatement = connection.prepareStatement("insert into course( course_name, teacher_id) values (?,?)");
            preparedStatement.setString(1 , course.getCourse_name());

            sonuc = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sonuc>0)
            return true;
        return false;

    }

    @Override
    public boolean delete(Course course) {
        PreparedStatement preparedStatement;
        int sonuc=-1;
        try {
            preparedStatement = connection.prepareStatement("delete from course where course_id=?");
            preparedStatement.setInt(1,course.getCourse_id());
            sonuc= preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sonuc>0)
            return true;
        return false;
    }

}

