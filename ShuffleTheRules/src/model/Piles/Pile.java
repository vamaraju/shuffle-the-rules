/*
* Requirements mandating inclusion:
* */
package model.Piles;

import model.Card;
import model.CardOrientation;
import model.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Pile implements Serializable, Iterable {

    private ArrayList<Card> cards = new ArrayList<>();
    private String name;
    private int startingSize;
    private int minSize;
    private int maxSize;
    private CardOrientation cardOrientation;
    private PileType pileType;
    private String viewablePlayers;

    public Pile() {
        this.pileType = PileType.GENERAL;
    }

    public Pile(int minSize, int maxSize){
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = minSize;
        this.pileType = PileType.GENERAL;
    }

    public Pile(int minSize, int maxSize, int startingSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = startingSize;
        this.pileType = PileType.GENERAL;
    }


    public Pile(String name, int minSize, int maxSize) {
        this.name = name;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = minSize;
        this.pileType = PileType.GENERAL;
    }

    public Pile(String name, int minSize, int maxSize, int startingSize) {
        this.name = name;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = startingSize;
        this.pileType = PileType.GENERAL;
    }

    public Pile(String name, int minSize, int maxSize, int startingSize, CardOrientation cardOrientation) {
        this.name = name;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = startingSize;
        this.cardOrientation = cardOrientation;
        this.pileType = PileType.GENERAL;
    }

    public Pile(String name, int minSize, int maxSize, int startingSize, CardOrientation cardOrientation, String viewablePlayers) {
        this.name = name;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = startingSize;
        this.cardOrientation = cardOrientation;
        this.viewablePlayers = viewablePlayers;
        this.pileType = PileType.GENERAL;
    }

    public void add(Card card){
        cards.add(card);
    };

    public void addToTop(Card card) {
        cards.add(0, card);
    }

    public void addToBottom(Card card) {
        cards.add(card);
    }

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

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize(){
        return maxSize;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardOrientation getCardOrientation() {
        return cardOrientation;
    }

    public void setCardOrientation(CardOrientation cardOrientation) {
        this.cardOrientation = cardOrientation;
    }

    public PileType getPileType() {
        return pileType;
    }

    public void setPileType(PileType pileType) {
        this.pileType = pileType;
    }

    public String getViewablePlayers() {
        return viewablePlayers;
    }

    public void setViewablePlayers(String viewablePlayers) {
        this.viewablePlayers = viewablePlayers;
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
