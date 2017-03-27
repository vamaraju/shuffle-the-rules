package model;

import model.Piles.Pile;

import java.util.ArrayList;

/**
 * This class contains the parties/objects involved in a particular game rule.
 * For example, for OnPileClickedEvent, the piles array should be populated with the clicked pile, and if necessary
 * the players array should be populated with the player involved in the click.
 * Similarly, for MoveCardAction, the cards array should be populated with the Cards involved in the movement,
 * and the piles array should be populated with the relevant piles.
 * Basically, this class just stores information (specifics) about the particular rule by storing all the involved
 * objects.
 */
public class GameRuleProperties {

    private ArrayList<Pile> piles = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    public ArrayList<Pile> getPiles() {
        return piles;
    }

    public void setPiles(ArrayList<Pile> piles) {
        this.piles = piles;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }


}
