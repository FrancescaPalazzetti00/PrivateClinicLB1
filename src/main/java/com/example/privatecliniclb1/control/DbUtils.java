package com.example.privatecliniclb1.control;

import com.example.privatecliniclb1.ds.Patient;
import com.example.privatecliniclb1.ds.Secretary;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

import java.sql.*;

public class DbUtils {

    /*public static Connection connectToDb() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost/private_clinic_system";
            String USER = "root";
            String PASS = "";
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException t) {
            t.printStackTrace();
        }
        return conn;
    }

    public static void disconnectFromDb(Connection connection, Statement statement) {

        try {
            if (connection != null && statement != null) {
                connection.close();
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

    }

    public static Patient getPatient(String login, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        Patient patient = null;
        connection = DbUtils.connectToDb();
        String sql = "SELECT * FROM users AS u WHERE u.login = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,login);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            patient = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
        }
        DbUtils.disconnectFromDb(connection, preparedStatement);
        return patient;
    }

    public static Secretary getSecretary(String login, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        Secretary secretary = null;
        connection = DbUtils.connectToDb();
        String sql = "SELECT * FROM users AS u WHERE u.login = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,login);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            secretary = new Secretary(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14));
        }
        DbUtils.disconnectFromDb(connection, preparedStatement);
        return secretary;
    }*/

    public static void alertMessage (String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Message text:");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }


}
