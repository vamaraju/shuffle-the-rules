/*
* Requirements mandating inclusion:
*
* 3.2.2.2.3.4 User can select Card(s) to swap between Piles.
* */

package view.Gameplay;


import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Piles.Pile;

/*
* This class provides an interface for the user to use to swap Cards
* between Piles.
*
* A user may swap 0 or more Cards between two different Piles.
*
* */
public class SwapDialog extends Dialog<ButtonType> {

    private GridPane dialogLayout = new GridPane();
    private VBox pile1Layout = new VBox();
    private VBox pile2Layout = new VBox();

    private ChoiceBox pile1Selection;
    private ChoiceBox pile2Selection;

    private PileView pile1_pileView;
    private PileView pile2_pileView;

    public SwapDialog(){
        initialize();

    }

    public void initialize(){
        this.setTitle("Swap Cards");

        initPileViews();
        initChoiceBoxes();
        initLayout();
        initSubmitButtons();

        this.getDialogPane().setContent(dialogLayout);
    }

    private void initLayout() {

        dialogLayout.setVgap(10);
        dialogLayout.setHgap(4);

        /* VERY IMPORTANT WARNING MESSAGE */
        Label pileWarningMessage = new Label("Control click to select and deselect multiple cards");
        pileWarningMessage.setStyle("-fx-font-weight: bold");

        dialogLayout.add(pileWarningMessage,1,1,6,1);

        dialogLayout.add(pile1Selection,1,2,2,1);
        dialogLayout.add(pile2Selection,3,2,2,1);

        dialogLayout.add(pile1_pileView,1,4,2,4);
        dialogLayout.add(pile2_pileView,3,4,2,4);
    }

    private void initPileViews(){
        pile1_pileView = new PileView(Orientation.VERTICAL);
        pile2_pileView = new PileView(Orientation.VERTICAL);
    }

    private void initChoiceBoxes(){
        pile1Selection = new ChoiceBox();
        pile2Selection = new ChoiceBox();

        pile1Selection.getSelectionModel().selectedItemProperty().addListener(this::updateDisplayedPile);
        pile2Selection.getSelectionModel().selectedItemProperty().addListener(this::updateDisplayedPile);
    }

    private void initSubmitButtons() {
        this.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        this.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    }

    public void updatePile(PileView pileViewToUpdate, Pile newPile){
        pileViewToUpdate.updatePile(newPile);

    }

    public void updateDisplayedPile(ObservableValue observable, Object oldValue, Object newValue){
        /* Wipe current selections. Add method in PileView. Each pileView has a controller */

        /* Update pile */

    }
}
