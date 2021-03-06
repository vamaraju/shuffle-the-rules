/*
* Requirements mandating inclusion:
*
* This class is a view that opens a help window for the Editor Tab with various notes and instructions.
* It is indirectly related to all requirements of the editor tab, but essentially is just a helper class.
* */

package view;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class EditorHelpView extends TextFlow {

    public EditorHelpView() {
        initialize();
    }

    public void initialize() {
        Text title = new Text("Editor Tab Instructions And Notes For Creating A Game\n\n\n\n");

        Text text1 = new Text("Notes:\n\n");
        Text text2 = new Text("---Every game needs exactly one of the following game rules: OnGameStartEvent, OnRoundStartEvent, OnTurnStartEvent, OnTurnEndEvent.\n\n");
        Text text3 = new Text("---Every game needs at least one of the following game rules: PlayerWinAction.\n\n");
        Text text4 = new Text("---The role of OnRoundStartEvent is to reset the current player back to the first " +
                "(starting) player. It is run automatically by OnTurnEndEvent when necessary. There should be no " +
                "player-specific ('Current Player') game rules immediately proceeding OnRoundStartEvent. " +
                "OnTurnStartEvent can safely follow OnRoundStartEvent.\n\n");
        Text text5 = new Text("---The role of OnTurnEndEvent is to launch OnTurnStartEvent and/or " +
                "OnRoundStartEvent with the appropriate player. It does this automatically. There should be no " +
                "game rules following OnTurnEndEvent; it will automatically call the " +
                "OnRoundStartEvent/OnTurnStartEvent. If there are any game rules immediately proceeding " +
                "OnTurnEndEvent, they will launch BEFORE updating the player to the next player, and before launching " +
                "OnRoundStartEvent or OnTurnStartEvent.\n\n");
        Text text6 = new Text("---If OnTurnEndEvent is on the same level as any other game rule, it will be launched last.\n\n");
        Text text7 = new Text("---PlayerWinAction is expected to follow the \"win condition\", which is typically " +
                "an event like OnHandEmptyEvent. It indicates that the player has won the game, and will " +
                "essentially end the game; any game rules the launch after this point may or may not work correctly.\n\n");
        Text text8 = new Text("---If actions and events are in the same level in the graph (i.e., they have the " +
                "same previous rule, or immediately follow the same game rule), all of the actions will always run " +
                "first, and then the events.\n\n");
        Text text9 = new Text("---Multiple actions can follow the same game rule. During gameplay, if an action cannot " +
                "be taken (due to any restrictions placed on the action), the user can click the \"Skip Action\" " +
                "button, and the next action in the same level will be attempted.\n\n");
        Text text10 = new Text("---Once an event or action is successfully taken, no other events or actions in the " +
                "same level will be attempted. The graph will proceed depth-first through the successful rule, and " +
                "ignore (it will not run) any other rules at the same depth level.\n\n");
        Text text11 = new Text("---OnGameEndEvent closes the game window immediately upon being launched. This " +
                "can be used after a PlayerWinAction rule to exit the game once a player wins.\n\n");
        Text text12 = new Text("---Multiple previous rules can be specified by separating them with semicolons (;) " +
                "in the Previous Rule text box. The selected rule will be automatically connected to all of the " +
                "listed previous rules.\n\n");
        Text text13 = new Text("---The priority of an action or event in a particular level defines when the action " +
                "or event will be executed (the sequence). Lowest priorities go first. For example, if in the same " +
                "level there is a DrawCardAction with priority 1, PlaceCardAction with priority 0, and OnHandEmptyEvent" +
                "with priority 0, the execution sequence will be: PlaceCardAction, DrawCardAction, and then OnHandEmptyEvent. " +
                "Note that regardless of priority, actions are always executed before events, and OnTurnEndEvent is " +
                "always executed last in a particular level.\n\n");
        Text text14 = new Text("---A player can only take one action in a particular level. After taking an action, " +
                "the 'action phase' will complete, and any other actions in the same level will be skipped. The game " +
                "will proceed to execute the children rules of the taken action (if there are any), or the events that " +
                "are on the same level as this action. If an action does not have any children, please ensure that " +
                "there is an event on its level to avoid the game entering an incorrect state. \n\n");
        Text text15 = new Text("---EndTurnAction is functionally equivalent to OnTurnEndEvent, but the " +
                "action version (rather than an event). It launches OnTurnEndEvent immediately upon execution, meaning " +
                "that it ends the turn of the current player and starts the turn of the next player. Since it " +
                "is an action, it can be paired together on the same graph level as other actions, and by " +
                "manipulating priorities, EndTurnAction can be made to abruptly end a player's turn as needed. " +
                "Note that EndTurnAction cannot be skipped, and it will end the current turn as soon it is reached, " +
                "regardless of any other actions or events on the same level. No post-rules of EndTurnAction " +
                "are ever launched.\n\n");

        this.getChildren().addAll(title, text1, text2, text3, text4, text5, text6, text7,
                text8, text9, text10, text11, text12, text13, text14, text15);
    }

}
