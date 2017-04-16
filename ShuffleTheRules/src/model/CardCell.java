/*
* Requirements mandating inclusion:
*
* 3.2.2.1.3.1 User can view their Hand.
* 3.2.2.1.3.2 User can sort their Hand.
* 3.2.2.2.3.1 User can select Card(s) from Hand to play.
* 3.2.2.2.3.2 User can select Card(s) from Hand to discard.
* 3.2.2.2.3.4 User can select Card(s) to swap between Piles.
* */
package model;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/* referenced http://docs.oracle.com/javafx/2/ui_controls/list-view.htm */
public class CardCell extends ListCell<Card> {

    public CardCell(){

    }

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

        }
    }


}
