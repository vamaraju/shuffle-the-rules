package model;

import model.GameActions.GameAction;
import model.GameEvents.GameEvent;
import model.GameEvents.OnTurnEndEvent;
import model.Piles.Pile;
import view.Gameplay.GameplayViewUpdater;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class GameRule implements Serializable, Runnable {

    protected String name;
    protected String className;
    protected String description;
    protected ArrayList<GameRule> postRules = new ArrayList<>();
    protected GameRule parentRule;
    protected boolean finalAction = false;

    protected Pile pile;
    protected int numCards;
    protected String cardValue;
    protected String cardSuit;
    protected String player;
    protected int priority;

    public GameRule() {
        this.name = "Generic Game Rule.";
        this.className = "GameRule";
        this.description = "A generic game rule.";
    }

    public abstract void run();

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public GameRule getParentRule() {
        return parentRule;
    }

    public void setParentRule(GameRule parentRule) {
        this.parentRule = parentRule;
    }

    public boolean isFinalAction() {
        return finalAction;
    }

    public void setFinalAction(boolean finalAction) {
        this.finalAction = finalAction;
    }



    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
