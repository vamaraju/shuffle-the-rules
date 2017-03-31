package controller.TableTab;

import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Piles.BasicPile;
import model.Piles.Pile;
import model.Piles.PileInterface;
import model.RuleElementRectangle;
import view.TableTab.PileSettingsMenuView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PileSettingsMenuController {

    private PileSettingsMenuView pileSettingsMenuView;
    /* will store BasicPiles and Hands */
    private Map<String, PileInterface> piles;


    public PileSettingsMenuController(PileSettingsMenuView view){
        piles = new HashMap<String, PileInterface>();
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
    public PileInterface getPile(String pileName){
        return new BasicPile("placeholder",0,2);
    }
}
