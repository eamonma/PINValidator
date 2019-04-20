package com.eamonma.pin;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by eamonma on 2019-04-19.
 */
public class HandleJSON {

    public static boolean userMatch(String username, JSONArray jArr, boolean email) {

        try {
            for (Object user : jArr) {
                JSONObject userObj = (JSONObject) user;
                String name = (String) userObj.get("username");
                if (email) {
                    name = (String) userObj.get("email");
                }
                if (username.equals(name)) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public static boolean pwMatch(String password, String username, JSONArray jArr, Boolean isEmail) {
        try {
            for (Object user : jArr) {
                JSONObject userObj = (JSONObject) user;
                if (userObj.get(isEmail ? "email" : "username").equals(username)) {
                    String pw = (String) userObj.get("password");
                    if (password.equals(pw)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public static JSONArray readJSONFromFile(String filename) {
//      reads json from file
//      returns JSONArray
        JSONParser parser = new JSONParser();

        try {
//            reads the json file
            FileReader reader = new FileReader(filename);

            Object obj = parser.parse(reader);

            return (JSONArray) obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new JSONArray();

    }

    public static void writeJSONToFile(JSONObject obj, String filename) {
        JSONArray users = readJSONFromFile(filename);
        users.add(obj);

        try (FileWriter file = new FileWriter(filename)) {

            file.write(users.toJSONString());
            file.flush();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
