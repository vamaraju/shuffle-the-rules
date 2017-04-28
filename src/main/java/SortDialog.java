/*
* Requirements mandating inclusion:
*
*
* */

import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;


public class SortDialog extends Dialog {

    private Label sortPrompt = new Label("Sort by:");
    private Button submitSortCriteria = new Button("SORT");

    private Label value = new Label("Value");
    private Label suit = new Label("Suit");
    

    public SortDialog(){
        this.setTitle("Sort Hand");

    }
}
