package model;


import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class CardSettings {
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

    }

    public Set<String> getCardList(){
        return cardSettings.keySet();
    }

    public void updateSuit(String key, String suit, int value){
        
    }


}
