package controller;


import model.CardSettings;
import model.TableGrid;
import view.TableTab.TableTabView;

public class TableTabController {
    private CardSettings cardSettings;
    private TableGrid tableGrid;
    private TableTabView tableTabView;


    public TableTabController(CardSettings cardSettingsModel, TableGrid tableGridModel, TableTabView tableTabView){
        cardSettings = cardSettingsModel;
        tableGrid = tableGridModel;
        tableTabView = tableTabView;
    }
}
