package controller.TableTab;


import javafx.event.Event;
import model.GameSettings;
import view.TableTab.GeneralSettingsMenuView;

import java.awt.*;

public class GeneralSettingsMenuController {
    private GeneralSettingsMenuView generalSettingsMenuView;
    private GameSettings gameSettings;

    public GeneralSettingsMenuController(GeneralSettingsMenuView view){
        generalSettingsMenuView = view;
        gameSettings = new GameSettings();
        generalSettingsMenuView.getUpdateButton().setOnAction(this::onUpdateButtonClick);
        updateMinCount();
        updateMaxCount();
    }

    public GeneralSettingsMenuView getGeneralSettingsMenuView() {
        return generalSettingsMenuView;
    }

    public void onUpdateButtonClick(Event event){
        System.out.println("Update pressed - General Settings Menu");
        /* Get input */
        String minText = generalSettingsMenuView.getMinNumPlayersInput();
        String maxText = generalSettingsMenuView.getMaxNumPlayersInput();
         /* validate */
        if (!minText.isEmpty() && !maxText.isEmpty()){
            try{
                int min = Integer.parseInt(minText);
                int max = Integer.parseInt(maxText);
                if(min <= max){
                    /* update model */
                    gameSettings.setMinPlayers(min);
                    gameSettings.setMaxPlayers(max);
                }


            }catch (Exception e){
                System.out.println("exception thrown.");
            }
        }

        System.out.print("game settings min ");
        System.out.println(gameSettings.getMinPlayers());
        System.out.print("game settings max ");
        System.out.println(gameSettings.getMaxPlayers());

    }

    public void updateMinCount(){
        getGeneralSettingsMenuView().setMinNumPlayersInput(Integer.toString(gameSettings.getMinPlayers()));
    }

    public void updateMaxCount(){
        getGeneralSettingsMenuView().setMaxNumPlayersInput(Integer.toString(gameSettings.getMaxPlayers()));
    }
}
