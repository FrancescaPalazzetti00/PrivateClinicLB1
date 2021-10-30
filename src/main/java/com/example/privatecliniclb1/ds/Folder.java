package com.example.privatecliniclb1.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Folder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String folderName;
    private LocalDate dateCreated;
    @OneToMany(mappedBy = "parentFolder", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> subFolders;
    @ManyToOne
    private Folder parentFolder;
    @ManyToMany
    private List<User> editors;
    @OneToMany(mappedBy ="folder", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Document> documents = new ArrayList<>();
    @ManyToOne
    private Archive parentArchive;

    public Folder(int id, String folderName, LocalDate dateCreated, List<Folder> subFolders, Folder parentFolder, List<User> editors, List<Document> documents, Archive parentArchive) {
        this.id = id;
        this.folderName = folderName;
        this.dateCreated = dateCreated;
        this.subFolders = subFolders;
        this.parentFolder = parentFolder;
        this.editors = editors;
        this.documents = documents;
        this.parentArchive = parentArchive;
    }

    public Folder() {
    }

    public Folder(String folderName) {
        this.folderName = folderName;
        this.dateCreated = LocalDate.now();
        this.editors = new ArrayList<>();
        this.documents = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Folder> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(List<Folder> subFolders) {
        this.subFolders = subFolders;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public List<User> getEditors() {
        return editors;
    }

    public void setEditors(List<User> editors) {
        this.editors = editors;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Archive getParentArchive() {
        return parentArchive;
    }

    public void setParentArchive(Archive parentArchive) {
        this.parentArchive = parentArchive;
    }


}
