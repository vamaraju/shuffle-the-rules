/*
* Requirements mandating inclusion:
* */
package model.Piles;

import model.Card;
import model.CardOrientation;

import java.io.Serializable;
import java.util.*;

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
        this.cardOrientation = CardOrientation.EITHER;
    }

    public Pile(int minSize, int maxSize, int startingSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = startingSize;
        this.pileType = PileType.GENERAL;
        this.cardOrientation = CardOrientation.EITHER;
    }


    public Pile(String name, int minSize, int maxSize) {
        this.name = name;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = minSize;
        this.pileType = PileType.GENERAL;
        this.cardOrientation = CardOrientation.EITHER;
    }

    public Pile(String name, int minSize, int maxSize, int startingSize) {
        this.name = name;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.startingSize = startingSize;
        this.pileType = PileType.GENERAL;
        this.cardOrientation = CardOrientation.EITHER;
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
        card.setOrientation(this.cardOrientation);
        cards.add(card);
    }

    public void addToTop(Card card) {
        card.setOrientation(this.cardOrientation);
        cards.add(0, card);
    }

    public void addToBottom(Card card) {
        card.setOrientation(this.cardOrientation);
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
        Collections.shuffle(cards);
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

    public void populate(List<Card> cardPool) {
        for (int i = 0; i < startingSize; i++) {
            add(cardPool.remove(0));
        }
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public Card getTopCard() {
        return cards.get(0);
    }

    public Card getBottomCard() {
        return cards.get(cards.size()-1);
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public String toString() {
        return name;
    }
}
