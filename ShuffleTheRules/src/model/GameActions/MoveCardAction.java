/*
* Requirements mandating inclusion:
* */
package model.GameActions;


public class MoveCardAction extends GameAction {

    public MoveCardAction() {
        this.name = "MoveCardAction";
        this.description = "A card (or cards) is moved.";
    }

    @Override
    public void run(Object... obj) {

    }
}
