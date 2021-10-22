package com.example.privatecliniclb1.fxControllers;

import com.example.privatecliniclb1.ds.Archive;
import com.example.privatecliniclb1.ds.Patient;
import com.example.privatecliniclb1.ds.Secretary;
import com.example.privatecliniclb1.ds.User;
import com.example.privatecliniclb1.hibernateControllers.ArchiveHibController;
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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AllArchiveForm implements Initializable {
    public TableView archiveTable;
    public TableColumn<ArchiveTableParameters, Integer> colId;
    public TableColumn<ArchiveTableParameters, String> colCreated;
    public TableColumn<ArchiveTableParameters, String> colModified;
    public TableColumn<ArchiveTableParameters, String> colTitle;
    public TableColumn<ArchiveTableParameters, String> colDescription;
    public TableColumn<ArchiveTableParameters, Void> dummyField;

    private ObservableList<UserTableParameters> data = FXCollections.observableArrayList();
    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    ArchiveHibController archiveHibController = new ArchiveHibController(entityManagerFactory);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        archiveTable.setEditable(true);

        archiveHibController.getAllArchive(true, -1,-1);

        colId.setCellValueFactory(new PropertyValueFactory<>("archiveId"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        colModified.setCellValueFactory(new PropertyValueFactory<>("dateModified"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        colTitle.setOnEditCommit(t -> {
            t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).setTitle(t.getNewValue());
            Archive archive = archiveHibController.getArchiveById(t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).getArchiveId());
            archive.setArchiveName(t.getNewValue());
            archiveHibController.editArchive(archive);
        });
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        colDescription.setOnEditCommit(t -> t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setDescription(t.getNewValue()));

        Callback<TableColumn<ArchiveTableParameters, Void>, TableCell<ArchiveTableParameters, Void>> cellFactory = new Callback<TableColumn<ArchiveTableParameters, Void>, TableCell<ArchiveTableParameters, Void>>() {
            @Override
            public TableCell<ArchiveTableParameters, Void> call(final TableColumn<ArchiveTableParameters, Void> param) {
                final TableCell<ArchiveTableParameters, Void> cell = new TableCell<>() {
                    private final Button button = new Button("Delete");

                    {
                        button.setOnAction((ActionEvent event) -> {
                            ArchiveTableParameters data = getTableView().getItems().get(getIndex());
                            archiveHibController.removeArchive(data.getArchiveId());
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
            loadAllArchive();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void loadAllArchive() throws SQLException {
        archiveTable.setEditable(true);
        archiveTable.getItems().clear();
        List<Archive> allArchive = archiveHibController.getAllArchive();

        allArchive.forEach(a -> {
            ArchiveTableParameters archiveTableParameters = new ArchiveTableParameters();
            archiveTableParameters.setarchiveId(a.getId());
            archiveTableParameters.setArchiveDateCreated(a.getDateCreated().toString());
            //archiveTableParameters.setArchiveDateModified(a.getDateModified().toString());
            archiveTableParameters.setTitle(a.getArchiveName());
            archiveTableParameters.setDescription(a.getArchiveDescription());

            data.add(archiveTableParameters);
        });

        archiveTable.setItems(data);
    }

}
