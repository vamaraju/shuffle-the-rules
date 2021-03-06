/*
* Requirements mandating inclusion:
*
* 3.2.2.2.3.3 User can choose Card orientation (face up, face down).
* 3.2.1.1.3.6 User can specify whether or not the Cards in the Pile are face down or face up or a combination of both.
* */
package model;


import java.io.Serializable;

public enum CardOrientation implements Serializable {

    DOWN(0, "Face Down", "assets/playing_cards/back/blue.png"),
    UP(1, "Face Up", "assets/playing_cards/front/front.png"),
    EITHER(2, "Either", "assets/playing_cards/back/red.png");

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
