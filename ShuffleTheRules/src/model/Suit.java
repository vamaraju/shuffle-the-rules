package model;

import java.io.Serializable;

public enum Suit implements Serializable {

    CLUB(0, "Club", "assets/playing_cards/suit/club.png", "assets/playing_cards/club/"),
    DIAMOND(1, "Diamond", "assets/playing_cards/suit/diamond.png", "assets/playing_cards/diamond/"),
    HEART(2, "Heart", "assets/playing_cards/suit/heart.png", "assets/playing_cards/heart/"),
    SPADE(3, "Spade", "assets/playing_cards/suit/spade.png", "assets/playing_cards/spade/");

    private final int rank;
    private final String name;
    private final String suitAssetLocation;
    private final String cardAssetLocation;

    private Suit(int rank, String name, String suitAssetLocation, String cardAssetLocation) {
        this.rank = rank;
        this.name = name;
        this.suitAssetLocation = suitAssetLocation;
        this.cardAssetLocation = cardAssetLocation;
    }

    public int getRank() {
        return this.rank;
    }

    public String getName() {
        return this.name;
    }

    public String getSuitAssetLocation() {
        return this.suitAssetLocation;
    }

    public String getCardAssetLocation() {
        return this.cardAssetLocation;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
