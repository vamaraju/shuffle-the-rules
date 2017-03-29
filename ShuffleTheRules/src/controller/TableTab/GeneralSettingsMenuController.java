package controller.TableTab;


import model.GameSettings;
import view.TableTab.GeneralSettingsMenuView;

public class GeneralSettingsMenuController {
    private GeneralSettingsMenuView generalSettingsMenuView;
    private GameSettings gameSettings;

    public GeneralSettingsMenuController(GeneralSettingsMenuView view){
        generalSettingsMenuView = view;
        gameSettings = new GameSettings();
    }

    public GeneralSettingsMenuView getGeneralSettingsMenuView() {
        return generalSettingsMenuView;
    }
}
