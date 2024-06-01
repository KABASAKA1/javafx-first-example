package com.javafx_database.controller;


import com.javafx_database.model.Student;
import com.javafx_database.model.DAO.StudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    private final StudentDAO studentDAO= new StudentDAO();

    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student,Integer> studentIDColumn;
    @FXML
    private TableColumn<Student,String> studentNameColumn;
    @FXML
    private TableColumn<Student,Integer> studentAgeColumn;
    @FXML
    private Button buttonList;
    @FXML
    private Button buttonReply;
    @FXML
    private TextField tfStudentId;
    @FXML
    private Button btnFindId;
    @FXML
    private TextField tfStudentName;
    @FXML
    private TextField tfStudentAge;
    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnDeleteStudent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        studentAgeColumn.setCellValueFactory(new PropertyValueFactory<>("student_age"));
    }

    @FXML
    public void loadStudentsData(){
        List<Student> studentList = studentDAO.getAllStudent();
        ObservableList<Student> studentObservableList = FXCollections.observableList(studentList);
        tableView.setItems(studentObservableList);
    }

    @FXML
    public void yenile(){
        tableView.getItems().clear();
    }

    @FXML
    public void findStudentById(){
        Student student = studentDAO.getStudentByID(Integer.parseInt(tfStudentId.getText()));
        tfStudentId.clear();
        ObservableList<Student> studentObservableList = FXCollections.observableArrayList();
        studentObservableList.add(student);
        tableView.setItems(studentObservableList);
    }

    @FXML
    public void addStudent(){
        Student student = new Student();
        student.setStudent_name(tfStudentName.getText());
        student.setStudent_age(Integer.parseInt(tfStudentAge.getText()));
        studentDAO.addStudent(student);
    }
    @FXML
    public void deleteStudent(){
        Student student = new Student();
        student.setStudent_id(Integer.parseInt(tfStudentId.getText()));
        studentDAO.deleteStudent(student);
    }

}

