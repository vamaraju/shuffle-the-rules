package model;

public enum SortType {

    ASCENDING(0, "Ascending"),
    DESCENDING(1, "Descending");

    private final int value;
    private final String name;

    private SortType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
