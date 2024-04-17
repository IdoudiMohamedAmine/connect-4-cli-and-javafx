package com.example.Interface;

import com.example.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppPuissance extends Application {
    @Override
    public void start(Stage stage) {
        Controller c = new Controller();
        Scene scene = new Scene(c.getBorderpane(), 680, 480);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args) {
        launch();
    }
}
