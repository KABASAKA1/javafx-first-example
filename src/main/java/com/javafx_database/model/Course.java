package com.javafx_database.model;

import javafx.beans.property.*;

public class Course {
    private IntegerProperty course_id;
    private StringProperty course_name;
    private ObjectProperty<Teacher> teacher;

    public Course() {
        course_id = new SimpleIntegerProperty();
        course_name = new SimpleStringProperty();
        teacher = new SimpleObjectProperty<>();
    }
    public Course(Integer course_id, String course_name, Teacher teacher) {
        this.course_id = new SimpleIntegerProperty(course_id);
        this.course_name = new SimpleStringProperty(course_name);
        this.teacher = new SimpleObjectProperty<>(teacher);
    }

    public int getCourse_id() {
        return course_id.get();
    }

    public IntegerProperty course_idProperty() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id.set(course_id);
    }

    public String getCourse_name() {
        return course_name.get();
    }

    public StringProperty course_nameProperty() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name.set(course_name);
    }

    public Teacher getTeacher() {
        return teacher.get();
    }

    public ObjectProperty<Teacher> teacherProperty() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher.set(teacher);
    }
}
