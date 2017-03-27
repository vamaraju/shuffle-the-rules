package controller;


import model.CardSettings;
import model.GameCreation;
import model.GameSettings;
import model.TableGrid;
import view.TableTab.TableTabView;

import java.util.ArrayList;
import java.util.List;

public class TableTabController {
    private CardSettings cardSettings;
    private TableGrid tableGrid;
    private TableTabView tableTabView;
    private GameSettings gameSettings;
    private GameCreation gameCreation;

    public TableTabController(CardSettings cardSettingsModel, TableGrid tableGridModel, TableTabView tableTabView, GameSettings gameSettingsModel){
        cardSettings = cardSettingsModel;
        tableGrid = tableGridModel;
        tableTabView = tableTabView;
        gameSettings = gameSettingsModel;


    }


    public List<String> getCardList(){
        return cardSettings.getCardList();
    }
}
