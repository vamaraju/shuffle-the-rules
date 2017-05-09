/*
* Requirements mandating inclusion:
*
* 3.2.2.2.3.1 User can select Card(s) from Hand to play.
* 3.2.2.2.3.2 User can select Card(s) from Hand to discard.
* 3.2.2.2.3.3 User can choose Card orientation (face up, face down).
* 3.2.2.2.3.4 User can select Card(s) to swap between Piles.
* 3.2.2.2.3.5 User can draw Card(s) from a Pile.
* 3.2.2.2.3.6 User can place Card(s) on a Pile.
* 3.2.2.2.3.3 User can choose Card orientation (face up, face down).
* 3.2.1.1.3.6 User can specify whether or not the Cards in the Pile are face down or face up or a combination of both.
* */
package model;

import java.io.Serializable;


public class Card implements Serializable, Comparable {
    private PlayingCard value;
    private Suit suit;
    private String cardFaceAssetLocation;
    private String cardBackAssetLocation;
    private CardOrientation orientation;

    public Card() {

    }

    public Card(PlayingCard value, Suit suit){
        this.value = value;
        this.suit = suit;

        this.cardFaceAssetLocation = suit.getCardAssetLocation() + value.getName().toLowerCase() + ".png";
        this.cardBackAssetLocation = "assets/playing_cards/back/blue.png";
    }

    public Card(PlayingCard value, Suit suit, CardOrientation orientation){
        this(value, suit);
        this.orientation = orientation;
    }

    public String getCardFaceAssetLocation() {
        return this.cardFaceAssetLocation;
    }

    public void setCardFaceAssetLocation(String cardFaceAssetLocation) {
        this.cardFaceAssetLocation = cardFaceAssetLocation;
    }

    public String getCardBackAssetLocation() {
        return this.cardBackAssetLocation;
    }

    public void setCardBackAssetLocation(String cardBackAssetLocation) {
        this.cardBackAssetLocation = cardBackAssetLocation;
    }

    public CardOrientation getOrientation() {
        return this.orientation;
    }

    public void setOrientation(CardOrientation orientation) {
        this.orientation = orientation;
    }

    public PlayingCard getValue() {
        return this.value;
    }

    public void setValue(PlayingCard value) {
        this.value = value;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public int hashCode() {
        return this.value.getValue() + this.suit.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (obj == null) {return false;}
        if (!(obj instanceof Card)) {return false;}

        Card otherCard = (Card) obj;
        return (this.getValue() == otherCard.getValue() && this.getSuit() == otherCard.getSuit());
    }

    @Override
    public int compareTo(Object obj) {
        Card otherCard = (Card) obj;
        if (this.getValue().getValue() < otherCard.getValue().getValue()) {
            return -1;
        } else if (this.getValue().getValue() > otherCard.getValue().getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
