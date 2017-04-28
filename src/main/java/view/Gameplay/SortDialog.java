package view.Gameplay;/*
* Requirements mandating inclusion:
*
*
* */

import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

/* Currently will return a string (ASC/DESC) and a Boolean (True/False as to
* whether or not to sort by suit
*
* Will change the ASC/DESC from strings to enums */
public class SortDialog extends Dialog<Pair<String, Boolean>> {

    private Label sortPrompt = new Label("Sort by:");
    private Button submitSortCriteria = new Button("SORT");

    private Label value = new Label("Value");
    private Label suit = new Label("Suit");

    private GridPane sortLayout = new GridPane();

    private Optional<Pair<String, Boolean>> result;

    public SortDialog(){
        this.setTitle("Sort Hand");
        this.setHeaderText("How would you like to sort your hand?");

        initialize();


        result = this.showAndWait();
    }

    public void initialize(){
        sortLayout.add(sortPrompt,1,1);

        sortLayout.add(submitSortCriteria,2,8,2,2);


        this.getDialogPane().setContent(sortLayout);
    }
}
