/*
* Requirements mandating inclusion:
* */
package controller;


import controller.GameplayMode.GameplayController;
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
import model.GameView;
import view.ApplicationMenuBarView;
import view.EditorTab.DrawingPane;
import view.EditorTab.RuleElementRectangle;
import view.Gameplay.GameplayView;
import java.io.File;
import java.util.Optional;

public class ApplicationMenuBarController {

    private ApplicationMenuBarView view;

    private DrawingPane drawingPane;

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
        drawingPane = GameView.getInstance().getEditorTab().getEditorDrawingPane();
        if (runAllValidations()) {
            GameCreation.getInstance().setRuleGraph(drawingPane.toRuleGraph());
            showValidationSuccessfulAlert();
        }
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
        gameplayStage.setScene(new Scene(gameplayView, 1200, 800));
        gameplayStage.show();
    }





    private boolean gameStartExistsValidation() {
        if (drawingPane.getRectByClass(OnGameStartEvent.class) == null) {
            showRuleNotFoundErrorAlert("OnGameStartEvent");
            return false;
        }
        return true;
    }


    private boolean roundStartExistsValidation() {
        if (drawingPane.getRectByClass(OnRoundStartEvent.class) == null) {
            showRuleNotFoundErrorAlert("OnRoundStartEvent");
            return false;
        }
        return true;
    }


    private boolean turnStartExistsValidation() {
        if (drawingPane.getRectByClass(OnTurnStartEvent.class) == null) {
            showRuleNotFoundErrorAlert("OnTurnStartEvent");
            return false;
        }
        return true;
    }


    private boolean playerWinExistsValidation() {
        if (drawingPane.getRectByClass(PlayerWinAction.class) == null) {
            showRuleNotFoundErrorAlert("PlayerWinAction");
            return false;
        }
        return true;
    }


    private boolean singleGameStartValidation() {
        if (drawingPane.getCountByClass(OnGameStartEvent.class) > 1) {
            showExcessRuleErrorAlert("OnGameStartEvent");
            return false;
        }
        return true;
    }


    private boolean singleRoundStartValidation() {
        if (drawingPane.getCountByClass(OnRoundStartEvent.class) > 1) {
            showExcessRuleErrorAlert("OnRoundStartEvent");
            return false;
        }
        return true;
    }


    private boolean singleTurnStartValidation() {
        if (drawingPane.getCountByClass(OnTurnStartEvent.class) > 1) {
            showExcessRuleErrorAlert("OnTurnStartEvent");
            return false;
        }
        return true;
    }


    private boolean runAllValidations() {
        if (!gameStartExistsValidation()) {return false;}
        if (!roundStartExistsValidation()) {return false;}
        if (!turnStartExistsValidation()) {return false;}
        if (!playerWinExistsValidation()) {return false;}
        if (!singleGameStartValidation()) {return false;}
        if (!singleRoundStartValidation()) {return false;}
        if (!singleTurnStartValidation()) {return false;}

        return true;
    }


    private void showRuleNotFoundErrorAlert(String ruleName) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The following Game Rule was not found in the rule graph (Editor Tab): " + ruleName + ".\nEvery game needs exactly one of: OnGameStartEvent, OnRoundStartEvent, OnTurnStartEvent, and PlayerWinAction.");
        alert.setTitle("Rule Not Found Error");
        alert.setHeaderText(ruleName + " Not Found In Rule Graph");
        alert.showAndWait();
    }


    private void showExcessRuleErrorAlert(String ruleName) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The following Game Rule is present in the rule graph (Editor Tab) more than once: " + ruleName + ".\nEvery game needs exactly one of: OnGameStartEvent, OnRoundStartEvent, OnTurnStartEvent, and PlayerWinAction.");
        alert.setTitle("Extra Rule Error");
        alert.setHeaderText(ruleName + " In Rule Graph More Than Once");
        alert.showAndWait();
    }


    private void showValidationSuccessfulAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game validated successfully! The game (Rule Graph) currently defined in the Editor Tab is ready to be played!\nThe game can be tested in Play->Gameplay Testing.");
        alert.setTitle("Validation Successful");
        alert.setHeaderText("Game Validated Successfully");
        alert.showAndWait();
    }
}
