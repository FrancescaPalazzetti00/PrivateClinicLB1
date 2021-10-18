package com.example.privatecliniclb1.ds;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String docName;
    private String docDesc;
    private LocalDate dateCreated;
    private LocalDate dateCompleted;
    @ManyToOne
    private Folder folder;
    @ManyToOne
    private User owner;

    public Document(int id, String docName, String docDesc, LocalDate dateCreated, LocalDate dateCompleted, Folder folder, User owner) {
        this.id = id;
        this.docName = docName;
        this.docDesc = docDesc;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
        this.folder = folder;
        this.owner = owner;
    }

    public Document() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocDesc() {
        return docDesc;
    }

    public void setDocDesc(String docDesc) {
        this.docDesc = docDesc;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


}
