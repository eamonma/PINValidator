package com.eamonma.pin;

// javafx imports
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

// regex import
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.regex.*;

// file reading imports
import java.io.IOException;
import java.io.FileWriter;


import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.internal.org.objectweb.asm.Handle;
import org.json.simple.*;

public class Controller {

    public Stage primaryStage;

//      username and password
    public TextField username;
    public PasswordField password;
    public Label status;

    public void loginUser() {
//      handles the user clicking login
//      obtain the username and password
        String usernameText = username.getText();
        String passwordText = password.getText();

        JSONObject obj = new JSONObject();
        obj.put("username", usernameText);

        Boolean isEmail = U.isEmail(usernameText);

        System.out.println(isEmail);
        Boolean canLogIn = U.userCanLogIn(usernameText, passwordText, isEmail);

        System.out.println(canLogIn);

        if(canLogIn) {
            status.setTextFill(Color.web("#27ae60"));
            status.setText("Success!");
        } else {
            status.setTextFill(Color.web("#c0392b"));
            status.setText("Please enter a valid username/password!");
        }

    }

    public void switchToRegisterView() {
        try {
            load("views/register.fxml", "Register", this.primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load(String view, String title, Stage stage) throws Exception {
        this.primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource(view));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
    }

}

class U {
    private static final String MATCHPW = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$^+=!*()@%&]).{8,20}$";
    private static final String MATCHEMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    public static boolean passwordRequirementsMet(String password) {
        // Matches the input password with the regex: at least one lowercase letter,
        // at least one uppercase letter, at least one digit, at least one special symbol. 8-20 characters long.
        Boolean valid = false;
        Pattern p = Pattern.compile(MATCHPW);
        Matcher m = p.matcher(password);
        valid = m.matches();
        return valid;
    }

    public static boolean usernameIsValid(String inputUsername, String checkUsername) {
        return inputUsername.equals(checkUsername);
    }

    public static boolean passwordIsValid(String inputPassword, String checkPassword) {
        return inputPassword.equals(checkPassword);
    }

    public static boolean emailIsValid(String inputEmail, String checkEmail) {
        return inputEmail.equals(checkEmail);
    }

    public static boolean isEmail(String email) {
        Boolean valid = false;
        Pattern p = Pattern.compile(MATCHEMAIL);
        Matcher m = p.matcher(email);
        valid = m.matches();
        return valid;
    }

    public static boolean userCanLogIn(String username, String password, Boolean isEmail) {
        JSONArray users = HandleJSON.readJSONFromFile("users.json");
        Boolean userExists = HandleJSON.userMatch(username, users, isEmail);
        Boolean pwCorrect = HandleJSON.pwMatch(password, username, users, isEmail);
        if(userExists && pwCorrect) {
            return true;
        }
        return false;
    }
}