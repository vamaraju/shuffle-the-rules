/*
* Requirements mandating inclusion:
*
* 3.2.2.1.3.1 User can view their Hand.
* 3.2.2.1.3.2 User can sort their Hand.
* 3.2.2.2.3.1 User can select Card(s) from Hand to play.
* 3.2.2.2.3.2 User can select Card(s) from Hand to discard.
* 3.2.2.2.3.4 User can select Card(s) to swap between Piles.
* */
package view.Gameplay;


import controller.GameplayMode.PileViewController;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.*;
import model.Piles.Pile;


public class PileView extends ListView<Card> {

    private PileViewController pileViewController = new PileViewController(this);

    public PileView() {
        initialize(Orientation.HORIZONTAL);
    }

    public PileView(Orientation orientation) {
        initialize(orientation);
    }

    public void initialize(Orientation orientation) {
        pileViewController = new PileViewController(this);

        /* Allows user to be able to select multiple Cards (CardCells). Uses Ctrl-Click to select multiple items. */
        this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.setOrientation(orientation);


        this.setCellFactory(new Callback<ListView<Card>, ListCell<Card>>() {
            @Override
            public ListCell<Card> call(ListView<Card> list) {
                return new CardCell();
            }
        });
    }

    public void updatePile(Pile pile){
        /* change the displayed Pile (Cards displayed in ListView) */
        this.getItems().setAll(pile.getCards());
    }

    public void clearPile() {
        this.getItems().clear();
    }

    /* TODO change to List? */
    public ObservableList<Card> getSelectedCards(){
        return this.getSelectionModel().getSelectedItems();
    }
}
