/*
* Requirements mandating inclusion:
*
* Allows Piles to be viewed.
*
* 3.2.1.1.3.5 User can specify the Players that are allowed to view a Pile.
* 3.2.2.1.3.1 User can view their Hand.
* 3.2.2.1.3.2 User can sort their Hand.
* 3.2.2.2.3.1 User can select Card(s) from Hand to play.
* 3.2.2.2.3.2 User can select Card(s) from Hand to discard.
* 3.2.2.2.3.4 User can select Card(s) to swap between Piles.
* */

package controller.GameplayMode;


import view.Gameplay.PileView;

public class PileViewController {
    private PileView pileView;


    public PileViewController(PileView view){
        pileView = view;
    }

}
