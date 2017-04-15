package model.Piles;

import model.CardOrientation;
import model.Player;

import java.util.ArrayList;

public class Deck extends Pile {

    public Deck() {
        super();
    }

    public Deck(int minSize, int maxSize){
        super(minSize, maxSize);
    }

    public Deck(int minSize, int maxSize, int startingSize){
        super(minSize, maxSize, startingSize);
    }

    public Deck(String name, int minSize, int maxSize, int startingSize) {
        super(name, minSize, maxSize, startingSize);
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
