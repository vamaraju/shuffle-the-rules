/*
* Requirements mandating inclusion:
* */
package model;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


/* referenced http://docs.oracle.com/javafx/2/ui_controls/list-view.htm */
public class CardCell extends ListCell<Card> {

    public CardCell(){
        //this.setOnMouseClicked(this::cellClicked);

    }

   /* private void cellClicked(MouseEvent mouseEvent) {
        System.out.println("cell originally is " + this.isSelected());
        //if(this.isSelected()){
           // this.updateSelected(false);
          //  System.out.println("cell deselected" + this.getItem().getSuit() + " value " + this.getItem().getValue());

        //}else{
            this.updateSelected(true);
            System.out.println("cell selected" + this.getItem().getSuit() + " value " + this.getItem().getValue());
        //}
    }
*/
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
