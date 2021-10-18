package com.example.privatecliniclb1.ds;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Patient extends User implements Serializable {

    private String name;
    private String surname;
    private String taxCode;
    private String email;
    private String address;
    @ManyToMany
    private List<Archive> myArchives;

    public Patient(int id, String login, String psw, LocalDate dateCreated, LocalDate dateModified, String name, String surname, String taxCode, String email, String address ) {
        super(id, login, psw, dateCreated, dateModified);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.taxCode = taxCode;
    }

    public Patient(String login, String psw, String name, String surname, String taxCode, String email, String address) {
        super(login, psw);
        this.name = name;
        this.surname = surname;
        this.taxCode = taxCode;
        this.email = email;
        this.address = address;

    }

    public Patient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String position) {
        this.address = position;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public List<Archive> getMyArchives() {
        return myArchives;
    }

    public void setMyArchives(List<Archive> myArchives) {
        this.myArchives = myArchives;
    }
}
