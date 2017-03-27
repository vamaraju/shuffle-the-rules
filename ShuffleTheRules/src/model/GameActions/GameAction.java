package model.GameActions;

import model.GameEvents.GameEvent;

import java.util.ArrayList;

public abstract class GameAction {

    protected String name;
    protected String description;
    protected ArrayList<GameAction> postActions = new ArrayList<>();
    protected ArrayList<GameEvent> postEvents = new ArrayList<>();

    public GameAction() {
        this.name = "GameAction";
        this.description = "A generic game action.";
    }

    /* Needs ArrayList argument */
    public abstract void run();

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
