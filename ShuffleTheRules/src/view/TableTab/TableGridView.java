package view.TableTab;


import javafx.scene.layout.GridPane;


import javafx.scene.Node;
import javafx.scene.layout.*;

import java.util.ArrayList;



public class TableGridView extends GridPane {

    private final String enableGridCSS = "-fx-background-color: black, green; -fx-background-insets: 0, 0 1 1 0;";
    private final String disableGridCSS = "-fx-background-color: green;";

    private int numRows = 5;
    private int numCols = 5;
    private double cellWidth = 70;
    private double cellHeight = cellWidth*1.45;
    private ArrayList<TableGridElement> currentPiles = new ArrayList<>();

    public TableGridView() {
        initialize();
    }

    public void initialize() {
        this.setStyle("-fx-background-color: white; -fx-padding: 10;");
        initGrid();
    }

    public void initGrid() {
        this.getColumnConstraints().clear();
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints column = new ColumnConstraints(cellWidth);
            this.getColumnConstraints().add(column);
        }

        this.getRowConstraints().clear();
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
            if (t.isPosition(x, y)) {return t;}
        }
        return null;
    }

    public void set(TableGridElement pile, int x, int y) {
        for (int i = 0; i < this.getChildren().size(); i++) {
            TableGridElement t = (TableGridElement) this.getChildren().get(i);
            if (t.isPosition(x, y)) {this.getChildren().set(i, pile);}
        }
    }

    public void addPile(TableGridElement pile, int x, int y) {
        currentPiles.add(pile);
        set(pile, x, y);
    }

    public ArrayList<TableGridElement> getCurrentPiles() {
        return currentPiles;
    }


    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public double getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(double cellHeight) {
        this.cellHeight = cellHeight;
    }

    public double getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(double cellWidth) {
        this.cellWidth = cellWidth;
    }
}
