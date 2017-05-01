/*
* Requirements mandating inclusion:
* */

package controller.EditorTab;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
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
import view.EditorTab.DrawingPane;
import view.EditorTab.RuleElementLine;
import view.EditorTab.RuleElementRectangle;
import view.EditorTab.EditorTabView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EditorTabController {

    private EditorTabView view;

    private TripleHashMap<String, Node, Node> activeGridElements;
    private GameRuleType currentRuleType;

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
        gameActions.add(new PlayerWinAction());
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


    public void onEventsExpanded(ObservableValue observable, Object oldExpandedValue, Object newExpandedValue) {
        boolean expanded = (boolean) newExpandedValue;
        if (expanded) {
            activeGridElements = view.getEventsGridElements();
            currentRuleType = GameRuleType.EVENT;
        }
    }


    public void onActionsExpanded(ObservableValue observable, Object oldExpandedValue, Object newExpandedValue) {
        boolean expanded = (boolean) newExpandedValue;
        if (expanded) {
            activeGridElements = view.getActionsGridElements();
            currentRuleType = GameRuleType.ACTION;
        }
    }


    /**
     * Onclick listener for the "Add Event" button in the Editor tab, Events sub-tab.
     * Adds a rectangle with text to the drawing pane, as well as any connecting lines to other rectangles.
     *
     * @param e
     */
    public void onAddButtonClick(Event e) {
        ComboBox typeComboBox = view.getTypeComboBox(activeGridElements);
        TextField nameTextField = view.getNameTextField(activeGridElements);
        TextField previousRuleTextField = view.getPreviousRuleTextField(activeGridElements);
        TextField descriptionTextField = view.getDescriptionTextField(activeGridElements);

        if (runAllValidations()) {
            GameRule gameRule = getGameRuleFromComboBox();
            setRuleDescription(gameRule);
            setRuleSpecificSettings(gameRule);
            createAndAddRect(nameTextField.getText(), gameRule, view.getEditorDrawingPane().getRectByName(previousRuleTextField.getText()));
        }
    }


    public void createAndAddRect(String ruleName, GameRule gameRule, RuleElementRectangle previousRect) {
        DrawingPane drawingPane = view.getEditorDrawingPane();

        RuleElementRectangle r = new RuleElementRectangle(0, 0, ruleName, currentRuleType);
        r.setGameRule(gameRule);
        r.addPreRule(previousRect);
        previousRect.addPostRule(r);
        drawingPane.setRelativePlacement(r, previousRect);

        RuleElementLine l = new RuleElementLine(previousRect.getCenterX(), previousRect.getEndY(), r.getCenterX(), r.getY());
        previousRect.getOutLines().add(l);
        r.getInLines().add(l);

        r.setOnMouseClicked(this::onRectangleClicked);
        r.getTextObj().setOnMouseClicked(this::onRectangleClicked);

        drawingPane.addRuleAndLine(r, l);
        drawingPane.extendToFit(r);
    }


    public void addOnGameStart() {
        RuleElementRectangle r = new RuleElementRectangle(160, 50, DrawingPane.ROOT_NAME, GameRuleType.EVENT);
        r.setDefaultBorderColor(Color.BLACK);
        r.setStroke(Color.BLACK);

        r.setGameRule(new OnGameStartEvent());

        view.getEditorDrawingPane().addRule(r);

        r.setOnMouseClicked(this::onRectangleClicked);
        r.getTextObj().setOnMouseClicked(this::onRectangleClicked);
    }


    public void onUpdateButtonClick(Event e) {
        DrawingPane drawingPane = view.getEditorDrawingPane();

        RuleElementRectangle r = view.getClickedRectangle();

        if (runAllValidations()) {
            GameRule gameRule = getGameRuleFromComboBox();
            r.setGameRule(gameRule);
            r.setName(view.getNameTextField(activeGridElements).getText());
            setRuleDescription(gameRule);
            setRuleSpecificSettings(gameRule);

            deletePreLines(r);
            r.getPreRules().clear();
            RuleElementRectangle preRule = drawingPane.getRectByName(view.getPreviousRuleTextField(activeGridElements).getText());
            RuleElementLine inLine = new RuleElementLine(preRule.getCenterX(), preRule.getEndY(), r.getCenterX(), r.getY());

            r.getPreRules().add(preRule);
            preRule.getPostRules().add(r);
            r.getInLines().add(inLine);
            preRule.getOutLines().add(inLine);
            drawingPane.addLine(inLine);

            showUpdateSuccessfulAlert();
        }
    }


    public void onDeleteButtonClick(Event e) {
        RuleElementRectangle r = view.getClickedRectangle();
        deletePreLines(r);
        deletePostLines(r);
        deletePostRules(r);
    }


    private void deletePreLines(RuleElementRectangle r) {
        for (RuleElementLine preLine : r.getInLines()) {
            for (RuleElementRectangle preRule : r.getPreRules()) {
                preRule.getOutLines().remove(preLine);
                preRule.getPostRules().remove(r);
            }
            view.getEditorDrawingPane().removeLine(preLine);
        }
        r.getInLines().clear();
    }


    private void deletePostLines(RuleElementRectangle r) {
        for (RuleElementLine postLine : r.getOutLines()) {
            for (RuleElementRectangle postRule : r.getPostRules()) {
                postRule.getInLines().remove(postLine);
                postRule.getPreRules().remove(r);
            }
            view.getEditorDrawingPane().removeLine(postLine);
        }
        r.getOutLines().clear();
    }


    private void deletePostRules(RuleElementRectangle r) {
        for (RuleElementRectangle postRule : r.getPostRules()) {
            deletePreLines(postRule);
            deletePostLines(postRule);
            deletePostRules(postRule);
        }
        r.getPreRules().clear();
        r.getPostRules().clear();
        view.getEditorDrawingPane().removeRule(r);
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
        eventPlayerComboBox.getItems().add("*All*");
        eventPlayerComboBox.getItems().add("*Current*");
        eventPlayerComboBox.getItems().addAll(GameCreation.getInstance().getPlayers());

        actionPlayerComboBox.getItems().clear();
        actionPlayerComboBox.getItems().add("*All*");
        actionPlayerComboBox.getItems().add("*Current*");
        actionPlayerComboBox.getItems().addAll(GameCreation.getInstance().getPlayers());
    }


    public void onEventTypeChanged(ObservableValue observable, Object oldEventType, Object newEventType) {
        GameEvent selectedGameEvent = (GameEvent) newEventType;
        if (selectedGameEvent instanceof OnCardDrawnEvent) {
            view.getEventPileComboBox().setDisable(true);
            view.getEventNumCardsTextField().setDisable(true);
            view.getEventCardValueComboBox().setDisable(false);
            view.getEventCardSuitComboBox().setDisable(false);
            view.getEventPlayerComboBox().setDisable(true);
        } else if (selectedGameEvent instanceof OnCardPlayedEvent) {
            view.getEventPileComboBox().setDisable(false);
            view.getEventNumCardsTextField().setDisable(true);
            view.getEventCardValueComboBox().setDisable(false);
            view.getEventCardSuitComboBox().setDisable(false);
            view.getEventPlayerComboBox().setDisable(true);
        } else if (selectedGameEvent instanceof OnGameStartEvent || selectedGameEvent instanceof OnGameEndEvent ||
                selectedGameEvent instanceof OnRoundStartEvent || selectedGameEvent instanceof OnRoundEndEvent) {
            view.getEventPileComboBox().setDisable(true);
            view.getEventNumCardsTextField().setDisable(true);
            view.getEventCardValueComboBox().setDisable(true);
            view.getEventCardSuitComboBox().setDisable(true);
            view.getEventPlayerComboBox().setDisable(true);
        } else if (selectedGameEvent instanceof OnHandEmptyEvent || selectedGameEvent instanceof OnHandFullEvent ||
                   selectedGameEvent instanceof OnPlayerClickEvent || selectedGameEvent instanceof OnPlayerTurnEvent ||
                   selectedGameEvent instanceof OnTurnEndEvent || selectedGameEvent instanceof OnTurnStartEvent) {
            view.getEventPileComboBox().setDisable(true);
            view.getEventNumCardsTextField().setDisable(true);
            view.getEventCardValueComboBox().setDisable(true);
            view.getEventCardSuitComboBox().setDisable(true);
            view.getEventPlayerComboBox().setDisable(false);
        } else if (selectedGameEvent instanceof OnPileEmptyEvent || selectedGameEvent instanceof OnPileFullEvent) {
            view.getEventPileComboBox().setDisable(false);
            view.getEventNumCardsTextField().setDisable(true);
            view.getEventCardValueComboBox().setDisable(true);
            view.getEventCardSuitComboBox().setDisable(true);
            view.getEventPlayerComboBox().setDisable(true);
        }
    }


    public void onActionTypeChanged(ObservableValue observable, Object oldEventType, Object newActionType) {
        GameAction selectedGameAction = (GameAction) newActionType;
        if (selectedGameAction instanceof DealCardAction || selectedGameAction instanceof DrawCardAction) {
            view.getActionPileComboBox().setDisable(false);
            view.getActionNumCardsTextField().setDisable(false);
            view.getActionCardValueComboBox().setDisable(false);
            view.getActionCardSuitComboBox().setDisable(false);
            view.getActionPlayerComboBox().setDisable(false);
        } else if (selectedGameAction instanceof EndGameAction) {
            view.getActionPileComboBox().setDisable(true);
            view.getActionNumCardsTextField().setDisable(true);
            view.getActionCardValueComboBox().setDisable(true);
            view.getActionCardSuitComboBox().setDisable(true);
            view.getActionPlayerComboBox().setDisable(true);
        } else if (selectedGameAction instanceof EndTurnAction || selectedGameAction instanceof SkipTurnAction || selectedGameAction instanceof StartTurnAction || selectedGameAction instanceof PlayerWinAction) {
            view.getActionPileComboBox().setDisable(true);
            view.getActionNumCardsTextField().setDisable(true);
            view.getActionCardValueComboBox().setDisable(true);
            view.getActionCardSuitComboBox().setDisable(true);
            view.getActionPlayerComboBox().setDisable(false);
        } else if (selectedGameAction instanceof MoveCardAction || selectedGameAction instanceof PlaceCardAction) {
            view.getActionPileComboBox().setDisable(false);
            view.getActionNumCardsTextField().setDisable(true);
            view.getActionCardValueComboBox().setDisable(false);
            view.getActionCardSuitComboBox().setDisable(false);
            view.getActionPlayerComboBox().setDisable(true);
        } else if (selectedGameAction instanceof ShufflePileAction) {
            view.getActionPileComboBox().setDisable(false);
            view.getActionNumCardsTextField().setDisable(true);
            view.getActionCardValueComboBox().setDisable(true);
            view.getActionCardSuitComboBox().setDisable(true);
            view.getActionPlayerComboBox().setDisable(true);
        }
    }


    private void setRuleSpecificSettings(GameRule gameRule) {
        ComboBox pileComboBox = view.getPileComboBox(activeGridElements);
        TextField numCardsTextField = view.getNumCardsTextField(activeGridElements);
        ComboBox cardValueComboBox = view.getCardValueComboBox(activeGridElements);
        ComboBox cardSuitComboBox = view.getCardSuitComboBox(activeGridElements);
        ComboBox playerComboBox = view.getPlayerComboBox(activeGridElements);

        if (!pileComboBox.isDisabled()) {
            gameRule.setPile((Pile) pileComboBox.getValue());
        }
        if (!numCardsTextField.isDisabled()) {
            gameRule.setNumCards(Integer.parseInt(numCardsTextField.getText()));
        }
        if (!cardValueComboBox.isDisabled()) {
            gameRule.setCardValue(cardValueComboBox.getValue().toString());
        }
        if (!cardSuitComboBox.isDisabled()) {
            gameRule.setCardSuit(cardSuitComboBox.getValue().toString());
        }
        if (!playerComboBox.isDisabled()) {
            gameRule.setPlayer(playerComboBox.getValue().toString());
        }
    }


    private void setRuleSpecificInputs(GameRule gameRule) {
        ComboBox pileComboBox = view.getPileComboBox(activeGridElements);
        TextField numCardsTextField = view.getNumCardsTextField(activeGridElements);
        ComboBox cardValueComboBox = view.getCardValueComboBox(activeGridElements);
        ComboBox cardSuitComboBox = view.getCardSuitComboBox(activeGridElements);
        ComboBox playerComboBox = view.getPlayerComboBox(activeGridElements);

        if (!pileComboBox.isDisabled()) {
            pileComboBox.setValue(gameRule.getPile());
        }
        if (!numCardsTextField.isDisabled()) {
            numCardsTextField.setText(Integer.toString(gameRule.getNumCards()));
        }
        if (!cardValueComboBox.isDisabled()) {
            cardValueComboBox.setValue(gameRule.getCardValue());
        }
        if (!cardSuitComboBox.isDisabled()) {
            cardSuitComboBox.setValue(gameRule.getCardSuit());
        }
        if (!playerComboBox.isDisabled()) {
            playerComboBox.setValue(gameRule.getPlayer());
        }
    }


    private void setRuleDescription(GameRule gameRule) {
        String ruleDescription = view.getDescriptionTextField(activeGridElements).getText();
        if (!(ruleDescription == null || ruleDescription.isEmpty())) {
            gameRule.setDescription(ruleDescription);
        }
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
        RuleElementRectangle r = view.getEditorDrawingPane().getRectByCoordinates(e.getX(), e.getY());

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

            if (!view.getEditorDrawingPane().isRoot(r)) {
                view.getUpdateEventButton().setDisable(false);
                view.getAddEventButton().setDisable(true);
                view.getDeleteEventButton().setDisable(false);
            }

            view.getEventTypeComboBox().setValue(r.getGameRule());
            view.getEventNameTextField().setText(r.getName());
            view.getEventDescriptionTextField().setText(r.getGameRule().getDescription());
            view.getEventPreviousRuleTextField().setText(getPreviousRuleText(r));
        } else if (r.getRuleType() == GameRuleType.ACTION) { // rectangle is for an action
            view.getEventsPane().setExpanded(false);
            view.getActionsPane().setExpanded(true);

            view.getUpdateActionButton().setDisable(false);
            view.getAddActionButton().setDisable(true);
            view.getDeleteActionButton().setDisable(false);

            view.getActionTypeComboBox().setValue(r.getGameRule());
            view.getActionNameTextField().setText(r.getName());
            view.getActionDescriptionTextField().setText(r.getGameRule().getDescription());
            view.getActionPreviousRuleTextField().setText(getPreviousRuleText(r));
        }
        setRuleSpecificInputs(r.getGameRule());
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





    private GameRule getGameRuleFromComboBox() {
        GameRule gameRule = (GameRule) view.getTypeComboBox(activeGridElements).getValue();
        String gameRuleName = gameRule.getFullClassName();

        try {
            Class gameRuleClass = Class.forName(gameRuleName);
            Object gameRuleObject = gameRuleClass.getConstructor().newInstance();
            GameRule gameRuleInstance = gameRule.getClass().cast(gameRuleObject);
            return gameRuleInstance;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private String getPreviousRuleText(RuleElementRectangle r) {
        String previousRulesStr = "";
        if (r.getPreRules().size() > 0) {
            for (RuleElementRectangle preRule : r.getPreRules()) {
                previousRulesStr += preRule.getName() + "; ";
            }
            previousRulesStr = previousRulesStr.substring(0, previousRulesStr.length()-2);
        } else {
            previousRulesStr = "N/A";
        }
        return previousRulesStr;
    }


    private boolean previousRuleNotFoundValidation() {
        String previousRuleName = view.getPreviousRuleTextField(activeGridElements).getText();
        RuleElementRectangle previousRect = view.getEditorDrawingPane().getRectByName(previousRuleName);
        if (previousRect == null) {
            showPreviousRuleNotFoundErrorAlert(previousRuleName);
            return false;
        }
        return true;
    }


    private boolean ruleTypeNotSelectedValidation() {
        ComboBox typeComboBox = view.getTypeComboBox(activeGridElements);
        if (typeComboBox.getValue() == null) {
            showRuleTypeNotSelectedErrorAlert();
            return false;
        }
        return true;
    }


    private boolean ruleTypeIsOnGameStartValidation() {
        ComboBox typeComboBox = view.getTypeComboBox(activeGridElements);
        if (typeComboBox.getValue() instanceof OnGameStartEvent) {
            showRuleTypeIsOnGameStartErrorAlert();
            return false;
        }
        return true;
    }


    private boolean ruleNameEmptyValidation() {
        String ruleName = view.getNameTextField(activeGridElements).getText();
        if (ruleName == null || ruleName.isEmpty()) {
            showRuleNameEmptyErrorAlert();
            return false;
        }
        return true;
    }


    private boolean ruleNameExistsValidation() {
        String ruleName = view.getNameTextField(activeGridElements).getText();
        RuleElementRectangle r = view.getEditorDrawingPane().getRectByName(ruleName);
        // Need this extensive check due to Update.
        // If trying to add/edit Game Start, show the error.
        // If the rectangle exists but is clicked, proceed (return true). This means an update is being attempted.
        // Updates are not allowed for Game Start, hence the immediate check.
        if (ruleName.equals(DrawingPane.ROOT_NAME) || (r != null && !r.isClicked())) {
            showRuleNameExistsErrorAlert(ruleName);
            return false;
        }
        return true;
    }


    private boolean pileEmptyValidation() {
        ComboBox pileComboBox = view.getPileComboBox(activeGridElements);
        if (!pileComboBox.isDisabled()) {
            if (pileComboBox.getValue() == null || pileComboBox.getValue().equals("")) {
                showSpecificSettingEmptyErrorAlert("Pile");
                return false;
            }
        }
        return true;
    }


    private boolean numCardsEmptyValidation() {
        TextField numCardsTextField = view.getNumCardsTextField(activeGridElements);
        if (!numCardsTextField.isDisabled()) {
            if (numCardsTextField.getText() == null || numCardsTextField.getText().isEmpty()) {
                showSpecificSettingEmptyErrorAlert("Number of Cards");
                return false;
            }
        }
        return true;
    }


    private boolean cardValueEmptyValidation() {
        ComboBox cardValueComboBox = view.getCardValueComboBox(activeGridElements);
        if (!cardValueComboBox.isDisabled()) {
            if (cardValueComboBox.getValue() == null || cardValueComboBox.getValue().equals("")) {
                showSpecificSettingEmptyErrorAlert("Card Value");
                return false;
            }
        }
        return true;
    }


    private boolean cardSuitEmptyValidation() {
        ComboBox cardSuitComboBox = view.getCardSuitComboBox(activeGridElements);
        if (!cardSuitComboBox.isDisabled()) {
            if (cardSuitComboBox.getValue() == null || cardSuitComboBox.getValue().equals("")) {
                showSpecificSettingEmptyErrorAlert("Card Suit");
                return false;
            }
        }
        return true;
    }


    private boolean playerEmptyValidation() {
        ComboBox playerComboBox = view.getPlayerComboBox(activeGridElements);
        if (!playerComboBox.isDisabled()) {
            if (playerComboBox.getValue() == null || playerComboBox.getValue().equals("")) {
                showSpecificSettingEmptyErrorAlert("Player(s)");
                return false;
            }
        }
        return true;
    }


    private boolean numberFieldValidation() {
        TextField numCardsTextField = view.getNumCardsTextField(activeGridElements);
        if (!numCardsTextField.isDisabled()) {
            if (!numCardsTextField.getText().matches("[0-9]*")) {
                showNumberErrorAlert();
                return false;
            }
        }
        return true;
    }


    private boolean runAllValidations() {
        ComboBox ruleTypeComboBox = view.getTypeComboBox(activeGridElements);
        TextField ruleNameTextField = view.getNameTextField(activeGridElements);
        TextField ruleDescriptionTextField = view.getDescriptionTextField(activeGridElements);
        TextField previousRuleNameTextField = view.getPreviousRuleTextField(activeGridElements);
        ComboBox pileComboBox = view.getPileComboBox(activeGridElements);
        TextField numCardsTextField = view.getNumCardsTextField(activeGridElements);
        ComboBox cardValueComboBox = view.getCardValueComboBox(activeGridElements);
        ComboBox cardSuitComboBox = view.getCardSuitComboBox(activeGridElements);
        ComboBox playerComboBox = view.getPlayerComboBox(activeGridElements);

        String ruleTypeName = ruleTypeComboBox.getValue().toString();
        if (!previousRuleNotFoundValidation()) {return false;}
        if (!ruleTypeNotSelectedValidation()) {return false;}
        if (!ruleTypeIsOnGameStartValidation()) {return false;}
        if (!ruleNameEmptyValidation()) {return false;}
        if (!ruleNameExistsValidation()) {return false;}
        if (!pileEmptyValidation()) {return false;}
        if (!numCardsEmptyValidation()) {return false;}
        if (!cardValueEmptyValidation()) {return false;}
        if (!cardSuitEmptyValidation()) {return false;}
        if (!playerEmptyValidation()) {return false;}
        if (!numberFieldValidation()) {return false;}

        return true;
    }

    private void showNumberErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "One or more number fields contain an invalid character (a non-number). Please enter a positive integer in all number fields.");
        alert.setTitle("Number Field Error");
        alert.setHeaderText("Number Field Contains A Non-Number");
        alert.showAndWait();
    }

    private void showPreviousRuleNotFoundErrorAlert(String previousRuleName) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The specified previous rule name cannot be found in editor: \n" + previousRuleName + "\nPlease enter a previous rule name that already exists in the rule tree. Note that the names are case-sensitive.");
        alert.setTitle("Previous Rule Error");
        alert.setHeaderText("Previous Rule Not Found");
        alert.showAndWait();
    }


    private void showRuleTypeNotSelectedErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a Game " + currentRuleType + " type from the drop-down box.");
        alert.setTitle(currentRuleType + " Type Error");
        alert.setHeaderText(currentRuleType + " Type Not Selected");
        alert.showAndWait();
    }


    private void showRuleTypeIsOnGameStartErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Only one OnGameStartEvent is allowed in a game. Please select another Event Type.");
        alert.setTitle("Duplicate OnGameStartEvent Error");
        alert.setHeaderText("OnGameStartEvent Selected As Event Type");
        alert.showAndWait();
    }


    private void showRuleNameEmptyErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter an " + currentRuleType + " name in the text field.");
        alert.setTitle(currentRuleType + " Name Error");
        alert.setHeaderText(currentRuleType + " Name Is Missing");
        alert.showAndWait();
    }


    private void showRuleNameExistsErrorAlert(String eventName) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a unique " + currentRuleType + " name in the text field. The specified name already exists in the rule tree: \n" + eventName);
        alert.setTitle(currentRuleType + " Name Error");
        alert.setHeaderText(currentRuleType + " Name Is A Duplicate");
        alert.showAndWait();
    }


    private void showSpecificSettingEmptyErrorAlert(String settingName) {
        String ruleName = view.getTypeComboBox(activeGridElements).getValue().toString();
        Alert alert = new Alert(Alert.AlertType.WARNING, "An " + currentRuleType + "-Specific setting is unspecified.\nThe " + settingName + " setting is required for " + ruleName + ".");
        alert.setTitle(currentRuleType + "-Specific Setting Error");
        alert.setHeaderText("An " + currentRuleType + "-Specific Setting Is Empty");
        alert.showAndWait();
    }


    private void showUpdateSuccessfulAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, currentRuleType + " updated successfully!");
        alert.setTitle(currentRuleType + " Update Successful");
        alert.setHeaderText(currentRuleType + " Update Successful");
        alert.showAndWait();
    }
}
