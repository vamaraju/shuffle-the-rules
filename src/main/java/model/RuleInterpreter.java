/*
* Requirements mandating inclusion:
*
* 3.2.2.5.3.1 Rule Interpreter will interpret and execute Game Rules for a Card Game.
*/
package model;

import javafx.scene.control.ButtonType;
import model.GameActions.GameAction;
import model.GameEvents.GameEvent;
import model.GameEvents.OnTurnEndEvent;
import view.Gameplay.GameplayViewUpdater;
import view.Gameplay.PlaceCardDialog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RuleInterpreter {


    public static void execute(RuleGraph ruleGraph) {
        if (ruleGraph != null && ruleGraph.getRoot() != null) {
            new Thread(ruleGraph.getRoot()).start();
        }
    }


    public static void launchPostRules(GameRule rule) {
        List<GameRule> postActions = new ArrayList<>();
        List<GameRule> postEvents = new ArrayList<>();

        GameplayViewUpdater.enableDefaultButtons();

        // Populate postActions and postEvents using postRules.
        for (GameRule r : rule.getPostRules()) {
            GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, "Possible action/event for this turn: " + r.getName());
            if (r instanceof GameAction) {
                postActions.add(r);
            } else if (r instanceof GameEvent) {
                postEvents.add(r);
            }

            // Set the parent of each postRule.
            r.setParentRule(rule);
        }

        // Sort the actions and events by priority. Lowest priority goes first.
        postActions.sort(Comparator.comparingInt(GameRule::getPriority));
        postEvents.sort(Comparator.comparingInt(GameRule::getPriority));

        // Set the finalAction flag of the final action.
        if (postActions.size() > 0) {
            postActions.get(postActions.size()-1).setFinalAction(true);
        }

        // Enable 'Skip Action' button if there are sufficient actions/events.
        if (postActions.size() > 1 || (postActions.size() == 1 && postEvents.size() > 0)) {
            GameplayViewUpdater.enableSkipActionButton();
        }

        // If there is a postAction, reset actionPhaseCompleted flag to false (before it starts executing).
        if (postActions.size() > 0) {
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


    public static void launchRoundStartEvent() {
        GameCreation.getInstance().getRuleGraph().getRoundStart().run();
    }


    public static void launchTurnStartEvent() {
        GameCreation.getInstance().getRuleGraph().getTurnStart().run();
    }


    public static void launchTurnEndEvent() {
        GameCreation.getInstance().getRuleGraph().getTurnEnd().run();
    }


    public static boolean playerInactive() {
        if (GameState.getInstance().getCurrentPlayer().isInactive()) {
            GameCreation.getInstance().getRuleGraph().getTurnEnd().run();
            return true;
        }
        return false;
    }


    public static boolean skipAction() {
        if (GameState.getInstance().isSkipActionClicked()) {
            GameState.getInstance().setSkipActionClicked(false);
            GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, "Skipped Action: " + GameState.getInstance().getCurrentRule().getName() + ".");
            return true;
        }
        return false;
    }


    public static boolean skipTurn() {
        if (GameState.getInstance().isSkipTurnClicked()) {
            GameState.getInstance().setSkipTurnClicked(false);
            GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, "Skipped turn for player: " + GameState.getInstance().getCurrentPlayer().getName() + ".");
            GameCreation.getInstance().getRuleGraph().getTurnEnd().run();
            return true;
        }
        return false;
    }


    public static boolean gameCompleted() {
        return GameState.getInstance().isGameCompleted();
    }

    public static boolean actionPhaseCompleted() {
        return GameState.getInstance().isActionPhaseCompleted();
    }

    public static String defaultGameplayMessage() {
        return GameState.getInstance().getCurrentRule().getName() + ": " + GameState.getInstance().getCurrentRule().getDescription();
    }

    public static String finishedGameplayMessage() {
        return "Finished executing: " + GameState.getInstance().getCurrentRule().getName() + ".";
    }

    public static void runPlaceCardDialog() {
        PlaceCardDialog placeCardDialog = new PlaceCardDialog();
        Optional<ButtonType> buttonClickResult = placeCardDialog.showAndWait();

        if (buttonClickResult.isPresent() && buttonClickResult.get() == ButtonType.APPLY) {
            if (placeCardDialog.getSelectedToggle() != null) {
                CardOrientation orientation = (CardOrientation) placeCardDialog.getSelectedToggle().getUserData();

                List<Card> clickedCards = GameState.getInstance().getClickedCards();
                for (Card c : clickedCards) {
                    c.setOrientation(orientation);
                }
            }
        }
        GameState.getInstance().getLock().unlock();
    }
}
