/*
* Requirements mandating inclusion:
* */
package model;

import model.Piles.Pile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TableGrid implements Serializable {

    private int numRows;
    private int numCols;
    private double cellWidth;
    private double cellHeight;
    private HashMap<Pile, TableGridPosition> pileMap = new HashMap<>();

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

    public boolean positionFilled(TableGridPosition position) {
        for (TableGridPosition activePosition : pileMap.values()) {
            if (position.equals(activePosition)) {return true;}
        }
        return false;
    }

    public void clearPosition(TableGridPosition position) {
        for (Map.Entry<Pile, TableGridPosition> entry : pileMap.entrySet()) {
            if (entry.getValue().equals(position)) {
                pileMap.remove(entry.getKey());
            }
        }
    }

    public void updatePileMap(Pile p, TableGridPosition gridPosition) {
        if (positionFilled(gridPosition)) {
            clearPosition(gridPosition);
        }
        pileMap.put(p, gridPosition);
    }

    public void removePile(Pile p) {
        pileMap.remove(p);
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

    public HashMap<Pile, TableGridPosition> getPileMap() {
        return pileMap;
    }

    public void setPileMap(HashMap<Pile, TableGridPosition> pileMap) {
        this.pileMap = pileMap;
    }
}
