package com.example.privatecliniclb1.fxControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserTableParameters {

    private SimpleIntegerProperty userId = new SimpleIntegerProperty();
    private SimpleStringProperty login = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private SimpleStringProperty dateCreated = new SimpleStringProperty();
    private SimpleStringProperty dateModified = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty surname = new SimpleStringProperty();
    private SimpleStringProperty taxCode = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty address = new SimpleStringProperty();
    private SimpleStringProperty sec_name = new SimpleStringProperty();
    private SimpleStringProperty sec_surname = new SimpleStringProperty();
    private SimpleStringProperty sec_address = new SimpleStringProperty();
    private SimpleStringProperty sec_phone = new SimpleStringProperty();

    public UserTableParameters(SimpleIntegerProperty userId, SimpleStringProperty login, SimpleStringProperty password, SimpleStringProperty dateCreated, SimpleStringProperty dateModified, SimpleStringProperty name, SimpleStringProperty surname, SimpleStringProperty taxCode, SimpleStringProperty email, SimpleStringProperty address, SimpleStringProperty sec_name, SimpleStringProperty sec_surname, SimpleStringProperty sec_address, SimpleStringProperty sec_phone) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.name = name;
        this.surname = surname;
        this.taxCode = taxCode;
        this.email = email;
        this.address = address;
        this.sec_name = sec_name;
        this.sec_surname = sec_surname;
        this.sec_address = sec_address;
        this.sec_phone = sec_phone;
    }

    public UserTableParameters() {

    }

    public int getUserId() {
        return userId.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getDateCreated() {
        return dateCreated.get();
    }

    public SimpleStringProperty dateCreatedProperty() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated.set(dateCreated);
    }

    public String getDateModified() {
        return dateModified.get();
    }

    public SimpleStringProperty dateModifiedProperty() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified.set(dateModified);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getTaxCode() {
        return taxCode.get();
    }

    public SimpleStringProperty taxCodeProperty() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode.set(taxCode);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getSec_name() {
        return sec_name.get();
    }

    public SimpleStringProperty sec_nameProperty() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name.set(sec_name);
    }

    public String getSec_surname() {
        return sec_surname.get();
    }

    public SimpleStringProperty sec_surnameProperty() {
        return sec_surname;
    }

    public void setSec_surname(String sec_surname) {
        this.sec_surname.set(sec_surname);
    }

    public String getSec_address() {
        return sec_address.get();
    }

    public SimpleStringProperty sec_addressProperty() {
        return sec_address;
    }

    public void setSec_address(String sec_address) {
        this.sec_address.set(sec_address);
    }

    public String getSec_phone() {
        return sec_phone.get();
    }

    public SimpleStringProperty sec_phoneProperty() {
        return sec_phone;
    }

    public void setSec_phone(String sec_phone) {
        this.sec_phone.set(sec_phone);
    }


}
