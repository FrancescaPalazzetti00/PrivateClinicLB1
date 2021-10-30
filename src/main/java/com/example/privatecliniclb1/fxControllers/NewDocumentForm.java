package com.example.privatecliniclb1.fxControllers;


import com.example.privatecliniclb1.ds.Document;
import com.example.privatecliniclb1.ds.Folder;
import com.example.privatecliniclb1.hibernateControllers.FolderHibController;
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

    public void setFolder(Folder folder) {
        this.currentFolder = folder;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    FolderHibController folderHibController = new FolderHibController(entityManagerFactory);

    public void createDocument(ActionEvent actionEvent) {

        if (!documentName.getText().trim().isEmpty() && !documentDesc.getText().trim().isEmpty()) {
            Document document = new Document(documentName.getText(), documentDesc.getText());

            currentFolder.getDocuments().add(document);

            //Folder folder = folderHibController.getFolderById(currentFolder.getId());
            //folder.getDocuments().add(document);
            //document.getFolder()

            folderHibController.createFolder(currentFolder);
            //if completely new



            alertMessage("User created successfully.");
        } else {
            alertMessage("Please, fill out all fields");
        }
    }
}
