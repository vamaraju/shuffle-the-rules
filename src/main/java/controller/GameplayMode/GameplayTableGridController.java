package controller.GameplayMode;

import javafx.scene.input.MouseEvent;
import model.GameView;
import view.Gameplay.GameplayTableGridView;
import view.TableTab.PileSettingsMenuView;
import view.TableTab.TableGridElement;
import view.TableTab.TableGridView;

public class GameplayTableGridController {

    private GameplayTableGridView view;

    public GameplayTableGridController(GameplayTableGridView view) {
        this.view = view;
    }

    public void onGridElementClicked(MouseEvent e) {
        TableGridElement clickedGridElement = (TableGridElement) e.getSource();
        view.setClickedElement(clickedGridElement);
        PileSettingsMenuView pileSettingsView = GameView.getInstance().getTableTab().getPileSettingsMenu();

        if (clickedGridElement.hasPile()) {
            pileSettingsView.setExpanded(true);
            pileSettingsView.getUpdatePileButton().setDisable(false);
            pileSettingsView.getAddPileButton().setDisable(true);
            pileSettingsView.getDeletePileButton().setDisable(false);

            pileSettingsView.getPileNameTextField().setText(clickedGridElement.getPileName());
            pileSettingsView.getPileTypeComboBox().setValue(clickedGridElement.getPileType());
            pileSettingsView.getMinCardsTextField().setText(Integer.toString(clickedGridElement.getPileMinSize()));
            pileSettingsView.getMaxCardsTextField().setText(Integer.toString(clickedGridElement.getPileMaxSize()));
            pileSettingsView.getStartingCardsTextField().setText(Integer.toString(clickedGridElement.getPileStartingSize()));
            pileSettingsView.getXCoordinateTextField().setText(Integer.toString(clickedGridElement.getX()));
            pileSettingsView.getYCoordinateTextField().setText(Integer.toString(clickedGridElement.getY()));
            pileSettingsView.getViewableByComboBox().setValue(clickedGridElement.getPile().getViewablePlayers());
            pileSettingsView.getCardOrientationComboBox().setValue(clickedGridElement.getPileCardOrientation());
        } else {
            pileSettingsView.setExpanded(true);
            pileSettingsView.getUpdatePileButton().setDisable(true);
            pileSettingsView.getAddPileButton().setDisable(false);
            pileSettingsView.getDeletePileButton().setDisable(true);

            pileSettingsView.clearAllInputs();
            pileSettingsView.getXCoordinateTextField().setText(Integer.toString(clickedGridElement.getX()));
            pileSettingsView.getYCoordinateTextField().setText(Integer.toString(clickedGridElement.getY()));
        }
    }

}
