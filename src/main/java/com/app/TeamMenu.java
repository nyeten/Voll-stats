package com.app;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class TeamMenu {

    @FXML private Text curTeam;
    @FXML private Button editTeam, addTeam, next, last, delTeam;
    @FXML private VBox editorArea;

    private Stage stage;
    private int teamIndex = 0;
    private int playerIndex = 0;
    private int numTeams = 0;
    private ArrayList<Team> teams = new ArrayList<>();

    public TeamMenu(Stage stage) {
        this.stage = stage;
    }

    public void switchScene(Scene newScene) {

        // If a scene already exists, use its size — otherwise fall back to stage size
        double width = stage.getWidth();
        double height = stage.getHeight();

        // Preserve window position
        double x = stage.getX();
        double y = stage.getY();

        // Apply the new scene and restore bounds
        stage.setScene(newScene);
        newScene.getRoot().resize(width, height);
        stage.setX(x);
        stage.setY(y);
        stage.setWidth(width);
        stage.setHeight(height);
    }

    @FXML
    private void initialize() {
        // Load teams from json
        teams = (ArrayList<Team>) TeamJsonWriter.loadTeams();
        numTeams = teams.size();

        // If there are teams, set current team text
        if (numTeams > 0) {
            curTeam.setText(teams.get(teamIndex).getName());
        } else {
            curTeam.setText("Current Team");
        }

        // Make current team text look nice
        curTeam.setStyle("-fx-font-size: 20px; -fx-fill: white; -fx-font-weight: bold;");

        updateTeamButtonStates();

        // Style top buttons
        styleButton(editTeam);
        styleButton(addTeam);
        styleButton(next);
        styleButton(last);
        styleButton(delTeam);
    }

    // Style buttons uniformly
    private void styleButton(Button btn) {
        btn.setStyle(
            "-fx-background-color: #2B2B2B;\n" +
            "-fx-text-fill: white;\n" +
            "-fx-font-size: 14px;\n" +
            "-fx-font-weight: bold;\n" +
            "-fx-background-radius: 6;\n" +
            "-fx-padding: 8 16 8 16;\n" +
            "-fx-border-color: red;\n" +
            "-fx-border-width: 1;\n" +
            "-fx-border-radius: 6;\n" +
            "-fx-cursor: hand;"
        );
    }

    // Update the enabled/disabled state of team navigation buttons
    private void updateTeamButtonStates() {
        boolean hasTeams = numTeams > 0;

        editTeam.setDisable(!hasTeams);
        delTeam.setDisable(!hasTeams);
        next.setDisable(!hasTeams || teamIndex >= numTeams - 1);
        last.setDisable(!hasTeams || teamIndex <= 0);
        addTeam.setDisable(false);
    }

    //add a new team
    @FXML
    public void addTeam() {
        // Clear editor area
        editorArea.getChildren().clear();

        // Create UI elements for new team
        Text title = new Text("New Team");
        title.setStyle("-fx-font-size: 18px; -fx-fill: white; -fx-font-weight: bold;");
    

        // Input field for team name
        TextField name = new TextField();
        name.setPromptText("Enter Team Name");
        name.setStyle("-fx-font-size: 14px;");
        name.setMaxWidth(200);

        // Save and Cancel buttons
        Button save = new Button("Save Team");
        Button cancel = new Button("Cancel");
        styleButton(save);
        styleButton(cancel);

        // Layout for buttons
        VBox buttonBox = new VBox(8, save, cancel);
        buttonBox.setStyle("-fx-alignment: center;");

        // Add all elements to editor area
        editorArea.getChildren().addAll(title, name, buttonBox);
        editorArea.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-spacing: 12; -fx-background-color: #1A1A1A;");

        // Handle save and cancel actions
        save.setOnAction(e -> {
            // Only add team if name is not empty
            if (!name.getText().trim().isEmpty()) {
                Team newTeam = new Team(name.getText());
                teams.add(newTeam);
                numTeams++;
                teamIndex = teams.size() - 1;
                curTeam.setText(newTeam.getName());
                editorArea.getChildren().clear();
                updateTeamButtonStates();
                TeamJsonWriter.saveTeamsToJson(teams, "teams.json");
            }
        });

        cancel.setOnAction(e -> editorArea.getChildren().clear());
    }

    // Navigate to next team
    @FXML
    public void nextTeam() {
        if (teamIndex < numTeams - 1) {
            teamIndex++;
            curTeam.setText(teams.get(teamIndex).getName());
        }
        updateTeamButtonStates();
    }

    // Navigate to last team
    @FXML
    public void lastTeam() {
        if (teamIndex > 0) {
            teamIndex--;
            curTeam.setText(teams.get(teamIndex).getName());
        }
        updateTeamButtonStates();
    }

    // Edit current team
    @FXML
    public void editTeam() {
        // Clear editor area
        if (teams.isEmpty()) return;
        Team team = teams.get(teamIndex);
        playerIndex = 0;

        editorArea.getChildren().clear();
        editorArea.setStyle("-fx-padding: 20; -fx-background-color: #1A1A1A; -fx-spacing: 12; -fx-alignment: center;");

        // Create UI elements for editing team
        Text title = new Text("Editing Team: " + team.getName());
        title.setStyle("-fx-font-size: 18px; -fx-fill: white; -fx-font-weight: bold;");

        TextField teamNameField = new TextField(team.getName());
        teamNameField.setStyle("-fx-font-size: 14px;");
        teamNameField.setMaxWidth(250);

        Text playerName = new Text();
        Text playerNumber = new Text();
        Text playerPosition = new Text();

        // Style player info texts
        for (Text t : new Text[]{playerName, playerNumber, playerPosition}) {
            t.setStyle("-fx-font-size: 14px; -fx-fill: white;");
        }

        // Display first player if exists
        if (team.size() > 0) {
            playerName.setText("Name: " + team.getPlayer(0).getName());
            playerNumber.setText("Number: " + team.getPlayer(0).getNumber());
            playerPosition.setText("Position: " + team.getPlayer(0).getPosition());
        } else {
            playerName.setText("No players yet.");
        }

        // Create buttons for player navigation and actions
        Button addPlayer = new Button("Add Player");
        Button lastPlayer = new Button("Last Player");
        Button nextPlayer = new Button("Next Player");
        Button delPlayer = new Button("Delete Player");
        Button saveTeam = new Button("Save Team");

        // Style buttons
        for (Button b : new Button[]{addPlayer, lastPlayer, nextPlayer, delPlayer, saveTeam}) {
            styleButton(b);
        }

        // Add elements to editor area
        editorArea.getChildren().addAll(
            title,
            teamNameField,
            playerName, playerNumber, playerPosition,
            new VBox(6, lastPlayer, nextPlayer, delPlayer),
            addPlayer, saveTeam
        );

        updatePlayerButtonStates(lastPlayer, nextPlayer, delPlayer, team);

        // Handle player navigation and actions
        nextPlayer.setOnAction(e -> {
            if (playerIndex < team.size() - 1) {
                playerIndex++;
                updatePlayerDisplay(playerName, playerNumber, playerPosition, team);
            }
            updatePlayerButtonStates(lastPlayer, nextPlayer, delPlayer, team);
        });


        lastPlayer.setOnAction(e -> {
            if (playerIndex > 0) {
                playerIndex--;
                updatePlayerDisplay(playerName, playerNumber, playerPosition, team);
            }
            updatePlayerButtonStates(lastPlayer, nextPlayer, delPlayer, team);
        });

        delPlayer.setOnAction(e -> {
            if (team.size() > 0) {
                team.delPlayer(playerIndex);
                if (playerIndex >= team.size() && playerIndex > 0) {
                    playerIndex--;
                }
                    if (team.size() > 0) {
                    updatePlayerDisplay(playerName, playerNumber, playerPosition, team);
                } else {
                    playerName.setText("No players yet.");
                    playerNumber.setText("");
                    playerPosition.setText("");
                }
                updatePlayerButtonStates(lastPlayer, nextPlayer, delPlayer, team);
            }
        });

        addPlayer.setOnAction(e -> addPlayerFields(team, playerName, playerNumber, playerPosition, lastPlayer, nextPlayer, delPlayer));

        // Handle saving team changes
        saveTeam.setOnAction(s -> {
            String newName = teamNameField.getText().trim();
            // Only update if name is not empty
            if (!newName.isEmpty()) {
                team.setName(newName);
                curTeam.setText(newName);
            }
            editorArea.getChildren().clear();
            updateTeamButtonStates();
            TeamJsonWriter.saveTeamsToJson(teams, "teams.json");
        });
    }

    // Update player display info
    private void updatePlayerDisplay(Text playerName, Text playerNumber, Text playerPosition, Team team) {
        playerName.setText("Name: " + team.getPlayer(playerIndex).getName());
        playerNumber.setText("Number: " + team.getPlayer(playerIndex).getNumber());
        playerPosition.setText("Position: " + team.getPlayer(playerIndex).getPosition());
    }

    // Add fields to input new player info
    private void addPlayerFields(Team team, Text playerName, Text playerNumber, Text playerPosition, Button lastPlayer, Button nextPlayer, Button delPlayer) {
        // Create text fields and add prompts
        TextField namePrompt = new TextField();
        TextField numberPrompt = new TextField();
        TextField positionPrompt = new TextField();

        namePrompt.setPromptText("Player Name");
        numberPrompt.setPromptText("Player Number");
        positionPrompt.setPromptText("Player Position");

        // Style input fields
        for (TextField f : new TextField[]{namePrompt, numberPrompt, positionPrompt}) {
            f.setStyle("-fx-font-size: 14px;");
            f.setMaxWidth(200);
        }

        // Create Save and Cancel buttons
        Button save = new Button("Save Player");
        Button cancel = new Button("Cancel");
        styleButton(save);
        styleButton(cancel);

        // Layout for new player input
        VBox box = new VBox(8, namePrompt, numberPrompt, positionPrompt, new VBox(5, save, cancel));
        box.setStyle("-fx-alignment: center;");
        editorArea.getChildren().add(box);

        // Handle save and cancel actions
        save.setOnAction(p -> {
            if (!namePrompt.getText().isEmpty() &&
                numberPrompt.getText().matches("\\d+") &&
                !positionPrompt.getText().isEmpty()) {

                team.addPlayer(
                    namePrompt.getText(),
                    Integer.parseInt(numberPrompt.getText()),
                    positionPrompt.getText()
                );

                playerIndex = team.size() - 1;
                updatePlayerDisplay(playerName, playerNumber, playerPosition, team);
                editorArea.getChildren().remove(box);
                updatePlayerButtonStates(lastPlayer, nextPlayer, delPlayer, team);
            }
        });

        cancel.setOnAction(c -> editorArea.getChildren().remove(box));
    }

    // Update next/last player button states
    private void updatePlayerButtonStates(Button lastPlayer, Button nextPlayer, Button delPlayer, Team team) {
        boolean hasPlayers = team.size() > 0;
        delPlayer.setDisable(!hasPlayers);
        lastPlayer.setDisable(!hasPlayers || playerIndex <= 0);
        nextPlayer.setDisable(!hasPlayers || playerIndex >= team.size() - 1);
    }

    // Delete current team
    @FXML
    public void deleteTeam() {
        if (teams.isEmpty()) return;
        teams.remove(teamIndex);
        numTeams--;
        if (teamIndex >= numTeams) {
            teamIndex = Math.max(0, numTeams - 1);
        }
        curTeam.setText(numTeams == 0 ? "Current Team" : teams.get(teamIndex).getName());
        updateTeamButtonStates();
        TeamJsonWriter.saveTeamsToJson(teams, "teams.json");
    }

    // Switch back to main menu
    @FXML
    public void exit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/mainMenu.fxml"));
        MainMenu controller = new MainMenu(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        switchScene(scene);
    }
}