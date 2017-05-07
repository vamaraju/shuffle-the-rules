/*
* Requirements mandating inclusion:
*
* 3.2.2.1.3.2 User can sort their Hand.
* */

package view.Gameplay;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import model.CardOrientation;
import model.SortType;


public class PlaceCardDialog extends Dialog<ButtonType> {

    private GridPane dialogLayout = new GridPane();

    /* ToggleGroup is used to make radio button selection mutually exclusive */
    private ToggleGroup sortValueRadioGroup = new ToggleGroup();
    private RadioButton faceUpRadioButton;
    private RadioButton faceDownRadioButton;

    public PlaceCardDialog() {
        initialize();
    }

    public void initialize() {
        this.setTitle("Place Card");

        initRadioButtons();
        initLayout();
        initSubmitButtons();

        this.getDialogPane().setContent(dialogLayout);
    }

    private void initRadioButtons() {
        faceUpRadioButton = new RadioButton(CardOrientation.UP.getName());
        faceDownRadioButton = new RadioButton(CardOrientation.DOWN.getName());
        faceUpRadioButton.setUserData(CardOrientation.UP);
        faceDownRadioButton.setUserData(CardOrientation.DOWN);

        sortValueRadioGroup.getToggles().add(faceUpRadioButton);
        sortValueRadioGroup.getToggles().add(faceDownRadioButton);
    }

    private void initLayout() {
        dialogLayout.setVgap(10);
        dialogLayout.setHgap(4);
        dialogLayout.add(new Label("Place card:"), 1, 1);

        dialogLayout.add(faceUpRadioButton, 1, 2);
        dialogLayout.add(faceDownRadioButton, 1, 3);
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

    public RadioButton getFaceUpRadioButton() {
        return faceUpRadioButton;
    }

    public RadioButton getFaceDownRadioButton() {
        return faceDownRadioButton;
    }
}
