package com.app;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TeamMenu {

    @FXML
    private Pane root;
    @FXML
    private Text curTeam;
    @FXML
    private Button editTeam;
    @FXML
    private Button addTeam;
    @FXML
    private Button next;
    @FXML
    private Button last;
    @FXML
    private Button delTeam;

    private Stage stage;

    private int teamIndex = 0;
    private int playerIndex = 0;
    private int numTeams = 0;

    private ArrayList<Team> teams = new ArrayList<>();

    public TeamMenu(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        curTeam.setText("Current Team");
        updateTeamButtonStates();
    }

    /** Updates which TEAM buttons should be enabled/disabled */
    private void updateTeamButtonStates() {
        boolean hasTeams = numTeams > 0;

        editTeam.setDisable(!hasTeams);
        delTeam.setDisable(!hasTeams);

        // navigation buttons only if multiple teams
        next.setDisable(!hasTeams || teamIndex >= numTeams - 1);
        last.setDisable(!hasTeams || teamIndex <= 0);

        // addTeam is always available
        addTeam.setDisable(false);
    }

    /** Updates which PLAYER buttons should be enabled/disabled inside edit mode */
    private void updatePlayerButtonStates(Button lastPlayer, Button nextPlayer, Team team) {
        boolean hasPlayers = team.size() > 0;
        lastPlayer.setDisable(!hasPlayers || playerIndex <= 0);
        nextPlayer.setDisable(!hasPlayers || playerIndex >= team.size() - 1);
    }

    @FXML
    public void addTeam() {
        // disable menu buttons during team creation
        editTeam.setDisable(true);
        addTeam.setDisable(true);
        next.setDisable(true);
        delTeam.setDisable(true);
        last.setDisable(true);

        TextField name = textfield(200, 50, "Enter Team Name");
        root.getChildren().add(name);

        Button save = button(450, 200, "Save Team");
        Button cancel = button(540, 200, "Cancel");
        root.getChildren().addAll(save, cancel);

        save.setOnAction(e -> {
            if (!name.getText().trim().isEmpty()) {
                Team newTeam = new Team(name.getText());
                teams.add(newTeam);
                numTeams++;
                curTeam.setText(teams.get(teamIndex).getName());
                root.getChildren().removeAll(name, cancel, save);
                updateTeamButtonStates();
            }
        });

        cancel.setOnAction(e -> {
            root.getChildren().removeAll(name, cancel, save);
            updateTeamButtonStates();
        });
    }

    @FXML
    public void nextTeam() {
        if (teamIndex < numTeams - 1) {
            teamIndex++;
            curTeam.setText(teams.get(teamIndex).getName());
        }
        updateTeamButtonStates();
    }

    @FXML
    public void lastTeam() {
        if (teamIndex > 0) {
            teamIndex--;
            curTeam.setText(teams.get(teamIndex).getName());
        }
        updateTeamButtonStates();
    }

    @FXML
    public void editTeam() {
        if (teams.isEmpty()) return;

        Team team = teams.get(teamIndex);
        playerIndex = 0;

        // disable all menu buttons while editing
        editTeam.setDisable(true);
        addTeam.setDisable(true);
        next.setDisable(true);
        delTeam.setDisable(true);
        last.setDisable(true);

        // TextField for editing team name
        TextField teamNameField = textfield(200, 50, "Team Name");
        teamNameField.setText(team.getName());
        root.getChildren().add(teamNameField);

        // UI labels for player info
        Text name = text(275, 100, "Player Name");
        Text number = text(275, 130, "Player Number");
        Text position = text(275, 160, "Player Position");
        root.getChildren().addAll(name, number, position);

        Button addPlayer = button(450, 130, "Add Player");
        Button lastPlayer = button(420, 90, "Last");
        Button nextPlayer = button(460, 90, "Next");
        Button saveTeam = button(400, 400, "Save Team");
        root.getChildren().addAll(addPlayer, lastPlayer, nextPlayer, saveTeam);

        // If players exist, show first player
        if (team.size() > 0) {
            name.setText(team.getPlayer(0).getName());
            number.setText(Integer.toString(team.getPlayer(0).getNumber()));
            position.setText(team.getPlayer(0).getPosition());
        }

        updatePlayerButtonStates(lastPlayer, nextPlayer, team);

        nextPlayer.setOnAction(e -> {
            if (playerIndex < team.size() - 1) {
                playerIndex++;
                name.setText(team.getPlayer(playerIndex).getName());
                number.setText(Integer.toString(team.getPlayer(playerIndex).getNumber()));
                position.setText(team.getPlayer(playerIndex).getPosition());
            }
            updatePlayerButtonStates(lastPlayer, nextPlayer, team);
        });

        lastPlayer.setOnAction(e -> {
            if (playerIndex > 0) {
                playerIndex--;
                name.setText(team.getPlayer(playerIndex).getName());
                number.setText(Integer.toString(team.getPlayer(playerIndex).getNumber()));
                position.setText(team.getPlayer(playerIndex).getPosition());
            }
            updatePlayerButtonStates(lastPlayer, nextPlayer, team);
        });

        addPlayer.setOnAction(e -> {
            // disable addPlayer while in process
            addPlayer.setDisable(true);

            TextField namePrompt = textfield(270, 85, "Player Name");
            TextField numberPrompt = textfield(270, 115, "Player Number");
            TextField positionPrompt = textfield(270, 145, "Player Position");
            Button save = button(530, 200, "Save Player");
            Button cancel = button(620, 200, "Cancel");
            root.getChildren().addAll(namePrompt, numberPrompt, positionPrompt, save, cancel);

            save.setOnAction(p -> {
                if (!namePrompt.getText().trim().isEmpty() &&
                    !numberPrompt.getText().trim().isEmpty() &&
                    !positionPrompt.getText().trim().isEmpty()) {

                    team.addPlayer(namePrompt.getText(),
                                   Integer.parseInt(numberPrompt.getText()),
                                   positionPrompt.getText());

                    playerIndex = team.size() - 1; // jump to new player
                    name.setText(team.getPlayer(playerIndex).getName());
                    number.setText(Integer.toString(team.getPlayer(playerIndex).getNumber()));
                    position.setText(team.getPlayer(playerIndex).getPosition());

                    root.getChildren().removeAll(namePrompt, numberPrompt, positionPrompt, save, cancel);
                    addPlayer.setDisable(false);
                    updatePlayerButtonStates(lastPlayer, nextPlayer, team);
                }
            });

            cancel.setOnAction(c -> {
                root.getChildren().removeAll(namePrompt, numberPrompt, positionPrompt, save, cancel);
                addPlayer.setDisable(false);
            });
        });

        saveTeam.setOnAction(s -> {
            // update team name
            String newName = teamNameField.getText().trim();
            if (!newName.isEmpty()) {
                team.setName(newName);
                curTeam.setText(newName);
            }

            // --- cancel any unfinished Add Player process ---
            root.getChildren().removeIf(node ->
                node instanceof TextField && (
                    ((TextField) node).getPromptText().equals("Player Name") ||
                    ((TextField) node).getPromptText().equals("Player Number") ||
                    ((TextField) node).getPromptText().equals("Player Position")
                )
            );

            // remove Save/Cancel buttons from Add Player (if still present)
            root.getChildren().removeIf(node ->
                node instanceof Button && (
                    ((Button) node).getText().equals("Save Player") ||
                    ((Button) node).getText().equals("Cancel")
                )
            );

            // re-enable Add Player button
            addPlayer.setDisable(false);

            // remove editing UI
            root.getChildren().removeAll(teamNameField, name, number, position,
                                        addPlayer, lastPlayer, nextPlayer, saveTeam);

            updateTeamButtonStates();
        });
    }

    @FXML
    public void deleteTeam() {
        if (teams.isEmpty()) return;

        teams.remove(teamIndex);
        numTeams--;

        if (teamIndex >= numTeams) {
            teamIndex = Math.max(0, numTeams - 1);
        }

        if (numTeams == 0) {
            curTeam.setText("Current Team");
        } else {
            curTeam.setText(teams.get(teamIndex).getName());
        }

        updateTeamButtonStates();
    }

    // Helper UI factory methods
    public TextField textfield(int x, int y, String prompt) {
        TextField tf = new TextField();
        tf.setLayoutX(x);
        tf.setLayoutY(y);
        tf.setPromptText(prompt);
        return tf;
    }

    public Text text(int x, int y, String text) {
        Text tf = new Text();
        tf.setLayoutX(x);
        tf.setLayoutY(y);
        tf.setText(text);
        return tf;
    }

    public Button button(int x, int y, String text) {
        Button tf = new Button();
        tf.setLayoutX(x);
        tf.setLayoutY(y);
        tf.setText(text);
        return tf;
    }
}