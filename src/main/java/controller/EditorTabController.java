/*
* Requirements mandating inclusion:
* */

package controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.*;
import model.GameActions.*;
import model.GameEvents.*;
import model.Piles.Pile;
import view.RuleElementLine;
import view.RuleElementRectangle;
import view.EditorTabView;

import java.util.ArrayList;
import java.util.Random;

public class EditorTabController {

    private EditorTabView view;
    private final double ROW_SEPARATION_DISTANCE = 70;
    private final double COL_SEPARATION_DISTANCE = 40;

    public EditorTabController(EditorTabView view) {
        this.view = view;
    }


    public void initEventTypeComboBox() {
        ComboBox eventTypeComboBox = view.getEventTypeComboBox();

        ArrayList<GameEvent> gameEvents = new ArrayList<>();
        gameEvents.add(new OnCardDrawnEvent());
        gameEvents.add(new OnCardPlayedEvent());
        gameEvents.add(new OnGameEndEvent());
        gameEvents.add(new OnHandEmptyEvent());
        gameEvents.add(new OnHandFullEvent());
        gameEvents.add(new OnPileEmptyEvent());
        gameEvents.add(new OnPileFullEvent());
        gameEvents.add(new OnPlayerClickEvent());
        gameEvents.add(new OnPlayerTurnEvent());
        gameEvents.add(new OnRoundEndEvent());
        gameEvents.add(new OnRoundStartEvent());
        gameEvents.add(new OnTurnEndEvent());
        gameEvents.add(new OnTurnStartEvent());

        eventTypeComboBox.getItems().addAll(gameEvents);
        eventTypeComboBox.setPromptText("Select a Game Event");
        eventTypeComboBox.setEditable(false);
    }


    public void initActionTypeComboBox() {
        ComboBox actionTypeComboBox = view.getActionTypeComboBox();

        ArrayList<GameAction> gameActions = new ArrayList<>();
        gameActions.add(new DealCardAction());
        gameActions.add(new DrawCardAction());
        gameActions.add(new EndGameAction());
        gameActions.add(new EndTurnAction());
        gameActions.add(new MoveCardAction());
        gameActions.add(new PlaceCardAction());
        gameActions.add(new ShufflePileAction());
        gameActions.add(new SkipTurnAction());
        gameActions.add(new StartTurnAction());

        actionTypeComboBox.getItems().addAll(gameActions);
        actionTypeComboBox.setPromptText("Select a Game Action");
        actionTypeComboBox.setEditable(false);
    }


    public void initCardValueComboBoxes() {
        ComboBox eventCardValueComboBox = view.getEventCardValueComboBox();
        ComboBox actionCardValueComboBox = view.getActionCardValueComboBox();

        eventCardValueComboBox.getItems().add("*Any*");
        eventCardValueComboBox.getItems().add("*Match Pile*");
        eventCardValueComboBox.getItems().addAll(PlayingCard.values());
        actionCardValueComboBox.getItems().add("*Any*");
        actionCardValueComboBox.getItems().add("*Match Pile*");
        actionCardValueComboBox.getItems().addAll(PlayingCard.values());
    }


    public void initCardSuitComboBoxes() {
        ComboBox eventCardSuitComboBox = view.getEventCardSuitComboBox();
        ComboBox actionCardSuitComboBox = view.getActionCardSuitComboBox();

        eventCardSuitComboBox.getItems().add("*Any*");
        eventCardSuitComboBox.getItems().add("*Match Pile*");
        eventCardSuitComboBox.getItems().addAll(Suit.values());
        actionCardSuitComboBox.getItems().add("*Any*");
        actionCardSuitComboBox.getItems().add("*Match Pile*");
        actionCardSuitComboBox.getItems().addAll(Suit.values());
    }


