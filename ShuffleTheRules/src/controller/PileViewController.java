/*
* Requirements mandating inclusion:
* */

package controller;


import javafx.beans.value.ObservableValue;
import view.Gameplay.PileView;

public class PileViewController {
    private PileView pileView;


    public PileViewController(PileView view){
        pileView = view;
    }

    public void changeDisplayedSuitCounts(ObservableValue observable, Object oldValue, Object newValue){

    }
}
