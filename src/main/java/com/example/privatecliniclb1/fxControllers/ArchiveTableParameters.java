package com.example.privatecliniclb1.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ArchiveTableParameters extends UserTableParameters {

    private SimpleIntegerProperty archiveId = new SimpleIntegerProperty();
    private SimpleStringProperty archiveDateCreated = new SimpleStringProperty();
    private SimpleStringProperty archiveDateModified = new SimpleStringProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();

    public ArchiveTableParameters(SimpleIntegerProperty archiveId, SimpleStringProperty archiveDateCreated, SimpleStringProperty archiveDateModified, SimpleStringProperty title, SimpleStringProperty description) {
        this.archiveId = archiveId;
        this.archiveDateCreated = archiveDateCreated;
        this.archiveDateModified = archiveDateModified;
        this.title = title;
        this.description = description;
    }

    public ArchiveTableParameters() {
    }

    public int getArchiveId() {
        return archiveId.get();
    }

    public SimpleIntegerProperty archiveIdProperty() {
        return archiveId;
    }

    public void setarchiveId(int tableId) {
        this.archiveId.set(tableId);
    }

    public String getArchiveDateCreated() {
        return archiveDateCreated.get();
    }

    public SimpleStringProperty archiveDateCreatedProperty() {
        return archiveDateCreated;
    }

    public void setArchiveDateCreated(String archiveDateCreated) {
        this.archiveDateCreated.set(archiveDateCreated);
    }

    public String getArchiveDateModified() {
        return archiveDateModified.get();
    }

    public SimpleStringProperty archiveDateModifiedProperty() {
        return archiveDateModified;
    }

    public void setArchiveDateModified(String archiveDateModified) {
        this.archiveDateModified.set(archiveDateModified);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
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
