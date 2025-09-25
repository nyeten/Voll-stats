package com.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import javafx.stage.Stage;

public class MainMenu {

    Stage stage;

    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    // public MainMenu() {
    //     // Required for FXMLLoader
    // }
    // public void setSceneController(SceneController sceneController) {
    //     this.sceneController = sceneController;
    // }

    @FXML
    private void switchToScore() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/score.fxml"));
        Score controller = new Score(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    private void switchToStats() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/playerStats.fxml"));
        PlayerStats controller = new PlayerStats(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
    @FXML
    private void switchToTeamMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/teamMenu.fxml"));
        TeamMenu controller = new TeamMenu(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
    
}
