package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    // Read JSON file and return it as JSON object
    public static JSONObject parseJson(String jsonFilePath) {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        String currentDirectory = System.getProperty("user.dir");

        try {
            reader = new FileReader(currentDirectory + jsonFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("No file was found in the specified directory.");
            e.printStackTrace();
        }

        //Read JSON file
        JSONObject obj = null;
        try {
            obj = (JSONObject) jsonParser.parse(reader);
        } catch (IOException | ParseException e) {
            System.out.println("Failed to parse the file.");
            e.printStackTrace();
        }
        return obj;
    }
}
