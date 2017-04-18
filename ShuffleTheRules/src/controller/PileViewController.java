/*
* Requirements mandating inclusion:
* */

package controller;


import model.Piles.Pile;
import view.Gameplay.PileView;

public class PileViewController {
    private PileView pileView;


    public PileViewController(PileView view){
        pileView = view;
    }


    public void updateDisplayedPile(Pile pile){
        pileView.updatePile(pile);
    }
}
