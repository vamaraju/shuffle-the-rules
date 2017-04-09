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
    Map<PlayingCard,Map<Suit,Integer>> cardSettings;

    public CardSettings(){
        initialize();
    }

    private void initialize(){
        cardSettings = new LinkedHashMap<>();
        for (PlayingCard card: PlayingCard.values()) {
            /* Default values - deck with 52 cards */
            addCard(card);
            //updateAllSuits(card, 1, 1, 1, 1);
        }
    }

    public List<PlayingCard> getCardList(){
        List cardList = new ArrayList(cardSettings.keySet());
        return cardList;
    }
    /* TO validation or try/catch? */
    public void updateSuit(PlayingCard card, Suit suit, int value){
        cardSettings.get(card).put(suit,value);
    }


    public void updateAllSuits(PlayingCard card, int heartValue, int spadeValue, int clubValue, int diamondValue){
        updateSuit(card, Suit.HEART, heartValue);
        updateSuit(card, Suit.SPADE, spadeValue);
        updateSuit(card, Suit.CLUB, clubValue);
        updateSuit(card, Suit.DIAMOND, diamondValue);
    }

    public int getSuitCount(PlayingCard card, Suit suit){
        return cardSettings.get(card).get(suit);
    }

    public int getTotalSuitCount(PlayingCard card){
        return getSuitCount(card, Suit.HEART) +  getSuitCount(card, Suit.SPADE) + getSuitCount(card, Suit.CLUB) +  getSuitCount(card, Suit.DIAMOND);
    }

/* TODO check that this clears the suits mappings and the card mappings */
    public void clearAllCardSettings(){
        for (PlayingCard card: PlayingCard.values()) {
            removeCard(card);
        }
        cardSettings.clear();
    }

    /* TODO check that this clears the suits mappings and the card mappings */
    public void removeCard(PlayingCard card){
        cardSettings.get(card).clear();
        cardSettings.remove(card);
    }

    public void addSuit(PlayingCard card, Suit suit, int value){
        cardSettings.get(card).put(suit,value);
    }

    public void addCard(PlayingCard card){
        Map<Suit,Integer> suitValueMappings = new LinkedHashMap<>();
        suitValueMappings.put(Suit.HEART,1);
        suitValueMappings.put(Suit.SPADE,1);
        suitValueMappings.put(Suit.CLUB,1);
        suitValueMappings.put(Suit.DIAMOND,1);
        cardSettings.put(card, suitValueMappings);
    }
}
