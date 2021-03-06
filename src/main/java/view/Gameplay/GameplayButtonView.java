/*
* Requirements mandating inclusion:
*
* 3.2.2.4.3.1 Player can end game.
* 3.2.2.3.3.2 Player’s Turn will be skipped if Player is inactive.
* 3.2.2.2.3.4 User can select Card(s) to swap between Piles.
* 3.2.2.1.3.1 User can view their Hand.
* 3.2.2.1.3.2 User can sort their Hand.
*/

package view.Gameplay;

import controller.GameplayMode.GameplayButtonController;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameplayButtonView extends GridPane {

    private GameplayButtonController controller;

    private Map<String, Button> buttons = new LinkedHashMap<>();

    public GameplayButtonView() {
        initialize();
    }

    public void initialize() {
        controller = new GameplayButtonController(this);

        buttons.put("play", new Button("PLAY"));
        buttons.put("skipAction", new Button("Skip Action"));
        buttons.put("showHand", new Button("Show Hand"));
        buttons.put("sortHand", new Button("Sort Hand"));
        buttons.put("swapCards", new Button("Swap Cards"));
        buttons.put("skipTurn", new Button("Skip Turn"));
        buttons.put("endTurn", new Button("End Turn"));
        buttons.put("endGame", new Button("End Game"));
        buttons.put("becomeInactive", new Button("Become Inactive"));

        setButtonSpacing(30);
        addButtonsToGrid();
        setListeners();
    }

    public void setButtonSpacing(double rowHeight) {
        this.getRowConstraints().clear();
        for (int i = 0; i < buttons.size(); i++) {
            RowConstraints row = new RowConstraints(rowHeight);
            this.getRowConstraints().add(row);
        }
    }

    public void addButtonsToGrid() {
        int index = 0;
        for (Button b : buttons.values()) {
            b.setMaxWidth(Double.MAX_VALUE);
            this.add(b, 0, index++);
        }
    }

    public void setListeners() {
        getPlayButton().setOnAction(controller::onPlayButtonClick);
        getSkipActionButton().setOnAction(controller::onSkipActionButtonClick);
        getShowHandButton().setOnAction(controller::onShowHandButtonClick);
        getSortHandButton().setOnAction(controller::onSortHandButtonClick);
        getSwapCardsButton().setOnAction(controller::onSwapCardsButtonClick);
        getSkipTurnButton().setOnAction(controller::onSkipTurnButtonClick);
        getEndTurnButton().setOnAction(controller::onEndTurnButtonClick);
        getEndGameButton().setOnAction(controller::onEndGameButtonClick);
        getBecomeInactiveButton().setOnAction(controller::onBecomeInactiveButtonClick);
    }

    public void disablePlayButton() {
        getPlayButton().setDisable(true);
    }

    public void enablePlayButton() {
        getPlayButton().setDisable(false);
    }

    public void disableSkipActionButton() {
        getSkipActionButton().setDisable(true);
    }

    public void enableSkipActionButton() {
        getSkipActionButton().setDisable(false);
    }

    public void disableShowHandButton() {
        getShowHandButton().setDisable(true);
    }

    public void enableShowHandButton() {
        getShowHandButton().setDisable(false);
    }

    public void disableSortHandButton() {
        getSortHandButton().setDisable(true);
    }

    public void enableSortHandButton() {
        getSortHandButton().setDisable(false);
    }

    public void disableSwapCardsButton() {
        getSwapCardsButton().setDisable(true);
    }

    public void enableSwapCardsButton() {
        getSwapCardsButton().setDisable(false);
    }

    public void disableSkipTurnButton() {
        getSkipTurnButton().setDisable(true);
    }

    public void enableSkipTurnButton() {
        getSkipTurnButton().setDisable(false);
    }

    public void disableEndTurnButton() {
        getEndTurnButton().setDisable(true);
    }

    public void enableEndTurnButton() {
        getEndTurnButton().setDisable(false);
    }

    public void disableEndGameButton() {
        getEndGameButton().setDisable(true);
    }

    public void enableEndGameButton() {
        getEndGameButton().setDisable(false);
    }

    public void disableBecomeInactiveButton() {
        getBecomeInactiveButton().setDisable(true);
    }

    public void enableBecomeInactiveButton() {
        getBecomeInactiveButton().setDisable(false);
    }

    public void disableAllButtons() {
        for (Button b : buttons.values()) {
            b.setDisable(true);
        }
    }

    public void enableAllButtons() {
        for (Button b : buttons.values()) {
            b.setDisable(false);
        }
    }

    public void enableDefaultButtons() {
        enableAllButtons();
        disableSkipActionButton();
//        disableSwapCardsButton();
        disableEndTurnButton();
    }

    public void disableAllButtonsExceptEndTurn() {
        disableAllButtons();
        enableEndTurnButton();
    }

    public Button getPlayButton() {
        return buttons.get("play");
    }

    public Button getSkipActionButton() {
        return buttons.get("skipAction");
    }

    public Button getShowHandButton() {
        return buttons.get("showHand");
    }

    public Button getSortHandButton() {
        return buttons.get("sortHand");
    }

    public Button getSwapCardsButton() {
        return buttons.get("swapCards");
    }

    public Button getSkipTurnButton() {
        return buttons.get("skipTurn");
    }

    public Button getEndTurnButton() {
        return buttons.get("endTurn");
    }

    public Button getEndGameButton() {
        return buttons.get("endGame");
    }

    public Button getBecomeInactiveButton() {
        return buttons.get("becomeInactive");
    }
}
