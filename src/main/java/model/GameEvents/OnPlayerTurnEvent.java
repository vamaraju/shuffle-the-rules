/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.GameState;
import model.Player;



public class OnPlayerTurnEvent extends GameEvent {

    public OnPlayerTurnEvent() {
        this.className = "OnPlayerTurnEvent";
        this.description = "A specific player's turn is starting.";
    }

    @Override
    public void run(Object... args) {
        Player player = (Player) args[0];

        if (GameState.getInstance().getActivePlayer() == player) {
            for (int i = 1; i < args.length; i++) {
                GameRule rule = (GameRule) args[i];
                rule.run(args);
            }
        }
    }
}
