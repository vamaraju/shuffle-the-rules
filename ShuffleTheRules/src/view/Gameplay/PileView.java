/*
* Requirements mandating inclusion:
* */
package view.Gameplay;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import model.GameplayMessage;


public class PileView extends ListView {

    private ObservableList<GameplayMessage> items;

    /* set the view to be horizontal or vertical */
    public PileView(Orientation orientation){
        items = FXCollections.observableArrayList();
        this.setItems(items);
        this.setOrientation(orientation);


    }
}
