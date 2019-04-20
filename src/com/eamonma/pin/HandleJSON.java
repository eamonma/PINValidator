package com.eamonma.pin;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by eamonma on 2019-04-19.
 */
public class HandleJSON {

    public static void readJSONFromFile(String filename) { // reads json from file

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("employees.json"))
        {
            //Read JSON file
            Object obj = parser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over employee array
            employeeList.forEach( emp -> parseObj( (JSONObject) emp ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static void parseObj(JSONObject obj) {

    }

    public static void writeJSONToFile(JSONObject obj) {
        try (FileWriter file = new FileWriter("users.json")) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
