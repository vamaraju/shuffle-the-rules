/*
* Requirements mandating inclusion:
* */
package model.Piles;

import java.io.Serializable;

public enum PileType implements Serializable {

    GENERAL("General"),
    DECK("Deck"),
    HAND("Hand");

    private final String name;
    private PileType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Pile getObject() {
        switch (this) {
            case GENERAL:
                return new Pile();
            case DECK:
                return new Deck();
            case HAND:
                return new Hand();
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