    /**
     * Onclick listener for the "Add Event" button in the Editor tab, Events sub-tab.
     * Adds a rectangle with text to the drawing pane, as well as any connecting lines to other rectangles.
     *
     * @param e
     */
    public void onAddEventButtonClick(Event e) {
        Pane drawingPane = view.getEditorDrawingPane();

        ComboBox typeComboBox = view.getEventTypeComboBox();
        TextField nameTextField = view.getEventNameTextField();
        TextField previousRuleTextField = view.getEventPreviousRuleTextField();
        TextField descriptionTextField = view.getEventDescriptionTextField();

        if (runAllValidations(GameRuleType.EVENT, typeComboBox, nameTextField.getText(), previousRuleTextField.getText(), descriptionTextField.getText())) {
            GameRule gameRule = (GameEvent) typeComboBox.getValue();
            String ruleDescription = descriptionTextField.getText();
            if (!(ruleDescription == null || ruleDescription.isEmpty())) {
                gameRule.setDescription(ruleDescription);
            }

            createAndAddRect(drawingPane, nameTextField.getText(), GameRuleType.EVENT, gameRule, findRectangleByName(previousRuleTextField.getText()));
        }
    }


    /**
     * Onclick listener for the "Add Action" button in the Editor tab, Actions sub-tab.
     * Adds a rectangle with text to the drawing pane, as well as any connecting lines to other rectangles.
     *
     * @param e
     */
    public void onAddActionButtonClick(Event e) {
        Pane drawingPane = view.getEditorDrawingPane();

        ComboBox typeComboBox = view.getActionTypeComboBox();
        TextField nameTextField = view.getActionNameTextField();
        TextField previousRuleTextField = view.getActionPreviousRuleTextField();
        TextField descriptionTextField = view.getActionDescriptionTextField();

        if (runAllValidations(GameRuleType.ACTION, typeComboBox, nameTextField.getText(), previousRuleTextField.getText(), descriptionTextField.getText())) {
            GameRule gameRule = (GameAction) typeComboBox.getValue();
            String ruleDescription = descriptionTextField.getText();
            if (!(ruleDescription == null || ruleDescription.isEmpty())) {
                gameRule.setDescription(ruleDescription);
            }

            createAndAddRect(drawingPane, nameTextField.getText(), GameRuleType.ACTION, gameRule, findRectangleByName(previousRuleTextField.getText()));
        }
    }


    public void createAndAddRect(Pane drawingPane, String ruleName, GameRuleType ruleType, GameRule gameRule, RuleElementRectangle previousRect) {
        RuleElementRectangle r = new RuleElementRectangle(0, 0, ruleName, ruleType);
        r.setGameRule(gameRule);
        r.addPreRule(previousRect);
        previousRect.addPostRule(r);
        setRectXPlacement(r, previousRect);
        setRectYPlacement(r, previousRect);

        RuleElementLine l = new RuleElementLine(previousRect.getCenterX(), previousRect.getEndY(), r.getCenterX(), r.getY());
        previousRect.getOutLines().add(l);
        r.getInLines().add(l);

        if (r.getEndX() > drawingPane.getMinWidth()-100) {
            drawingPane.setMinWidth(r.getEndX()+100);
        }

        if (r.getEndY() > drawingPane.getMinHeight()-100) {
            drawingPane.setMinHeight(r.getEndY()+100);
        }

        r.setOnMouseClicked(this::onRectangleClicked);
        r.getTextObj().setOnMouseClicked(this::onRectangleClicked);

        drawingPane.getChildren().addAll(r, r.getTextObj(), l, l.getArrowhead());
    }


    public void addOnGameStart(Pane drawingPane) {
        RuleElementRectangle r = new RuleElementRectangle(160, 50, "Game Start", GameRuleType.EVENT);
        r.setDefaultBorderColor(Color.BLACK);
        r.setStroke(Color.BLACK);

        r.setGameRule(new OnGameStartEvent());

        drawingPane.getChildren().addAll(r, r.getTextObj());

        r.setOnMouseClicked(this::onRectangleClicked);
        r.getTextObj().setOnMouseClicked(this::onRectangleClicked);
    }


    public void onUpdateEventButtonClick(Event e) {
        RuleElementRectangle r = view.getClickedRectangle();

        //validate before updating!!! runallvalidations()

        r.setGameRule((GameRule) view.getEventTypeComboBox().getValue());
        r.setText(view.getEventNameTextField().getText());
        r.getGameRule().setDescription(view.getEventDescriptionTextField().getText());
        // update previous rule.....??
        showUpdateSuccessfulAlert(GameRuleType.EVENT);
    }


