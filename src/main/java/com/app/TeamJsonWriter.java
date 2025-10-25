package com.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamJsonWriter {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveTeamsToJson(List<Team> teams, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(teams, writer);
            //System.out.println("Teams saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Load teams back from JSON file */
    public static List<Team> loadTeams() {
        try (FileReader reader = new FileReader("teams.json")) {
            Type listType = new TypeToken<ArrayList<Team>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            // File doesn’t exist yet or couldn’t be read
            return new ArrayList<>();
        }
    }
}
