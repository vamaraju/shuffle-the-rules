/*
* Requirements mandating inclusion:
* */
package view.Gameplay;


import controller.PileViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.*;
import model.Piles.Pile;


public class PileView extends ListView<Card> {

    private ObservableList<Card> items;
    private PileViewController pileViewController;

    /* set the view to be horizontal or vertical */
    public PileView(Orientation orientation){
        items = FXCollections.observableArrayList();
        this.setItems(items);

        this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.setOrientation(orientation);

        pileViewController = new PileViewController(this);
        this.getSelectionModel().selectedItemProperty().addListener(pileViewController::changeDisplayedSuitCounts);

        /* for easy testing. Sorry, will be deleted! */
        items.add(new Card(PlayingCard.ACE, Suit.HEART));
        items.add(new Card(PlayingCard.ACE, Suit.SPADE));
        items.add(new Card(PlayingCard.FOUR, Suit.CLUB));
        items.add(new Card(PlayingCard.SEVEN, Suit.SPADE));
        items.add(new Card(PlayingCard.THREE, Suit.CLUB));
        items.add(new Card(PlayingCard.TEN, Suit.CLUB));
        items.add(new Card(PlayingCard.ACE, Suit.DIAMOND));
        items.add(new Card(PlayingCard.ACE, Suit.CLUB));
        items.add(new Card(PlayingCard.FOUR, Suit.HEART));
        items.add(new Card(PlayingCard.SEVEN, Suit.DIAMOND));
        items.add(new Card(PlayingCard.THREE, Suit.HEART));
        items.add(new Card(PlayingCard.TEN, Suit.SPADE));



        this.setCellFactory(new Callback<ListView<Card>, ListCell<Card>>() {
            @Override
            public ListCell<Card> call(ListView<Card> list) {
                return new CardCell();
            }
        });

        System.out.println(this.getSelectionModel().getSelectedItem());

    }

    public void updatePile(Pile pile){
        /* change the displayed Pile (Cards displayed) */
        this.items = FXCollections.observableArrayList(pile.getCards());
    }

    /* TODO change to List? */
    public ObservableList<Card> getSelectedCards(){
        return this.getSelectionModel().getSelectedItems();
    }
}
