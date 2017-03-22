package controller;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.w3c.dom.css.Rect;
import view.EditorTabView;

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
    public ArrayList<String> getGameEventList() {
        ArrayList<String> gameEventNames = new ArrayList<>();

        gameEventNames.add("onCardDrawn");
        gameEventNames.add("onCardPlayed");
        gameEventNames.add("onGameEnd");
        gameEventNames.add("onGameStart");
        gameEventNames.add("onHandEmpty");
        gameEventNames.add("onHandFull");
        gameEventNames.add("onPileClick");
        gameEventNames.add("onPileEmpty");
        gameEventNames.add("onPileFull");
        gameEventNames.add("onPlayerClick");
        gameEventNames.add("onPlayerTurn");
        gameEventNames.add("onRoundEnd");
        gameEventNames.add("onRoundStart");
        gameEventNames.add("onTurnEnd");
        gameEventNames.add("onTurnStart");

        return gameEventNames;
    }

    /**
     * Populates and returns an ArrayList of Game Actions (strings) for use in drop-down menus.
     * Populated based on the contents of the model/GameActions directory.
     *
     * @return An ArrayList of Game Actions names (strings).
     */
    public ArrayList<String> getGameActionList() {
        ArrayList<String> gameActionNames = new ArrayList<>();

        gameActionNames.add("DealCard");
        gameActionNames.add("DrawCard");
        gameActionNames.add("EndGame");
        gameActionNames.add("EndTurn");
        gameActionNames.add("MoveCard");
        gameActionNames.add("PlaceCard");
        gameActionNames.add("ShufflePile");
        gameActionNames.add("SkipTurn");
        gameActionNames.add("StartTurn");

        return gameActionNames;
    }

    public void onAddEventButtonClick(Event e){
        GridPane editorGrid = view.getEditorGrid();
        TextField eventIdTextField = view.getEventIdTextField();

        Rectangle r = new Rectangle(100, 50);
        r.setFill(Color.WHITE);
        r.setStrokeWidth(5);
        r.setStroke(Color.BLACK);

        StackPane s = new StackPane();
        s.getChildren().addAll(r, new Text(eventIdTextField.getText()));

        editorGrid.add(s, 0, 0);
    }

    public void onAddActionButtonClick(Event e){
        GridPane editorGrid = view.getEditorGrid();
        TextField actionIdTextField = view.getActionIdTextField();

        Rectangle r = new Rectangle(100, 50);
        r.setFill(Color.WHITE);
        r.setStrokeWidth(5);
        r.setStroke(Color.BLACK);

        StackPane s = new StackPane();
        s.getChildren().addAll(r, new Text(actionIdTextField.getText()));

        editorGrid.add(s, 1, 0);
    }

}
