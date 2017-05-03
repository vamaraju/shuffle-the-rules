/*
* Requirements mandating inclusion:
* */
package view.Gameplay;


import controller.GameplayMode.GameplayTableGridController;
import controller.TableTab.TableGridViewController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;


import javafx.scene.Node;
import javafx.scene.layout.*;
import model.*;
import model.Piles.Deck;
import model.Piles.Pile;
import view.TableTab.TableGridCSS;
import view.TableTab.TableGridDefaults;
import view.TableTab.TableGridElement;

import java.util.Map;


public class GameplayTableGridView extends ScrollPane {

    private GameplayTableGridController controller;

    private GridPane grid = new GridPane();
    private TableGrid tableGrid;
    private TableGridElement clickedElement;

    public GameplayTableGridView(TableGrid tableGrid){
        controller = new GameplayTableGridController(this);
        this.tableGrid = tableGrid;
        GameState.getInstance().setCurrentTableGrid(tableGrid);
        initScrollPane();
        grid.setStyle(TableGridCSS.DEFAULT_GRID.getStyle());
        drawGrid();
        this.setContent(grid);
        enableBackgroundImage();
    }

    public void initScrollPane() {
        this.setFitToWidth(true);
        this.setFitToHeight(true);
        this.setStyle("-fx-focus-color: transparent;");
    }

    public void drawGrid() {
        grid.getColumnConstraints().clear();
        for (int i = 0; i < tableGrid.getNumCols(); i++) {
            ColumnConstraints column = new ColumnConstraints(tableGrid.getCellWidth());
            grid.getColumnConstraints().add(column);
        }

        grid.getRowConstraints().clear();
        for (int i = 0; i < tableGrid.getNumRows(); i++) {
            RowConstraints row = new RowConstraints(tableGrid.getCellHeight());
            grid.getRowConstraints().add(row);
        }

        grid.getChildren().clear();
        for (int i = 0; i < tableGrid.getNumCols(); i++) {
            for (int j = 0; j < tableGrid.getNumRows(); j++) {
                TableGridElement gridElement = new TableGridElement(i, j, tableGrid.getCellWidth(), tableGrid.getCellHeight());
                gridElement.setStyle(TableGridCSS.ENABLE_GRID.getStyle());
                grid.add(gridElement, i, j);
                gridElement.setOnMouseClicked(controller::onGridElementClicked);
            }
        }

        for (Map.Entry<Pile, TableGridPosition> gridEntry : tableGrid.getPileMap().entrySet()) {
            set(gridEntry.getValue(), gridEntry.getKey());
        }
    }

    public void enableBackgroundImage() {
        for (Node n : grid.getChildren()) {
            n.setStyle(TableGridCSS.ENABLE_BACKGROUND_IMAGE.getStyle());
        }
    }

    public TableGridElement getElement(int x, int y) {
        for (Node n : grid.getChildren()) {
            TableGridElement t = (TableGridElement) n;
            if (t.isPosition(x, y)) {return t;}
        }
        return null;
    }

    public TableGridElement getElement(TableGridPosition position) {
        for (Node n : grid.getChildren()) {
            TableGridElement t = (TableGridElement) n;
            if (t.isPosition(position)) {return t;}
        }
        return null;
    }

    public void set(TableGridElement pile, int x, int y) {
        for (int i = 0; i < grid.getChildren().size(); i++) {
            TableGridElement t = (TableGridElement) grid.getChildren().get(i);
            if (t.isPosition(x, y)) {grid.getChildren().set(i, pile);}
        }
    }

    public void set(TableGridPosition gridPosition, Pile p) {
        for (int i = 0; i < grid.getChildren().size(); i++) {
            TableGridElement t = (TableGridElement) grid.getChildren().get(i);
            if (t.isPosition(gridPosition)) {((TableGridElement) grid.getChildren().get(i)).updatePile(p);}
        }
    }

    public void addPile(TableGridElement pile, int x, int y) {
        set(pile, x, y);
    }

    public void updateElement(int x, int y, Pile p) {
        updateElement(new TableGridPosition(x, y), p);
    }

    public void updateElement(TableGridPosition gridPosition, Pile p) {
        TableGridElement t = getElement(gridPosition);
        t.updatePile(p);
        tableGrid.updatePileMap(p, gridPosition);
    }

    public void removeElement(TableGridPosition gridPosition) {
        TableGridElement t = getElement(gridPosition);
        t.removePile();
        tableGrid.clearPosition(gridPosition);
    }

    public void removeElement(TableGridPosition gridPosition, Pile p) {
        TableGridElement t = getElement(gridPosition);
        t.removePile();
        tableGrid.removePile(p);
    }

    public TableGrid getTableGrid() {
        return tableGrid;
    }

    public void setTableGrid(TableGrid tableGrid) {
        this.tableGrid = tableGrid;
    }

    public TableGridElement getClickedElement() {
        return clickedElement;
    }

    public void setClickedElement(TableGridElement clickedElement) {
        this.clickedElement = clickedElement;
    }

    public void setClickedElement(TableGridPosition gridPosition) {
        this.clickedElement = getElement(gridPosition);
    }

    public void resetClickedElement() {
        this.clickedElement = null;
    }

    public double getCellWidth() {
        return tableGrid.getCellWidth();
    }

    public double getCellHeight() {
        return tableGrid.getCellHeight();
    }
}
