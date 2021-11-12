package com.example.privatecliniclb1.fxControllers;

import com.example.privatecliniclb1.StartProgram;
import com.example.privatecliniclb1.ds.Archive;
import com.example.privatecliniclb1.ds.Document;
import com.example.privatecliniclb1.ds.Folder;
import com.example.privatecliniclb1.ds.User;
import com.example.privatecliniclb1.hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.IOException;

import static com.example.privatecliniclb1.control.DbUtils.alertMessage;

public class NewFolderForm {
    public TextField folderName;

    private Folder currentFolder;
    private User currentUser;

    public void setFolder(Folder folder) {
        this.currentFolder = folder;
    }
    public void setUser(User user) {this.currentUser = user; }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    UserHibController userHibController = new UserHibController(entityManagerFactory);


    public void createFolder(ActionEvent actionEvent) throws IOException {

        if (!folderName.getText().trim().isEmpty()) {
            Folder folder = new Folder(folderName.getText());

            User user = userHibController.getUserById(currentUser.getId());
            user.getMyFolders().add(folder);
            folder.getEditors().add(user);

            userHibController.editUser(user);

            alertMessage("User created successfully.");
            returnTo();
        } else {
            alertMessage("Please, fill out all fields");
        }
    }

    public void addNewDocument(ActionEvent actionEvent) throws IOException {

        if (!folderName.getText().trim().isEmpty()) {
            FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("new-document-form.fxml"));
            Parent root = fxmlLoader.load();

            Folder folder = new Folder();

            NewDocumentForm newDocumentForm = fxmlLoader.getController();
            newDocumentForm.setFolder(folder);
            newDocumentForm.setUser(currentUser);

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("FP_Clinic System");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }else {
            alertMessage("Please, fill out all fields");
        }
    }

    public void returnTo(ActionEvent actionEvent) throws IOException {
        returnTo();
    }


    public void returnTo() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("menu-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) folderName.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
