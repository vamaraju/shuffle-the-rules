package view.TableTab;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

/*
* Class is responsible for drawing the settings in the Pile Settings Menu.
* It is in a separate class to
* TODO
*   make look better - css or general font formatting/ spacing
*   spacing for input boxes needs work
*   text/input box alignment
*   sceen size affects view of menu
*   make things generic - ie. type menus
* */
public class PileSettingsView extends VBox {

    private Button updatePileButton;
    private Button addPileButton;

    private TextField nameInput;
    private ChoiceBox typeChoiceInput;
    private TextField minNumCardsInput;
    private TextField maxNumCardsInput;

    private TextField xCoordInput;
    private TextField yCoordInput;
    /* TODO missing Pile Associations */
    /* private pileAssociations */

    private ChoiceBox pileViewableChoiceInput;
    private ChoiceBox cardOrientationChoiceInput;

    public PileSettingsView(){
        this.setSpacing(2);

        /* Name - extract */
        HBox nameHBox = new HBox(5);
        Label name = new Label("Name:");
        nameInput = new TextField();
        nameInput.setMaxSize(120, 20);
        nameHBox.getChildren().addAll(name, nameInput);

        /* Type - extract */
        HBox typeHBox = new HBox(4);
        Label type = new Label("Type:");
        typeChoiceInput = new ChoiceBox();
        typeChoiceInput.getItems().addAll("Hand", "Deck", "Discard", "None");
        typeHBox.getChildren().addAll(type, typeChoiceInput);


        /* max/min number Cards in Pile - extract */
        VBox pileCardCount = new VBox();
        Label numCards = new Label("Number Cards");
        /* min */
        HBox minNumCardsHBox = new HBox(4);
        Label minNumCards = new Label("min:");
        minNumCardsInput = new TextField();
        minNumCardsInput.setMaxSize(50, 20);
        minNumCardsHBox.getChildren().addAll(minNumCards, minNumCardsInput);
        /* max */
        HBox maxNumCardsHBox = new HBox(4);
        Label maxNumCards = new Label("max:");
        maxNumCardsInput = new TextField();
        maxNumCardsInput.setMaxSize(50, 20);
        maxNumCardsHBox.getChildren().addAll(maxNumCards, maxNumCardsInput);

        pileCardCount.getChildren().addAll(numCards, minNumCardsHBox, maxNumCardsHBox);


        /* Corrdinates - extract */
        Label gridCoordinates = new Label("Coordinates");
        VBox pileCoordinates = new VBox();
        /* x */
        HBox xCoordHBox = new HBox(4);
        Label xCoord = new Label("x:");
        xCoordInput = new TextField();
        xCoordInput.setMaxSize(30, 10);
        xCoordHBox.getChildren().addAll(xCoord, xCoordInput);
        /*y */
        Label yCoord = new Label("y:");
        HBox yCoordHBox = new HBox(4);
        yCoordInput = new TextField();
        yCoordInput.setMaxSize(30, 10);
        yCoordHBox.getChildren().addAll(yCoord, yCoordInput);

        pileCoordinates.getChildren().addAll(gridCoordinates, xCoordHBox, yCoordHBox);

        /* Player Pile Association Settings */
        Label playerAssociation = new Label("Player Association");

        /* Pile Viewable Settings */
        HBox pileViewableHBox = new HBox(4);
        Label viewableBy = new Label("Viewable By:");

        pileViewableChoiceInput = new ChoiceBox();
        pileViewableChoiceInput.getItems().addAll("All", "None");
        pileViewableHBox.getChildren().addAll(viewableBy, pileViewableChoiceInput);

        /* Pile Orientation Settings - for display purposes.  Will determine how top of Pile is displayed */
        HBox cardOrientationHBox = new HBox(4);
        Label orientation = new Label("Card Orientation");
        cardOrientationChoiceInput = new ChoiceBox();
        cardOrientationChoiceInput.getItems().addAll("Face Up", "Face Down");
        cardOrientationHBox.getChildren().addAll(orientation, cardOrientationChoiceInput);

        /* TODO add anchor?*/
        HBox addUpdateButtonsHBox = new HBox(20);
        updatePileButton = new Button("Update Pile");
        addPileButton = new Button("Add Pile");
        addUpdateButtonsHBox.getChildren().addAll(updatePileButton, addPileButton);

        this.getChildren().addAll(nameHBox, typeHBox, pileCardCount, pileCoordinates, playerAssociation, pileViewableHBox, cardOrientationHBox, addUpdateButtonsHBox);
    }

    public Button getAddPileButton() {
        return addPileButton;
    }

    public Button getUpdatePileButton() {
        return updatePileButton;
    }

    public TextField getNameInput() {
        return nameInput;
    }

    public ChoiceBox getTypeChoiceInput() {
        return typeChoiceInput;
    }

    public TextField getMinNumCardsInput() {
        return minNumCardsInput;
    }

    public TextField getMaxNumCardsInput() {
        return maxNumCardsInput;
    }

    public TextField getxCoordInput() {
        return xCoordInput;
    }

    public TextField getyCoordInput() {
        return yCoordInput;
    }

    public ChoiceBox getPileViewableChoiceInput() {
        return pileViewableChoiceInput;
    }

    public ChoiceBox getCardOrientationChoiceInput() {
        return cardOrientationChoiceInput;
    }

}
