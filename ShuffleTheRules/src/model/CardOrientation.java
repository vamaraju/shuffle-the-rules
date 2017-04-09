package model;

public enum CardOrientation {

    DOWN(0, "Face Down"),
    UP(1, "Face Up");

    private final int value;
    private final String name;

    private CardOrientation(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
