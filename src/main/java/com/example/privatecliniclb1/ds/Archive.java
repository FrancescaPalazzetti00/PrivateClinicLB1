package com.example.privatecliniclb1.ds;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Archive implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String archiveName;
    private String archiveDescription;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    @ManyToMany
    private List<User> responsibleUsers;
    @ManyToMany(mappedBy = "myArchives", cascade = {CascadeType.PERSIST})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Patient> patients;
    @OneToMany(mappedBy = "parentArchive", cascade = {CascadeType.PERSIST})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> folders;
    @ManyToMany(mappedBy = "myAdminArchives", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> admin;


    public Archive(int id, String archiveName, String archiveDescription, LocalDate dateCreated, LocalDate dateModified, List<User> responsibleUsers, List<Patient> patients, List<Folder> folders) {
        this.id = id;
        this.archiveName = archiveName;
        this.archiveDescription = archiveDescription;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.responsibleUsers = responsibleUsers;
        this.patients = patients;
        this.folders = folders;
    }

    public Archive() {

    }

    public Archive(int id, String archiveName, String archiveDescription) {
        this.id = id;
        this.archiveName = archiveName;
        this.archiveDescription = archiveDescription;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Archive(String archiveName, String archiveDescription) {
        this.archiveName = archiveName;
        this.archiveDescription = archiveDescription;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
        this.responsibleUsers = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.folders = new ArrayList<>();
        this.admin = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }

    public String getArchiveDescription() {
        return archiveDescription;
    }

    public void setArchiveDescription(String archiveDescription) {
        this.archiveDescription = archiveDescription;
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

    public List<User> getResponsibleUsers() {
        return responsibleUsers;
    }

    public void setResponsibleUsers(List<User> responsibleUsers) {
        this.responsibleUsers = responsibleUsers;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public List<User> getAdmin() {
        return admin;
    }

    public void setAdmin(List<User> admin) {
        this.admin = admin;
    }
}
