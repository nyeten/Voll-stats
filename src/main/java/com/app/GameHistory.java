package com.app;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class GameHistory {

    Stage stage;
    @FXML
    private Text instructions;

    public GameHistory(Stage stage) {
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

    public void initialize() {
        //instructions for opening game history
        String str = "If unable to open game history, navigate to users on pc file explorer,\nfind Documents file, then locate 'game_history.xlsx' and upload \nfile to excel through excel online to open game history.";
        instructions.setText(str);
    }
    @FXML
    private void openGameHistory() throws IOException {
        //open excel file from documents
        String filePath = System.getProperty("user.home") + "/Documents/game_history.xlsx";
        File file = new File(filePath);

        //check if file exists
        if (file.exists()) {
            java.awt.Desktop.getDesktop().open(file);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("No Game History Found");
            alert.setContentText("No game history file was found yet.\n" +
                                "Play and save a game first to create one!");
            alert.showAndWait();
        }
    }

    //exit to main menu
    @FXML
    private void exit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/mainMenu.fxml"));
        MainMenu controller = new MainMenu(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        switchScene(scene);
    }
}
