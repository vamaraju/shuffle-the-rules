package controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.GameEvents.GameEvent;
import model.GameEvents.OnGameStartEvent;
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
    public ArrayList<String> getEventList() {
        ArrayList<String> gameEventNames = new ArrayList<>();

        gameEventNames.add("OnCardDrawnEvent");
        gameEventNames.add("OnCardPlayedEvent");
        gameEventNames.add("OnGameEndEvent");
        gameEventNames.add("OnGameStartEvent");
        gameEventNames.add("OnHandEmptyEvent");
        gameEventNames.add("OnHandFullEvent");
        gameEventNames.add("OnPileClickEvent");
        gameEventNames.add("OnPileEmptyEvent");
        gameEventNames.add("OnPileFullEvent");
        gameEventNames.add("OnPlayerClickEvent");
        gameEventNames.add("OnPlayerTurnEvent");
        gameEventNames.add("OnRoundEndEvent");
        gameEventNames.add("OnRoundStartEvent");
        gameEventNames.add("OnTurnEndEvent");
        gameEventNames.add("OnTurnStartEvent");

        return gameEventNames;
    }


    /**
     * Populates and returns an ArrayList of Game Actions (strings) for use in drop-down menus.
     * Populated based on the contents of the model/GameActions directory.
     *
     * @return An ArrayList of Game Actions names (strings).
     */
    public ArrayList<String> getActionList() {
        ArrayList<String> gameActionNames = new ArrayList<>();

        gameActionNames.add("DealCardAction");
        gameActionNames.add("DrawCardAction");
        gameActionNames.add("EndGameAction");
        gameActionNames.add("EndTurnAction");
        gameActionNames.add("MoveCardAction");
        gameActionNames.add("PlaceCardAction");
        gameActionNames.add("ShufflePileAction");
        gameActionNames.add("SkipTurnAction");
        gameActionNames.add("StartTurnAction");

        return gameActionNames;
    }


    public void onAddEventButtonClick(Event event) {
        Pane drawingPane = view.getEditorDrawingPane();
        ComboBox eventComboBox = view.getEventComboBox();
        TextField eventNameTextField = view.getEventNameTextField();
        TextField eventPreviousRuleTextField = view.getEventPreviousRuleTextField();

        String previousRuleName = eventPreviousRuleTextField.getText();
        RuleElementRectangle previousRect = findRectangleByName(previousRuleName);
        if (previousRect == null) {
            showPreviousRuleErrorAlert(previousRuleName);
            return;
        }

        if (eventComboBox.getValue() == null) {
            showEventTypeErrorAlert();
            return;
        }
        String gameEventClassName = eventComboBox.getValue().toString();

        String eventName = eventNameTextField.getText();
        if (eventName == null || eventName.equals("")) {
            showEventNameEmptyErrorAlert();
            return;
        } else if (findRectangleByName(eventName) != null) {
            showEventNameExistsErrorAlert(eventName);
            return;
        }

        RuleElementRectangle r = new RuleElementRectangle(0, 0, eventNameTextField.getText(), "event");
        r.setGameRule(this.getGameEventFromName(gameEventClassName));
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

        r.setOnMouseClicked(this::onEventRectangleClicked);
        r.getTextObj().setOnMouseClicked(this::onEventRectangleClicked);

        drawingPane.getChildren().addAll(r, r.getTextObj(), l);
    }


    public void onAddActionButtonClick(Event event){
        Pane drawingPane = view.getEditorDrawingPane();
        TextField actionIdTextField = view.getActionNameTextField();

        Rectangle r = new Rectangle(100, 200, 100, 50);
        r.setFill(Color.WHITE);
        r.setStrokeWidth(2);
        r.setStroke(Color.RED);

        StackPane s = new StackPane();
        s.getChildren().addAll(r, new Text(actionIdTextField.getText()));

        drawingPane.getChildren().add(s);
    }





    private GameEvent getGameEventFromName(String gameEventClassName) {
        try {
            Class gameEventClass = Class.forName("model.GameEvents." + gameEventClassName);
            GameEvent gameEvent = (GameEvent) gameEventClass.getConstructor().newInstance();
            return gameEvent;
        } catch (ClassNotFoundException e) {
            System.out.println("Error converting combobox GameEvent name to a class: Class " + gameEventClassName + "not found.");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("Error getting constructor for GameEvent: No constructor found for Class " + gameEventClassName);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("Error creating instance of GameEvent: Instantiation failed for Class " + gameEventClassName);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Error accessing instance of GameEvent: Instantiation failed for Class " + gameEventClassName);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("Error invoking instance of GameEvent: Instantiation failed for Class " + gameEventClassName);
            e.printStackTrace();
        }
        return null;
    }

    public void addOnGameStart(Pane drawingPane) {
        RuleElementRectangle r = new RuleElementRectangle(160, 50, "Game Start", "event");
        r.setGameRule(new OnGameStartEvent());

        drawingPane.getChildren().addAll(r, r.getTextObj());

        r.setOnMouseClicked(this::onEventRectangleClicked);
        r.getTextObj().setOnMouseClicked(this::onEventRectangleClicked);
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


    private void onEventRectangleClicked(MouseEvent e) {
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

        view.getClickedEventTypeHeader().setVisible(true);
        view.getClickedEventTypeValue().setVisible(true);
        view.getClickedEventNameHeader().setVisible(true);
        view.getClickedEventNameValue().setVisible(true);
        view.getClickedEventPreviousEventHeader().setVisible(true);
        view.getClickedEventPreviousEventValue().setVisible(true);

        view.getClickedEventTypeValue().setText(r.getGameRuleName());
        view.getClickedEventNameValue().setText(r.getText());
        view.getClickedEventPreviousEventValue().setText("previous event");
    }

    private void rectangleUnclick(RuleElementRectangle r) {
        r.setClicked(false);
        r.setStroke(r.getDefaultBorderColor());

        view.getClickedEventTypeHeader().setVisible(false);
        view.getClickedEventTypeValue().setVisible(false);
        view.getClickedEventNameHeader().setVisible(false);
        view.getClickedEventNameValue().setVisible(false);
        view.getClickedEventPreviousEventHeader().setVisible(false);
        view.getClickedEventPreviousEventValue().setVisible(false);
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


    private void showPreviousRuleErrorAlert(String previousRuleName) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The specified previous rule name cannot be found in editor: \n" + previousRuleName + "\nPlease enter a previous rule name that already exists in the rule tree. Note that the names are case-sensitive.");
        alert.setTitle("Previous Rule Error");
        alert.setHeaderText("Previous Rule Not Found");
        alert.showAndWait();
    }


    private void showEventTypeErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a game event type from the drop-down box.");
        alert.setTitle("Event Type Error");
        alert.setHeaderText("Event Type Not Selected");
        alert.showAndWait();
    }


    private void showEventNameEmptyErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter an event name in the text field.");
        alert.setTitle("Event Name Error");
        alert.setHeaderText("Event Name Is Missing");
        alert.showAndWait();
    }


    private void showEventNameExistsErrorAlert(String eventName) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a unique event name in the text field. The specified name already exists in the rule tree: \n" + eventName);
        alert.setTitle("Event Name Error");
        alert.setHeaderText("Event Name Is A Duplicate");
        alert.showAndWait();
    }
}
