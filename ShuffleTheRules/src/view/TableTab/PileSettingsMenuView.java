package view.TableTab;



import controller.TableTab.PileSettingsMenuController;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class PileSettingsMenuView extends TitledPane {

    private PileSettingsMenuController controller;

    private GridPane pileSettingsGrid;

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

    public PileSettingsMenuView(){
        initialize();
    }

    public void initialize(){
         /* Menu title */
        this.controller = new PileSettingsMenuController(this);
        this.setText("Piles");

        this.pileSettingsGrid = new GridPane();
        this.setContent(pileSettingsGrid);


        this.pileSettingsGrid.setHgap(2);
        this.pileSettingsGrid.setVgap(4);

        /* Name - extract */
        Label name = new Label("Name:");
        nameInput = new TextField();
        nameInput.setMaxSize(120, 20);

        this.pileSettingsGrid.add(name,1,1,1,1);
        this.pileSettingsGrid.add(nameInput,2,1,1,1);


        /* Type - extract */
        Label type = new Label("Type:");
        typeChoiceInput = new ChoiceBox();
        typeChoiceInput.getItems().addAll("General", "Hand", "Deck", "Discard");

        this.pileSettingsGrid.add(type,1,2,1,1);
        this.pileSettingsGrid.add(typeChoiceInput,2,2,1,1);


        /* max/min number Cards in Pile - extract */
        Label numCards = new Label("Number Cards");
        this.pileSettingsGrid.add(numCards,1,4,2,2);

        /* min */
        Label minNumCards = new Label("min:");
        minNumCardsInput = new TextField();
        minNumCardsInput.setMaxSize(50, 20);

        this.pileSettingsGrid.add(minNumCards,1,6,1,1);
        this.pileSettingsGrid.add(minNumCardsInput,2,6,1,1);

        /* max */
        Label maxNumCards = new Label("max:");
        maxNumCardsInput = new TextField();
        maxNumCardsInput.setMaxSize(50, 20);

        this.pileSettingsGrid.add(maxNumCards,1,7,1,1);
        this.pileSettingsGrid.add(maxNumCardsInput,2,7,1,1);


        /* Corrdinates - extract */
        Label gridCoordinates = new Label("Coordinates");
        this.pileSettingsGrid.add(gridCoordinates,1,8,2,2);

        /* x */

        Label xCoord = new Label("x:");
        xCoordInput = new TextField();
        xCoordInput.setMaxSize(50, 20);

        this.pileSettingsGrid.add(xCoord,1,10,1,1);
        this.pileSettingsGrid.add(xCoordInput,2,10,1,1);

        /*y */
        Label yCoord = new Label("y:");
        yCoordInput = new TextField();
        yCoordInput.setMaxSize(50, 20);

        this.pileSettingsGrid.add(yCoord,1,11,1,1);
        this.pileSettingsGrid.add(yCoordInput,2,11,1,1);

        /* Player Pile Association Settings */
        Label playerAssociation = new Label("Player Association");
        this.pileSettingsGrid.add(playerAssociation,1,12,1,1);

        /* Pile Viewable Settings */
        Label viewableBy = new Label("Viewable By:");
        pileViewableChoiceInput = new ChoiceBox();
        pileViewableChoiceInput.getItems().addAll("All", "None");
        this.pileSettingsGrid.add(viewableBy,1,13,1,1);
        this.pileSettingsGrid.add(pileViewableChoiceInput,2,13,1,1);


        /* Pile Orientation Settings - for display purposes.  Will determine how top of Pile is displayed */
        Label orientation = new Label("Card Orientation");
        cardOrientationChoiceInput = new ChoiceBox();
        cardOrientationChoiceInput.getItems().addAll("Face Up", "Face Down");

        this.pileSettingsGrid.add(orientation,1,14,1,1);
        this.pileSettingsGrid.add(cardOrientationChoiceInput,2,14,1,1);


        updatePileButton = new Button("Update Pile");
        updatePileButton.setDisable(true);
        this.pileSettingsGrid.add(updatePileButton,1,15,1,2);

        addPileButton = new Button("Add Pile");
        addPileButton.setPrefWidth(100);
        this.pileSettingsGrid.add(addPileButton,2,15,1,2);

        deletePileButton = new Button("Delete Pile");
        deletePileButton.setDisable(true);
        this.pileSettingsGrid.add(deletePileButton,3,15,1,2);

        this.addPileButton.setOnAction(controller::onAddPileButtonClick);
        this.updatePileButton.setOnAction(controller::onUpdatePileButtonClick);
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
