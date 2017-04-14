/*
* Requirements mandating inclusion:
* */
package view.Gameplay;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.*;
import model.Piles.Pile;


public class PileView extends ListView<Card> {

    private ObservableList<Card> items;

    /* set the view to be horizontal or vertical */
    public PileView(Orientation orientation){
        items = FXCollections.observableArrayList();
        this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        items.add(new Card(PlayingCard.ACE, Suit.HEART));
        items.add(new Card(PlayingCard.ACE, Suit.SPADE));
        items.add(new Card(PlayingCard.FOUR, Suit.HEART));
        items.add(new Card(PlayingCard.SEVEN, Suit.SPADE));
        items.add(new Card(PlayingCard.THREE, Suit.HEART));
        items.add(new Card(PlayingCard.TEN, Suit.SPADE));
        items.add(new Card(PlayingCard.ACE, Suit.HEART));
        items.add(new Card(PlayingCard.ACE, Suit.SPADE));
        items.add(new Card(PlayingCard.FOUR, Suit.HEART));
        items.add(new Card(PlayingCard.SEVEN, Suit.SPADE));
        items.add(new Card(PlayingCard.THREE, Suit.HEART));
        items.add(new Card(PlayingCard.TEN, Suit.SPADE));

        this.setItems(items);
        this.setOrientation(orientation);

        this.setCellFactory(new Callback<ListView<Card>, ListCell<Card>>() {
            @Override
            public ListCell<Card> call(ListView<Card> list) {
                return new CardCell();
            }
        });



    }

    public void showPile(Pile pile){
        /* change the displayed Pile (Cards displayed) */

    }
}
