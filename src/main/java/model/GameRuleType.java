/*
* Requirements mandating inclusion:
* */
package model;

import java.io.Serializable;

public enum GameRuleType implements Serializable {

    EVENT("Event", "0x0000ffff"),
    ACTION("Action", "0xff0000ff");

    private final String name;
    private final String color;
    private GameRuleType(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public String getClassName() {
        return "Game" + this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
