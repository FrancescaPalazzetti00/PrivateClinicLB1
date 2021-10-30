package com.example.privatecliniclb1.fxControllers;


import com.example.privatecliniclb1.ds.Archive;
import com.example.privatecliniclb1.ds.Document;
import com.example.privatecliniclb1.ds.User;
import com.example.privatecliniclb1.hibernateControllers.DocumentHibController;
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

public class AllDocumentForm implements Initializable {

    public TableView documentTable;
    public TableColumn<DocumentTableParameters, Integer> colId;
    public TableColumn<DocumentTableParameters, String> colCreated;
    public TableColumn<DocumentTableParameters, String> colModified;
    public TableColumn<DocumentTableParameters, String> colName;
    public TableColumn<DocumentTableParameters, String> colDescription;
    public TableColumn<DocumentTableParameters, Void> dummyField;

    private ObservableList<DocumentTableParameters> data = FXCollections.observableArrayList();
    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    DocumentHibController documentHibController = new DocumentHibController(entityManagerFactory);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        documentTable.setEditable(true);

        documentHibController.getAllDocument(true, -1,-1);

        colId.setCellValueFactory(new PropertyValueFactory<>("documentId"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        colModified.setCellValueFactory(new PropertyValueFactory<>("dateModified"));
        colName.setCellValueFactory(new PropertyValueFactory<>("documentName"));
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit(t -> {
            t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).setDocumentName(t.getNewValue());
            Document document = documentHibController.getDocumentById(t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).getDocumentId());
            document.setDocName(t.getNewValue());
            documentHibController.editDocument(document);
        });
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        colDescription.setOnEditCommit(t -> t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setDescription(t.getNewValue()));

        Callback<TableColumn<DocumentTableParameters, Void>, TableCell<DocumentTableParameters, Void>> cellFactory = new Callback<TableColumn<DocumentTableParameters, Void>, TableCell<DocumentTableParameters, Void>>() {
            @Override
            public TableCell<DocumentTableParameters, Void> call(final TableColumn<DocumentTableParameters, Void> param) {
                final TableCell<DocumentTableParameters, Void> cell = new TableCell<>() {
                    private final Button button = new Button("Delete");

                    {
                        button.setOnAction((ActionEvent event) -> {
                            DocumentTableParameters data = getTableView().getItems().get(getIndex());
                            documentHibController.removeDocument(data.getDocumentId());
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
            loadAllDocument();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllDocument() throws SQLException {
        documentTable.setEditable(true);
        documentTable.getItems().clear();
        List<Document> allDocument = documentHibController.getAllDocument();

        allDocument.forEach(a -> {
            DocumentTableParameters documentTableParameters = new DocumentTableParameters();
            documentTableParameters.setDocumentId(a.getId());
            documentTableParameters.setDocumentDateCreated(a.getDateCreated().toString());
            documentTableParameters.setDocumentDateModified(a.getDateCompleted().toString());
            documentTableParameters.setDocumentName(a.getDocName());
            documentTableParameters.setDescription(a.getDocDesc());

            data.add(documentTableParameters);
        });

        documentTable.setItems(data);
    }

}
