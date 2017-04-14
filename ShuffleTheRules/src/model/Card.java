/*
* Requirements mandating inclusion:
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
        return ((this.getValue() == otherCard.getValue()) && (this.getSuit().equals(otherCard.getSuit())));
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
