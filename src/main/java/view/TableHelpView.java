package view;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class TableHelpView extends TextFlow {

    public TableHelpView() {
        initialize();
    }

    public void initialize() {
        Text title = new Text("Table Tab Instructions And Notes For Creating A Game\n\n\n\n");

        Text text1 = new Text("Notes:\n\n");



        this.getChildren().addAll(title, text1);
    }

}
