package model;

import java.io.Serializable;

public enum GameRuleType implements Serializable {

    EVENT("Event"),
    ACTION("Action");

    private String name;
    private GameRuleType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getClassName() {
        return "Game" + this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
