package controller.TableTab;

import model.Piles.Pile;
import view.TableTab.PileSettingsMenuView;

import java.util.ArrayList;


public class PileSettingsMenuController {

    private PileSettingsMenuView pileSettingsMenuView;
    private ArrayList<Pile> piles;

    PileSettingsMenuController(PileSettingsMenuView view){
        pileSettingsMenuView = view;
    }

    public PileSettingsMenuView getPileSettingsMenuView() {
        return pileSettingsMenuView;
    }


}
