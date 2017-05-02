/*
* Requirements mandating inclusion:
* */
package model.GameEvents;

import model.GameCreation;
import model.GameRule;
import model.GameState;
import model.Player;


public class OnTurnEndEvent extends GameEvent {

    public OnTurnEndEvent() {
        this.className = "OnTurnEndEvent";
        this.description = "The turn is ending.";
    }

    @Override
    public void run(Object... args) {

        Player activePlayer = GameState.getInstance().getActivePlayer();
        Player nextPlayer = GameCreation.getInstance().getNextPlayer(activePlayer);

        while (nextPlayer.isSkipFlag()) {
            nextPlayer.setSkipFlag(false);
            nextPlayer = GameCreation.getInstance().getNextPlayer(nextPlayer);
        }

        GameState.getInstance().setActivePlayer(nextPlayer);

        for (int i = 0; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }
}
