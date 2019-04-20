package com.eamonma.pin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.ActionEvent;

public class Controller {

//    public Button button;

    public TextField username;
    public PasswordField password;

    public Button login;

    public void loginUser() {
        String usernameText = username.getText();
        String passwordText = password.getText();
        System.out.println(usernameText + " " + passwordText);
    }

}
