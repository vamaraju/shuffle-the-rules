/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;
import model.Piles.Pile;


public class ShufflePileAction extends GameAction {

    private Pile pile;

    public ShufflePileAction() {
        this.className = "ShufflePileAction";
        this.description = "A pile is shuffled.";
    }

    @Override
    public void run() {
    }

}
