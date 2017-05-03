package model;


import javafx.scene.control.Alert;
import model.GameActions.PlayerWinAction;
import model.GameEvents.OnGameStartEvent;
import model.GameEvents.OnRoundStartEvent;
import model.GameEvents.OnTurnStartEvent;
import view.EditorTab.DrawingPane;

public class GameValidator {

    private DrawingPane drawingPane;
    private GameSettings gameSettings;
    private CardSettings cardSettings;


    public GameValidator() {
        initFields();
    }


    public void initFields() {
        drawingPane = GameView.getInstance().getEditorTab().getEditorDrawingPane();
        gameSettings = GameCreation.getInstance().getGameSettings();
        cardSettings = GameCreation.getInstance().getCardSettings();
    }


    public void validateCurrentGame() {
        initFields();
        if (runAllValidations()) {
            GameCreation.getInstance().setRuleGraph(drawingPane.toRuleGraph());
            showValidationSuccessfulAlert();
        }
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


    private boolean numPlayersValidation() {
        if (gameSettings.getMinPlayers() <= 0 || gameSettings.getMaxPlayers() <= 0) {
            showNumPlayersErrorAlert();
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
        if (!numPlayersValidation()) {return false;}

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


    private void showNumPlayersErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The min/max number of players (in General Settings) is set to 0. Please ensure that both the minimum and maximum number of players is greater than 0 in General Settings.");
        alert.setTitle("Number Of Players Error");
        alert.setHeaderText("Min or Max Number Of Players Is 0");
        alert.showAndWait();
    }


    private void showValidationSuccessfulAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game validated successfully! The game (Rule Graph) currently defined in the Editor Tab is ready to be played!\nThe game can be tested in Play->Gameplay Testing.");
        alert.setTitle("Validation Successful");
        alert.setHeaderText("Game Validated Successfully");
        alert.showAndWait();
    }
}
