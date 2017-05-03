/*
* Requirements mandating inclusion:
*
*
* */

package view.Gameplay;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import model.SortType;


public class SortDialog extends Dialog<ButtonType> {

    private GridPane dialogLayout = new GridPane();

    /* ToggleGroup is used to make radio button selection mutually exclusive */
    private ToggleGroup sortValueRadioGroup = new ToggleGroup();
    private RadioButton valueAscendingRadioButton;
    private RadioButton valueDescendingRadioButton;

    private CheckBox suitCheckBox = new CheckBox("Suit");

    public SortDialog() {
        initialize();
    }

    public void initialize() {
        this.setTitle("Sort Hand");

        initRadioButtons();
        initLayout();
        initSubmitButtons();

        this.getDialogPane().setContent(dialogLayout);
    }

    private void initRadioButtons() {
        valueAscendingRadioButton = new RadioButton(SortType.ASCENDING.getName());
        valueDescendingRadioButton = new RadioButton(SortType.DESCENDING.getName());
        valueAscendingRadioButton.setUserData(SortType.ASCENDING);
        valueDescendingRadioButton.setUserData(SortType.DESCENDING);

        sortValueRadioGroup.getToggles().add(valueAscendingRadioButton);
        sortValueRadioGroup.getToggles().add(valueDescendingRadioButton);
    }

    private void initLayout() {
        dialogLayout.setVgap(10);
        dialogLayout.setHgap(4);
        dialogLayout.add(new Label("Sort by:"), 1, 1);

        dialogLayout.add(new Label("Value"), 1, 2);
        dialogLayout.add(valueAscendingRadioButton, 2, 3);
        dialogLayout.add(valueDescendingRadioButton, 2, 4);

        dialogLayout.add(suitCheckBox, 1, 5);
    }

    private void initSubmitButtons() {
        this.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        this.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    }

    public ToggleGroup getToggleGroup() {
        return sortValueRadioGroup;
    }

    public Toggle getSelectedToggle() {
        return sortValueRadioGroup.getSelectedToggle();
    }

    public CheckBox getSuitCheckBox() {
        return suitCheckBox;
    }

    public boolean getSuitCheckBoxValue() {
        return suitCheckBox.isSelected();
    }

    public RadioButton getAscendingRadioButton() {
        return valueAscendingRadioButton;
    }

    public RadioButton getDescendingRadioButton() {
        return valueDescendingRadioButton;
    }
}
