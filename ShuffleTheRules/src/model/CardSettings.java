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
    Map<String,Map<String,Integer>> cardSettings;

    public CardSettings(){
        initialize();
    }

    private void initialize(){
        cardSettings = new LinkedHashMap<>();
        String initialDeck[] = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
        for (String card: initialDeck) {
            /* Default values - deck with 52 cards */
            addCard(card);
            //updateAllSuits(card, 1, 1, 1, 1);
        }
    }

    public List<String> getCardList(){
        List cardList = new ArrayList(cardSettings.keySet());
        return cardList;
    }
    /* TO validation or try/catch? */
    public void updateSuit(String card, String suit, int value){
        cardSettings.get(card).put(suit,value);
    }


    public void updateAllSuits(String card, int heartValue, int spadeValue, int clubValue, int diamondValue){
        updateSuit(card, "heart", heartValue);
        updateSuit(card, "spade", spadeValue);
        updateSuit(card, "club", clubValue);
        updateSuit(card, "diamond", diamondValue);
    }

    public int getSuitCount(String card, String suit){
        return cardSettings.get(card).get(suit);
    }

    public int getTotalSuitCount(String card){
        return getSuitCount(card,"heart") +  getSuitCount(card,"spade") + getSuitCount(card,"club") +  getSuitCount(card,"diamond");
    }

/* TODO check that this clears the suits mappings and the card mappings */
    public void clearAllCardSettings(){
        String initialDeck[] = {"Ace","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
        for (String card: initialDeck) {
            removeCard(card);
        }
        cardSettings.clear();
    }

    /* TODO check that this clears the suits mappings and the card mappings */
    public void removeCard(String card){
        cardSettings.get(card).clear();
        cardSettings.remove(card);
    }

    public void addSuit(String card, String suit, int value){
        cardSettings.get(card).put(suit,value);
    }

    public void addCard(String card){
        Map<String,Integer> suitValueMappings = new LinkedHashMap<>();
        suitValueMappings.put("heart",1);
        suitValueMappings.put("spade",1);
        suitValueMappings.put("club",1);
        suitValueMappings.put("diamond",1);
        cardSettings.put(card, suitValueMappings);
    }
}
