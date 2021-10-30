package com.example.privatecliniclb1.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FolderTableParameters {

    private SimpleIntegerProperty folderId = new SimpleIntegerProperty();
    private SimpleStringProperty folderDateCreated = new SimpleStringProperty();
    private SimpleStringProperty folderName = new SimpleStringProperty();

    public FolderTableParameters(SimpleIntegerProperty folderId, SimpleStringProperty folderDateCreated, SimpleStringProperty folderName) {
        this.folderId = folderId;
        this.folderDateCreated = folderDateCreated;
        this.folderName = folderName;
    }

    public FolderTableParameters() {
    }

    public int getFolderId() {
        return folderId.get();
    }

    public SimpleIntegerProperty folderIdProperty() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId.set(folderId);
    }

    public String getFolderDateCreated() {
        return folderDateCreated.get();
    }

    public SimpleStringProperty folderDateCreatedProperty() {
        return folderDateCreated;
    }

    public void setFolderDateCreated(String folderDateCreated) {
        this.folderDateCreated.set(folderDateCreated);
    }

    public String getFolderName() {
        return folderName.get();
    }

    public SimpleStringProperty folderNameProperty() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName.set(folderName);
    }
}
