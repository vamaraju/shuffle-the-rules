package controller;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.GameActions.DealCardAction;
import model.GameActions.GameAction;
import model.GameEvents.GameEvent;
import model.GameEvents.OnCardDrawnEvent;
import model.GameRule;
import model.RuleElementRectangle;
import org.w3c.dom.css.Rect;
import view.EditorTabView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class EditorTabController {

    private EditorTabView view;

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

    public void onAddEventButtonClick(Event event){
        Pane drawingPane = view.getEditorDrawingPane();
        TextField eventIdTextField = view.getEventIdTextField();
        ComboBox eventComboBox = view.getEventComboBox();

//        Text t = new Text(150, 250, eventIdTextField.getText());
//        t.setFont(new Font(15));
//        t.setWrappingWidth(100);

        RuleElementRectangle r = new RuleElementRectangle(100, 100, eventIdTextField.getText());
        r.setFill(Color.WHITE);
        r.setStrokeWidth(2);
        r.setStroke(Color.BLACK);

        String gameEventClassName = eventComboBox.getValue().toString();
        r.setGameRule(this.getGameEventFromName(gameEventClassName));
        System.out.println(r.getGameRule().getDescription());

        drawingPane.getChildren().addAll(r, r.getTextObj());

        r.setOnMousePressed(e -> {
            System.out.println("pressed mouse");
        });
    }

    public void onAddActionButtonClick(Event event){
        Pane drawingPane = view.getEditorDrawingPane();
        TextField actionIdTextField = view.getActionIdTextField();

        Rectangle r = new Rectangle(100, 200, 100, 50);
        r.setFill(Color.WHITE);
        r.setStrokeWidth(5);
        r.setStroke(Color.BLACK);

        StackPane s = new StackPane();
        s.getChildren().addAll(r, new Text(actionIdTextField.getText()));

        drawingPane.getChildren().add(s);
    }

    public void drawingPaneOnMousePressed(MouseEvent event) {
        Pane drawingPane = view.getEditorDrawingPane();

        if (!event.isPrimaryButtonDown()) {
            return;
        }

        Line curLine = new Line(
                event.getX(), event.getY(),
                event.getX(), event.getY()
        );
        drawingPane.getChildren().add(curLine);

        Rectangle r = new Rectangle(event.getX(), event.getY(), 100, 50);
        r.setFill(Color.WHITE);
        r.setStrokeWidth(5);
        r.setStroke(Color.BLACK);
        //drawingPane.getChildren().add(r);
    }

    public void drawingPaneOnMouseDragged(MouseEvent event) {
        if (!event.isPrimaryButtonDown()) {
            return;
        }

        Line curLine = new Line();
        Pane drawingPane = view.getEditorDrawingPane();

        if (curLine == null) {
            return;
        }

        curLine.setEndX(event.getX());
        curLine.setEndY(event.getY());

        double mx = Math.max(curLine.getStartX(), curLine.getEndX());
        double my = Math.max(curLine.getStartY(), curLine.getEndY());

        if (mx > drawingPane.getMinWidth()-100) {
            drawingPane.setMinWidth(mx+100);
        }

        if (my > drawingPane.getMinHeight()-100) {
            drawingPane.setMinHeight(my+100);
        }
    }

    public void drawingPaneOnMouseReleased(MouseEvent event) {
        Line curLine = null;
        Pane drawingPane = view.getEditorDrawingPane();
        for (int i = 0; i < drawingPane.getChildren().size(); i++) {
            //System.out.println(drawingPane.getChildren().get(i));
        }
        System.out.println("Current Line:");
        System.out.println(drawingPane.getChildren().get(drawingPane.getChildren().size()-1));
    }

    public GameEvent getGameEventFromName(String gameEventClassName) {
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
}
