package view.TableTab;


import javafx.scene.layout.GridPane;


import javafx.scene.Node;
import javafx.scene.layout.*;



public class TableGridView extends GridPane {

    private final String enableGridCSS = "-fx-background-color: black, green; -fx-background-insets: 0, 0 1 1 0;";
    private final String disableGridCSS = "-fx-background-color: green;";

    public TableGridView() {
        initialize();
    }

    public void initialize() {
        int numRows = 5;
        int numCols = 9;
        double rowHeight = 72;
        double colWidth = 50;

        this.setStyle("-fx-background-color: white; -fx-padding: 10;");

        for(int i = 0; i < numCols; i++) {
            ColumnConstraints column = new ColumnConstraints(colWidth);
            this.getColumnConstraints().add(column);
        }

        for(int i = 0; i < numRows; i++) {
            RowConstraints row = new RowConstraints(rowHeight);
            this.getRowConstraints().add(row);
        }

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                Pane pane = new Pane();
                pane.setStyle(enableGridCSS);
                this.add(pane, i, j);
            }
        }
    }

    public void enableGridLines() {
        for (Node n : this.getChildren()) {
            n.setStyle(enableGridCSS);
        }
    }

    public void disableGridLines() {
        for (Node n : this.getChildren()) {
            n.setStyle(disableGridCSS);
        }
    }
}
