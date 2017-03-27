package model;

import model.GameActions.GameAction;
import model.GameEvents.GameEvent;
import java.util.ArrayList;

public class GameRule {

    protected String name;
    protected String description;
    protected ArrayList<GameAction> postActions = new ArrayList<>();
    protected ArrayList<GameEvent> postEvents = new ArrayList<>();

    public GameRule() {
        this.name = "GameRule";
        this.description = "A generic game rule.";
    }

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

}
