/*
* Requirements mandating inclusion:
* */

package controller.TableTab;


import javafx.event.ActionEvent;
import view.TableTab.TableGridElement;
import view.TableTab.TableGridView;
import view.TableTab.TableTabView;

import java.util.ArrayList;


public class TableTabController {

    private TableTabView view;

    public TableTabController(TableTabView view){
        this.view = view;
    }

    public void onGridHideCheckboxClick(ActionEvent e) {
        if (view.getGridHideCheckBox().isSelected()) {
            view.getTableGridView().disableGridLines();
        } else {
            view.getTableGridView().enableGridLines();
        }
    }

    public void onGridUpdateButtonClick(ActionEvent e) {
        int gridWidth = view.getGridWidthSetting();
        int gridHeight = view.getGridHeightSetting();
        double gridCellWidth = view.getGridCellWidthSetting();
        ArrayList<TableGridElement> currentPiles = view.getGridCurrentPiles();

        TableGridView tableGridView = view.getTableGridView();
        tableGridView.initGrid(gridWidth, gridHeight, gridCellWidth);

        for (TableGridElement pile : currentPiles) {
            if ((pile.getX() <= gridWidth) && (pile.getY() <= gridHeight)) {
                tableGridView.set(pile, pile.getX(), pile.getY());
            } else {
                currentPiles.remove(pile);
            }
        }
    }

}
