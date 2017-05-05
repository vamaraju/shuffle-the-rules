/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.Card;
import model.GameRule;
import model.Piles.Pile;
import model.Player;

public class DrawCardAction extends GameAction {

    private Pile drawingPile;
    private int numCards;
    private Player player;

    public DrawCardAction() {
        this.className = "DrawCardAction";
        this.description = "A card (or cards) is drawn.";
    }

    @Override
    public void run() {

    }

}
