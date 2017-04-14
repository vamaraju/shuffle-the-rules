/*
* Requirements mandating inclusion:
* */

package controller.TableTab;

import javafx.event.Event;
import model.GameCreation;
import model.GameSettings;
import view.TableTab.GeneralSettingsMenuView;


public class GeneralSettingsMenuController {
    private GeneralSettingsMenuView generalSettingsMenuView;
    private GameSettings gameSettings = GameCreation.getInstance().getGameSettings();

    public GeneralSettingsMenuController(GeneralSettingsMenuView view){
        generalSettingsMenuView = view;
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
                System.out.println("exception thrown while trying to parse min and max values in General Settings into integers.");
                
            }

        }

        System.out.print("game settings min ");
        System.out.println(gameSettings.getMinPlayers());
        System.out.print("game settings max ");
        System.out.println(gameSettings.getMaxPlayers());

    }


}
