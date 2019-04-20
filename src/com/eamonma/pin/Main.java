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
//
//        window = primaryStage;
//        window.setTitle("Login Page");
//
//        TextField username = new TextField();
//
//
//
//        BorderPane borderPane = new BorderPane();
//
//
//        Scene scene = new Scene(borderPane, 500, 500);
//
//        window.setScene(scene);
//        window.show();

        Parent root = FXMLLoader.load(getClass().getResource("views/home.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.show();
    }

}
