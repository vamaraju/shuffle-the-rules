package view.Gameplay;/*
* Requirements mandating inclusion:
*
*
* */

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import model.Piles.Pile;

/*
* This class provides an interface for the user to use to swap Cards
* between Piles.
*
* A user may swap 0 or more Cards between two different Piles.
*
* */
public class SwapDialog extends Dialog<ButtonType> {


    private ChoiceBox pile1Selection;
    private ChoiceBox pile2Selection;

    private PileView pile1_PileView;
    private PileView pile2_pileView;

    public SwapDialog(){
        init();
        initSubmitButtons();

    }

    public void init(){
        this.setTitle("Swap Cards");

        /* VERY IMPORTANT WARNING MESSAGE */
        Label pileWarningMessage = new Label("Control click to select and deselect multiple cards");
        pileWarningMessage.setStyle("-fx-font-weight: bold");


        pile1_PileView = new PileView(Orientation.VERTICAL);
        pile2_pileView = new PileView(Orientation.VERTICAL);



        pile1Selection.getSelectionModel().selectedItemProperty().addListener(this::updateDisplayedPile);
        pile2Selection.getSelectionModel().selectedItemProperty().addListener(this::updateDisplayedPile);

    }


    public void updateDisplayedPile(ObservableValue observable, Object oldValue, Object newValue){
        /* Wipe current selections. */
        /* Update pile */

    }

    private void initSubmitButtons() {
        this.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        this.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    }

    public void updatePile(PileView pileViewToUpdate, Pile newPile){
        pileViewToUpdate.updatePile(newPile);


    }
}
