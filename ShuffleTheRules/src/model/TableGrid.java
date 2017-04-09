package model;


import java.util.ArrayList;

public class TableGrid {

    int gridDimensionX;
    int getGridDimensionY;
    float gridScaleFactor;
    Boolean showGridlines;
    ArrayList gridValues;


    public TableGrid(){

    }

    public int getGridDimensionX() {
        return gridDimensionX;
    }

    public void setGridDimensionX(int gridDimensionX) {
        this.gridDimensionX = gridDimensionX;
    }

    public int getGetGridDimensionY() {
        return getGridDimensionY;
    }

    public void setGetGridDimensionY(int getGridDimensionY) {
        this.getGridDimensionY = getGridDimensionY;
    }

    public float getGridScaleFactor() {
        return gridScaleFactor;
    }

    public void setGridScaleFactor(float gridScaleFactor) {
        this.gridScaleFactor = gridScaleFactor;
    }

    public Boolean getShowGridlines() {
        return showGridlines;
    }

    public void setShowGridlines(Boolean showGridlines) {
        this.showGridlines = showGridlines;
    }

    public ArrayList getGridValues() {
        return gridValues;
    }

    public void setGridValues(ArrayList gridValues) {
        this.gridValues = gridValues;
    }
}
