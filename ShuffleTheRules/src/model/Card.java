package model;

public class Card {
    int value;
    String suit;
    String cardFaceAssetLocation;
    String cardBackAssetLocation;
    String cardOrientation;

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
}
