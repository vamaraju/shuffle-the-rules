/*
* Requirements mandating inclusion:
*
* 3.2.1.2.3.1 User can specify number of each type and suit of Card(s).
* */
package model;


import java.io.Serializable;
import java.util.*;

/* TODO how will Joker be handled?*/
/* TODO Error handling for methods */
public class CardSettings implements Serializable {
    /* stores all Card information:
       <value:<heart: num_hearts
               spade: num_spades
               club: num_clubs
               diamond: num_diamonds*/
    private Map<PlayingCard, Map<Suit, Integer>> cardRestrictions = new LinkedHashMap<>();
    private int numDecks;

    public CardSettings() {
        initialize(1);
    }

    public void reset() {
        cardRestrictions.clear();
        initialize(1);
    }

    private void initialize(int numDecks) {
        this.numDecks = numDecks;
        for (PlayingCard card: PlayingCard.values()) {
            cardRestrictions.put(card, new LinkedHashMap<>());
            for (Suit suit : Suit.values()) {
                cardRestrictions.get(card).put(suit, numDecks);
            }
        }
    }


    public void updateCount(PlayingCard card, Suit suit, int count) {
        cardRestrictions.get(card).put(suit, count);
    }


    public int getCount(PlayingCard card, Suit suit) {
        return cardRestrictions.get(card).get(suit);
    }


    public int getSuitCount(Suit suit) {
        int sum = 0;
        for (PlayingCard card : cardRestrictions.keySet()) {
            sum += cardRestrictions.get(card).get(suit);
        }
        return sum;
    }


    public int getCardCount(PlayingCard card) {
        int sum = 0;
        for (Suit suit : cardRestrictions.get(card).keySet()) {
            sum += cardRestrictions.get(card).get(suit);
        }
        return sum;
    }


    public int getTotalCount() {
        int total = 0;
        for (PlayingCard card : cardRestrictions.keySet()) {
            for (Suit suit : cardRestrictions.get(card).keySet()) {
                total += cardRestrictions.get(card).get(suit);
            }
        }
        return total;
    }


    public void clear() {
        cardRestrictions.clear();
    }


    public int getNumDecks() {
        return numDecks;
    }


    public void setNumDecks(int numDecks) {
        this.numDecks = numDecks;
        clear();
        initialize(numDecks);
    }


    public List<Card> generateCardPool() {
        List<Card> cardPool = new LinkedList<>();
        for (PlayingCard c : cardRestrictions.keySet()) {
            for (Suit s : cardRestrictions.get(c).keySet()) {
                for (int i = 0; i < cardRestrictions.get(c).get(s); i++) {
                    cardPool.add(new Card(c, s));
                }
            }
        }
        Collections.shuffle(cardPool);
        return cardPool;
    }
}
