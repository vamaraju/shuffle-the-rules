package view.Gameplay;/*
* Requirements mandating inclusion:
*
*
* */

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

/* Currently will return a string (ASC/DESC) and a Boolean (True/False as to
* whether or not to sort by suit
*
* TODO Will change the ASC/DESC from strings to enums */
public class SortDialog extends Dialog<Pair<String, Boolean>> {

    private GridPane sortLayout = new GridPane();

    private Label sortPrompt = new Label("Sort by:");

    private Button submitSortCriteria = new Button("SORT");

    private Label value = new Label("Value");
    private Label suit = new Label("Suit");

    private RadioButton sortAscending = new RadioButton("Ascending");
    private RadioButton sortDescending = new RadioButton("Descending");

    private CheckBox suitCheckBox = new CheckBox("Suit");

    private Optional<Pair<String, Boolean>> result;

    public SortDialog(){
        this.setTitle("Sort Hand");
        this.setHeaderText("How would you like to sort your hand?");
        //this.setOnCloseRequest();

        initialize();

        result = this.showAndWait();
    }

    public void initialize(){
        sortLayout.add(sortPrompt,1,1);

        sortLayout.add(value,2,2);
        sortLayout.add(sortAscending,3,2);
        sortLayout.add(sortDescending,4,2);

        sortLayout.add(suit,2,5);
        sortLayout.add(suitCheckBox,3,5);

        sortLayout.add(submitSortCriteria,2,8,2,2);


        this.getDialogPane().setContent(sortLayout);
    }

    /* on click method will check get input values, then pass to Gameplay Controller */
    /* the button will set the result of the dialog*/

}
