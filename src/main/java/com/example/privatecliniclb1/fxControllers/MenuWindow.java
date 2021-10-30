package com.example.privatecliniclb1.fxControllers;

import com.example.privatecliniclb1.StartProgram;
import com.example.privatecliniclb1.ds.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuWindow {
    public Button users_form;
    public Button archive_form;
    public Button new_archive;
    public Button folder_form;
    public Button document_form;
    public Button new_folder;

    private User currentUser;

    public void setUser(User user){
        this.currentUser = user;
    }

    public void getUserForms(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("all-users-form.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("FP_Clinic System");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void getArchiveForms(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("all-archive-form.fxml"));
        Parent root = fxmlLoader.load();

        AllArchiveForm allArchiveForm = fxmlLoader.getController();
        allArchiveForm.setUser(currentUser);

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("FP_Clinic System");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void getArchive(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("new-archive-form.fxml"));
        Parent root = fxmlLoader.load();
        NewArchiveForm newArchiveForm = fxmlLoader.getController();
        newArchiveForm.setUser(currentUser);
        Scene scene = new Scene(root);

        Stage stage = (Stage) users_form.getScene().getWindow();
        stage.setTitle("FP_Clinic System");
        stage.setScene(scene);
        stage.show();
    }

    public void getFolderForms(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("all-folder-form.fxml"));
        Parent root = fxmlLoader.load();

        AllFolderForm allFolderForm = fxmlLoader.getController();
        allFolderForm.setUser(currentUser);

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("FP_Clinic System");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void getDocumentForms(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("all-document-form.fxml"));
        Parent root = fxmlLoader.load();

        AllDocumentForm allDocumentForm = fxmlLoader.getController();
        allDocumentForm.setUser(currentUser);

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("FP_Clinic System");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void getFolder(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("new-folder-form.fxml"));
        Parent root = fxmlLoader.load();
        NewFolderForm newFolderForm = fxmlLoader.getController();
        newFolderForm.setUser(currentUser);
        Scene scene = new Scene(root);

        Stage stage = (Stage) users_form.getScene().getWindow();
        stage.setTitle("FP_Clinic System");
        stage.setScene(scene);
        stage.show();
    }

    public void returnToLogin() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("login-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) users_form.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
