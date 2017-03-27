package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public abstract class GameEvent {

    protected String name;
    protected String description;
    protected ArrayList<GameAction> postActions = new ArrayList<>();
    protected ArrayList<GameEvent> postEvents = new ArrayList<>();

    public GameEvent() {
        this.name = "GameEvent";
        this.description = "A generic game event.";
    }

    /* actions can contain any object that implements the GameAction interface,
    *  it will accept all our our Actions which implement GameAction */
    public abstract void run(ArrayList<GameAction> actions);

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