    public void onUpdateActionButtonClick(Event e) {
        RuleElementRectangle r = view.getClickedRectangle();
        r.setGameRule((GameRule) view.getActionTypeComboBox().getValue());
        r.setText(view.getActionNameTextField().getText());
        r.getGameRule().setDescription(view.getActionDescriptionTextField().getText());
        // update previous rule.....??
        showUpdateSuccessfulAlert(GameRuleType.ACTION);
    }


    public void onTabSelected(ObservableValue observable, Object oldSelectedValue, Object newSelectedValue) {
        boolean expanded = (boolean) newSelectedValue;
        if (expanded) {
            updatePileComboBoxes();
            updatePlayerComboBoxes();
        }
    }


    public void updatePileComboBoxes() {
        ComboBox eventPileComboBox = view.getEventPileComboBox();
        ComboBox actionPileComboBox = view.getActionPileComboBox();
        eventPileComboBox.getItems().clear();
        eventPileComboBox.getItems().addAll(GameView.getInstance().getTableTab().getTableGrid().getPileMap().keySet());
        actionPileComboBox.getItems().clear();
        actionPileComboBox.getItems().addAll(GameView.getInstance().getTableTab().getTableGrid().getPileMap().keySet());
    }


    public void updatePlayerComboBoxes() {
        ComboBox eventPlayerComboBox = view.getEventPlayerComboBox();
        ComboBox actionPlayerComboBox = view.getActionPlayerComboBox();

        eventPlayerComboBox.getItems().clear();
        eventPlayerComboBox.getItems().add("All");
        eventPlayerComboBox.getItems().addAll(GameCreation.getInstance().getPlayers());

        actionPlayerComboBox.getItems().clear();
        actionPlayerComboBox.getItems().add("All");
        actionPlayerComboBox.getItems().addAll(GameCreation.getInstance().getPlayers());
    }


    public void onEventTypeChanged(ObservableValue observable, Object oldEventType, Object newEventType) {
        GameEvent selectedGameEvent = (GameEvent) newEventType;
        System.out.println(selectedGameEvent);
    }


    public void drawingPaneOnMouseDragged(MouseEvent e) {
        Pane drawingPane = view.getEditorDrawingPane();

        if (!e.isPrimaryButtonDown()) {
            return;
        }

        if (e.getX() > drawingPane.getMinWidth()-100) {
            drawingPane.setMinWidth(e.getX()+100);
        }

        if (e.getY() > drawingPane.getMinHeight()-100) {
            drawingPane.setMinHeight(e.getY()+100);
        }
    }


    public void onRectangleClicked(MouseEvent e) {
        RuleElementRectangle r = this.findClickedRectangle(e.getX(), e.getY());

        if (!r.isClicked()) { // the Rectangle has just been clicked. r.clicked needs to be set to true
            if (view.getClickedRectangle() != null) {
                rectangleUnclick(view.getClickedRectangle());
            }
            view.setClickedRectangle(r);
            rectangleClick(r);
        } else { // the Rectangle has just been un-clicked. r.clicked needs to be set to false
            rectangleUnclick(r);
            view.setClickedRectangle(null);
        }
    }


    private void rectangleClick(RuleElementRectangle r) {
        r.setClicked(true);
        r.setStroke(Color.GAINSBORO);

        if (r.getRuleType() == GameRuleType.EVENT) {  // rectangle is for an event
            view.getActionsPane().setExpanded(false);
            view.getEventsPane().setExpanded(true);

            view.getAddEventButton().setDisable(true);
            view.getUpdateEventButton().setDisable(false);
            view.getDeleteEventButton().setDisable(false);

            view.getEventTypeComboBox().setValue(r.getGameRule());
            view.getEventNameTextField().setText(r.getText());
            view.getEventDescriptionTextField().setText(r.getGameRule().getDescription());
            view.getEventPreviousRuleTextField().setText(getPreviousRuleText(r));
        } else if (r.getRuleType() == GameRuleType.ACTION) { // rectangle is for an action
            view.getEventsPane().setExpanded(false);
            view.getActionsPane().setExpanded(true);

            view.getAddActionButton().setDisable(true);
            view.getUpdateActionButton().setDisable(false);
            view.getDeleteActionButton().setDisable(false);

            view.getActionTypeComboBox().setValue(r.getGameRule());
            view.getActionNameTextField().setText(r.getText());
            view.getActionDescriptionTextField().setText(r.getGameRule().getDescription());
            view.getActionPreviousRuleTextField().setText(getPreviousRuleText(r));
        }
    }


