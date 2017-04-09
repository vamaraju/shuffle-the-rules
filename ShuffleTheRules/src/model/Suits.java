package model;

import java.io.Serializable;

public enum Suits implements Serializable {

    CLUB(0, "Club"),
    DIAMOND(1, "Diamond"),
    HEART(2, "Heart"),
    SPADE(3, "Spade");

    private final int rank;
    private final String name;

    private Suits(int rank, String name) {
        this.rank = rank;
        this.name = name;
    }

    public int getRank() {
        return this.rank;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
