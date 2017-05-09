/*
* Requirements mandating inclusion:
*
* 3.2.1.6.3.1 User can specify Game Rules associated with particular Cards.
* 3.2.1.6.3.2 User can create Game Rules by selecting Game Events and adding a Game Action to that event.
* 3.2.1.6.3.3 User can create a Game Flow Sequence by linking Game Rules.
* */

package model;

import java.io.Serializable;

public enum GameRuleType implements Serializable {

    EVENT("Event", "0x0000ffff", "BLUE"),
    ACTION("Action", "0xff0000ff", "RED");

    private final String name;
    private final String color;
    private final String colorName;

    private GameRuleType(String name, String color, String colorName) {
        this.name = name;
        this.color = color;
        this.colorName = colorName;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public String getColorName() {
        return this.colorName;
    }

    public String getClassName() {
        return "Game" + this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
