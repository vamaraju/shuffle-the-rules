/*
* Requirements mandating inclusion:
* */
package model.GameActions;


public class PlaceCardAction extends GameAction {

    public PlaceCardAction() {
        this.name = "PlaceCardAction";
        this.description = "A card (or cards) is placed.";
    }

    @Override
    public void run(Object... obj) {

    }
}
