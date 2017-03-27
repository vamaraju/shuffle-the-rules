package controller.TableTab;


import model.CardSettings;
import model.GameCreation;
import model.GameSettings;
import model.TableGrid;
import view.TableTab.TableTabView;


public class TableTabController {

    private TableGrid tableGrid;
    private TableTabView tableTabView;
    private GameSettings gameSettings;
    private GameCreation gameCreation;

    public TableTabController(CardSettings cardSettingsModel, TableGrid tableGridModel, TableTabView tableTabView, GameSettings gameSettingsModel){

        tableGrid = tableGridModel;
        tableTabView = tableTabView;
        gameSettings = gameSettingsModel;


    }



}
