/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.*;
import model.Piles.Deck;
import model.Piles.Pile;
import view.Gameplay.GameplayView;

import java.util.ArrayList;

public class DealCardAction extends GameAction {

    public DealCardAction() {
        this.className = "DealCardAction";
        this.description = "A card (or cards) is dealt.";
    }

    @Override
    public void run(Object... args) {
        postGameplayMessage(GameplayMessageType.ACTION);

        Pile dealingPile = this.getPile();
        ArrayList<Player> players = new ArrayList<>();

        if (getPlayer().equals("*All*")) {
            players = GameCreation.getInstance().getPlayers();
        } else if (getPlayer().equals("*Current*")) {
            players.add(GameState.getInstance().getActivePlayer());
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

        launchPostRules();
    }

}
