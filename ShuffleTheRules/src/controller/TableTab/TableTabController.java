package controller.TableTab;


import javafx.event.ActionEvent;
import model.CardSettings;
import model.GameCreation;
import model.GameSettings;
import model.TableGrid;
import view.TableTab.TableTabView;


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
        //int gridWidth = view.getGridTextField("gridWidth").getText();
    }

}
