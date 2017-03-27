package controller.TableTab;


import model.GameSettings;
import view.TableTab.GeneralSettingsMenuView;

public class GeneralSettingsMenuController {
    private GeneralSettingsMenuView generalSettingsMenuView;
    private GameSettings gameSettings;

    GeneralSettingsMenuController(GeneralSettingsMenuView view, GameSettings model){
        generalSettingsMenuView = view;
        gameSettings = model;
    }
}
