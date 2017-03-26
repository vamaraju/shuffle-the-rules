package model;


import java.util.Map;
import java.util.Set;

/* TODO how will Joker be handled?*/
/* TODO Error handling for methods */
public class CardSettings {
    /* stores all Card information:
       <value:<heart: num_hearts
               spade: num_spades
               club: num_clubs
               diamond: num_diamonds*/
    Map<String,Map<String,Integer>> cardSettings;

    private CardSettings(){
        initialize();
    }

    private void initialize(){
        String initialDeck[] = {"Ace","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
        for (String card: initialDeck) {
            /* Default values - deck with 52 cards */
            updateAllSuits(card, 1, 1, 1, 1);
        }
    }

    public Set<String> getCardList(){
        return cardSettings.keySet();
    }

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

    public void clearAllCardSettings(){
        String initialDeck[] = {"Ace","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
        for (String card: initialDeck) {
            cardSettings.remove(card);
        }
        cardSettings.clear();
    }

    public void removeCard(String card){
        cardSettings.get(card).clear();
    }

    public void addCard(String card){
        updateAllSuits(card, 1, 1, 1, 1);
    }
}
