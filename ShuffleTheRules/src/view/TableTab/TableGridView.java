/*
* Requirements mandating inclusion:
* */
package view.TableTab;


import javafx.scene.layout.GridPane;


import javafx.scene.Node;
import javafx.scene.layout.*;
import model.CardOrientation;
import model.Piles.Pile;
import model.TableGrid;

import java.util.ArrayList;



public class TableGridView extends GridPane {

    private final String enableGridCSS = "-fx-background-color: black, green; -fx-background-insets: 0, 0 1 1 0;";
    private final String disableGridCSS = "-fx-background-color: green;";
    private final String enableBackgroundImageCSS = "-fx-background-image: url('assets/background/green.jpg')";

    private TableGrid tableGrid;
    private ArrayList<TableGridElement> currentPiles = new ArrayList<>();

    public TableGridView() {
        this.setStyle("-fx-background-color: white; -fx-padding: 10;");
        resetGrid();
    }

    public void resetGrid() {
        initGrid(5, 5, 70);
    }

    public void initGrid(int numCols, int numRows, double cellWidth) {
        this.tableGrid = new TableGrid(numCols, numRows, cellWidth);

        this.getColumnConstraints().clear();
        for (int i = 0; i < tableGrid.getNumCols(); i++) {
            ColumnConstraints column = new ColumnConstraints(tableGrid.getCellWidth());
            this.getColumnConstraints().add(column);
        }

        this.getRowConstraints().clear();
        for (int i = 0; i < tableGrid.getNumRows(); i++) {
            RowConstraints row = new RowConstraints(tableGrid.getCellHeight());
            this.getRowConstraints().add(row);
        }

        this.getChildren().clear();
        for (int i = 0; i < tableGrid.getNumCols(); i++) {
            for (int j = 0; j < tableGrid.getNumRows(); j++) {
                TableGridElement gridElement = new TableGridElement(i, j, tableGrid.getCellWidth(), tableGrid.getCellHeight());
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

    public void enableBackgroundImage() {
        for (Node n : this.getChildren()) {
            n.setStyle(enableBackgroundImageCSS);
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

    public void updateElement(int x, int y, Pile p, CardOrientation orientation) {
        TableGridElement t = get(x, y);
        t.addPile(p, orientation);
        currentPiles.add(t);
    }

    public ArrayList<TableGridElement> getCurrentPiles() {
        return currentPiles;
    }

    public TableGrid getTableGrid() {
        return tableGrid;
    }

    public void setTableGrid(TableGrid tableGrid) {
        this.tableGrid = tableGrid;
    }

    public double getCellWidth() {
        return tableGrid.getCellWidth();
    }

    public double getCellHeight() {
        return tableGrid.getCellHeight();
    }
}
