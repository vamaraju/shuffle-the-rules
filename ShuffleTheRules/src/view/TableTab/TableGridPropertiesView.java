/*
* Requirements mandating inclusion:
* */
package view.TableTab;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.HashMap;
import java.util.LinkedHashMap;

/*
* Used inside centre pane of TableTabView.
* Displays and allows modifications to be made to grid properties.
*
* Currently, viewable only because the functionality is part of the list
* of low priority requirements.
* */
public class TableGridPropertiesView extends GridPane {

    private HashMap<String, Label> labels = new LinkedHashMap<>();
    private HashMap<String, TextField> textFields = new LinkedHashMap<>();

    private CheckBox gridHideCheckBox = new CheckBox();
    private Button updateButton = new Button("Update Grid");

    private String gridWidthKey = "gridWidth";
    private String gridHeightKey = "gridHeight";
    private String gridCellWidthKey = "gridCellWidth";
    private String gridHide = "gridHide";

    public TableGridPropertiesView() {
        initialize();
    }

    private void initialize(){
        this.setHgap(10);
        this.setVgap(10);

        labels.put(gridWidthKey, new Label("Grid Width (x):"));
        labels.put(gridHeightKey, new Label("Grid Height (y):"));
        labels.put(gridCellWidthKey, new Label("Grid Cell Width:"));
        labels.put(gridHide, new Label("Hide Grid:"));

        textFields.put(gridWidthKey, new TextField("5"));
        textFields.put(gridHeightKey, new TextField("5"));
        textFields.put(gridCellWidthKey, new TextField("70"));

        int i = 1;
        for (String key : textFields.keySet()) {
            textFields.get(key).setMaxSize(50,20);
            this.add(labels.get(key), i, 1);
            this.add(textFields.get(key), i+1, 1);
            i += 2;
        }

        this.add(labels.get(gridHide), i++, 1);
        this.add(gridHideCheckBox, i++, 1);
        this.add(updateButton, i++, 1);
    }

    public CheckBox getGridHideCheckBox() {
        return gridHideCheckBox;
    }

    public HashMap<String, Label> getLabels() {
        return labels;
    }

    public HashMap<String, TextField> getTextFields() {
        return textFields;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public String getGridWidthTextFieldValue() {
        return textFields.get(gridWidthKey).getText();
    }

    public String getGridHeightTextFieldValue() {
        return textFields.get(gridHeightKey).getText();
    }

    public String getGridCellWidthTextFieldValue() {
        return textFields.get(gridCellWidthKey).getText();
    }
}