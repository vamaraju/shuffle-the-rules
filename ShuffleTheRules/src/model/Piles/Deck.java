package model.Piles;

import model.Player;

import java.util.ArrayList;

public class Deck extends Pile {

    public Deck() {
        super();
    }

    public void deal(Player player) {
        player.getHand().add(this.getCards().remove(0));
    }

    public void deal(Player player, int numCards) {
        for (int i = 0; i < numCards; i++) {
            deal(player);
        }
    }

    public void deal(ArrayList<Player> players, int numCards) {
        for (Player player : players) {
            deal(player, numCards);
        }
    }
}
