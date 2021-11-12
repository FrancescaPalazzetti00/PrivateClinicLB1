package com.example.privatecliniclb1.fxControllers;


import com.example.privatecliniclb1.ds.Document;
import com.example.privatecliniclb1.ds.Folder;
import com.example.privatecliniclb1.ds.User;
import com.example.privatecliniclb1.hibernateControllers.FolderHibController;
import com.example.privatecliniclb1.hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import static com.example.privatecliniclb1.control.DbUtils.alertMessage;

public class NewDocumentForm {
    public TextField documentName;
    public TextArea documentDesc;

    private Folder currentFolder;
    private User currentUser;

    public void setFolder(Folder folder) {
        this.currentFolder = folder;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    FolderHibController folderHibController = new FolderHibController(entityManagerFactory);
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    public void createDocument(ActionEvent actionEvent) {

        if (!documentName.getText().trim().isEmpty() && !documentDesc.getText().trim().isEmpty()) {
            Document document = new Document(documentName.getText(), documentDesc.getText());

            User user = userHibController.getUserById(currentUser.getId());
            user.getMyFolders().add(currentFolder);
            user.getMyDocuments().add(document);
            currentFolder.getDocuments().add(document);
            document.setFolder(currentFolder);
            currentFolder.getEditors().add(user);
            document.setOwner(user);

            userHibController.editUser(user);

            alertMessage("User created successfully.");
        } else {
            alertMessage("Please, fill out all fields");
        }
    }

    public void setUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
