package model;

import model.GameActions.GameAction;
import model.GameEvents.GameEvent;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class GameRule implements Serializable {

    protected String name;
    protected String description;
    protected ArrayList<GameAction> postActions = new ArrayList<>();
    protected ArrayList<GameEvent> postEvents = new ArrayList<>();
    protected GameRuleProperties properties = new GameRuleProperties();

    public GameRule() {
        this.name = "GameRule";
        this.description = "A generic game rule.";
    }

    public abstract void run(Object... args);

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<GameAction> getPostActions() {
        return this.postActions;
    }

    public ArrayList<GameEvent> getPostEvents() {
        return this.postEvents;
    }

    public GameRuleProperties getProperties() {
        return this.properties;
    }

}
