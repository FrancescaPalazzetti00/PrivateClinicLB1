package com.example.privatecliniclb1.ds;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Secretary extends User implements Serializable {

    private String secretaryName;
    private String secretarySurname;
    private String secretaryPhoneNum;
    private String secretaryAddress;

    public Secretary(int id, String login, String psw, LocalDate dateCreated, LocalDate dateModified, String secretaryAddress, String secretaryName, String secretarySurname, String secretaryPhoneNum) {
        super(id, login, psw, dateCreated, dateModified);
        this.secretaryAddress = secretaryAddress;
        this.secretaryName = secretaryName;
        this.secretarySurname = secretarySurname;
        this.secretaryPhoneNum = secretaryPhoneNum;
    }

    public Secretary(String login, String psw, String secretaryAddress, String secretaryName, String secretarySurname, String secretaryPhoneNum) {
        super(login, psw);
        this.secretaryAddress = secretaryAddress;
        this.secretaryName = secretaryName;
        this.secretarySurname = secretarySurname;
        this.secretaryPhoneNum = secretaryPhoneNum;
    }

    public Secretary() {

    }

    public String getSecretaryAddress() {
        return secretaryAddress;
    }

    public void setSecretaryAddress(String secretaryId) {
        this.secretaryAddress = secretaryId;
    }

    public String getSecretaryName() {
        return secretaryName;
    }

    public void setSecretaryName(String secretaryName) {
        this.secretaryName = secretaryName;
    }

    public String getSecretaryPhoneNum() {
        return secretaryPhoneNum;
    }

    public void setSecretaryPhoneNum(String secretaryPhoneNum) {
        this.secretaryPhoneNum = secretaryPhoneNum;
    }

    public String getSecretarySurname() {
        return secretarySurname;
    }

    public void setSecretarySurname(String secretarySurname) {
        this.secretarySurname = secretarySurname;
    }
}
