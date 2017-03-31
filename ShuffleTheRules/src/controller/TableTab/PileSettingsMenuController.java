package controller.TableTab;

import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Piles.Pile;
import model.RuleElementRectangle;
import view.TableTab.PileSettingsMenuView;

import java.util.ArrayList;


public class PileSettingsMenuController {

    private PileSettingsMenuView pileSettingsMenuView;
    private ArrayList<Pile> basicPiles;
    private ArrayList<Pile> hands;

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
        /* update model */
        createNewPile();
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
        Pile pile = new Pile();
    }
}
