/*
* Requirements mandating inclusion:
*
* 3.2.1.4.3.1 User can create a new game.
* 3.2.1.4.3.2 User can save a game to file.
* 3.2.1.4.3.3 User can load a game from file.
* 3.2.1.4.3.4 User can edit a previously created game file.
* 3.2.1.4.3.5 User can exit the Application.
* */
package controller;


import controller.GameplayMode.GameplayController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.GameActions.PlayerWinAction;
import model.GameCreation;
import model.GameEvents.OnGameStartEvent;
import model.GameEvents.OnRoundStartEvent;
import model.GameEvents.OnTurnStartEvent;
import model.GameValidator;
import model.GameView;
import view.ApplicationMenuBarView;
import view.EditorHelpView;
import view.EditorTab.DrawingPane;
import view.EditorTab.RuleElementRectangle;
import view.Gameplay.GameplayView;
import view.TableHelpView;

import java.io.File;
import java.util.Optional;

public class ApplicationMenuBarController {

    private ApplicationMenuBarView view;

    public ApplicationMenuBarController(ApplicationMenuBarView view) {
        this.view = view;
    }

    /**
     * Onclick listener for "New Game" menu button.
     * Clears the current instance of GameCreation.
     *
     * @param e
     */
    public void onNewGameClick(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you wish to create a new game? This will clear all current game settings.", ButtonType.YES, ButtonType.NO);
        alert.setTitle("New Game");
        alert.setHeaderText("New Game Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES){
            GameCreation.resetInstance();
            GameCreation.resetViews();
            GameView.getInstance().getEditorTab().clearEditorDrawingPane();
            GameView.getInstance().getTableTab().getTableGridView().resetGrid();
        }
    }


    /**
     * Onclick listener for "Save Game" menu button.
     * Saves (serializes) the current instance of GameCreation to a user-specified file.
     *
     * @param e
     */
    public void onSaveGameClick(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Game");
        fileChooser.setInitialFileName("STRGame.str");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Shuffle The Rules Game Files", "*.str"));
        File saveFile = fileChooser.showSaveDialog(null);

        if (saveFile != null) {
            GameCreation.serializeToFile(saveFile.getPath());
        }
    }


    /**
     * Onclick listener for "Load Game" menu button.
     * Loads (de-serializes) an instance of GameCreation from a user-specified file and updates the GUI accordingly.
     *
     * @param e
     */
    public void onLoadGameClick(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Game");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Shuffle The Rules Game Files", "*.str"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            GameCreation.deserializeFromFile(selectedFile.getPath());
        }
    }

    public void onValidateGameClick(ActionEvent e) {
        new GameValidator().validateCurrentGame();
    }

    public void onExitClick(ActionEvent e) {
        Platform.exit();
        System.exit(0);
    }

    public void onEditorHelpClick(ActionEvent e) {
        Stage gameplayStage = new Stage();
        gameplayStage.setTitle("Editor Instructions and Notes");
        EditorHelpView view = new EditorHelpView();
        gameplayStage.setScene(new Scene(view, 900, 600));
        gameplayStage.show();
    }

    public void onTableHelpClick(ActionEvent e) {
        Stage gameplayStage = new Stage();
        gameplayStage.setTitle("Table Instructions and Notes");
        TableHelpView view = new TableHelpView();
        gameplayStage.setScene(new Scene(view, 900, 600));
        gameplayStage.show();
    }

    public void onGameplayTestingClick(Event e) {
        Stage gameplayStage = new Stage();
        gameplayStage.setTitle("Shuffle The Rules - Gameplay Mode");
        GameplayView gameplayView = new GameplayView();
        /* controller should not be here TODO move the controller initialization */
        GameplayController gameplayController = new GameplayController(gameplayView);
        gameplayStage.setScene(new Scene(gameplayView, 1200, 800));
        gameplayStage.show();
    }

}
