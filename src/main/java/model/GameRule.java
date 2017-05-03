package model;

import model.GameActions.GameAction;
import model.GameEvents.GameEvent;
import model.Piles.Pile;
import view.Gameplay.GameplayView;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class GameRule implements Serializable {

    protected String name;
    protected String className;
    protected String description;
    protected ArrayList<GameRule> postRules = new ArrayList<>();
    protected ArrayList<GameAction> postActions = new ArrayList<>();
    protected ArrayList<GameEvent> postEvents = new ArrayList<>();
    protected GameRuleProperties properties = new GameRuleProperties();

    protected Pile pile;
    protected int numCards;
    protected String cardValue;
    protected String cardSuit;
    protected String player;

    public GameRule() {
        this.name = "Generic Game Rule.";
        this.className = "GameRule";
        this.description = "A generic game rule.";
    }

    public abstract void run(Object... args);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return this.className;
    }

    public String getDescription() {
        return this.description;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public ArrayList<GameRule> getPostRules() {
        return postRules;
    }

    public Pile getPile() {
        return pile;
    }

    public void setPile(Pile pile) {
        this.pile = pile;
    }

    public int getNumCards() {
        return numCards;
    }

    public void setNumCards(int numCards) {
        this.numCards = numCards;
    }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    public String getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(String cardSuit) {
        this.cardSuit = cardSuit;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getFullClassName() {
        return this.getClass().getCanonicalName();
    }

    public void launchPostRules() {
        for (GameRule r : this.getPostRules()) {
            r.run();
        }
    }

    public void postGameplayMessage(GameplayMessageType type) {
        postGameplayMessage(type, this.getName() + ": " + this.getDescription());
    }

    public void postGameplayMessage(GameplayMessageType type, String message) {
        GameplayView game = GameView.getInstance().getGameplayView();
        game.getGameplayMessageView().addMessage(type, message);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