    private void rectangleUnclick(RuleElementRectangle r) {
        r.setClicked(false);
        r.setStroke(r.getDefaultBorderColor());

        view.getAddEventButton().setDisable(false);
        view.getUpdateEventButton().setDisable(true);
        view.getDeleteEventButton().setDisable(true);

        view.getAddActionButton().setDisable(false);
        view.getUpdateActionButton().setDisable(true);
        view.getDeleteActionButton().setDisable(true);
    }


    private RuleElementRectangle findClickedRectangle(double x, double y) {
        ObservableList drawingPaneChildren = view.getEditorDrawingPane().getChildren();
        for (int i = drawingPaneChildren.size()-1; i >= 0; i--) {
            Object o = drawingPaneChildren.get(i);
            if (o instanceof Rectangle) {
                RuleElementRectangle r = (RuleElementRectangle) o;
                if (r.contains(x, y)) {
                    return r;
                }
            }
        }
        return null;
    }


    private RuleElementRectangle findRectangleByName(String name) {
        ObservableList drawingPaneChildren = view.getEditorDrawingPane().getChildren();
        for (int i = drawingPaneChildren.size()-1; i >= 0; i--) {
            Object o = drawingPaneChildren.get(i);
            if (o instanceof Rectangle) {
                RuleElementRectangle r = (RuleElementRectangle) o;
                if (r.getText().equals(name)) {
                    return r;
                }
            }
        }
        return null;
    }


    private String getPreviousRuleText(RuleElementRectangle r) {
        String previousRulesStr = "";
        if (r.getPreRules().size() > 0) {
            for (RuleElementRectangle preRule : r.getPreRules()) {
                previousRulesStr += preRule.getText() + ", ";
            }
            previousRulesStr = previousRulesStr.substring(0, previousRulesStr.length()-2);
        } else {
            previousRulesStr = "N/A";
        }
        return previousRulesStr;
    }


    private void setRectXPlacement(RuleElementRectangle currentRect, RuleElementRectangle previousRect) {
        double currentRowY = previousRect.getEndY() + ROW_SEPARATION_DISTANCE;
        ArrayList<RuleElementRectangle> currentRow = new ArrayList<>();

        ObservableList drawingPaneChildren = view.getEditorDrawingPane().getChildren();
        for (Object o : drawingPaneChildren) {
            if (o instanceof Rectangle) {
                RuleElementRectangle r = (RuleElementRectangle) o;
                if (r.getY() == currentRowY) {
                    currentRow.add(r);
                }
            }
        }

        double maxX = 0;
        double minX = Double.POSITIVE_INFINITY;
        RuleElementRectangle maxXRect = previousRect;
        RuleElementRectangle minXRect = previousRect;
        if (currentRow.size() > 0) {
            for (RuleElementRectangle rect : currentRow) {
                if (rect.getX() > maxX) {
                    maxX = rect.getX();
                    maxXRect = rect;
                }
                if (rect.getX() < minX) {
                    minX = rect.getX();
                    minXRect = rect;
                }
            }

            if ((minXRect.getX() - currentRect.getWidth() - COL_SEPARATION_DISTANCE - 20 > 0) && (new Random().nextInt(10) < 5)) {
                currentRect.setX(minXRect.getX() - currentRect.getWidth() - COL_SEPARATION_DISTANCE, true, true);
            } else {
                currentRect.setX(maxXRect.getEndX() + COL_SEPARATION_DISTANCE, true, true);
            }

        } else { // current row is empty
            currentRect.setCenterX(previousRect.getCenterX());
        }
    }


