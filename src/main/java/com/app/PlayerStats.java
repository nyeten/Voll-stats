package com.app;

import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class PlayerStats {

    Stage stage;
    //block
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





    int statToChange = 0;
    int[][] stats = new int[6][3];

    public PlayerStats(Stage stage) {
        this.stage = stage;
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
    @FXML
    public void back () throws IOException{
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/com/app/mainMenu.fxml"));
        MainMenu controller = new MainMenu(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }




    @FXML
    public void goBack() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/mainMenu.fxml"));
        MainMenu controller = new MainMenu(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

}
