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

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Card;
import model.CardOrientation;


/* referenced http://docs.oracle.com/javafx/2/ui_controls/list-view.htm */
/* Used to display Cards in Gameplay mode to the user. Each CardCell stores a Card
*  object.  The grpahic displayed to the user is dependent on the orientation of the Card. */
public class CardCell extends ListCell<Card> {

    public CardCell() {

    }

    @Override
    public void updateItem(Card card, boolean empty) {
        super.updateItem(card, empty);

        if (card != null) {
            try {
                ImageView cardImage;
                /* image displayed is dependent on orientation of Card (item) */
                if (card.getOrientation() == CardOrientation.UP) {
                    cardImage = new ImageView(new Image(card.getCardFaceAssetLocation()));
                } else {
                    cardImage = new ImageView(new Image(card.getCardBackAssetLocation()));
                }
                /* scale image */
                cardImage.setPreserveRatio(true);
                cardImage.setFitWidth(125);
                cardImage.setFitHeight(250);

                setGraphic(cardImage);
            } catch(Exception e){
                /* if we can't display the image asset */
                System.out.println("Could not display image for Card with Value: " + this.getItem().getValue() + " and Suit: " + this.getItem().getSuit());
                e.printStackTrace();
            }
        }
    }


}
