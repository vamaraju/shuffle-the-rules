package model;

public class Card {
    private int value;
    private String suit;
    private String cardFaceAssetLocation;
    private String cardBackAssetLocation;
    private String cardOrientation;

    public Card(int value, String suit){
        value = value;
        suit = suit;
    }

    public String getCardFaceAssetLocation() {
        return cardFaceAssetLocation;
    }

    public void setCardFaceAssetLocation(String cardFaceAssetLocation) {
        this.cardFaceAssetLocation = cardFaceAssetLocation;
    }

    public String getCardBackAssetLocation() {
        return cardBackAssetLocation;
    }

    public void setCardBackAssetLocation(String cardBackAssetLocation) {
        this.cardBackAssetLocation = cardBackAssetLocation;
    }

    public String getCardOrientation() {
        return cardOrientation;
    }

    public void setCardOrientation(String cardOrientation) {
        this.cardOrientation = cardOrientation;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }
}
