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

import javax.xml.soap.Text;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class GeneralSettingsMenuView extends TitledPane {

    private GeneralSettingsMenuController controller;

    private GridPane generalMenuContent;
    private TripleHashMap<String, Node, Node> elements = new TripleHashMap<>();

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

        elements.put("numPlayersHeader", new Label("Number of Players"), null);
        elements.put("minPlayers", new Label("Min:"), new TextField());
        elements.put("maxPlayers", new Label("Max:"), new TextField());

        elements.put("playerSettingsHeader", new Label("Player Settings"), null);
        elements.put("player", new Label("Player:"), new ComboBox<Player>());
        elements.put("playerName", new Label("Player Name:"), new TextField());
        elements.put("playerTurn", new Label("Turn Number:"), new ComboBox<Integer>());

        elements.put("handSizeHeader", new Label("Hand Size"), null);
        elements.put("minHandSize", new Label("Min:"), new TextField());
        elements.put("maxHandSize", new Label("Max:"), new TextField());
        elements.put("startingHandSize", new Label("Starting:"), new TextField());

        String[] headers = {"numPlayersHeader", "playerSettingsHeader", "handSizeHeader"};
        for (int i = 0; i < headers.length; i++) {
            elements.getValue1(headers[i]).setStyle("-fx-font-weight: bold");
        }

        ((TextField) elements.getValue2("minPlayers")).setPromptText("Enter a Number");
        ((TextField) elements.getValue2("maxPlayers")).setPromptText("Enter a Number");
        ((TextField) elements.getValue2("playerName")).setPromptText("Enter a Name");
        ((TextField) elements.getValue2("minHandSize")).setPromptText("Enter a Number");
        ((TextField) elements.getValue2("maxHandSize")).setPromptText("Enter a Number");
        ((TextField) elements.getValue2("startingHandSize")).setPromptText("Enter a Number");

        addGridContent();

        this.setContent(generalMenuContent);
        this.updateButton.setOnAction(controller::onUpdateButtonClick);
    }

    public void addGridContent() {
        int row = 0;
        for (String key : elements.keySet()) {
            if (elements.getValue2(key) == null) {
                if (row != 0) {
                    generalMenuContent.add(new Separator(), 0, row++);
                }
                generalMenuContent.add(elements.getValue1(key), 0, row++);
            } else {
                generalMenuContent.add(elements.getValue1(key), 0, row);
                generalMenuContent.add(elements.getValue2(key), 1, row++);
            }
        }
        generalMenuContent.add(updateButton, 0, row++);
    }

    public Button getUpdateButton(){
        return updateButton;
    }

    public TextField getMinPlayersTextField() {
        return (TextField) elements.getValue2("minPlayers");
    }

    public String getMinPlayersTextFieldValue() {
        return getMinPlayersTextField().getText();
    }

    public TextField getMaxPlayersTextField() {
        return (TextField) elements.getValue2("maxPlayers");
    }

    public String getMaxPlayersTextFieldValue() {
        return getMaxPlayersTextField().getText();
    }

    public ComboBox getPlayerComboBox() {
        return (ComboBox) elements.getValue2("player");
    }

    public Player getPlayerComboBoxValue() {
        return (Player) getPlayerComboBox().getValue();
    }

    public TextField getPlayerNameTextField() {
        return (TextField) elements.getValue2("playerName");
    }

    public String getPlayerNameTextFieldValue() {
        return getPlayerNameTextField().getText();
    }

    public ComboBox getTurnNumberComboBox() {
        return (ComboBox) elements.getValue2("playerTurn");
    }

    public Integer getTurnNumberComboBoxValue() {
        return (Integer) getTurnNumberComboBox().getValue();
    }

    public TextField getMinHandSizeTextField() {
        return (TextField) elements.getValue2("minHandSize");
    }

    public String getMinHandSizeTextFieldValue() {
        return getMinHandSizeTextField().getText();
    }

    public TextField getMaxHandSizeTextField() {
        return (TextField) elements.getValue2("maxHandSize");
    }

    public String getMaxHandSizeTextFieldValue() {
        return getMaxHandSizeTextField().getText();
    }

    public TextField getStartingHandSizeTextField() {
        return (TextField) elements.getValue2("startingHandSize");
    }

    public String getStartingHandSizeTextFieldValue() {
        return getStartingHandSizeTextField().getText();
    }
}
