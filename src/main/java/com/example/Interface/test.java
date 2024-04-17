package com.example.Interface;

import com.example.controller.Controller;
import com.example.controller.ControllerListeJoueur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class test extends Application {
    @Override
    public void start(Stage stage) {
        ControllerListeJoueur c = new ControllerListeJoueur();
        Scene scene = new Scene(c.getRoot(), 680, 480);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args) {
        launch();
    }
}