    private void setRectYPlacement(RuleElementRectangle currentRect, RuleElementRectangle previousRect) {
        currentRect.setY(previousRect.getEndY() + ROW_SEPARATION_DISTANCE, true, true);
    }


    private boolean previousRuleNotFoundValidation(String previousRuleName) {
        RuleElementRectangle previousRect = findRectangleByName(previousRuleName);
        if (previousRect == null) {
            showPreviousRuleNotFoundErrorAlert(previousRuleName);
            return false;
        }
        return true;
    }


    private boolean ruleTypeNotSelectedValidation(GameRuleType ruleType, ComboBox typeComboBox) {
        if (typeComboBox.getValue() == null) {
            showRuleTypeNotSelectedErrorAlert(ruleType);
            return false;
        }
        return true;
    }


    private boolean ruleTypeIsOnGameStartValidation(ComboBox typeComboBox) {
        if (typeComboBox.getValue() instanceof OnGameStartEvent) {
            showRuleTypeIsOnGameStartErrorAlert();
            return false;
        }
        return true;
    }


    private boolean ruleNameEmptyValidation(GameRuleType ruleType, String ruleName) {
        if (ruleName == null || ruleName.isEmpty()) {
            showRuleNameEmptyErrorAlert(ruleType);
            return false;
        }
        return true;
    }


    private boolean ruleNameExistsValidation(GameRuleType ruleType, String ruleName) {
        if (findRectangleByName(ruleName) != null) {
            showRuleNameExistsErrorAlert(ruleType, ruleName);
            return false;
        }
        return true;
    }


    private boolean runAllValidations(GameRuleType ruleType, ComboBox typeComboBox, String ruleName, String previousRuleName, String ruleDescription) {
        if (!previousRuleNotFoundValidation(previousRuleName)) {return false;}
        if (!ruleTypeNotSelectedValidation(ruleType, typeComboBox)) {return false;}
        if (!ruleTypeIsOnGameStartValidation(typeComboBox)) {return false;}
        if (!ruleNameEmptyValidation(ruleType, ruleName)) {return false;}
        if (!ruleNameExistsValidation(ruleType, ruleName)) {return false;}
        return true;
    }

    private void showPreviousRuleNotFoundErrorAlert(String previousRuleName) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The specified previous rule name cannot be found in editor: \n" + previousRuleName + "\nPlease enter a previous rule name that already exists in the rule tree. Note that the names are case-sensitive.");
        alert.setTitle("Previous Rule Error");
        alert.setHeaderText("Previous Rule Not Found");
        alert.showAndWait();
    }


    private void showRuleTypeNotSelectedErrorAlert(GameRuleType ruleType) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a Game " + ruleType.getName() + " type from the drop-down box.");
        alert.setTitle(ruleType.getName() + " Type Error");
        alert.setHeaderText(ruleType.getName() + " Type Not Selected");
        alert.showAndWait();
    }


    private void showRuleTypeIsOnGameStartErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Only one OnGameStartEvent is allowed in a game. Please select another Event Type.");
        alert.setTitle("Duplicate OnGameStartEvent Error");
        alert.setHeaderText("OnGameStartEvent Selected As Event Type");
        alert.showAndWait();
    }


    private void showRuleNameEmptyErrorAlert(GameRuleType ruleType) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter an " + ruleType.getName() + " name in the text field.");
        alert.setTitle(ruleType.getName() + " Name Error");
        alert.setHeaderText(ruleType.getName() + " Name Is Missing");
        alert.showAndWait();
    }


    private void showRuleNameExistsErrorAlert(GameRuleType ruleType, String eventName) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a unique " + ruleType.getName() + " name in the text field. The specified name already exists in the rule tree: \n" + eventName);
        alert.setTitle(ruleType.getName() + " Name Error");
        alert.setHeaderText(ruleType.getName() + " Name Is A Duplicate");
        alert.showAndWait();
    }


    private void showUpdateSuccessfulAlert(GameRuleType ruleType) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, ruleType.getName() + " updated successfully!");
        alert.setTitle(ruleType.getName() + " Update Successful");
        alert.setHeaderText(ruleType.getName() + " Update Successful");
        alert.showAndWait();
    }
}
