/*
* Requirements mandating inclusion:
* */
package model.GameActions;


public class EndTurnAction extends GameAction  {

    public EndTurnAction() {
        this.name = "EndTurnAction";
        this.description = "The turn ends.";
    }

    @Override
    public void run(Object... obj) {

    }
}
