package model;


import javafx.scene.Node;
import javafx.scene.control.Alert;
import model.GameActions.PlayerWinAction;
import model.GameEvents.OnGameStartEvent;
import model.GameEvents.OnRoundStartEvent;
import model.GameEvents.OnTurnEndEvent;
import model.GameEvents.OnTurnStartEvent;
import model.Piles.Pile;
import view.EditorTab.DrawingPane;
import view.EditorTab.RuleElementRectangle;

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


    private boolean turnEndExistsValidation() {
        if (drawingPane.getRectByClass(OnTurnEndEvent.class) == null) {
            showRuleNotFoundErrorAlert("OnTurnEndEvent");
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


    private boolean singleTurnEndValidation() {
        if (drawingPane.getCountByClass(OnTurnEndEvent.class) > 1) {
            showExcessRuleErrorAlert("OnTurnEndEvent");
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


    private boolean pileObjectMatchValidation() {
        TableGrid currentTableGrid = GameView.getInstance().getTableTab().getTableGrid();
        DrawingPane currentDrawingPane = GameView.getInstance().getEditorTab().getEditorDrawingPane();

        for (Node n : currentDrawingPane.getChildren()) {
            if (n instanceof RuleElementRectangle) {
                RuleElementRectangle r = (RuleElementRectangle) n;
                Pile p = r.getGameRule().getPile();
                if (p == null) {
                    continue;
                }
                if (!currentTableGrid.getPileMap().containsKey(p)) {
                    showPileObjectMismatchErrorAlert(p, r.getGameRule());
                    return false;
                }
            }
        }
        return true;
    }


    private boolean cardPoolSizeValidation() {
        int cardPoolSize = GameCreation.getInstance().getCardPool().size();
        int totalCardsNeeded = 0;

        for (Pile p : GameView.getInstance().getTableTab().getTableGrid().getPileMap().keySet()) {
            totalCardsNeeded += p.getStartingSize();
        }

        if (totalCardsNeeded > cardPoolSize) {
            showCardPoolSizeErrorAlert(cardPoolSize, totalCardsNeeded);
            return false;
        }
        return true;
    }


    private boolean runAllValidations() {
        if (!gameStartExistsValidation()) {return false;}
        if (!roundStartExistsValidation()) {return false;}
        if (!turnStartExistsValidation()) {return false;}
        if (!turnEndExistsValidation()) {return false;}
        if (!playerWinExistsValidation()) {return false;}
        if (!singleGameStartValidation()) {return false;}
        if (!singleRoundStartValidation()) {return false;}
        if (!singleTurnStartValidation()) {return false;}
        if (!singleTurnEndValidation()) {return false;}
        if (!cardPoolSizeValidation()) {return false;}
        if (!numPlayersValidation()) {return false;}
        if (!pileObjectMatchValidation()) {return false;}

        return true;
    }


    private void showRuleNotFoundErrorAlert(String ruleName) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The following Game Rule was not found in the rule graph (Editor Tab): " + ruleName + ".\nEvery game needs exactly one of: OnGameStartEvent, OnRoundStartEvent, OnTurnStartEvent, and PlayerWinAction.");
        alert.setTitle("Rule Not Found Error");
        alert.setHeaderText(ruleName + " Not Found In Rule Graph");
        alert.showAndWait();
    }


    private void showExcessRuleErrorAlert(String ruleName) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The following Game Rule is present in the rule graph (Editor Tab) more than once: " + ruleName + ".\n" +
                "This rule can only be present in the graph once.");
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


    private void showPileObjectMismatchErrorAlert(Pile p, GameRule g) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "A pile has been updated in the Table Tab, but not in the " +
                "Editor Tab. The same pile needs to be re-selected in the Editor Tab wherever it is being used. \n" +
                "Please refresh (re-select) the pile named '" + p.getName() + "' in the game rule '" + g.getName() +
                "', and anywhere else it being used.");
        alert.setTitle("Pile Object Mismatch Error");
        alert.setHeaderText("Pile Needs To Be Refreshed");
        alert.showAndWait();
    }


    private void showCardPoolSizeErrorAlert(int cardPoolSize, int totalCardsNeeded) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The card pool is smaller than the total number of " +
                "starting cards over all piles. Please increase the number of decks in the card pool (in the " +
                "Card Restrictions Menu), or lower the number of starting cards in the piles on the Table Grid.\n\n" +
                "Current Size of Card Pool: " + cardPoolSize + "\n" +
                "Required Number of Cards: " + totalCardsNeeded);
        alert.setTitle("Card Pool Size Error");
        alert.setHeaderText("Card Pool Too Small");
        alert.showAndWait();
    }


    private void showValidationSuccessfulAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game validated successfully! The game (Rule Graph) currently defined in the Editor Tab is ready to be played!\nThe game can be tested in Play->Gameplay Testing.");
        alert.setTitle("Validation Successful");
        alert.setHeaderText("Game Validated Successfully");
        alert.showAndWait();
    }
}
