package com.example.privatecliniclb1.fxControllers;

import com.example.privatecliniclb1.StartProgram;
import com.example.privatecliniclb1.ds.Archive;

import com.example.privatecliniclb1.ds.User;
import com.example.privatecliniclb1.hibernateControllers.ArchiveHibController;
import com.example.privatecliniclb1.hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

import static com.example.privatecliniclb1.control.DbUtils.alertMessage;

public class NewArchiveForm {
    public TextField archiveTitle;
    public TextArea archiveDesc;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    public void createArchive(ActionEvent actionEvent) throws IOException {

        if (!archiveTitle.getText().trim().isEmpty() && !archiveDesc.getText().trim().isEmpty()) {
            Archive archive = new Archive(archiveTitle.getText(), archiveDesc.getText());

            User user = userHibController.getUserById(currentUser.getId());
            user.getMyAdminArchives().add(archive);
            archive.getResponsibleUsers().add(user);

            userHibController.editUser(user);

            alertMessage("User created successfully.");
            returnToLogin();
        } else {
            alertMessage("Please, fill out all fields");
        }
    }

    public void returnToLogin(ActionEvent actionEvent) throws IOException {
        returnToLogin();
    }

    public void returnToLogin() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("menu-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) archiveTitle.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
