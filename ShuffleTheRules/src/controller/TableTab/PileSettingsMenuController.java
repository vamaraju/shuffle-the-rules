/*
* Requirements mandating inclusion:
* */

package controller.TableTab;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import model.CardOrientation;
import model.GameCreation;
import model.Piles.BasicPile;
import model.Piles.Pile;
import model.Piles.PileType;
import model.Player;
import view.TableTab.PileSettingsMenuView;
import java.util.HashMap;
import java.util.Observable;


public class PileSettingsMenuController {

    private PileSettingsMenuView view;

    public PileSettingsMenuController(PileSettingsMenuView view){
        this.view = view;
    }

    public void onTabExpanded(ObservableValue observable, Object oldExpandedValue, Object newExpandedValue) {
        boolean expanded = (boolean) newExpandedValue;
        if (expanded) {
            setViewableByComboBox();
        }
    }

    public void setPileTypeComboBox() {
        view.getPileTypeComboBox().getItems().addAll(PileType.values());
    }

    public void setViewableByComboBox() {
        ComboBox c = view.getViewableByComboBox();
        c.getItems().clear();
        c.getItems().addAll("All", "None");
        for (Player p : GameCreation.getInstance().getPlayers()) {
            c.getItems().add(p.getName());
        }
    }

    public void setCardOrientationComboBox() {
        view.getCardOrientationComboBox().getItems().addAll(CardOrientation.values());
    }

    public void onAddPileButtonClick(Event e){

    }

    public void onUpdatePileButtonClick(Event e){

    }

    public void onDeletePileButtonClick(Event e){

    }

}
