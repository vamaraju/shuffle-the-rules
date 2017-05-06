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

    public void launchPostRules() {
        List<GameRule> postActions = new ArrayList<>();
        List<GameRule> postEvents = new ArrayList<>();

        GameplayViewUpdater.enableDefaultButtons();

        // Populate postActions and postEvents using postRules.
        for (GameRule r : this.getPostRules()) {
            GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, "Possible action/event for this turn: " + r.getName());
            if (r instanceof GameAction) {
                postActions.add(r);
            } else if (r instanceof GameEvent) {
                postEvents.add(r);
            }

            // Set the parent of each postRule.
            r.setParentRule(this);
        }

        // Sort the actions and events by priority. Lowest priority goes first.
        postActions.sort(Comparator.comparingInt((rule) -> rule.getPriority()));
        postEvents.sort(Comparator.comparingInt((rule) -> rule.getPriority()));

        // Set the finalAction flag of the final action.
        if (postActions.size() > 0) {
            postActions.get(postActions.size()-1).setFinalAction(true);
        }

        // Enable 'Skip Action' button if there are sufficient actions/events.
        // Also, since there is a postAction, set actionPhaseCompleted flag to false (before they start executing).
        if (postActions.size() > 1 || (postActions.size() == 1 && postEvents.size() > 0)) {
            GameplayViewUpdater.enableSkipActionButton();
            GameState.getInstance().setActionPhaseCompleted(false);
        }

        for (GameRule postAction : postActions) {
            postAction.run();
        }

        GameRule onTurnEnd = null;
        for (GameRule postEvent : postEvents) {
            // Run all events except for OnTurnEndEvent. It will be run last.
            if (postEvent instanceof OnTurnEndEvent) {
                onTurnEnd = postEvent;
            } else {
                postEvent.run();
            }
        }

        // Run OnTurnEndEvent last.
        if (onTurnEnd != null) {
            onTurnEnd.run();
        }
    }

    public boolean playerInactive() {
        if (GameState.getInstance().getCurrentPlayer().isInactive()) {
            GameCreation.getInstance().getRuleGraph().getTurnEnd().run();
            return true;
        }
        return false;
    }

    public boolean skipAction() {
        if (GameState.getInstance().isSkipActionClicked()) {
            GameState.getInstance().setSkipActionClicked(false);
            GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, "Skipped Action: " + getName() + ".");
            return true;
        }
        return false;
    }

    public boolean gameCompleted() {
        return GameState.getInstance().isGameCompleted();
    }

    public boolean actionPhaseCompleted() {
        return GameState.getInstance().isActionPhaseCompleted();
    }

    public String defaultGameplayMessage() {
        return this.getName() + ": " + this.getDescription();
    }

    public String finishedGameplayMessage() {
        return "Finished executing: " + this.getName() + ".";
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
