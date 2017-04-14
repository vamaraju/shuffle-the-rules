package view.Gameplay;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import model.GameplayMessage;

public class PileView extends Pane {

    private ObservableList<GameplayMessage> items = FXCollections.observableArrayList();
    private ListView<GameplayMessage> listView;

    /* set the view to be horizontal or vertical */
    public PileView(Orientation orientation){
        items = FXCollections.observableArrayList();
        listView = new ListView<>(items);
        listView.setOrientation(orientation);

        this.getChildren().addAll(listView);

    }
}
