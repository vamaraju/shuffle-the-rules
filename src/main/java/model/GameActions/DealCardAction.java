/*
* Requirements mandating inclusion:
*
* 3.2.1.6.3.2 User can create Game Rules by selecting Game Events and adding a Game Action to that event.
* 3.2.1.3.7.2 User can view Event actions and rules.
* 3.2.1.3.7.3 User can edit Event actions and rules.
* 3.2.2.5.3.1 Rule Interpreter will interpret and execute Game Rules for a Card Game.
* */
package model.GameActions;

import model.*;
import model.Piles.Pile;
import view.Gameplay.GameplayViewUpdater;

import java.util.ArrayList;

public class DealCardAction extends GameAction {

    public DealCardAction() {
        this.className = "DealCardAction";
        this.description = "A card (or cards) is dealt.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        if (RuleInterpreter.gameCompleted()) {return;}

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ACTION, RuleInterpreter.defaultGameplayMessage());

        Pile dealingPile = this.getPile();
        ArrayList<Player> players = new ArrayList<>();

        if (getPlayer().equals("*All*")) {
            players = GameCreation.getInstance().getPlayers();
        } else if (getPlayer().equals("*Current*")) {
            players.add(GameState.getInstance().getCurrentPlayer());
        } else {
            players.add(GameCreation.getInstance().getPlayerFromPlayerName(getPlayer()));
        }

        if (getCardValue().equals("*Any*") && getCardSuit().equals("*Any*")) {
            for (Player p : players) {
                for (int i = 0; i < getNumCards(); i++) {
                    p.getHand().add(dealingPile.draw());
                }
            }
        }

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, RuleInterpreter.finishedGameplayMessage());
        RuleInterpreter.launchPostRules(this);
    }

}
