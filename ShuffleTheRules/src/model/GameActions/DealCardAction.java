/*
* Requirements mandating inclusion:
* */
package model.GameActions;



public class DealCardAction extends GameAction {

    public DealCardAction() {
        this.name = "DealCardAction";
        this.description = "A card (or cards) is dealt.";
    }

    @Override
    public void run(Object... obj) {

    }
}
