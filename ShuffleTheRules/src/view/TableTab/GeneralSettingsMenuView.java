/*
* Requirements mandating inclusion:
* */
package view.TableTab;


import controller.TableTab.GeneralSettingsMenuController;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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

        elements.put("playersHeader", new Label("Number of Players"), null);
        elements.put("minPlayers", new Label("Min:"), new TextField());
        elements.put("maxPlayers", new Label("Max:"), new TextField());
        elements.put("handSizeHeader", new Label("Hand Size"), null);
        elements.put("minHandSize", new Label("Min:"), new TextField());
        elements.put("maxHandSize", new Label("Max:"), new TextField());
        elements.put("startingHandSize", new Label("Starting:"), new TextField());

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
                Node n = elements.getValue2(key);
                if (n instanceof TextField) {
                    ((TextField) n).setMaxSize(50, 20);
                }
                generalMenuContent.add(elements.getValue1(key), 0, row);
                generalMenuContent.add(n, 1, row++);
            }
        }
        generalMenuContent.add(updateButton, 0, row++);
    }

    public Button getUpdateButton(){
        return updateButton;
    }

    public String getMinPlayersTextFieldValue() {
        return ((TextField) elements.getValue2("minPlayers")).getText();
    }

    public String getMaxPlayersTextFieldValue() {
        return ((TextField) elements.getValue2("maxPlayers")).getText();
    }
}
