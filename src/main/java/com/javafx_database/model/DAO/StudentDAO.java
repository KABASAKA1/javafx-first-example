package com.javafx_database.model.DAO;

import com.javafx_database.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private DATABASE1 database1 = DATABASE1.getDatabase();
    private Connection connection = database1.getConnection();


    public Student getStudentByID(int studentID){
        Student student = new Student();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from student where student_id=?");
            preparedStatement.setInt(1,studentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                student.setStudent_name(resultSet.getString("student_name"));
                student.setStudent_id(resultSet.getInt("student_id"));
                student.setStudent_age(resultSet.getInt("student_age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }


    public List<Student> getAllStudent(){
        List<Student> studentList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from student");
            resultSet = preparedStatement.executeQuery();
            Student student;
            while (resultSet.next()){
                student = new Student();
                student.setStudent_id(resultSet.getInt(1));
                student.setStudent_name(resultSet.getString(2));
                student.setStudent_age(resultSet.getInt(3));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }


    public boolean addStudent(Student student){
        PreparedStatement preparedStatement;
        int sonuc;
        try {
            preparedStatement = connection.prepareStatement("insert into student( student_name, student_age) values (?,?)");
            preparedStatement.setString(1 , student.getStudent_name());
            preparedStatement.setInt(2,student.getStudent_age());
            sonuc = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (sonuc>0)
            return true;
        return false;
    }


    public boolean deleteStudent(Student student){
        PreparedStatement preparedStatement;
        int sonuc;
        try {
            preparedStatement = connection.prepareStatement("delete from student where student_id=?");
            preparedStatement.setInt(1,student.getStudent_id());
            sonuc= preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (sonuc>0)
            return true;
        return false;
    }


}
