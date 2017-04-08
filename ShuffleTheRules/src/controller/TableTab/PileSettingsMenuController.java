package controller.TableTab;

import javafx.event.Event;
import javafx.scene.control.Alert;
import model.GameCreation;
import model.Piles.BasicPile;
import model.Piles.Pile;
import view.TableTab.PileSettingsMenuView;

import java.util.HashMap;
import java.util.Map;


public class PileSettingsMenuController {

    private PileSettingsMenuView pileSettingsMenuView;
    /* will store BasicPiles and Hands */
    private HashMap<String, Pile> piles = GameCreation.getInstance().getPilesHashMap();


    public PileSettingsMenuController(PileSettingsMenuView view){
        pileSettingsMenuView = view;
        pileSettingsMenuView.getPileSettingsView().getAddPileButton().setOnAction(this::onAddPileButtonClick);
        pileSettingsMenuView.getPileSettingsView().getUpdatePileButton().setOnAction(this::onUpdatePileButtonClick);

    }

    public PileSettingsMenuView getPileSettingsMenuView() {
        return pileSettingsMenuView;
    }

    public void onAddPileButtonClick(Event event){
        System.out.println("Add Pile pressed");
        /* Get input from view*/
        /* validate */
        /* create a Pile - Basic or Hand */

        /* update view */
    }

    public void onUpdatePileButtonClick(Event event){
        System.out.println("Update Pile pressed");
        /* Get input from view*/

        /* validate */
        /* update model */
        /* update view */
        /* */
    }

    public void createNewPile(String type){

    }

    /* Access a specific Pile*/
    public Pile getPile(String pileName){
        return new BasicPile("placeholder",0,2);
    }

    public void deletePile(String pileName){
        
    }
}
