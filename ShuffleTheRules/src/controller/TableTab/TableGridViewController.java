package controller.TableTab;

import javafx.scene.input.MouseEvent;
import model.GameView;
import view.TableTab.PileSettingsMenuView;
import view.TableTab.TableGridElement;
import view.TableTab.TableGridView;

public class TableGridViewController {

    private TableGridView view;

    public TableGridViewController(TableGridView view) {
        this.view = view;
    }

    public void onGridElementClicked(MouseEvent e) {
        TableGridElement gridElement = (TableGridElement) e.getSource();
        PileSettingsMenuView pileSettingsView = GameView.getInstance().getTableTab().getPileSettingsMenu();

        if (gridElement.hasPile()) {
            pileSettingsView.setExpanded(true);
            pileSettingsView.getPileNameTextField().setText(gridElement.getPileName());
            pileSettingsView.getPileTypeComboBox().setValue(gridElement.getPileType());
            pileSettingsView.getMinCardsTextField().setText(Integer.toString(gridElement.getPileMinSize()));
            pileSettingsView.getMaxCardsTextField().setText(Integer.toString(gridElement.getPileMaxSize()));
            pileSettingsView.getStartingCardsTextField().setText(Integer.toString(gridElement.getPileStartingSize()));
            pileSettingsView.getXCoordinateTextField().setText(Integer.toString(gridElement.getX()));
            pileSettingsView.getYCoordinateTextField().setText(Integer.toString(gridElement.getY()));
            pileSettingsView.getViewableByComboBox().setValue(gridElement.getPile().getViewablePlayers());
            pileSettingsView.getCardOrientationComboBox().setValue(gridElement.getCardOrientation());
        } else {
            pileSettingsView.setExpanded(true);
            pileSettingsView.clearAllInputs();
            pileSettingsView.getXCoordinateTextField().setText(Integer.toString(gridElement.getX()));
            pileSettingsView.getYCoordinateTextField().setText(Integer.toString(gridElement.getY()));
        }
    }

}
