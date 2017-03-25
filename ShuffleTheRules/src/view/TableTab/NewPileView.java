package view.TableTab;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

/*
* TODO
*   make look better - css or general font formatting/ spacing
*   spacing for input boxes needs work
*   text/input box alignment
*   sceen size affects view of menu
*   make things generic - ie. type menus
* */
public class NewPileView extends VBox {


    public NewPileView(){
        this.setSpacing(2);
        
        /* Name - extract */
        HBox nameHBox = new HBox(4);
        Label name = new Label("Name:");
        TextField nameInput = new TextField();
        nameInput.setMaxSize(120, 20);
        nameHBox.getChildren().addAll(name, nameInput);

        /* Type - extract */
        HBox typeHBox = new HBox(4);
        Label type = new Label("Type:");
        ChoiceBox typeChoiceInput = new ChoiceBox();
        typeChoiceInput.getItems().addAll("Hand", "Deck", "Discard", "None");
        typeHBox.getChildren().addAll(type, typeChoiceInput);


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

        pileCardCount.getChildren().addAll(numCards, minNumCardsHBox, maxNumCardsHBox);


        /* Corrdinates - extract */
        Label gridCoordinates = new Label("Coordinates");
        VBox pileCoordinates = new VBox();
        /* x */
        HBox xCoordHBox = new HBox(4);
        Label xCoord = new Label("x:");
        TextField xCoordInput = new TextField();
        xCoordInput.setMaxSize(30, 10);
        xCoordHBox.getChildren().addAll(xCoord, xCoordInput);
        /*y */
        Label yCoord = new Label("y:");
        HBox yCoordHBox = new HBox(4);
        TextField yCoordInput = new TextField();
        yCoordInput.setMaxSize(30, 10);
        yCoordHBox.getChildren().addAll(yCoord, yCoordInput);

        pileCoordinates.getChildren().addAll(gridCoordinates, xCoordHBox, yCoordHBox);

        /* Player Pile Association Settings */
        Label playerAssociation = new Label("Player Association");

        /* Pile Viewable Settings */
        HBox pileViewableHBox = new HBox(4);
        Label viewableBy = new Label("Viewable By:");

        ChoiceBox pileViewableChoiceInput = new ChoiceBox();
        pileViewableChoiceInput.getItems().addAll("All", "None");
        pileViewableHBox.getChildren().addAll(viewableBy, pileViewableChoiceInput);

        /* Pile Orientation Settings - for display purposes.  Will determine how top of Pile is displayed */
        HBox cardOrientationHBox = new HBox(4);
        Label orientation = new Label("Card Orientation");
        ChoiceBox cardOrientationChoiceInput = new ChoiceBox();
        cardOrientationChoiceInput.getItems().addAll("Face Up", "Face Down");
        cardOrientationHBox.getChildren().addAll(orientation, cardOrientationChoiceInput);

        /* TODO add anchor?*/
        Button addPileButton = new Button("Add Pile");
        this.getChildren().addAll(nameHBox, typeHBox, pileCardCount, pileCoordinates, playerAssociation, pileViewableHBox, cardOrientationHBox, addPileButton);
    }

}
