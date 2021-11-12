module com.example.privatecliniclb1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires mysql.connector.java;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.desktop;
    requires java.persistence;
    requires spring.context;
    requires spring.core;
    requires spring.web;
    requires com.google.gson;


    opens com.example.privatecliniclb1 to javafx.fxml, org.hibernate.orm.core, java.persistence;
    exports com.example.privatecliniclb1;
    exports com.example.privatecliniclb1.ds to org.hibernate.orm.core, java.persistence;
    opens com.example.privatecliniclb1.ds to org.hibernate.orm.core;
    exports com.example.privatecliniclb1.fxControllers;
    opens com.example.privatecliniclb1.fxControllers to java.persistence, javafx.base, javafx.fxml, org.hibernate.orm.core;

}