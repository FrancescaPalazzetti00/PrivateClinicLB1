package com.example.privatecliniclb1.fxControllers;

import com.example.privatecliniclb1.ds.Document;
import com.example.privatecliniclb1.ds.Folder;
import com.example.privatecliniclb1.ds.User;
import com.example.privatecliniclb1.hibernateControllers.FolderHibController;
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

public class AllFolderForm implements Initializable {

    public TableView folderTable;
    public TableColumn<FolderTableParameters, Integer> colId;
    public TableColumn<FolderTableParameters, String> colCreated;
    public TableColumn<FolderTableParameters, String> colName;
    public TableColumn<FolderTableParameters, Void> dummyField;

    private ObservableList<FolderTableParameters> data = FXCollections.observableArrayList();
    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    FolderHibController folderHibController = new FolderHibController(entityManagerFactory);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        folderTable.setEditable(true);

        folderHibController.getAllFodler(true,-1, -1);

        colId.setCellValueFactory(new PropertyValueFactory<>("folderId"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        colName.setCellValueFactory(new PropertyValueFactory<>("folderName"));
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit(t -> {
            t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).setFolderName(t.getNewValue());
            Folder folder  = folderHibController.getFolderById(t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).getFolderId());
            folder.setFolderName(t.getNewValue());
            folderHibController.editFolder(folder);
        });

        Callback<TableColumn<FolderTableParameters, Void>, TableCell<FolderTableParameters, Void>> cellFactory = new Callback<TableColumn<FolderTableParameters, Void>, TableCell<FolderTableParameters, Void>>() {
            @Override
            public TableCell<FolderTableParameters, Void> call(final TableColumn<FolderTableParameters, Void> param) {
                final TableCell<FolderTableParameters, Void> cell = new TableCell<>() {
                    private final Button button = new Button("Delete");

                    {
                        button.setOnAction((ActionEvent event) -> {
                            FolderTableParameters data = getTableView().getItems().get(getIndex());
                            folderHibController.removeFolder(data.getFolderId());
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
            loadAllFolder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllFolder() throws SQLException {
        folderTable.setEditable(true);
        folderTable.getItems().clear();
        List<Folder> allFolder = folderHibController.getAllFolder();

        allFolder.forEach(a -> {
            FolderTableParameters folderTableParameters = new FolderTableParameters();
            folderTableParameters.setFolderId(a.getId());
            folderTableParameters.setFolderDateCreated(a.getDateCreated().toString());
            folderTableParameters.setFolderName(a.getFolderName());

            data.add(folderTableParameters);
        });

        folderTable.setItems(data);
    }
}
