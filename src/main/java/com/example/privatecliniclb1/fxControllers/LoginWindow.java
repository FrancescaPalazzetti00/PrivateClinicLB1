package com.example.privatecliniclb1.fxControllers;

import com.example.privatecliniclb1.StartProgram;
import com.example.privatecliniclb1.ds.User;
import com.example.privatecliniclb1.hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;


import static com.example.privatecliniclb1.control.DbUtils.alertMessage;

public class LoginWindow {
    @FXML
    public TextField loginF;
    @FXML
    public PasswordField pswF;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    public void validateAndLogin(ActionEvent actionEvent) throws IOException {

        if (!loginF.getText().trim().isEmpty() && !pswF.getText().trim().isEmpty()){
            User user = userHibController.getUserByLoginData(loginF.getText(), pswF.getText());
            if (user != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("all-users-form.fxml"));
                Parent root = fxmlLoader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("FP_Clinic System");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }
        }else {
            alertMessage("Please, fill out all fields");
        }
    }

    public void openSignUpForm(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("sign-up-form.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) loginF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}