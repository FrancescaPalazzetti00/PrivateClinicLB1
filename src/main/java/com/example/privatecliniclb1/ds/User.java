package com.example.privatecliniclb1.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String psw;
    private LocalDate dateCreated;
    private LocalDate dateModified;

    @ManyToMany(mappedBy = "responsibleUsers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Archive> myAdminArchives;
    @ManyToMany(mappedBy = "editors", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> myFolders;
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Document> myDocuments;

    public User(int id, String login, String psw, LocalDate dateCreated, LocalDate dateModified) {
        this.id = id;
        this.login = login;
        this.psw = psw;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }


    public User(String login, String psw, LocalDate dateCreated, LocalDate dateModified) {
        this.login = login;
        this.psw = psw;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public User(String login, String psw) {
        this.login = login;
        this.psw = psw;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public List<Archive> getMyAdminArchives() {
        return myAdminArchives;
    }

    public void setMyAdminArchives(List<Archive> myAdminArchives) {
        this.myAdminArchives = myAdminArchives;
    }

    public List<Folder> getMyFolders() {
        return myFolders;
    }

    public void setMyFolders(List<Folder> myFolders) {
        this.myFolders = myFolders;
    }

    public List<Document> getMyDocuments() {
        return myDocuments;
    }

    public void setMyDocuments(List<Document> myDocuments) {
        this.myDocuments = myDocuments;
    }

    @Override
    public String toString() {
        return "User: " + login;
    }
}
