package com.app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import java.io.IOException;
import javafx.scene.control.TextField;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    // OPTION 1 USING SCENECONTROLLER
    // @Override
    // public void start(Stage stage) throws IOException {
    //     //System.out.println(getClass().getResource("/com/app/mainMenu.fxml"));
    //     SceneController sceneController = new SceneController(stage);
    //     // sceneController.switchScene("/com/app/mainMenu.fxml");
    //     MainMenu mainMenu = new MainMenu(sceneController);
        
    //     mainMenu.setSceneController(sceneController);
    // }


    //OPTION 2 MANUALLY CHANGING SCENES
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/mainMenu.fxml"));
        MainMenu controller = new MainMenu(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    // OPTION 3 OG CODE WITHOUT MENU CLASS
    // @Override
    // public void start(Stage stage) throws IOException {
    //     Pane root = new Pane();
    //     scene = new Scene(root, 640, 480, Color.LIGHTCORAL);
    //     //root.setStyle("-fx-background-color: LIGHTCORAL;");
    //     stage.setScene(scene);
    //     stage.setTitle("volleyball");

    //     Text text = new Text("Score");
    //     text.setLayoutX(280);
    //     text.setLayoutY(100);
    //     text.setFont(Font.font("Arial", 30));
    //     text.setFill(Color.SEAGREEN);

    //     Text homeScore = createText("0", 160, 240, "Arial", 60, Color.SEAGREEN);

    //     Button HSplus = createButton("+1", 175, 300, "Arial", 30);
    //     HSplus.setOnAction(event -> {
    //         homeScore.setText(Integer.toString((Integer.parseInt(homeScore.getText())) + 1));
    //     });
    //     Button HSminus = createButton("-1", 105, 300, "Arial", 30);
    //     HSminus.setOnAction(event -> {
    //         homeScore.setText(Integer.toString((Integer.parseInt(homeScore.getText())) - 1));
    //     });

    //     Button HSreset = createButton("reset", 125, 370, "Arial", 30);
    //     HSreset.setOnAction(event -> {
    //         homeScore.setText("0");
    //     });



    //     Text awayScore = createText("0", 480, 240, "Arial", 60, Color.SEAGREEN);

    //     Button ASplus = createButton("+1", 495, 300, "Arial", 30);
    //     ASplus.setOnAction(event -> {
    //         awayScore.setText(Integer.toString((Integer.parseInt(awayScore.getText())) + 1));
    //     });

    //     Button ASminus = createButton("-1", 425, 300, "Arial", 30);
    //     ASminus.setOnAction(event -> {
    //         awayScore.setText(Integer.toString((Integer.parseInt(awayScore.getText())) - 1));
    //     });

    //     Button ASreset = createButton("reset", 445, 370, "Arial", 30);
    //     ASreset.setOnAction(event -> {
    //         awayScore.setText("0");
    //     });



    //     root.getChildren().add(text);
    //     root.getChildren().add(awayScore);
    //     root.getChildren().add(homeScore);
    //     root.getChildren().add(HSplus);
    //     root.getChildren().add(ASplus);
    //     root.getChildren().add(HSminus);
    //     root.getChildren().add(ASminus);
    //     root.getChildren().add(ASreset);
    //     root.getChildren().add(HSreset);



    //     stage.show();
    // }

    // private Text createText(String mess, int x, int y, String font, int fontSize, Color color) {
    //     Text text = new Text(mess);
    //     text.setLayoutX(x);
    //     text.setLayoutY(y);
    //     text.setFont(Font.font(font, fontSize));
    //     text.setFill(color);
    //     return text;
    // }

    // private Button createButton(String text, int x, int y, String font, int fontSize) {
    //     Button but = new Button(text);
    //     but.setLayoutX(x);
    //     but.setLayoutY(y);
    //     but.setFont(Font.font(font, fontSize));
    //     return but;
    // }

    public static void main(String[] args) {
        launch();
    }

}