/*
* Requirements mandating inclusion:
* */
package view.TableTab;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.TripleHashMap;

/*
* Used inside centre pane of TableTabView.
* Displays and allows modifications to be made to grid properties.
*
* */
public class TableGridPropertiesView extends GridPane {

    private TripleHashMap<String, Node, Node> gridElements;

    private Button updateButton = new Button("Update Grid");

    public TableGridPropertiesView() {
        initialize();
    }

    private void initialize(){
        this.setHgap(10);
        this.setVgap(10);

        gridElements = new TripleHashMap<>();

        gridElements.put("gridWidth", new Label("Grid Width (x):"), new TextField(TableGridDefaults.WIDTH.toIntStr()));
        gridElements.put("gridHeight", new Label("Grid Height (y):"), new TextField(TableGridDefaults.HEIGHT.toIntStr()));
        gridElements.put("gridCellWidthKey", new Label("Grid Cell Width:"), new TextField(TableGridDefaults.CELL_WIDTH.toIntStr()));
        gridElements.put("gridHide", new Label("Hide Grid:"), new CheckBox());

        addGridContent();
    }

    public void addGridContent() {
        int col = 1;
        for (String key : gridElements.keySet()) {
            this.add(gridElements.getValue1(key), col++, 1);
            this.add(gridElements.getValue2(key), col++, 1);

            if (gridElements.getValue2(key) instanceof TextField) {
                ((TextField) gridElements.getValue2(key)).setPrefWidth(50);
            }
        }
        this.add(updateButton, col++, 1);
    }


    public void resetInputsToDefaults() {
        getGridWidthTextField().setText(TableGridDefaults.WIDTH.toIntStr());
        getGridHeightTextField().setText(TableGridDefaults.HEIGHT.toIntStr());
        getGridCellWidthTextField().setText(TableGridDefaults.CELL_WIDTH.toIntStr());
        getGridHideCheckBox().setSelected(false);
    }


    public Button getUpdateButton() {
        return updateButton;
    }

    public CheckBox getGridHideCheckBox() {
        return (CheckBox) gridElements.getValue2("gridHide");
    }

    public TextField getGridWidthTextField() {
        return (TextField) gridElements.getValue2("gridWidth");
    }

    public String getGridWidthTextFieldValue() {
        return getGridWidthTextField().getText();
    }

    public TextField getGridHeightTextField() {
        return (TextField) gridElements.getValue2("gridHeight");
    }

    public String getGridHeightTextFieldValue() {
        return getGridHeightTextField().getText();
    }

    public TextField getGridCellWidthTextField() {
        return (TextField) gridElements.getValue2("gridCellWidthKey");
    }

    public String getGridCellWidthTextFieldValue() {
        return getGridCellWidthTextField().getText();
    }
}