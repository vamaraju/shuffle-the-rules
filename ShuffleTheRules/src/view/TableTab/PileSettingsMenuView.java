/*
* Requirements mandating inclusion:
* */
package view.TableTab;


import controller.TableTab.PileSettingsMenuController;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.CardOrientation;
import model.Piles.PileType;
import model.TripleHashMap;


public class PileSettingsMenuView extends TitledPane {

    private PileSettingsMenuController controller;

    private GridPane pileSettingsGrid;
    private TripleHashMap<String, Node, Node> gridElements;

    private Button updatePileButton = new Button("Update Pile");
    private Button addPileButton = new Button("Add Pile");
    private Button deletePileButton = new Button("Delete Pile");

    public PileSettingsMenuView(){
        initialize();
    }

    public void initialize(){
         /* Menu title */
        controller = new PileSettingsMenuController(this);
        this.setText("Piles");

        pileSettingsGrid = new GridPane();
        pileSettingsGrid.setHgap(2);
        pileSettingsGrid.setVgap(4);

        gridElements = new TripleHashMap<>();

        gridElements.put("generalHeader", new Label("General"), null);
        gridElements.put("name", new Label("Name:"), new TextField());
        gridElements.put("type", new Label("Type:"), new ComboBox<>());

        gridElements.put("numCardsHeader", new Label("Number of Cards"), null);
        gridElements.put("minNumCards", new Label("Min:"), new TextField());
        gridElements.put("maxNumCards", new Label("Max:"), new TextField());
        gridElements.put("startingNumCards", new Label("Starting:"), new TextField());

        gridElements.put("coordinatesHeader", new Label("Table Grid Coordinates"), null);
        gridElements.put("xCoordinate", new Label("X:"), new TextField());
        gridElements.put("yCoordinate", new Label("Y:"), new TextField());

        gridElements.put("playerAssociationHeader", new Label("Player Association"), null);
        gridElements.put("viewable", new Label("Viewable By:"), new ComboBox<>());

        gridElements.put("cardsHeader", new Label("Cards"), null);
        gridElements.put("cardOrientation", new Label("Card Orientation:"), new ComboBox<>());

        String[] headers = {"generalHeader", "numCardsHeader", "coordinatesHeader", "playerAssociationHeader", "cardsHeader"};
        for (int i = 0; i < headers.length; i++) {
            gridElements.getValue1(headers[i]).setStyle("-fx-font-weight: bold");
        }

        getPileNameTextField().setPromptText("Enter a Name");
        getMinCardsTextField().setPromptText("Enter a Number");
        getMaxCardsTextField().setPromptText("Enter a Number");
        getStartingCardsTextField().setPromptText("Enter a Number");
        getXCoordinateTextField().setPromptText("Enter a Number");
        getYCoordinateTextField().setPromptText("Enter a Number");

        controller.setPileTypeComboBox();
        controller.setViewableByComboBox();
        controller.setCardOrientationComboBox();

        addGridContent();

        updatePileButton.setDisable(true);
        deletePileButton.setDisable(true);

        this.setContent(pileSettingsGrid);
        this.expandedProperty().addListener(controller::onTabExpanded);
        addPileButton.setOnAction(controller::onAddPileButtonClick);
        updatePileButton.setOnAction(controller::onUpdatePileButtonClick);
        deletePileButton.setOnAction(controller::onDeletePileButtonClick);

    }

    public void addGridContent() {
        int row = 0;
        for (String key : gridElements.keySet()) {
            if (gridElements.getValue2(key) == null) {
                if (row != 0) {
                    pileSettingsGrid.add(new Separator(), 0, row++);
                }
                pileSettingsGrid.add(gridElements.getValue1(key), 0, row++);
            } else {
                pileSettingsGrid.add(gridElements.getValue1(key), 0, row);
                pileSettingsGrid.add(gridElements.getValue2(key), 1, row++);
            }
        }
        pileSettingsGrid.add(updatePileButton, 0, row);
        pileSettingsGrid.add(addPileButton, 1, row);
        pileSettingsGrid.add(deletePileButton, 2, row);
    }

    public Button getUpdatePileButton(){
        return updatePileButton;
    }

    public TextField getPileNameTextField() {
        return (TextField) gridElements.getValue2("name");
    }

    public String getPileNameTextFieldValue() {
        return getPileNameTextField().getText();
    }

    public ComboBox getPileTypeComboBox() {
        return (ComboBox) gridElements.getValue2("type");
    }

    public PileType getPileTypeComboBoxValue() {
        return (PileType) getPileTypeComboBox().getValue();
    }

    public TextField getMinCardsTextField() {
        return (TextField) gridElements.getValue2("minNumCards");
    }

    public String getMinCardsTextFieldValue() {
        return getMinCardsTextField().getText();
    }

    public TextField getMaxCardsTextField() {
        return (TextField) gridElements.getValue2("maxNumCards");
    }

    public String getMaxCardsTextFieldValue() {
        return getMaxCardsTextField().getText();
    }

    public TextField getStartingCardsTextField() {
        return (TextField) gridElements.getValue2("startingNumCards");
    }

    public String getStartingCardsTextFieldValue() {
        return getStartingCardsTextField().getText();
    }

    public TextField getXCoordinateTextField() {
        return (TextField) gridElements.getValue2("xCoordinate");
    }

    public String getXCoordinateTextFieldValue() {
        return getXCoordinateTextField().getText();
    }

    public TextField getYCoordinateTextField() {
        return (TextField) gridElements.getValue2("yCoordinate");
    }

    public String getYCoordinateTextFieldValue() {
        return getYCoordinateTextField().getText();
    }

    public ComboBox getViewableByComboBox() {
        return (ComboBox) gridElements.getValue2("viewable");
    }

    public String getViewableByComboBoxValue() {
        return (String) getViewableByComboBox().getValue();
    }

    public ComboBox getCardOrientationComboBox() {
        return (ComboBox) gridElements.getValue2("cardOrientation");
    }

    public CardOrientation getCardOrientationComboBoxValue() {
        return (CardOrientation) getCardOrientationComboBox().getValue();
    }
}
