/*
* Requirements mandating inclusion:
* */
package model.Piles;

import model.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Pile implements Serializable, Iterable {

    private ArrayList<Card> cards = new ArrayList<>();
    private int startingSize;
    private int gridDimensionX;    /* TODO add get/set? */
    private int gridDimensionY;    /* TODO add get/set? */
    /* TODO rename to name?*/
    private String name;
    private int minSize;
    private int maxSize;
    private boolean clicked;

    public Pile() {

    }

    public Pile(int minSize, int maxSize){
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = minSize;
    }

    public Pile(int minSize, int maxSize, int startingSize){
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = startingSize;
    }

    /* TODO add dimensions, startingNumCards to constructor input args? */
    public Pile(String name, int minSize, int maxSize){
        this.name = name;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = minSize;
    }



    public void add(Card card){
        cards.add(card);
    };

    public void view(){

    }

    public void swap(Pile pileToSwapWith, int cardIndexCurrentPile, int cardIndexSwapPile ){

    }

    public Card remove(int index){
        if ((index < 0) ||(index >= cards.size())) {
            return null;
        } else {
            return cards.remove(index);
        }
    }

    public boolean remove(Card card){
        return cards.remove(card);
    }

    public Card draw() {
        return this.remove(0);
    }

    public void shuffle() {
        Random generator = new Random();
        int size = cards.size();

        for (int i = 0; i < size; i++) {
            int index1 = generator.nextInt(size);
            int index2 = generator.nextInt(size);
            Card card1 = cards.get(index1);
            cards.set(index1, cards.get(index2));
            cards.set(index2, card1);
        }
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

    public int getStartingSize() {
        return startingSize;
    }

    public void setStartingSize(int startingSize) {
        this.startingSize = startingSize;
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

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    public Card get(int index) {
        return cards.get(index);
    }
}
