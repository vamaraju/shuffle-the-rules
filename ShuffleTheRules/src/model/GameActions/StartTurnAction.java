/*
* Requirements mandating inclusion:
* */
package model.GameActions;


public class StartTurnAction extends GameAction {

    public StartTurnAction() {
        this.name = "StartTurnAction";
        this.description = "A turn is started.";
    }

    @Override
    public void run(Object... obj) {

    }
}
