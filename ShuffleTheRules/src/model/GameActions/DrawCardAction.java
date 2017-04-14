package model.GameActions;

import model.Card;
import model.GameRule;
import model.Piles.Pile;
import model.Player;

import java.util.ArrayList;

public class DrawCardAction extends GameAction {

    private Pile drawingPile;
    private int numCards;
    private Player player;

    public DrawCardAction() {
        this.name = "DrawCardAction";
        this.description = "A card (or cards) is drawn.";
    }

    public DrawCardAction(Pile drawingPile, int numCards, Player player) {
        this();
        this.drawingPile = drawingPile;
        this.numCards = numCards;
        this.player = player;
    }

    @Override
    public void run(Object... args) {
        drawingPile = (args[0] != null ? (Pile) args[0] : drawingPile);
        numCards = (args[1] != null ? (int) args[1] : numCards);
        player = (args[2] != null ? (Player) args[2] : player);

        for (int i = 0; i < numCards; i++) {
            Card c = drawingPile.draw();
            if (c != null) {
                player.getHand().add(c);
            } else {
                break;
            }
        }

        for (int i = 3; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }

    public Pile getDrawingPile() {
        return drawingPile;
    }

    public void setDrawingPile(Pile drawingPile) {
        this.drawingPile = drawingPile;
    }

    public int getNumCards() {
        return numCards;
    }

    public void setNumCards(int numCards) {
        this.numCards = numCards;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
