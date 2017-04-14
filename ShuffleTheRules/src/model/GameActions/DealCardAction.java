package model.GameActions;

import model.Card;
import model.GameRule;
import model.Piles.Deck;
import model.Piles.Pile;
import model.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DealCardAction extends GameAction {

    private Deck dealingPile;
    private int numCards;
    private ArrayList<Player> players = new ArrayList<>();

    public DealCardAction() {
        this.name = "DealCardAction";
        this.description = "A card (or cards) is dealt.";
    }

    public DealCardAction(Deck dealingPile, int numCards, ArrayList<Player> players) {
        this();
        this.dealingPile = dealingPile;
        this.numCards = numCards;
        this.players = players;
    }

    @Override
    public void run(Object... args) {
        dealingPile = (args[0] != null ? (Deck) args[0] : dealingPile);
        numCards = (args[1] != null ? (int) args[1] : numCards);
        players = (args[2] != null ? (ArrayList<Player>) args[2] : players);

        dealingPile.deal(players, numCards);

        for (int i = 3; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }

    public Pile getDealingPile() {
        return dealingPile;
    }

    public void setDealingPile(Deck dealingPile) {
        this.dealingPile = dealingPile;
    }

    public int getNumCards() {
        return numCards;
    }

    public void setNumCards(int numCards) {
        this.numCards = numCards;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
