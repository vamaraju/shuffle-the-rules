/*
* Requirements mandating inclusion:
*
* 3.2.1.1.3.2 User can specify number of Cards in a Pile (max/min).
* 3.2.1.1.3.3 User can specify the Player that a Pile is associated with.
* 3.2.1.1.3.4 User can specify the type of a Pile. (Hand, Deck, Discard, Pile).
* 3.2.1.1.3.5 User can specify the Players that are allowed to view a Pile.
* 3.2.1.1.3.6 User can specify whether or not the Cards in the Pile are face down or face up or a combination of both.
* 3.2.1.1.3.8 User can specify a name for the Pile.
* 3.2.2.2.3.3 User can choose Card orientation (face up, face down).
* */
package model.Piles;

import model.Card;
import model.CardOrientation;
import model.SortType;
import model.Suit;

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

    public void add(int index, Card card){
        // If the pile already has a CardOrientation (UP or DOWN; not EITHER), make the card match it.
        // If the pile has no CardOrientation (or it is EITHER), then refer to the card.
        // If the card has no CardOrientation (or it is EITHER), default it to DOWN.
        if (this.cardOrientation != null && this.cardOrientation != CardOrientation.EITHER) {
            card.setOrientation(this.cardOrientation);
        } else if (card.getOrientation() == null || card.getOrientation() == CardOrientation.EITHER) {
            card.setOrientation(CardOrientation.DOWN);
        }
        if (index == -1) {
            cards.add(card);
        } else {
            cards.add(index, card);
        }
    }

    public void add(Card card){
        add(-1, card);
    }

    public void addToTop(Card card) {
        add(0, card);
    }

    public void addToBottom(Card card) {
        add(card);
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

    public void empty() {
        cards.clear();
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

    public int size() {
        return this.cards.size();
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

    public boolean isMin() {
        return this.cards.size() == this.minSize;
    }

    public void populate(List<Card> cardPool) {
        cards.clear();
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

    public List<Card> getCardsBySuit(Suit suit) {
        List<Card> cardList = new ArrayList<>();
        for (Card c : cards) {
            if (c.getSuit() == suit) {
                cardList.add(c);
            }
        }
        return cardList;
    }

    public void sort() {
        sort(SortType.ASCENDING);
    }

    public void sort(SortType type) {
        sortList(cards, type);
    }

    public void sort(SortType type, boolean bySuit) {
        if (bySuit) {
            // Split up the pile by suits into separate lists, and sort each list individually.
            List<List<Card>> cardsBySuit = new ArrayList<>();
            for (int i = 0; i < Suit.values().length; i++) {
                cardsBySuit.add(getCardsBySuit(Suit.values()[i]));
                sortList(cardsBySuit.get(i), type);
            }

            // Combine all the individual-suit lists.
            cards.clear();
            for (List<Card> cardsOfASuit : cardsBySuit) {
                cards.addAll(cardsOfASuit);
            }
        } else {
            sortList(cards, type);
        }
    }

    private void sortList(List<Card> list, SortType type) {
        if (type == null) {return;}
        switch (type) {
            case ASCENDING:
                list.sort(Comparator.comparingInt((c) -> c.getValue().getValue()));
                break;
            case DESCENDING:
                Collections.sort(list, (c1, c2) -> Integer.compare(c2.getValue().getValue(), c1.getValue().getValue()));
                break;
        }
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
