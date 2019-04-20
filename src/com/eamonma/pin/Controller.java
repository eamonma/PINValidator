package com.eamonma.pin;

// javafx imports
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

// regex import
import java.util.regex.*;

// file reading imports
import java.io.IOException;
import java.io.FileWriter;


import org.json.simple.*;

public class Controller {

    //  username and password
    public TextField username;
    public PasswordField password;

    public void loginUser() {
        JSONObject obj = new JSONObject();
        obj.put("firstName", "John");
        obj.put("lastName", "Smith");

        HandleJSON.writeJSONToFile(obj);

        System.out.println(obj.toJSONString());

        Data data = new Data();

//      obtain the username and password
        String usernameText = username.getText();
        String passwordText = password.getText();

        if(U.isEmail(usernameText)) {
            U.emailIsValid(usernameText, data.email);
        }

        System.out.println(U.usernameIsValid(usernameText, data.username));
        System.out.println(U.passwordIsValid(passwordText, data.password));
        System.out.println("Is email: " + U.isEmail(usernameText));
        System.out.println("Email matches: " + U.emailIsValid(usernameText, data.email));
    }

}

class Data {
    public static String username = "eamonma";
    public static String email = "m@eamonma.com";
    public static String password = "EamonM@123";
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
}