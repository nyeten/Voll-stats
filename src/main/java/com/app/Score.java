package com.app;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Score {

    Stage stage;
    @FXML
    private Text homeScore;
    @FXML
    private Text awayScore;

    private int home = 0;
    private int away = 0;

    public Score(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void homePlus() {
        home++;
        updateScores();
    }
    @FXML
    private void homeMinus() {
        home--;
        updateScores();
    }
    @FXML
    private void awayPlus() {
        away++;
        updateScores();
    }
    @FXML
    private void awayMinus() {
        away--;
        updateScores();
    }

    @FXML
    private void resetScore() {
        home = 0;
        away = 0;
        updateScores();
    }

    @FXML
    private void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/mainMenu.fxml"));
        MainMenu controller = new MainMenu(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    private void updateScores() {
        homeScore.setText(String.valueOf(home));
        awayScore.setText(String.valueOf(away));
    }
}
