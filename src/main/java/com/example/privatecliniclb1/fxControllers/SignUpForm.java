package com.example.privatecliniclb1.fxControllers;

import com.example.privatecliniclb1.StartProgram;
import com.example.privatecliniclb1.ds.Patient;
import com.example.privatecliniclb1.ds.Secretary;
import com.example.privatecliniclb1.hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.privatecliniclb1.control.DbUtils.alertMessage;

public class SignUpForm implements Initializable {

    @FXML
    public TextField loginF;
    @FXML
    public PasswordField passwordF;
    @FXML
    public PasswordField repeatPasswordF;
    @FXML
    public RadioButton radioS;
    @FXML
    public RadioButton radioP;
    @FXML
    public TextField secAddressF;
    @FXML
    public TextField secNameF;
    @FXML
    public TextField secSurnameF;
    @FXML
    public TextField secPhoneF;
    @FXML
    public TextField patNameF;
    @FXML
    public TextField patSurnameF;
    @FXML
    public TextField patTaxCodeF;
    @FXML
    public TextField patAddressF;
    @FXML
    public TextField patEmailF;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            patNameF.setDisable(true);
            patSurnameF.setDisable(true);
            patTaxCodeF.setDisable(true);
            patEmailF.setDisable(true);
            patAddressF.setDisable(true);

    }


    public void createUser(ActionEvent actionEvent) throws SQLException, IOException {
        if (radioP.isSelected()) {
            if (!loginF.getText().trim().isEmpty() && !passwordF.getText().trim().isEmpty() && !repeatPasswordF.getText().trim().isEmpty() && !patNameF.getText().trim().isEmpty() && !patSurnameF.getText().trim().isEmpty() && !patAddressF.getText().trim().isEmpty() && !patEmailF.getText().trim().isEmpty() && !patTaxCodeF.getText().trim().isEmpty()) {
                Patient patient = new Patient(loginF.getText(), passwordF.getText(), patNameF.getText(), patSurnameF.getText(), patTaxCodeF.getText(), patEmailF.getText(), patAddressF.getText());

                userHibController.createUser(patient);

                alertMessage("User created successfully.");
                returnToLogin();
            }
            else{
                alertMessage("Please, fill out all fields");
            }

        } else {
            if (!loginF.getText().trim().isEmpty() && !passwordF.getText().trim().isEmpty() && !repeatPasswordF.getText().trim().isEmpty() && !secAddressF.getText().trim().isEmpty() && !secNameF.getText().trim().isEmpty() && !secSurnameF.getText().trim().isEmpty() && !secPhoneF.getText().trim().isEmpty()) {
                Secretary secretary = new Secretary(loginF.getText(), passwordF.getText(), secAddressF.getText(), secNameF.getText(), secSurnameF.getText(), secPhoneF.getText());

                userHibController.createUser(secretary);

                alertMessage("User created successfully.");
                returnToLogin();
            }else{
                alertMessage("Please, fill out all fields");
            }
        }

    }

    public void enableFields(ActionEvent actionEvent) {

        if(radioS.isSelected()){
            secAddressF.setDisable(false);
            secNameF.setDisable(false);
            secSurnameF.setDisable(false);
            secPhoneF.setDisable(false);
            patNameF.setDisable(true);
            patSurnameF.setDisable(true);
            patTaxCodeF.setDisable(true);
            patAddressF.setDisable(true);
            patEmailF.setDisable(true);

        }else{
            patNameF.setDisable(false);
            patSurnameF.setDisable(false);
            patTaxCodeF.setDisable(false);
            patAddressF.setDisable(false);
            patEmailF.setDisable(false);
            secAddressF.setDisable(true);
            secNameF.setDisable(true);
            secSurnameF.setDisable(true);
            secPhoneF.setDisable(true);


        }
    }

    public void returnToLogin(ActionEvent actionEvent) throws IOException {
        returnToLogin();
    }

    public void returnToLogin() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("login-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) loginF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
