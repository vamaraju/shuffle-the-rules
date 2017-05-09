/*
* Requirements mandating inclusion:
*
* This class is needed to provide functionality for user interaction with
* Piles during a game.
*
* It causes the PileView to be updated and Cards to be displayed (viewed).
*
* 3.2.2.2.3.5 User can draw Card(s) from a Pile.
* 3.2.2.2.3.6 User can place Card(s) on a Pile.
* 3.2.1.1.3.5 User can specify the Players that are allowed to view a Pile.
* */
package controller.GameplayMode;

import javafx.scene.input.MouseEvent;
import model.GameState;
import model.GameView;
import view.Gameplay.GameplayTableGridView;
import view.Gameplay.PileView;
import view.TableTab.PileSettingsMenuView;
import view.TableTab.TableGridElement;
import view.TableTab.TableGridView;

public class GameplayTableGridController {

    private GameplayTableGridView view;

    public GameplayTableGridController(GameplayTableGridView view) {
        this.view = view;
    }

    public void onPileClicked(MouseEvent e) {
        TableGridElement clickedGridElement = (TableGridElement) e.getSource();
        view.setClickedElement(clickedGridElement);
        PileView pileView = GameView.getInstance().getGameplayView().getSelectedPileView();
        if (clickedGridElement.getPile() != null) {
            pileView.updatePile(clickedGridElement.getPile());
            GameState.getInstance().setSelectedPile(clickedGridElement.getPile());
        } else {
            pileView.clearPile();
            GameState.getInstance().setSelectedPile(null);
        }
    }

}
