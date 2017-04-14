/*
* Requirements mandating inclusion:
* */
package model.GameActions;


public class SkipTurnAction extends GameAction {

    public SkipTurnAction() {
        this.name = "SkipTurnAction";
        this.description = "A turn is skipped.";
    }

    @Override
    public void run(Object... obj) {

    }
}
