package view.TableTab;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;


public class NewPileView extends VBox {


    public NewPileView(){

        /* Name - extract */
        HBox nameHBox = new HBox(4);
        Label name = new Label("Name:");
        TextField nameInput = new TextField();
        nameInput.setMaxSize(120, 20);
        nameHBox.getChildren().addAll(name, nameInput);

        /* Type - extract */
        HBox typeHBox = new HBox(4);
        Label type = new Label("Type:");

        /* max/min number Cards in Pile - extract */
        VBox pileCardCount = new VBox();
        Label numCards = new Label("Number Cards");
        /* min */
        HBox minNumCardsHBox = new HBox(4);
        Label minNumCards = new Label("min:");
        TextField minNumCardsInput = new TextField();
        minNumCardsInput.setMaxSize(50, 20);
        minNumCardsHBox.getChildren().addAll(minNumCards, minNumCardsInput);
        /* max */
        HBox maxNumCardsHBox = new HBox(4);
        Label maxNumCards = new Label("max:");
        TextField maxNumCardsInput = new TextField();
        maxNumCardsInput.setMaxSize(50, 20);
        maxNumCardsHBox.getChildren().addAll(maxNumCards, maxNumCardsInput);

        pileCardCount.getChildren().addAll(minNumCardsHBox, maxNumCardsHBox);

        Label gridCoordinates = new Label("Coordinates");
        Label xCoord = new Label("x:");
        Label yCoord = new Label("y:");
        Label playerAssociation = new Label("Player Association");
        Label viewable = new Label("Viewable By:");
        Label orientation = new Label("Orientation");
        Button addPileButton = new Button("Add Pile");
        this.getChildren().addAll(nameHBox, type, numCards, pileCardCount, gridCoordinates, xCoord, yCoord, playerAssociation, viewable, orientation, addPileButton);
    }

}
