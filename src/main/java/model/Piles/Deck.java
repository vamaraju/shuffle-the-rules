/*
* Requirements mandating inclusion:
*
* 3.2.1.1.3.2 User can specify number of Cards in a Pile (max/min).
* 3.2.1.1.3.3 User can specify the Player that a Pile is associated with.
* 3.2.1.1.3.4 User can specify the type of a Pile. (Hand, Deck, Discard, Pile).
* 3.2.1.1.3.5 User can specify the Players that are allowed to view a Pile.
* 3.2.1.1.3.6 User can specify whether or not the Cards in the Pile are face down or face up or a combination of both.
* 3.2.1.1.3.8 User can specify a name for the Pile.
* */
package model.Piles;

import model.CardOrientation;
import model.Player;

import java.util.ArrayList;

public class Deck extends Pile {

    public Deck() {
        super();
        setPileType(PileType.DECK);
    }

    public Deck(int minSize, int maxSize){
        super(minSize, maxSize);
        setPileType(PileType.DECK);
    }

    public Deck(int minSize, int maxSize, int startingSize){
        super(minSize, maxSize, startingSize);
        setPileType(PileType.DECK);
    }

    public Deck(String name, int minSize, int maxSize, int startingSize) {
        super(name, minSize, maxSize, startingSize);
        setPileType(PileType.DECK);
    }

    public Deck(String name, int minSize, int maxSize, int startingSize, CardOrientation cardOrientation) {
        super(name, minSize, maxSize, startingSize, cardOrientation);
        setPileType(PileType.DECK);
    }

    public Deck(String name, int minSize, int maxSize, int startingSize, CardOrientation cardOrientation, String viewablePlayers) {
        super(name, minSize, maxSize, startingSize, cardOrientation, viewablePlayers);
        setPileType(PileType.DECK);
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
