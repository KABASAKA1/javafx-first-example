module com.javafx_database {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.javafx_database.model to javafx.base;
    opens com.javafx_database.controller to javafx.fxml;
    exports com.javafx_database;
    opens com.javafx_database.model.DAO to javafx.base;
}