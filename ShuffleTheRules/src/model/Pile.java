package model;

import java.util.ArrayList;

public class Pile {

    ArrayList<Card> cardList;
    int startingNumberOfCards;
    int gridDimensionX;    /* TODO add get/set? */
    int gridDimensionY;    /* TODO add get/set? */
    /* TODO rename to name?*/
    String pileID;
    int minPileSize;
    int maxPileSize;

    /* TODO add dimensions, startingNumberOfCards to constructor input args? */
    public Pile(String name, int minSize, int maxSize){
        cardList = new ArrayList<Card>();
        pileID = name;
        minPileSize = minSize;
        maxPileSize = maxSize;
        startingNumberOfCards = 0;
        //gridDimensionX
        //gridDimensionY

    }
    public void add(Card card){

    };

    public void view(){

    }

    public void swap(Pile pileToSwapWith, int cardIndexCurrentPile, int cardIndexSwapPile ){

    }

    public Card remove(int index){
        return new Card();
    }

    public Card remove(Card card){
        return new Card();
    }

    public int getMinPileSize(){
        return minPileSize;
    }

    /* TODO requries validation */
    public void setMinPileSize(int minSize){ minPileSize = minSize; }

    public int getMaxPileSize(){
        return maxPileSize;
    }

    /* TODO requries validation */
    public void setMaxPileSize(int maxSize){
        maxPileSize = maxSize;
    }
}
