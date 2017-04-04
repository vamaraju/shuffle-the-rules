package model.Piles;

import model.Card;

import java.io.Serializable;
import java.util.ArrayList;

public class Pile implements Serializable {

    ArrayList<Card> cardList;
    int startingNumberOfCards;
    int gridDimensionX;    /* TODO add get/set? */
    int gridDimensionY;    /* TODO add get/set? */
    /* TODO rename to name?*/
    String pileName;
    int minPileSize;
    int maxPileSize;

    /* TODO add dimensions, startingNumberOfCards to constructor input args? */
    public Pile(String name, int minSize, int maxSize){
        cardList = new ArrayList<Card>();
        pileName = name;
        minPileSize = minSize;
        maxPileSize = maxSize;
        startingNumberOfCards = 0;

    }
    public void add(Card card){
        cardList.add(card);
    };

    public void view(){

    }

    public void swap(Pile pileToSwapWith, int cardIndexCurrentPile, int cardIndexSwapPile ){

    }

    public Card remove(int index){
        Card removedCard = cardList.get(index);
        cardList.remove(index);
        return removedCard;
    }

    public Card remove(Card card){
        int removedCardIndex = cardList.indexOf(card);
        Card removedCard = cardList.get(removedCardIndex);
        return removedCard;
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

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    public int getStartingNumberOfCards() {
        return startingNumberOfCards;
    }

    public void setStartingNumberOfCards(int startingNumberOfCards) {
        this.startingNumberOfCards = startingNumberOfCards;
    }

    public int getGridDimensionX() {
        return gridDimensionX;
    }

    public void setGridDimensionX(int gridDimensionX) {
        this.gridDimensionX = gridDimensionX;
    }

    public int getGridDimensionY() {
        return gridDimensionY;
    }

    public void setGridDimensionY(int gridDimensionY) {
        this.gridDimensionY = gridDimensionY;
    }

    public String getPileName() {
        return pileName;
    }

    public void setPileName(String pileName) {
        this.pileName = pileName;
    }

}
