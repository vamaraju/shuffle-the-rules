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
        buttons.put("showHand", new Button("Show Hand"));
        buttons.put("sortHand", new Button("Sort Hand"));
        buttons.put("swapCards", new Button("Swap Cards"));
        buttons.put("endTurn", new Button("End Turn"));
        buttons.put("endGame", new Button("End Game"));
        buttons.put("becomeInactive", new Button("Become Inactive"));

        setButtonSpacing(40);
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
        getShowHandButton().setOnAction(controller::onShowHandButtonClick);
        getSortHandButton().setOnAction(controller::onSortHandButtonClick);
        getSwapCardsButton().setOnAction(controller::onSwapCardsButtonClick);
        getEndTurnButton().setOnAction(controller::onEndTurnButtonClick);
        getEndGameButton().setOnAction(controller::onEndGameButtonClick);
        getBecomeInactiveButton().setOnAction(controller::onBecomeInactiveButtonClick);
    }

    public void disableEndTurnButton() {
        getEndTurnButton().setDisable(true);
    }

    public void enableEndTurnButton() {
        getEndTurnButton().setDisable(false);
    }

    public Button getPlayButton() {
        return buttons.get("play");
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
