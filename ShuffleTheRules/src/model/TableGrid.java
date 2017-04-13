package model;

import java.io.Serializable;

public class TableGrid implements Serializable {

    private int numRows;
    private int numCols;
    private double cellWidth;
    private double cellHeight;

    public TableGrid() {

    }

    public TableGrid(int numCols, int numRows) {
        this.numCols = numCols;
        this.numRows = numRows;
    }

    public TableGrid(int numCols, int numRows, double cellWidth) {
        this.numCols = numCols;
        this.numRows = numRows;
        this.cellWidth = cellWidth;
        this.cellHeight = cellWidth*PlayingCard.ASPECT_RATIO;
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

    public double getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(double cellWidth) {
        this.cellWidth = cellWidth;
    }

    public double getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(double cellHeight) {
        this.cellHeight = cellHeight;
    }

}
