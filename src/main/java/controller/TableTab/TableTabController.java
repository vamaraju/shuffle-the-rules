/*
* Requirements mandating inclusion:
*
* 3.2.1.8.3.1 User can set the dimensions of the grid.
* 3.2.1.8.3.2 User can scale the size of the grid blocks.
* 3.2.1.8.3.4 User can show the grid.
* 3.2.1.8.3.5 User can hide the grid.
* */

package controller.TableTab;


import javafx.event.ActionEvent;
import model.Piles.Pile;
import model.TableGridPosition;
import view.TableTab.TableGridView;
import view.TableTab.TableTabView;

import java.util.HashMap;
import java.util.Map;


public class TableTabController {

    private TableTabView view;

    public TableTabController(TableTabView view){
        this.view = view;
    }

    public void onGridHideCheckboxClick(ActionEvent e) {
        if (view.getGridHideCheckBox().isSelected()) {
            view.getTableGrid().setHideGrid(true);
            view.getTableGridView().disableGridLines();
        } else {
            view.getTableGrid().setHideGrid(false);
            view.getTableGridView().enableGridLines();
        }
    }

    public void onGridUpdateButtonClick(ActionEvent e) {
        int gridWidth = view.getGridWidthSetting();
        int gridHeight = view.getGridHeightSetting();
        double gridCellWidth = view.getGridCellWidthSetting();
        Map<Pile, TableGridPosition> currentPiles = view.getGridPileMap();

        TableGridView tableGridView = view.getTableGridView(); // currentPiles is the pileMap of this view
        tableGridView.initGrid(gridWidth, gridHeight, gridCellWidth); // currentPiles is the pileMap of this view

        for (Pile pile : currentPiles.keySet()) {
            TableGridPosition pilePosition = currentPiles.get(pile);
            if (pilePosition.containedWithin(new TableGridPosition(gridWidth-1, gridHeight-1))) {
                // tableGridView.updateElement(pilePosition, pile);
            } else {
                currentPiles.remove(pile);
            }
        }

        view.getTableGridView().setGridLines(view.getTableGrid().isHideGrid());
    }

}
