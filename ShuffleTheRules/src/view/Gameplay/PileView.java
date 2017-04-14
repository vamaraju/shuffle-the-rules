/*
* Requirements mandating inclusion:
* */
package view.Gameplay;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    /* referenced http://docs.oracle.com/javafx/2/ui_controls/list-view.htm */
    static class CardCell extends ListCell<Card> {
        @Override
        public void updateItem(Card item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                ImageView cardImage;
                if (item.getOrientation() == CardOrientation.UP ) {
                    cardImage = new ImageView(new Image(item.getCardFaceAssetLocation()));
                }else{
                    cardImage = new ImageView(new Image(item.getCardBackAssetLocation()));
                }
                cardImage.setPreserveRatio(true);
                cardImage.setFitWidth(150);
                cardImage.setFitHeight(350);

                setGraphic(cardImage);
                this.setTooltip(new Tooltip("Value: " + item.getValue().toString() + " Suit: " +item.getSuit()));
            }
        }
    }

    public void showPile(Pile pile){
        /* change the displayed Pile (Cards displayed) */

    }
}
