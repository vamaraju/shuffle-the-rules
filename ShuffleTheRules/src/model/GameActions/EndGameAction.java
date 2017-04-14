/*
* Requirements mandating inclusion:
* */
package model.GameActions;


public class EndGameAction extends GameAction {

    public EndGameAction() {
        this.name = "EndGameAction";
        this.description = "The game ends.";
    }

    @Override
    public void run(Object... obj) {

    }
}
