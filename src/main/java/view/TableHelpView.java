/*
* Requirements mandating inclusion:
*
* This class is a view that opens a help window for the Table Tab with various notes and instructions.
* It is indirectly related to all requirements of the table tab, but essentially is just a helper class.
* */

package view;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class TableHelpView extends TextFlow {

    public TableHelpView() {
        initialize();
    }

    public void initialize() {
        Text title = new Text("Table Tab Instructions And Notes For Creating A Game\n\n\n\n");

        Text text1 = new Text("Notes:\n\n");

        Text text2 = new Text("---The Table Tab is used to customize the table layout and game objects (Piles, Cards, Players, Hands) and settings which will be used during the game.\n" +
                "---Each time an object or setting is added or updated, a helpful message will be displayed informing user whether or not their update was successful.  If an update was unsuccessful, the user will be told why.\n" +
                "---Before playing a game, the game must be validated.  Checks will be run over all input settings to make sure there are no contradictions (ie. the number of Cards in the game is the game is set to be 4, but the minimum number of Cards a Pile can have is 10).\n" +
                "---On the left, what the current table layout looks like will is displayed.  On the right are three expandable menus which allow customization of settings and game objects. \n\n\n");

        Text text3 = new Text("Customizing the Table Layout (Play Area) \n\n");

        Text text4 = new Text("---The table layout (play area) is represented as a grid.  Inside each grid block, 1 Pile can be placed.\n" +
                "---The height, width and size of grid blocks can be set.\n" +
                "---The table layout will automatically update if a Pile is added, moved or deleted.\n" +
                "---Please Note: If you resize the grid to have smaller dimensions, ANY PILES OUTSIDE OF THE NEW DIMENSIONS WILL BE DELETED WITHOUT WARNING. \n\n\n");

        Text text5 = new Text("General Settings Menu\n\n");

        Text text6 = new Text("---The General Settings Menu is responsible for general settings, such as the number of players (min, max), player hand size (min, max, starting), and player turn order.\n" +
                "---The number of players and hand size settings must be set.  Player turn order is optional.\n" +
                "---If players in the game will not have hands, set all of the hand size fields to be 0.\n\n\n");

        Text text7 = new Text("Piles Menu\n\n");


        Text text8 = new Text("---To add a Pile, all settings (General, Number of Cards, Coordinates, Player Association, Card Orientation) must be filled in.\n" +
                "---The table layout grid (play area) will automatically update when a Pile is added, moved or deleted.\n" +
                "---To view or update a Pile's settings, click the Pile on the table layout grid.\n" +
                "---Please Note: The grid is zero indexed, so when modifying a Pile's coordinates this must be taken into account.\n\n\n");


        Text text9 = new Text("Card Restrictions Menu \n\n");
        Text text10 = new Text("---The Card Restrictions Menu sets the number of and type of (value, suit) of cards available during gameplay.\n" +
                "---Individual card restrictions can be set or the Num Decks field can be used to quickly add a specified number of decks.\n" +
                "---Each Deck consists of 52 cards (with values Ace to King, and suits Heart, Club, Diamond, Spade).\n" +
                "---Please note: Using the Num Decks field WILL OVERRIDE ALL CUSTOMIZATIONS THAT HAVE BEEN MADE.\n\n\n");

        this.getChildren().addAll(title, text1, text2, text3, text4, text5, text6, text7, text8, text9, text10);
    }

}
