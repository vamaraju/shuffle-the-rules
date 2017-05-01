/*
* Requirements mandating inclusion:
* */
package controller;


import controller.GameplayMode.GameplayController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.event.Event;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.GameCreation;
import model.GameView;
import view.Gameplay.GameplayView;
import java.io.File;
import java.util.Optional;

public class ApplicationMenuBarController {

    /**
     * Onclick listener for "New Game" menu button.
     * Clears the current instance of GameCreation.
     *
     * @param e
     */
    public void onNewGameClick(Event e){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you wish to create a new game? This will clear all current game settings.", ButtonType.YES, ButtonType.NO);
        alert.setTitle("New Game");
        alert.setHeaderText("New Game Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES){
            GameCreation.resetInstance();
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
    public void onSaveGameClick(Event e) {
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
    public void onLoadGameClick(Event e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Game");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Shuffle The Rules Game Files", "*.str"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            GameCreation.deserializeFromFile(selectedFile.getPath());
        }
    }

    public void validateFile(){

    }

    public void exit(){

    }

    public void hostGame(){

    }

    public void joinGame(){

    }

    public void onGameplayTestingClick(Event e) {
        Stage gameplayStage = new Stage();
        gameplayStage.setTitle("Shuffle The Rules - Gameplay Mode");
        GameplayView gameplayView = new GameplayView();
        /* controller should not be here TODO move the controller initialization */
        GameplayController gameplayController = new GameplayController(gameplayView);
        gameplayStage.setScene(new Scene(gameplayView, 1000,600));
        gameplayStage.show();

    }
}
