/*
* Requirements mandating inclusion:
* */
package view.TableTab;


import controller.TableTab.GeneralSettingsMenuController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class GeneralSettingsMenuView extends TitledPane {

    private GeneralSettingsMenuController controller;

    private HashMap<String, Label> labels = new LinkedHashMap<>();
    private HashMap<String, TextField> textFields = new LinkedHashMap<>();

    private Button updateButton = new Button("Update");

    private String numPlayersHeaderKey = "playersHeader";
    private String minPlayersKey = "minPlayers";
    private String maxPlayersKey = "maxPlayers";

    public GeneralSettingsMenuView() {
        initialize();
    }

    public void initialize(){

        this.controller = new GeneralSettingsMenuController(this);
        this.setText("General Settings");

        /* organize fields in GridPane */
        GridPane generalMenuContent = new GridPane();
        generalMenuContent.setHgap(2);
        generalMenuContent.setVgap(4);

        labels.put(numPlayersHeaderKey, new Label("Number of Players"));
        labels.put(minPlayersKey, new Label("Min:"));
        labels.put(maxPlayersKey, new Label("Max:"));

        textFields.put(minPlayersKey, new TextField());
        textFields.put(maxPlayersKey, new TextField());

        generalMenuContent.add(labels.get(numPlayersHeaderKey), 0, 0);

        int i = 1;
        for (String key : textFields.keySet()) {
            textFields.get(key).setMaxSize(50,20);
            generalMenuContent.add(labels.get(key), 0, i);
            generalMenuContent.add(textFields.get(key), 1, i);
            i++;
        }

        generalMenuContent.add(updateButton, 0, i++);

        this.setContent(generalMenuContent);
        this.updateButton.setOnAction(controller::onUpdateButtonClick);
    }

    public Button getUpdateButton(){
        return updateButton;
    }

    public HashMap<String, Label> getLabels() {
        return labels;
    }

    public HashMap<String, TextField> getTextFields() {
        return textFields;
    }

    public String getMinPlayersTextFieldValue() {
        return textFields.get(minPlayersKey).getText();
    }

    public String getMaxPlayersTextFieldValue() {
        return textFields.get(maxPlayersKey).getText();
    }
}
