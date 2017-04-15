/*
* Requirements mandating inclusion:
* */
package view.TableTab;


import controller.TableTab.TableGridViewController;
import javafx.scene.layout.GridPane;


import javafx.scene.Node;
import javafx.scene.layout.*;
import model.GameCreation;
import model.GameView;
import model.Piles.Pile;
import model.TableGrid;
import model.TableGridPosition;

import java.util.Map;


public class TableGridView extends GridPane {

    private TableGridViewController controller;

    private TableGrid tableGrid;

    public TableGridView() {
        this.setStyle(TableGridCSS.DEFAULT_GRID.getStyle());
        resetGrid();
    }

    public void resetGrid() {
        initGrid(TableGridDefaults.NUM_COLUMNS.toInt(), TableGridDefaults.NUM_ROWS.toInt(), TableGridDefaults.CELL_WIDTH.toDouble());
    }

    public void initGrid(int numCols, int numRows, double cellWidth) {
        controller = new TableGridViewController(this);
        tableGrid = new TableGrid(numCols, numRows, cellWidth);
        GameCreation.getInstance().setTableGrid(tableGrid);

        drawGrid();
    }

    public void drawGrid() {
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
                gridElement.setStyle(TableGridCSS.ENABLE_GRID.getStyle());
                this.add(gridElement, i, j);
                gridElement.setOnMouseClicked(controller::onGridElementClicked);
            }
        }

        for (Map.Entry<Pile, TableGridPosition> gridEntry : tableGrid.getPileMap().entrySet()) {
            set(gridEntry.getValue(), gridEntry.getKey());
        }
    }

    public void enableGridLines() {
        for (Node n : this.getChildren()) {
            n.setStyle(TableGridCSS.ENABLE_GRID.getStyle());
        }
    }

    public void disableGridLines() {
        for (Node n : this.getChildren()) {
            n.setStyle(TableGridCSS.DISABLE_GRID.getStyle());
        }
    }

    public void enableBackgroundImage() {
        for (Node n : this.getChildren()) {
            n.setStyle(TableGridCSS.ENABLE_BACKGROUND_IMAGE.getStyle());
        }
    }

    public TableGridElement get(int x, int y) {
        for (Node n : this.getChildren()) {
            TableGridElement t = (TableGridElement) n;
            if (t.isPosition(x, y)) {return t;}
        }
        return null;
    }

    public TableGridElement get(TableGridPosition position) {
        for (Node n : this.getChildren()) {
            TableGridElement t = (TableGridElement) n;
            if (t.isPosition(position)) {return t;}
        }
        return null;
    }

    public void set(TableGridElement pile, int x, int y) {
        for (int i = 0; i < this.getChildren().size(); i++) {
            TableGridElement t = (TableGridElement) this.getChildren().get(i);
            if (t.isPosition(x, y)) {this.getChildren().set(i, pile);}
        }
    }

    public void set(TableGridPosition gridPosition, Pile p) {
        for (int i = 0; i < this.getChildren().size(); i++) {
            TableGridElement t = (TableGridElement) this.getChildren().get(i);
            if (t.isPosition(gridPosition)) {((TableGridElement) this.getChildren().get(i)).updatePile(p);}
        }
    }

    public void addPile(TableGridElement pile, int x, int y) {
        set(pile, x, y);
    }

    public void updateElement(int x, int y, Pile p) {
        updateElement(new TableGridPosition(x, y), p);
    }

    public void updateElement(TableGridPosition gridPosition, Pile p) {
        TableGridElement t = get(gridPosition);
        t.updatePile(p);
        tableGrid.updatePileMap(p, gridPosition);
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
