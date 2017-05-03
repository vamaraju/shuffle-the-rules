package controller.GameplayMode;

import javafx.scene.input.MouseEvent;
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
        } else {
            pileView.clearPile();
        }
    }

}
