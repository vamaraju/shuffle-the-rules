/*
* Requirements mandating inclusion:
* */
package view.TableTab;


import controller.TableTab.GeneralSettingsMenuController;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Player;
import model.TripleHashMap;


public class GeneralSettingsMenuView extends TitledPane {

    private GeneralSettingsMenuController controller;

    private GridPane generalMenuContent;
    private TripleHashMap<String, Node, Node> gridElements;

    private Button updateButton = new Button("Update");

    public GeneralSettingsMenuView() {
        initialize();
    }

    public void initialize(){
        controller = new GeneralSettingsMenuController(this);
        this.setText("General Settings");

        generalMenuContent = new GridPane();
        generalMenuContent.setHgap(2);
        generalMenuContent.setVgap(4);

        gridElements = new TripleHashMap<>();

        gridElements.put("numPlayersHeader", new Label("Number of Players"), null);
        gridElements.put("minPlayers", new Label("Min:"), new TextField());
        gridElements.put("maxPlayers", new Label("Max:"), new TextField());

        gridElements.put("handSizeHeader", new Label("Hand Size (All Players)"), null);
        gridElements.put("minHandSize", new Label("Min:"), new TextField());
        gridElements.put("maxHandSize", new Label("Max:"), new TextField());
        gridElements.put("startingHandSize", new Label("Starting:"), new TextField());

        gridElements.put("playerSettingsHeader", new Label("Player Settings"), null);
        gridElements.put("player", new Label("Player:"), new ComboBox<Player>());
        gridElements.put("playerName", new Label("Player Name:"), new TextField());
//        gridElements.put("playerTurn", new Label("Turn Number:"), new ComboBox<Integer>());
        gridElements.put("playerTurn", new Label("Turn Number:"), new Label());

        String[] headers = {"numPlayersHeader", "playerSettingsHeader", "handSizeHeader"};
        for (int i = 0; i < headers.length; i++) {
            gridElements.getValue1(headers[i]).setStyle("-fx-font-weight: bold");
        }

        getMinPlayersTextField().setPromptText("Enter a Number");
        getMaxPlayersTextField().setPromptText("Enter a Number");
        getPlayerNameTextField().setPromptText("Enter a Name");
        getMinHandSizeTextField().setPromptText("Enter a Number");
        getMaxHandSizeTextField().setPromptText("Enter a Number");
        getStartingHandSizeTextField().setPromptText("Enter a Number");

//        getMaxPlayersTextField().textProperty().addListener(controller::onMaxPlayersChanged);
        getPlayerComboBox().valueProperty().addListener(controller::onPlayerSelected);
//        getPlayerNameTextField().textProperty().addListener(controller::onPlayerNameChanged);

        controller.updatePlayersComboBox();
//        controller.updateTurnNumberComboBox();

        addGridContent();

        this.setContent(generalMenuContent);
        this.updateButton.setOnAction(controller::onUpdateButtonClick);
    }

    public void addGridContent() {
        int row = 0;
        for (String key : gridElements.keySet()) {
            if (gridElements.getValue2(key) == null) {
                if (row != 0) {
                    generalMenuContent.add(new Separator(), 0, row++);
                }
                generalMenuContent.add(gridElements.getValue1(key), 0, row++);
            } else {
                generalMenuContent.add(gridElements.getValue1(key), 0, row);
                generalMenuContent.add(gridElements.getValue2(key), 1, row++);
            }
        }
        generalMenuContent.add(updateButton, 0, row++);
    }

    public GeneralSettingsMenuController getController() {
        return controller;
    }

    public void clearAllInputs() {
        getMinPlayersTextField().clear();
        getMaxPlayersTextField().clear();
        getMinHandSizeTextField().clear();
        getMaxHandSizeTextField().clear();
        getStartingHandSizeTextField().clear();
        clearPlayerSettingsInputs();
    }

    public void clearPlayerSettingsInputs() {
        getPlayerComboBox().setValue(null);
        getPlayerNameTextField().clear();
        getTurnNumberLabel().setText("");
    }

    public Button getUpdateButton(){
        return updateButton;
    }

    public TextField getMinPlayersTextField() {
        return (TextField) gridElements.getValue2("minPlayers");
    }

    public String getMinPlayersTextFieldValue() {
        return getMinPlayersTextField().getText();
    }

    public TextField getMaxPlayersTextField() {
        return (TextField) gridElements.getValue2("maxPlayers");
    }

    public String getMaxPlayersTextFieldValue() {
        return getMaxPlayersTextField().getText();
    }

    public ComboBox getPlayerComboBox() {
        return (ComboBox<Player>) gridElements.getValue2("player");
    }

    public Player getPlayerComboBoxValue() {
        return (Player) getPlayerComboBox().getValue();
    }

    public TextField getPlayerNameTextField() {
        return (TextField) gridElements.getValue2("playerName");
    }

    public String getPlayerNameTextFieldValue() {
        return getPlayerNameTextField().getText();
    }

//    public ComboBox getTurnNumberComboBox() {
//        return (ComboBox<Integer>) gridElements.getValue2("playerTurn");
//    }

//    public Integer getTurnNumberComboBoxValue() {
//        return (Integer) getTurnNumberComboBox().getValue();
//    }

    public Label getTurnNumberLabel() {
        return (Label) gridElements.getValue2("playerTurn");
    }

    public String getTurnNumberLabelValue() {
        return getTurnNumberLabel().getText();
    }

    public TextField getMinHandSizeTextField() {
        return (TextField) gridElements.getValue2("minHandSize");
    }

    public String getMinHandSizeTextFieldValue() {
        return getMinHandSizeTextField().getText();
    }

    public TextField getMaxHandSizeTextField() {
        return (TextField) gridElements.getValue2("maxHandSize");
    }

    public String getMaxHandSizeTextFieldValue() {
        return getMaxHandSizeTextField().getText();
    }

    public TextField getStartingHandSizeTextField() {
        return (TextField) gridElements.getValue2("startingHandSize");
    }

    public String getStartingHandSizeTextFieldValue() {
        return getStartingHandSizeTextField().getText();
    }
}
