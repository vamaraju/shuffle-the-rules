package controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.GameActions.*;
import model.GameEvents.*;
import model.GameRule;
import model.RuleElementRectangle;
import view.EditorTabView;

import java.lang.reflect.InvocationTargetException;
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
        gameEvents.add(new OnGameStartEvent());
        gameEvents.add(new OnHandEmptyEvent());
        gameEvents.add(new OnHandFullEvent());
        gameEvents.add(new OnPileClickEvent());
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
        addButtonClick("event");
    }


    /**
     * Onclick listener for the "Add Action" button in the Editor tab, Actions sub-tab.
     * Adds a rectangle with text to the drawing pane, as well as any connecting lines to other rectangles.
     *
     * @param e
     */
    public void onAddActionButtonClick(Event e){
        addButtonClick("action");
    }


    /**
     * Logic for "Add Event" and "Add Action" button onclick listeners.
     *
     * @param ruleType A string that is either "event" or "action", corresponding to the specific button click (rule).
     */
    private void addButtonClick(String ruleType) {
        Pane drawingPane = view.getEditorDrawingPane();
        ComboBox comboBox = null;
        TextField nameTextField = null;
        TextField previousRuleTextField = null;

        if (ruleType.equals("event")) {
            comboBox = view.getEventComboBox();
            nameTextField = view.getEventNameTextField();
            previousRuleTextField = view.getEventPreviousRuleTextField();
        } else if (ruleType.equals("action")) {
            comboBox = view.getActionComboBox();
            nameTextField = view.getActionNameTextField();
            previousRuleTextField = view.getActionPreviousRuleTextField();
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
        if (ruleType.equals("event")) {
            gameRule = (GameEvent) comboBox.getValue();
        } else if (ruleType.equals("action")) {
            gameRule = (GameAction) comboBox.getValue();
        }

        RuleElementRectangle r = new RuleElementRectangle(0, 0, nameTextField.getText(), ruleType);
        r.setGameRule(gameRule);
        r.addPreRule(previousRect);
        setRectXPlacement(r, previousRect);
        setRectYPlacement(r, previousRect);

        Line l = new Line(previousRect.getCenterX(), previousRect.getEndY(), r.getCenterX(), r.getY());
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

        drawingPane.getChildren().addAll(r, r.getTextObj(), l);
    }


    public void addOnGameStart(Pane drawingPane) {
        RuleElementRectangle r = new RuleElementRectangle(160, 50, "Game Start", "event");
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


    private void onRectangleClicked(MouseEvent e) {
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
        r.setStroke(Color.GREY);

        if (r.getDefaultBorderColor() == Color.BLUE) {  // rectangle is for an event
            view.getClickedEventTypeHeader().setVisible(true);
            view.getClickedEventTypeValue().setVisible(true);
            view.getClickedEventNameHeader().setVisible(true);
            view.getClickedEventNameValue().setVisible(true);
            view.getClickedEventPreviousEventHeader().setVisible(true);
            view.getClickedEventPreviousEventValue().setVisible(true);

            view.getClickedEventTypeValue().setText(r.getGameRuleName());
            view.getClickedEventNameValue().setText(r.getText());
            view.getClickedEventPreviousEventValue().setText(getPreviousRuleText(r));
        } else if (r.getDefaultBorderColor() == Color.RED) { // rectangle is for an action
            view.getClickedActionTypeHeader().setVisible(true);
            view.getClickedActionTypeValue().setVisible(true);
            view.getClickedActionNameHeader().setVisible(true);
            view.getClickedActionNameValue().setVisible(true);
            view.getClickedActionPreviousEventHeader().setVisible(true);
            view.getClickedActionPreviousEventValue().setVisible(true);

            view.getClickedActionTypeValue().setText(r.getGameRuleName());
            view.getClickedActionNameValue().setText(r.getText());
            view.getClickedActionPreviousEventValue().setText(getPreviousRuleText(r));
        }
    }


    private void rectangleUnclick(RuleElementRectangle r) {
        r.setClicked(false);
        r.setStroke(r.getDefaultBorderColor());

        if (r.getDefaultBorderColor() == Color.BLUE) { // rectangle is for an event
            view.getClickedEventTypeHeader().setVisible(false);
            view.getClickedEventTypeValue().setVisible(false);
            view.getClickedEventNameHeader().setVisible(false);
            view.getClickedEventNameValue().setVisible(false);
            view.getClickedEventPreviousEventHeader().setVisible(false);
            view.getClickedEventPreviousEventValue().setVisible(false);
        } else if (r.getDefaultBorderColor() == Color.RED) { // rectangle is for an action
            view.getClickedActionTypeHeader().setVisible(false);
            view.getClickedActionTypeValue().setVisible(false);
            view.getClickedActionNameHeader().setVisible(false);
            view.getClickedActionNameValue().setVisible(false);
            view.getClickedActionPreviousEventHeader().setVisible(false);
            view.getClickedActionPreviousEventValue().setVisible(false);
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


    private void showRuleTypeNotSelectedErrorAlert(String ruleType) {
        String ruleTypeCapitalized = ruleType.substring(0, 1).toUpperCase() + ruleType.substring(1);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a game " + ruleType + " type from the drop-down box.");
        alert.setTitle(ruleTypeCapitalized + " Type Error");
        alert.setHeaderText(ruleTypeCapitalized + " Type Not Selected");
        alert.showAndWait();
    }


    private void showRuleNameEmptyErrorAlert(String ruleType) {
        String ruleTypeCapitalized = ruleType.substring(0, 1).toUpperCase() + ruleType.substring(1);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter an " + ruleType + " name in the text field.");
        alert.setTitle(ruleTypeCapitalized + " Name Error");
        alert.setHeaderText(ruleTypeCapitalized + " Name Is Missing");
        alert.showAndWait();
    }


    private void showRuleNameExistsErrorAlert(String ruleType, String eventName) {
        String ruleTypeCapitalized = ruleType.substring(0, 1).toUpperCase() + ruleType.substring(1);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a unique " + ruleType + " name in the text field. The specified name already exists in the rule tree: \n" + eventName);
        alert.setTitle(ruleTypeCapitalized + " Name Error");
        alert.setHeaderText(ruleTypeCapitalized + " Name Is A Duplicate");
        alert.showAndWait();
    }
}
