/*
* Requirements mandating inclusion:
*
* 3.2.2.1.3.1 User can view their Hand.
* 3.2.2.1.3.2 User can sort their Hand.
* 3.2.2.2.3.1 User can select Card(s) from Hand to play.
* 3.2.2.2.3.2 User can select Card(s) from Hand to discard.
* 3.2.2.2.3.4 User can select Card(s) to swap between Piles.
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

    private ObservableList<Card> cards;

    private PileViewController pileViewController = new PileViewController(this);

    /* One input argument - orientaton of the PileView.
     * horizontal - Orientation.HORIZONTAL
     * vertical - Orientation.VERTICAL */
    public PileView(Orientation orientation){
        cards = FXCollections.observableArrayList();
        this.setItems(cards);

        /* want user to be able to select multiple Cards (CardCells)*/
        this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        this.setOrientation(orientation);

        pileViewController = new PileViewController(this);

        /* for easy testing. Sorry, will be deleted! */
        /*
        cards.add(new Card(PlayingCard.ACE, Suit.HEART));
        cards.add(new Card(PlayingCard.ACE, Suit.SPADE));
        cards.add(new Card(PlayingCard.FOUR, Suit.CLUB));
        cards.add(new Card(PlayingCard.SEVEN, Suit.SPADE));
        cards.add(new Card(PlayingCard.THREE, Suit.CLUB));
        cards.add(new Card(PlayingCard.TEN, Suit.CLUB));
        cards.add(new Card(PlayingCard.ACE, Suit.DIAMOND));
        cards.add(new Card(PlayingCard.ACE, Suit.CLUB));
        cards.add(new Card(PlayingCard.FOUR, Suit.HEART));
        cards.add(new Card(PlayingCard.SEVEN, Suit.DIAMOND));
        cards.add(new Card(PlayingCard.THREE, Suit.HEART));
        cards.add(new Card(PlayingCard.TEN, Suit.SPADE));
*/


        this.setCellFactory(new Callback<ListView<Card>, ListCell<Card>>() {
            @Override
            public ListCell<Card> call(ListView<Card> list) {
                return new CardCell();
            }
        });

    }

    public void updatePile(Pile pile){
        /* change the displayed Pile (Cards displayed) */
        this.cards = FXCollections.observableArrayList(pile.getCards());
    }

    /* TODO change to List? */
    public ObservableList<Card> getSelectedCards(){
        return this.getSelectionModel().getSelectedItems();
    }
}
