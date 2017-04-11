package view.TableTab;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
public class PileSettingsView extends GridPane {

    private Button updatePileButton;
    private Button addPileButton;
    private Button deletePileButton;

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

        this.setHgap(2);
        this.setVgap(4);

        /* Name - extract */
        Label name = new Label("Name:");
        nameInput = new TextField();
        nameInput.setMaxSize(120, 20);

        this.add(name,1,1,1,1);
        this.add(nameInput,2,1,1,1);


        /* Type - extract */
        Label type = new Label("Type:");
        typeChoiceInput = new ChoiceBox();
        typeChoiceInput.getItems().addAll("Hand", "Deck", "Discard");

        this.add(type,1,2,1,1);
        this.add(typeChoiceInput,2,2,1,1);


        /* max/min number Cards in Pile - extract */
        Label numCards = new Label("Number Cards");
        this.add(numCards,1,4,2,2);

        /* min */
        Label minNumCards = new Label("min:");
        minNumCardsInput = new TextField();
        minNumCardsInput.setMaxSize(50, 20);

        this.add(minNumCards,1,6,1,1);
        this.add(minNumCardsInput,2,6,1,1);

        /* max */
        Label maxNumCards = new Label("max:");
        maxNumCardsInput = new TextField();
        maxNumCardsInput.setMaxSize(50, 20);

        this.add(maxNumCards,1,7,1,1);
        this.add(maxNumCardsInput,2,7,1,1);


        /* Corrdinates - extract */
        Label gridCoordinates = new Label("Coordinates");
        this.add(gridCoordinates,1,8,2,2);

        /* x */

        Label xCoord = new Label("x:");
        xCoordInput = new TextField();
        xCoordInput.setMaxSize(30, 10);

        this.add(xCoord,1,10,1,1);
        this.add(xCoordInput,2,10,1,1);

        /*y */
        Label yCoord = new Label("y:");
        yCoordInput = new TextField();
        yCoordInput.setMaxSize(30, 10);

        this.add(yCoord,1,11,1,1);



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
        updatePileButton.setDisable(true);
        addPileButton = new Button("Add Pile");
        deletePileButton = new Button("Delete Pile");
        deletePileButton.setDisable(true);
        addUpdateButtonsHBox.getChildren().addAll(updatePileButton, addPileButton, deletePileButton);



    }

    public Button getAddPileButton() {
        return addPileButton;
    }

    public Button getUpdatePileButton() {
        return updatePileButton;
    }

    public Button getDeletePileButton() { return deletePileButton; }

    public void enableAddPileButton(){ addPileButton.setDisable(false); }

    public void disableAddPileButton(){ addPileButton.setDisable(true); }

    public void enableUpdatePileButton(){ updatePileButton.setDisable(false); }

    public void disableUpdatePileButton(){ updatePileButton.setDisable(true); }

    public void enableDeletePileButton(){ deletePileButton.setDisable(false); }

    public void disableDeletePileButton(){ deletePileButton.setDisable(true); }

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
