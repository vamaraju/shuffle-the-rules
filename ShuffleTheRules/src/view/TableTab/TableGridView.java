package view.TableTab;


import javafx.scene.layout.GridPane;


import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;



public class TableGridView extends GridPane {

    private final String enableGridCSS = "-fx-background-color: black, green; -fx-background-insets: 0, 0 1 1 0;";
    private final String disableGridCSS = "-fx-background-color: green;";

    private int numRows;
    private int numCols;
    private double cellHeight;
    private double cellWidth;

    public TableGridView() {
        initialize();
    }

    public void initialize() {
        numRows = 5;
        numCols = 9;
        cellHeight = 72;
        cellWidth = 50;

        this.setStyle("-fx-background-color: white; -fx-padding: 10;");

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints column = new ColumnConstraints(cellWidth);
            this.getColumnConstraints().add(column);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints row = new RowConstraints(cellHeight);
            this.getRowConstraints().add(row);
        }

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                TableGridElement gridElement = new TableGridElement(i, j, cellWidth, cellHeight);
                gridElement.setStyle(enableGridCSS);
                this.add(gridElement, i, j);
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

    public TableGridElement get(int x, int y) {
        for (Node n : this.getChildren()) {
            TableGridElement t = (TableGridElement) n;
            if (t.atPosition(x, y)) {return t;}
        }
        return null;
    }
}
