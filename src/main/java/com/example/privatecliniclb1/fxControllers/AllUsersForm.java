package com.example.privatecliniclb1.fxControllers;


import com.example.privatecliniclb1.ds.Secretary;
import com.example.privatecliniclb1.ds.Patient;
import com.example.privatecliniclb1.ds.User;
import com.example.privatecliniclb1.hibernateControllers.UserHibController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class AllUsersForm implements Initializable {


    public TableView usersTable;
    public TableColumn<UserTableParameters, Integer> colId;
    public TableColumn<UserTableParameters, String> colLogin;
    public TableColumn<UserTableParameters, String> colCreated;
    public TableColumn<UserTableParameters, String> colModified;
    public TableColumn<UserTableParameters, String> colName;
    public TableColumn<UserTableParameters, String> colSurname;
    public TableColumn<UserTableParameters, String> colTaxCode;
    public TableColumn<UserTableParameters, String> colEmail;
    public TableColumn<UserTableParameters, String> colAddress;
    public TableColumn<UserTableParameters, String> colSecName;
    public TableColumn<UserTableParameters, String> colSecSur;
    public TableColumn<UserTableParameters, String> colSecAddress;
    public TableColumn<UserTableParameters, String> colSecPhone;
    public TableColumn<UserTableParameters, Void> dummyField;

    private ObservableList<UserTableParameters> data = FXCollections.observableArrayList();

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usersTable.setEditable(true);
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        colModified.setCellValueFactory(new PropertyValueFactory<>("dateModified"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colLogin.setCellFactory(TextFieldTableCell.forTableColumn());
        colLogin.setOnEditCommit(t -> {
            t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).setLogin(t.getNewValue());
            User user = userHibController.getUserById(t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).getUserId());
            user.setLogin(t.getNewValue());
            userHibController.editUser(user);
        });
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit(t -> t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setName(t.getNewValue()));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        colSurname.setOnEditCommit(t -> t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setSurname(t.getNewValue()));
        colTaxCode.setCellValueFactory(new PropertyValueFactory<>("taxCode"));
        colTaxCode.setCellFactory(TextFieldTableCell.forTableColumn());
        colTaxCode.setOnEditCommit(t -> t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setTaxCode(t.getNewValue()));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(t -> t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setEmail(t.getNewValue()));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        colAddress.setOnEditCommit(t -> t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setAddress(t.getNewValue()));
        colSecName.setCellValueFactory(new PropertyValueFactory<>("sec_name"));
        colSecName.setCellFactory(TextFieldTableCell.forTableColumn());
        colSecName.setOnEditCommit(t -> t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setSec_name(t.getNewValue()));
        colSecSur.setCellValueFactory(new PropertyValueFactory<>("sec_surname"));
        colSecSur.setCellFactory(TextFieldTableCell.forTableColumn());
        colSecSur.setOnEditCommit(t -> t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setSec_surname(t.getNewValue()));
        colSecAddress.setCellValueFactory(new PropertyValueFactory<>("sec_address"));
        colSecAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        colSecAddress.setOnEditCommit(t -> t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setSec_address(t.getNewValue()));
        colSecPhone.setCellValueFactory(new PropertyValueFactory<>("sec_phone"));
        colSecPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        colSecPhone.setOnEditCommit(t -> t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setSec_phone(t.getNewValue()));



        Callback<TableColumn<UserTableParameters, Void>, TableCell<UserTableParameters, Void>> cellFactory = new Callback<TableColumn<UserTableParameters, Void>, TableCell<UserTableParameters, Void>>() {
            @Override
            public TableCell<UserTableParameters, Void> call(final TableColumn<UserTableParameters, Void> param) {
                final TableCell<UserTableParameters, Void> cell = new TableCell<>() {
                    private final Button button = new Button("Delete");

                    {
                        button.setOnAction((ActionEvent event) -> {
                            UserTableParameters data = getTableView().getItems().get(getIndex());
                            userHibController.removeUser(data.getUserId());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) setGraphic(null);
                        else setGraphic(button);
                    }
                };
                return cell;
            }
        };

        dummyField.setCellFactory(cellFactory);

        try {
            loadAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllUsers() throws SQLException {
        usersTable.setEditable(true);
        usersTable.getItems().clear();
        List<User> allUsers = userHibController.getAllUsers();

        allUsers.forEach(u -> {
            UserTableParameters userTableParameters = new UserTableParameters();
            userTableParameters.setUserId(u.getId());
            userTableParameters.setLogin(u.getLogin());
            userTableParameters.setDateCreated(u.getDateCreated().toString());
            userTableParameters.setDateModified(u.getDateModified().toString());
            if (u instanceof Patient) {
                Patient p = (Patient) u;
                userTableParameters.setName(p.getName());
                userTableParameters.setSurname(p.getSurname());
                userTableParameters.setTaxCode(p.getTaxCode());
                userTableParameters.setEmail(p.getEmail());
                userTableParameters.setAddress(p.getAddress());
            } else if (u instanceof Secretary) {
                Secretary s = (Secretary) u;
                userTableParameters.setSec_name(s.getSecretaryName());
                userTableParameters.setSec_surname(s.getSecretarySurname());
                userTableParameters.setSec_address(s.getSecretaryAddress());
                userTableParameters.setSec_phone(s.getSecretaryPhoneNum());
            }
            data.add(userTableParameters);
        });

        usersTable.setItems(data);
    }
}
