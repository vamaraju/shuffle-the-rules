/*
* Requirements mandating inclusion:
* */
package model;


public enum CardOrientation {

    DOWN(0, "Face Down", "assets/playing_cards/back/blue.png"),
    UP(1, "Face Up", "assets/playing_cards/front/front.png");

    private final int value;
    private final String name;
    private final String assetLocation;

    private CardOrientation(int value, String name, String assetLocation) {
        this.value = value;
        this.name = name;
        this.assetLocation = assetLocation;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public String getAssetLocation() {
        return this.assetLocation;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
