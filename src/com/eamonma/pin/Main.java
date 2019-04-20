package com.eamonma.pin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller c = new Controller();
        c.load("views/login.fxml", "Login", primaryStage);
        //        Controller.load(primaryStage);
//        Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
//        primaryStage.setTitle("Login");
//        primaryStage.setScene(new Scene(root, 800, 400));
//        primaryStage.show();
    }

}
