package view.Gameplay;/*
* Requirements mandating inclusion:
*
*
* */

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import model.Piles.Pile;

/*
* This class provides an interface for the user to use to swap Cards
* between Piles.
*
* A user may swap 0 or more Cards between two different Piles.
*
* */
public class SwapDialog extends Dialog {


    private ChoiceBox pile1Selection;
    private ChoiceBox pile2Selection;

    private PileView pile1_PileView;
    private PileView pile2_pileView;

    private Button initiateSwapButton;

    public SwapDialog(){
        initialize();

    }

    public void initialize(){

        /* VERY IMPORTANT WARNING MESSAGE */
        Label pileWarningMessage = new Label("Control click to select and deselect multiple cards");
        pileWarningMessage.setStyle("-fx-font-weight: bold");


        pile1_PileView = new PileView(Orientation.VERTICAL);
        pile2_pileView = new PileView(Orientation.VERTICAL);



        this.initiateSwapButton.setOnAction(this::onInitiateSwapButtonClick);

        pile1Selection.getSelectionModel().selectedItemProperty().addListener(this::updateDisplayedPile);
        pile2Selection.getSelectionModel().selectedItemProperty().addListener(this::updateDisplayedPile);

    }

    private void onInitiateSwapButtonClick(ActionEvent actionEvent) {

    }

    public void updateDisplayedPile(ObservableValue observable, Object oldValue, Object newValue){
        /* Wipe current selections. */
        /* Update pile */

    }

    public void updatePile(PileView viewToUpdate, Pile newPile){
        viewToUpdate.updatePile(newPile);


    }

}
