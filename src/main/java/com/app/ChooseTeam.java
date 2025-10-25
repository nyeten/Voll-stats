package com.app;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.scene.text.Text;

public class ChooseTeam {
    private ArrayList<Team> teams = new ArrayList<>();
    private Stage stage;
    public Team selectedTeam;

    @FXML
    private VBox buttonContainer;

    @FXML
    private Text selectedTeamText;


    // Constructor receives stage
    public ChooseTeam(Stage stage) {
        this.stage = stage;
    }

    //function to switch scenes while preserving size and position
    public void switchScene(Scene newScene) {

        // Use curren stage size
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


    public void setButtonStyle(Button button) {
        button.setStyle(
            "-fx-background-color: #2b2b2b;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16px;" +
            "-fx-border-color: #d94a38;" +
            "-fx-border-width: 2;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );

        // Hover effect
        button.setOnMouseEntered(e ->
            button.setStyle(
                "-fx-background-color: #d94a38;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-border-color: white;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            )
        );

        button.setOnMouseExited(e ->
            button.setStyle(
                "-fx-background-color: #2b2b2b;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-border-color: #d94a38;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            )
        );
    }
 
    @FXML //function called on load
    private void initialize() {
        // Load teams
        teams = (ArrayList<Team>) TeamJsonWriter.loadTeams();

        // Create styled buttons for each team
        for (Team team : teams) {
            Button curTeam = new Button(team.getName());
            curTeam.setPrefWidth(240);
            curTeam.setPrefHeight(50);
            setButtonStyle(curTeam);


            // Selection behavior
            curTeam.setOnAction(e -> {
                if (selectedTeamText.getText().contains("⚠ Please select a team!")){
                    selectedTeamText.setStyle("-fx-fill: #fcfcfcff; -fx-font-weight: bold; -fx-font-size: 28px;");
                } 
                selectedTeamText.setText("Selected Team: " + team.getName());
                selectedTeam = team;
            });

            buttonContainer.getChildren().add(curTeam);
        }
    }

    @FXML
    private void confirmTeam() throws IOException {
        // Ensure a team is selected
        if (selectedTeam == null) {
            selectedTeamText.setText("⚠ Please select a team!");
            selectedTeamText.setStyle("-fx-fill: #ff8080; -fx-font-weight: bold; -fx-font-size: 28px;");
            return;
        }

        // Switch to game scene with selected team
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/game.fxml"));
        Game controller = new Game(stage, selectedTeam.getPlayers());
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        switchScene(scene);
    }

    //exits to main menu
    @FXML
    private void exit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/mainMenu.fxml"));
        MainMenu controller = new MainMenu(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        switchScene(scene);
    }
}