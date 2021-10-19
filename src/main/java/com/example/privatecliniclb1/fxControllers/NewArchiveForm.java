package com.example.privatecliniclb1.fxControllers;

import com.example.privatecliniclb1.ds.Archive;

import com.example.privatecliniclb1.hibernateControllers.ArchiveHibControl;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.privatecliniclb1.control.DbUtils.alertMessage;

public class NewArchiveForm {
    public TextField archiveTitle;
    public TextArea archiveDesc;

    public void createArchive(ActionEvent actionEvent) throws IOException {

        Archive archive = new Archive(archiveTitle.getText(), archiveDesc.getText());

        //ArchiveHibControl.createArchive(archive);

        alertMessage("User created successfully.");

    }


}
