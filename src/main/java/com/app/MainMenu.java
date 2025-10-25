package com.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import java.io.IOException;
import javafx.stage.Stage;

public class MainMenu {

    Stage stage;

    @FXML
    private Button teamEditor;
    @FXML
    private Button score;
    @FXML
    private Button gameStarter;
    @FXML
    private Button history;


    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        // Set button styles
        setButtonStyle(score);
        setButtonStyle(gameStarter);
        setButtonStyle(teamEditor);
        setButtonStyle(history);

    }

    // Button style for main menu buttons
    public void setButtonStyle(Button button) {
        button.setStyle(
            "-fx-background-color: #1E1E1E;" +
            "-fx-text-fill: #E0E0E0;" +
            "-fx-font-size: 25px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: #FF4C4C;" +
            "-fx-border-width: 1.5;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
        );
        // Hover effect
        button.setOnMouseEntered(e ->
            button.setStyle(
                "-fx-background-color: #292929;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #FF4C4C;" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-effect: dropshadow(three-pass-box, #FF4C4C, 8, 0, 0, 0);" +
                "-fx-cursor: hand;"
            )
        );

        button.setOnMouseExited(e ->
            button.setStyle(
                "-fx-background-color: #1E1E1E;" +
                "-fx-text-fill: #E0E0E0;" +
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #FF4C4C;" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
            )
        );
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

    
    //switch to score scene
    @FXML
    private void switchToScore() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/score.fxml"));
        Score controller = new Score(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        switchScene(scene);
        
    }

    // Switch to game starter scene
    @FXML
    private void switchToStats() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/chooseTeam.fxml"));
        ChooseTeam controller = new ChooseTeam(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        switchScene(scene);
        
    }

    // Switch to team editor scene
    @FXML
    private void switchToTeamMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/teamMenu.fxml"));

        // Controller factory that injects the stage
        loader.setControllerFactory(param -> {
            if (param == TeamMenu.class) {
                return new TeamMenu(stage);
            } else {
                try {
                    return param.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Scene scene = new Scene(loader.load());
        switchScene(scene);
    }

    // Switch to game history scene
    @FXML
    private void switchToHistory() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/gameHistory.fxml"));
        GameHistory controller = new GameHistory(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        switchScene(scene);
    }
    
}