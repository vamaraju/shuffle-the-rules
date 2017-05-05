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
        Text text2 = new Text("---Every game needs exactly one of the following game rules: OnGameStartEvent, OnRoundStartEvent, OnTurnStartEvent.\n\n");
        Text text3 = new Text("---Every game needs at least one of the following game rules: OnTurnEndEvent, PlayerWinAction.\n\n");
        Text text4 = new Text("---The role of OnRoundStartEvent is to reset the current player back to the first " +
                "(starting) player. It is run automatically by OnTurnEndEvent when necessary. There should be no " +
                "player-specific ('Current Player') game rules immediately proceeding OnRoundStartEvent. " +
                "OnTurnStartEvent can safely follow OnRoundStartEvent.\n\n");
        Text text5 = new Text("---The role of OnTurnEndEvent is to launch OnTurnStartEvent and/or " +
                "OnRoundStartEvent with the appropriate player. It does this automatically. There should be no " +
                "game rules following OnTurnEndEvent; it will automatically call the " +
                "OnRoundStartEvent/OnTurnStartEvent. If there are any game rules immediately proceeding " +
                "OnTurnEndEvent, they will launch BEFORE updating the player to the next player, and before launching" +
                "OnRoundStartEvent or OnTurnStartEvent.\n\n");
        Text text6 = new Text("---If OnTurnEndEvent is on the same level as any other game rule, it will be launched last.\n\n");
        Text text7 = new Text("---PlayerWinAction is expected to follow the \"win condition\", which is typically " +
                "an event like OnHandEmptyEvent. It indicates that the player has won the game, and will " +
                "essentially end the game; any game rules the launch after this point may or may not work correctly\n\n");
        Text text8 = new Text("---If actions and events are in the same level in the graph (i.e., they have the " +
                "same previous rule, or immediately follow the same game rule), all of the actions will always run" +
                "first, and then the events.\n\n");

        this.getChildren().addAll(title, text1, text2, text3, text4, text5, text6, text7, text8);
    }

}
