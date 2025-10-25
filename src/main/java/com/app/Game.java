package com.app;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Game {

    private Stage stage;
    private ArrayList<Player> players = new ArrayList<>();

    @FXML
    private GridPane teamGrid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private StackPane stack;


    public Game(Stage stage, ArrayList<Player> players) {
        this.players = players;
        this.stage = stage;
    }

    public void switchScene(Scene newScene) {

        // set width and height using stage
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

    //button style for player buttons
    public void setButtonStyle(Button button, int size) {
        button.setStyle(
            "-fx-background-color: #1E1E1E;" +
            "-fx-text-fill: #E0E0E0;" +
            "-fx-font-size: " + size + "px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: #FF4C4C;" +
            "-fx-border-width: 1.5;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
        );
        //Hover effect
        button.setOnMouseEntered(e ->
            button.setStyle(
                "-fx-background-color: #292929;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-font-size: " + size + "px;" +
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
                "-fx-font-size: " + size + "px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #FF4C4C;" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
            )
        );
    }

    @FXML
    public void initialize() {
        // Set up the grid pane
        teamGrid.setAlignment(Pos.CENTER);
        teamGrid.setHgap(15);
        teamGrid.setVgap(15);

        int columns = 4; // fit more per row
        int row = 0;
        int col = 0;

        // Create buttons for each player
        for (Player player : players) {
            if (player.getStats() == null) {
                player.setStats(new PlayerStats(stage, players));
            }

            Button button = new Button(
                player.getName() + "\n#" + player.getNumber() + "\n" + player.getPosition()
            );

            // Adjusted size & color
            button.setPrefSize(140, 70);
            setButtonStyle(button, 13);

            // Open player stats on click
            button.setOnAction(e -> {
                try {
                    openPlayerStats(player);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            // Add button to grid
            teamGrid.add(button, col, row);
            col++;
            if (col >= columns) {
                col = 0;
                row++;
            }
        }

        scrollPane.setFitToWidth(true);
    }

    //open player stats scene
    private void openPlayerStats(Player player) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/playerStats.fxml"));
        loader.setController(player.getStats());
        Scene scene = new Scene(loader.load());
        switchScene(scene);
    }

    //exit to choose team scene
    private void exitGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/chooseTeam.fxml"));
        ChooseTeam controller = new ChooseTeam(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        switchScene(scene);
    }


    //save game to excel file
    @FXML
    private void exit() throws IOException {

        // Create a VBox to hold all elements
        Text exitText = new Text();
        exitText.setStyle("-fx-font-size: 20px; -fx-fill: white;");
        exitText.setText("Are you sure you want to exit?\nUnsaved progress will be lost.");
        
        // Create buttons
        Button exitButton = new Button("Exit");
        Button cancelButton = new Button("Cancel");

        // Button styles
        setButtonStyle(exitButton, 20);
        setButtonStyle(cancelButton, 20);


        // Layout for buttons
        HBox buttonBox = new HBox(10, cancelButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Create the VBox for everything
        VBox vbox = new VBox(25, exitText, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: rgba(255,100,100,0.5);");
        vbox.setPadding(new Insets(15));

        // Add it to your stack pane
        stack.getChildren().add(vbox);


        // On Exit, call exitGame
        exitButton.setOnAction(e -> {
            try {
                exitGame();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        // Remove the entire VBox on cancel
        cancelButton.setOnAction(e -> {
            stack.getChildren().remove(vbox);
        });

        
    }

    //save game to excel file
    @FXML
    private void saveGame() throws IOException {
        // Create a VBox to hold all elements
        TextField positionPrompt = new TextField();
        positionPrompt.setStyle("-fx-font-size: 14px;");
        positionPrompt.setPrefWidth(200);
        positionPrompt.setMaxWidth(200);
        positionPrompt.setPromptText("Enter game title");
        
        // Create buttons
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        // Button styles
        setButtonStyle(saveButton, 15);
        setButtonStyle(cancelButton, 15);


        // Layout for buttons
        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Create the VBox for everything
        VBox vbox = new VBox(10, positionPrompt, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: rgba(255,100,100,0.5);");
        vbox.setPadding(new Insets(15));

        // Add it to your stack pane
        stack.getChildren().add(vbox);


        // On save, export the game and remove the VBox
        saveButton.setOnAction(e -> {
            stack.getChildren().remove(vbox);
            String filePath = System.getProperty("user.home") + "/Documents/game_history.xlsx";
            try {
                GameHistoryExporter.saveGame(players, filePath, positionPrompt.getText());
            } catch (IOException e1) {
                // Auto-generated catch block
                e1.printStackTrace();
            }
        });

        // Remove the entire VBox on cancel
        cancelButton.setOnAction(e -> {
            stack.getChildren().remove(vbox);
        });

        
    }
}
