package com.javafx_database.controller;

import com.javafx_database.model.Teacher;
import com.javafx_database.model.DAO.TeacherDAO;
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

public class TeacherController implements Initializable {
    private final TeacherDAO teacherDAO = new TeacherDAO();


    @FXML
    private TableView<Teacher> tableViewTeacher;
    @FXML
    private TableColumn<Teacher, Integer> teacherIdColumn;
    @FXML
    private TableColumn<Teacher, String> teacherNameColumn;
    @FXML
    private TableColumn<Teacher,Integer> teacherAgeColumn;
    @FXML
    private TextField tfTeacherId;
    @FXML
    private TextField tfTeacherName;
    @FXML
    private TextField tfTeacherAge;
    @FXML
    private Button btnListAll;
    @FXML
    private Button btnClearTable;
    @FXML
    private Button btnAddTeacher;
    @FXML
    private Button btnFindTeacher;
    @FXML
    private Button btnDeleteTeacher;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherIdColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_id"));
        teacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_name"));
        teacherAgeColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_age"));
    }



    @FXML
    public void loadTeachersData(){
        List<Teacher> teacherList = teacherDAO.getAll();
        ObservableList<Teacher> teacherObservableList = FXCollections.observableList(teacherList);
        tableViewTeacher.setItems(teacherObservableList);
    }

    @FXML
    public void yenile(){
        tableViewTeacher.getItems().clear();
    }

    @FXML
    public void findTeacherById(){
        Teacher teacher = teacherDAO.getById(Integer.parseInt(tfTeacherId.getText()));
        tfTeacherId.clear();
        ObservableList<Teacher> teacherObservableList = FXCollections.observableArrayList();
        teacherObservableList.add(teacher);
        tableViewTeacher.setItems(teacherObservableList);
    }

    @FXML
    public void addTeacher(){
        Teacher teacher = new Teacher();
        teacher.setTeacher_name(tfTeacherName.getText());
        teacher.setTeacher_age(Integer.parseInt(tfTeacherAge.getText()));
        teacherDAO.add(teacher);
    }
    @FXML
    public void deleteTeacher(){
        Teacher teacher = new Teacher();
        teacher.setTeacher_id(Integer.parseInt(tfTeacherId.getText()));
        teacherDAO.delete(teacher);
    }


}
