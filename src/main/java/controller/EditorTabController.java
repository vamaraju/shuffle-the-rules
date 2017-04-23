/*
* Requirements mandating inclusion:
* */

package controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.GameActions.*;
import model.GameEvents.*;
import model.GameRule;
import model.GameRuleType;
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


    /**
     * Populates and returns an ArrayList of Game Events (strings) for use in drop-down menus.
     * Populated based on the contents of the model/GameEvents directory.
     *
     * @return An ArrayList of Game Event names (strings).
     */
    public ArrayList<GameEvent> getEventList() {
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

        return gameEvents;
    }


    /**
     * Populates and returns an ArrayList of Game Actions (strings) for use in drop-down menus.
     * Populated based on the contents of the model/GameActions directory.
     *
     * @return An ArrayList of Game Actions names (strings).
     */
    public ArrayList<GameAction> getActionList() {
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

        return gameActions;
    }


    /**
     * Onclick listener for the "Add Event" button in the Editor tab, Events sub-tab.
     * Adds a rectangle with text to the drawing pane, as well as any connecting lines to other rectangles.
     *
     * @param e
     */
    public void onAddEventButtonClick(Event e) {
        addButtonClick(GameRuleType.EVENT);
    }


    /**
     * Onclick listener for the "Add Action" button in the Editor tab, Actions sub-tab.
     * Adds a rectangle with text to the drawing pane, as well as any connecting lines to other rectangles.
     *
     * @param e
     */
    public void onAddActionButtonClick(Event e){
        addButtonClick(GameRuleType.ACTION);
    }


    /**
     * Logic for "Add Event" and "Add Action" button onclick listeners.
     *
     * @param ruleType The GameRuleType (either EVENT or ACTION) corresponding to the specific button click.
     */
    private void addButtonClick(GameRuleType ruleType) {
        Pane drawingPane = view.getEditorDrawingPane();
        ComboBox comboBox = null;
        TextField nameTextField = null;
        TextField descriptionTextField = null;
        TextField previousRuleTextField = null;

        switch (ruleType) {
            case EVENT:
                comboBox = view.getEventComboBox();
                nameTextField = view.getEventNameTextField();
                previousRuleTextField = view.getEventPreviousRuleTextField();
                descriptionTextField = view.getEventDescriptionTextField();
                break;
            case ACTION:
                comboBox = view.getActionComboBox();
                nameTextField = view.getActionNameTextField();
                previousRuleTextField = view.getActionPreviousRuleTextField();
                descriptionTextField = view.getActionDescriptionTextField();
                break;
        }

        String previousRuleName = previousRuleTextField.getText();
        RuleElementRectangle previousRect = findRectangleByName(previousRuleName);
        if (previousRect == null) {
            showPreviousRuleNotFoundErrorAlert(previousRuleName);
            return;
        }

        String ruleName = nameTextField.getText();
        if (ruleName == null || ruleName.equals("")) {
            showRuleNameEmptyErrorAlert(ruleType);
            return;
        } else if (findRectangleByName(ruleName) != null) {
            showRuleNameExistsErrorAlert(ruleType, ruleName);
            return;
        }

        if (comboBox.getValue() == null) {
            showRuleTypeNotSelectedErrorAlert(ruleType);
            return;
        }

        GameRule gameRule = null;
        switch (ruleType) {
            case EVENT:
                gameRule = (GameEvent) comboBox.getValue();
                break;
            case ACTION:
                gameRule = (GameAction) comboBox.getValue();
                break;
        }

        String ruleDescription = descriptionTextField.getText();
        if (!(ruleDescription == null || ruleDescription.equals(""))) {
            gameRule.setDescription(ruleDescription);
        }

        RuleElementRectangle r = new RuleElementRectangle(0, 0, nameTextField.getText(), ruleType);
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

            view.getClickedEventTypeHeader().setVisible(true);
            view.getClickedEventTypeValue().setVisible(true);
            view.getClickedEventNameHeader().setVisible(true);
            view.getClickedEventNameValue().setVisible(true);
            view.getClickedEventDescriptionHeader().setVisible(true);
            view.getClickedEventDescriptionValue().setVisible(true);
            view.getClickedEventPreviousEventHeader().setVisible(true);
            view.getClickedEventPreviousEventValue().setVisible(true);

            view.getClickedEventTypeValue().setText(r.getGameRuleName());
            view.getClickedEventNameValue().setText(r.getText());
            view.getClickedEventDescriptionValue().setText(r.getGameRule().getDescription());
            view.getClickedEventPreviousEventValue().setText(getPreviousRuleText(r));
        } else if (r.getRuleType() == GameRuleType.ACTION) { // rectangle is for an action
            view.getEventsPane().setExpanded(false);
            view.getActionsPane().setExpanded(true);

            view.getClickedActionTypeHeader().setVisible(true);
            view.getClickedActionTypeValue().setVisible(true);
            view.getClickedActionNameHeader().setVisible(true);
            view.getClickedActionNameValue().setVisible(true);
            view.getClickedActionDescriptionHeader().setVisible(true);
            view.getClickedActionDescriptionValue().setVisible(true);
            view.getClickedActionPreviousActionHeader().setVisible(true);
            view.getClickedActionPreviousActionValue().setVisible(true);

            view.getClickedActionTypeValue().setText(r.getGameRuleName());
            view.getClickedActionNameValue().setText(r.getText());
            view.getClickedActionDescriptionValue().setText(r.getGameRule().getDescription());
            view.getClickedActionPreviousActionValue().setText(getPreviousRuleText(r));
        }
    }


    private void rectangleUnclick(RuleElementRectangle r) {
        r.setClicked(false);
        r.setStroke(r.getDefaultBorderColor());

        if (r.getRuleType() == GameRuleType.EVENT) { // rectangle is for an event
            view.getClickedEventTypeHeader().setVisible(false);
            view.getClickedEventTypeValue().setVisible(false);
            view.getClickedEventNameHeader().setVisible(false);
            view.getClickedEventNameValue().setVisible(false);
            view.getClickedEventDescriptionHeader().setVisible(false);
            view.getClickedEventDescriptionValue().setVisible(false);
            view.getClickedEventPreviousEventHeader().setVisible(false);
            view.getClickedEventPreviousEventValue().setVisible(false);
        } else if (r.getRuleType() == GameRuleType.ACTION) { // rectangle is for an action
            view.getClickedActionTypeHeader().setVisible(false);
            view.getClickedActionTypeValue().setVisible(false);
            view.getClickedActionNameHeader().setVisible(false);
            view.getClickedActionNameValue().setVisible(false);
            view.getClickedActionDescriptionHeader().setVisible(false);
            view.getClickedActionDescriptionValue().setVisible(false);
            view.getClickedActionPreviousActionHeader().setVisible(false);
            view.getClickedActionPreviousActionValue().setVisible(false);
        }
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
}
