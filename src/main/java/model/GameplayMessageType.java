/*
* Requirements mandating inclusion:
*
* 3.2.2.3.3.1 Player will be notified when their Turn begins.
* 3.2.2.3.3.2 Player will be notified when their Turn ends.
* 3.2.2.3.3.2 Player's Turn will be skipped if Player is inactive.
* */

package model;

import java.io.Serializable;

public enum GameplayMessageType implements Serializable {

    INFO(0, "INFO", "a9a9a9ff", "DARKGREY"),
    EVENT(1, "EVENT", "0x0000ffff", "BLUE"),
    ACTION(2, "ACTION", "0xff0000ff", "RED"),
    ALERT(3, "ALERT", "0xff8c00ff", "DARKORANGE"),
    TURN(4, "TURN", "0x000000ff", "BLACK"),
    INSTRUCTION(5, "INSTRUCTION", "0x000000ff", "BLACK");

    private final int value;
    private final String name;
    private final String color;
    private final String colorName;

    private GameplayMessageType(int value, String name, String color, String colorName) {
        this.value = value;
        this.name = name;
        this.color = color;
        this.colorName = colorName;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return this.color;
    }

    public String getColorName() {
        return this.colorName;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
