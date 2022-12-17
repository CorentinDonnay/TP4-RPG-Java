package com.example.tp4_v2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Math.max;


public class InterfaceGraphiqueMenu extends Application {
    public static final int SCREEN_SIZE = 900;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = new FXMLLoader(InterfaceGraphiqueMenu.class.getResource("menu.fxml")).load();


        stage.setTitle("RPG !");

        stage.setScene(new Scene(root, SCREEN_SIZE, SCREEN_SIZE));
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}