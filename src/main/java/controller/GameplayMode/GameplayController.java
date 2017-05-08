/*
* Requirements mandating inclusion:
*
* This has become a demo class.  The functionality provided has been moved elsewhere.
* */

package controller.GameplayMode;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.util.Pair;
import view.Gameplay.GameplayView;
import view.Gameplay.SortDialog;

/*
 * This has become a demo class.  The functionality provided has been moved elsewhere.
 *  */
public class GameplayController {

    private GameplayView gameplayView;

    public GameplayController(GameplayView view){
        this.gameplayView = view;

    }


    public void onEndTurnButtonClick(Event event){
        System.out.println("End Turn pressed - Gameplay View ");

    }

    public void onSortHandButtonClick(Event event){
        System.out.println("Sort Hand pressed - Gameplay View ");
        SortDialog sortDialog = new SortDialog();

    }




    public void onShowHandButtonClick(ActionEvent actionEvent) {
        System.out.println("Show Hand pressed - Gameplay View ");
    }

    public void onEndGameButtonClick(ActionEvent actionEvent) {
        System.out.println("End Game pressed - Gameplay View ");
    }

    public void onplayButtonClick(ActionEvent actionEvent) {
    }

    public void onSwapCardsButtonClick(ActionEvent actionEvent) {
    }

    public void onBecomeInactiveButtonClick(ActionEvent actionEvent) {
    }
}
