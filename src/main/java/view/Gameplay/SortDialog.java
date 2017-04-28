package view.Gameplay;/*
* Requirements mandating inclusion:
*
*
* */

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

/* Currently result will be a Pair<String, Boolean>.
* String value will be (ASC/DESC) - used to determine whether to sort by ascending or descending value.
* Boolean value will be True/False - used to determine whether or not to sort by suit.
*
* TODO Will change the ASC/DESC from strings to enums */

/* TODO BUG dialog window currently does not close */
public class SortDialog extends Dialog<Pair<String, Boolean>> {

    private GridPane sortLayout = new GridPane();

    private Label sortPrompt = new Label("Sort by:");

    private Button submitSortCriteriaButton = new Button("SORT");

    private Label value = new Label("Value");
    private Label suit = new Label("Suit");

    private ToggleGroup sortValueRadioGroup;
    private RadioButton valueAscendingRadioButton;
    private RadioButton valueDescendingRadioButton;

    private CheckBox suitCheckBox = new CheckBox("Suit");

    private Optional<Pair<String, Boolean>> result;

    public SortDialog(){
        this.setTitle("Sort Hand");
        //this.setOnCloseRequest();

        initialize();

        result = this.showAndWait();
    }

    public void initialize(){

        initializeRadioButtons();

        sortLayout.setVgap(10);
        sortLayout.setHgap(4);
        sortLayout.add(sortPrompt,1,1);

        sortLayout.add(value,2,2);
        sortLayout.add(valueAscendingRadioButton,3,3);
        sortLayout.add(valueDescendingRadioButton,3,4);

        sortLayout.add(suitCheckBox,2,5);

        sortLayout.add(submitSortCriteriaButton,2,8,2,2);


        this.getDialogPane().setContent(sortLayout);

        this.submitSortCriteriaButton.setOnAction(this::onSubmitSortCriteriaButtonClick);
    }

    private void onSubmitSortCriteriaButtonClick(ActionEvent actionEvent) {

    }

    private void initializeRadioButtons() {
        /* ToggleGroup used to make radio button selection mutually exclusive */
        sortValueRadioGroup = new ToggleGroup();

        valueAscendingRadioButton = new RadioButton("Ascending");
        valueAscendingRadioButton.setToggleGroup(sortValueRadioGroup);

        valueDescendingRadioButton = new RadioButton("Descending");
        valueDescendingRadioButton.setToggleGroup(sortValueRadioGroup);
    }


    /* on click method will check get input values, then pass to Gameplay Controller */
    /* the button will set the result of the dialog*/

}
