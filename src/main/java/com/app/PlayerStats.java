package com.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Button;

public class PlayerStats {

    Stage stage;
    //block
    @FXML
    private Button blockErrorButton;
    @FXML
    private Button blockAssistButton;
    @FXML
    private Button blockSoloButton;
    @FXML
    private Button attackErrorButton;
    @FXML
    private Button attackKillButton;
    @FXML
    private Button digErrorButton;
    @FXML
    private Button digSuccessButton;
    @FXML
    private Button setButton;
    @FXML
    private Button setAssistButton;
    @FXML
    private Button serveErrorButton;
    @FXML
    private Button serveAceButton;
    @FXML
    private Button passErrorButton;
    @FXML
    private Button passOnTarButton;
    @FXML
    private Button passOffTarButton;
    @FXML
    private Button plusButton;
    @FXML
    private Button minusButton;

    @FXML
    private Text blockErr;
    @FXML
    private Text blockAss;
    @FXML
    private Text blockSol;
    //attack
    @FXML
    private Text attackErr;
    @FXML
    private Text attackKil;
    //dig
    @FXML
    private Text digErr;
    @FXML
    private Text digSucc;
    //set
    @FXML
    private Text setErr;
    @FXML
    private Text setAss;
    //serve
    @FXML
    private Text serveErr;
    @FXML
    private Text serveAce;
    //pass
    @FXML
    private Text passErr;
    @FXML
    private Text passOnTar;
    @FXML
    private Text passOffTar;

    //stat to change
    int statToChange = 0;
    int[][] stats = new int[6][3];

    ArrayList<Player> players = null;

    
    public PlayerStats(Stage stage, ArrayList<Player> players) {
        //initialize stats to -1 for non applicable stats
        stats[1][2] = -1;
        stats[2][2] = -1;
        stats[3][2] = -1;
        stats[4][2] = -1;
        this.players = players;
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
    public void initialize() {
        updatestat();
        //set button styles
        setButtonStyle(blockErrorButton);
        setButtonStyle(blockAssistButton);
        setButtonStyle(blockSoloButton);
        setButtonStyle(attackErrorButton);
        setButtonStyle(attackKillButton);
        setButtonStyle(digErrorButton);
        setButtonStyle(digSuccessButton);
        setButtonStyle(setButton);
        setButtonStyle(setAssistButton);
        setButtonStyle(serveErrorButton);
        setButtonStyle(serveAceButton);
        setButtonStyle(passErrorButton);
        setButtonStyle(passOnTarButton);
        setButtonStyle(passOffTarButton);
        setButtonStyle(plusButton);
        setButtonStyle(minusButton);

    }

    public void setButtonStyle(Button button) {
        button.setStyle(
            "-fx-background-color: #1E1E1E;" +
            "-fx-text-fill: #E0E0E0;" +
            "-fx-font-size: 16px;" +
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
                "-fx-font-size: 16px;" +
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
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #FF4C4C;" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
            )
        );
    }  

    //BLOCKS
    @FXML
    public void blockError() {
        statToChange = 11;
    }

    @FXML
    public void blockAssist() {
        statToChange = 12;
    }
    @FXML
    public void blockSolo() {
        statToChange = 13;
    }

    //ATTACKS
    @FXML
    public void attackError() {
        statToChange = 21;
    }

    @FXML
    public void attackKill() {
        statToChange = 22;
    }

    //DIG
    @FXML
    public void digError() {
        statToChange = 31;
    }

    @FXML
    public void digSuccess() {
        statToChange = 32;
    }

    //SET
    @FXML
    public void setError() {
        statToChange = 41;
    }
    @FXML
    public void setAssist() {
        statToChange = 42;
    }

    //SERVES
    @FXML
    public void serveError() {
        statToChange = 51;
    }  

    @FXML
    public void serveAce() {
        statToChange = 52;
    }

    //PASS
    @FXML
    public void passError() {
        statToChange = 61;
    }

    @FXML
    public void passOnTarget() {
        statToChange = 62;
    }
    @FXML
    public void passOffTarget() {
        statToChange = 63;
    }

    //PLUS AND MINUS BUTTONS
    @FXML
    public void plus() {
        int row = statToChange / 10 - 1;
        int col = statToChange % 10 - 1;
        if (statToChange > 10) {
            stats[row][col]++;
            updatestat();
        }
    }

    @FXML
    public void minus() {
        int row = statToChange / 10 - 1;
        int col = statToChange % 10 - 1;
        if (statToChange > 10) {
            if (stats[row][col] > 0) {
                stats[row][col]--;
            }
        updatestat();
        }
    }

    //update stats display
    public void updatestat() {
        blockErr.setText(String.valueOf(stats[0][0]));
        blockAss.setText(String.valueOf(stats[0][1]));
        blockSol.setText(String.valueOf(stats[0][2]));
        attackErr.setText(String.valueOf(stats[1][0]));
        attackKil.setText(String.valueOf(stats[1][1]));
        digErr.setText(String.valueOf(stats[2][0]));
        digSucc.setText(String.valueOf(stats[2][1]));
        setErr.setText(String.valueOf(stats[3][0]));
        setAss.setText(String.valueOf(stats[3][1]));
        serveErr.setText(String.valueOf(stats[4][0]));
        serveAce.setText(String.valueOf(stats[4][1]));
        passErr.setText(String.valueOf(stats[5][0]));
        passOnTar.setText(String.valueOf(stats[5][1]));
        passOffTar.setText(String.valueOf(stats[5][2]));
    }


    //switch back to game scene
    @FXML
    public void back () throws IOException{
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/com/app/game.fxml"));
        Game controller = new Game(stage, players);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        switchScene(scene);
    }
}
