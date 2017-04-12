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

    public void onHideGridCheckboxClick(ActionEvent e) {
        if (view.getHideGridCheckBox().isSelected()) {
            view.getTableGridView().disableGridLines();
        } else {
            view.getTableGridView().enableGridLines();
        }
    }

}
