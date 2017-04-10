package model.Piles;

import model.Card;

import java.io.Serializable;
import java.util.ArrayList;

public class Pile implements Serializable {

    private ArrayList<Card> cards;
    private int startingNumberOfCards;
    private int gridDimensionX;    /* TODO add get/set? */
    private int gridDimensionY;    /* TODO add get/set? */
    /* TODO rename to name?*/
    private String name;
    private int minSize;
    private int maxSize;
    private boolean clicked;

    /* TODO add dimensions, startingNumberOfCards to constructor input args? */
    public Pile(String name, int minSize, int maxSize){
        cards = new ArrayList<Card>();
        this.name = name;
        this.minSize = minSize;
        this.maxSize = maxSize;
        startingNumberOfCards = 0;

    }
    public void add(Card card){
        cards.add(card);
    };

    public void view(){

    }

    public void swap(Pile pileToSwapWith, int cardIndexCurrentPile, int cardIndexSwapPile ){

    }

    public Card remove(int index){
        Card removedCard = cards.get(index);
        cards.remove(index);
        return removedCard;
    }

    public Card remove(Card card){
        int removedCardIndex = cards.indexOf(card);
        Card removedCard = cards.get(removedCardIndex);
        return removedCard;
    }

    public int getMinSize(){
        return minSize;
    }

    /* TODO requries validation */
    public void setMinSize(int minSize){ this.minSize = minSize; }

    public int getMaxSize(){
        return maxSize;
    }

    /* TODO requries validation */
    public void setMaxSize(int maxSize){
        this.maxSize = maxSize;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    public boolean isFull() {
        return this.cards.size() == this.maxSize;
    }
}
