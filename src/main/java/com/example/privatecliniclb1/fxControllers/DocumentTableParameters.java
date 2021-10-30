package com.example.privatecliniclb1.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DocumentTableParameters {

    private SimpleIntegerProperty documentId = new SimpleIntegerProperty();
    private SimpleStringProperty documentDateCreated = new SimpleStringProperty();
    private SimpleStringProperty documentDateModified = new SimpleStringProperty();
    private SimpleStringProperty documentName = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();

    public DocumentTableParameters(SimpleIntegerProperty documentId, SimpleStringProperty documentDateCreated, SimpleStringProperty documentDateModified, SimpleStringProperty documentName, SimpleStringProperty description) {
        this.documentId = documentId;
        this.documentDateCreated = documentDateCreated;
        this.documentDateModified = documentDateModified;
        this.documentName = documentName;
        this.description = description;
    }

    public DocumentTableParameters() {
    }

    public int getDocumentId() {
        return documentId.get();
    }

    public SimpleIntegerProperty documentIdProperty() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId.set(documentId);
    }

    public String getDocumentDateCreated() {
        return documentDateCreated.get();
    }

    public SimpleStringProperty documentDateCreatedProperty() {
        return documentDateCreated;
    }

    public void setDocumentDateCreated(String documentDateCreated) {
        this.documentDateCreated.set(documentDateCreated);
    }

    public String getDocumentDateModified() {
        return documentDateModified.get();
    }

    public SimpleStringProperty documentDateModifiedProperty() {
        return documentDateModified;
    }

    public void setDocumentDateModified(String documentDateModified) {
        this.documentDateModified.set(documentDateModified);
    }

    public String getDocumentName() {
        return documentName.get();
    }

    public SimpleStringProperty documentNameProperty() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName.set(documentName);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
