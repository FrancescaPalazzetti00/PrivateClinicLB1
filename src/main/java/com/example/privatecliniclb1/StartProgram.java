package com.example.privatecliniclb1;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class StartProgram extends Application{
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(StartProgram.class.getResource("login-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FP_Clinic");
        stage.setScene(scene);
        Image image = new Image("file:///C:/Users/palaz/IdeaProjects/PrivateClinicLB1/FP_Clinic.png");
        stage.getIcons().add(image);
        stage.show();

    }


        public static void main(String[] args) {
        launch();
    }
}