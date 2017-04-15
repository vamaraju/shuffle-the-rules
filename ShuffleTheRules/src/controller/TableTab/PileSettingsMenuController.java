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
import model.GameView;
import model.Piles.BasicPile;
import model.Piles.Deck;
import model.Piles.Pile;
import model.Piles.PileType;
import model.Player;
import view.TableTab.PileSettingsMenuView;
import view.TableTab.TableGridElement;
import view.TableTab.TableGridView;

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
        TableGridView tableGridView = GameView.getInstance().getTableTab().getTableGridView();

        if (runValidationChecks()) {
            int x = Integer.parseInt(view.getXCoordinateTextFieldValue());
            int y = Integer.parseInt(view.getYCoordinateTextFieldValue());
            double width = GameView.getInstance().getTableTab().getTableGridView().getCellWidth();
            double height = GameView.getInstance().getTableTab().getTableGridView().getCellHeight();
            String name = view.getPileNameTextFieldValue();
            int minCards = Integer.parseInt(view.getMinCardsTextFieldValue());
            int maxCards = Integer.parseInt(view.getMaxCardsTextFieldValue());
            int startingCards = Integer.parseInt(view.getStartingCardsTextFieldValue());

            TableGridElement tableGridElement = new TableGridElement(x, y, width, height);

            switch (view.getPileTypeComboBoxValue()) {
                case GENERAL:
                    tableGridView.updateElement(x, y, new Pile(name, minCards, maxCards, startingCards), view.getCardOrientationComboBoxValue());
                    break;
                case DECK:
                    tableGridView.updateElement(x, y, new Deck(name, minCards, maxCards, startingCards), view.getCardOrientationComboBoxValue());
                    break;
            }

            showUpdateSuccessAlert();
        }
    }

    public void onUpdatePileButtonClick(Event e){

    }

    public void onDeletePileButtonClick(Event e){

    }

    private boolean runValidationChecks() {
        if ((view.getPileNameTextFieldValue().isEmpty() || view.getPileNameTextFieldValue() == null) ||
                (view.getPileTypeComboBoxValue() == null) ||
                (view.getMinCardsTextFieldValue().isEmpty() || view.getMinCardsTextFieldValue() == null) ||
                (view.getMaxCardsTextFieldValue().isEmpty() || view.getMaxCardsTextFieldValue() == null) ||
                (view.getStartingCardsTextFieldValue().isEmpty() || view.getStartingCardsTextFieldValue() == null) ||
                (view.getXCoordinateTextFieldValue().isEmpty() || view.getXCoordinateTextFieldValue() == null) ||
                (view.getYCoordinateTextFieldValue().isEmpty() || view.getYCoordinateTextFieldValue() == null) ||
                (view.getViewableByComboBoxValue() == null) ||
                (view.getCardOrientationComboBoxValue() == null)) {
            showEmptyFieldErrorAlert();
            return false;
        }

        if (!view.getMinCardsTextFieldValue().matches("[0-9]*") ||
                !view.getMaxCardsTextFieldValue().matches("[0-9]*") ||
                !view.getStartingCardsTextFieldValue().matches("[0-9]*") ||
                !view.getXCoordinateTextFieldValue().matches("[0-9]*") ||
                !view.getYCoordinateTextFieldValue().matches("[0-9]*")) {
            showNumberErrorAlert();
            return false;
        }

        if (Integer.parseInt(view.getMinCardsTextFieldValue()) > Integer.parseInt(view.getMaxCardsTextFieldValue())) {
            showNumCardsMinMaxErrorAlert();
            return false;
        }

        if (Integer.parseInt(view.getStartingCardsTextFieldValue()) > Integer.parseInt(view.getMaxCardsTextFieldValue()) ||
                Integer.parseInt(view.getStartingCardsTextFieldValue()) < Integer.parseInt(view.getMinCardsTextFieldValue())) {
            showStartingPileSizeErrorAlert();
            return false;
        }

        if ((Integer.parseInt(view.getXCoordinateTextFieldValue()) > GameView.getInstance().getTableTab().getTableGridView().getTableGrid().getNumCols()-1) ||
                (Integer.parseInt(view.getYCoordinateTextFieldValue()) > GameView.getInstance().getTableTab().getTableGridView().getTableGrid().getNumRows()-1)) {
            showGridCoordinatesErrorAlert();
            return false;
        }

        return true;
    }

    private void showEmptyFieldErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "One or more required fields above are empty. Please enter a value in each field above.");
        alert.setTitle("Empty Field Error");
        alert.setHeaderText("A Field Is Empty");
        alert.showAndWait();
    }

    private void showNumberErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "One or more number fields contain an invalid character (a non-number). Please enter a positive integer in all number fields.");
        alert.setTitle("Number Field Error");
        alert.setHeaderText("Number Field Contains A Non-Number");
        alert.showAndWait();
    }

    private void showNumCardsMinMaxErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The minimum number of cards in the pile must be less than or equal to the maximum number of cards in the pile.");
        alert.setTitle("Min/Max Cards Error");
        alert.setHeaderText("Min Cards Greater Than Max Cards");
        alert.showAndWait();
    }

    private void showStartingPileSizeErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The starting pile size must be in between (or equal to) the minimum and maximum pile size.");
        alert.setTitle("Starting Pile Size Error");
        alert.setHeaderText("Starting Pile Size Invalid");
        alert.showAndWait();
    }

    private void showGridCoordinatesErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The given pile coordinates (X and/or Y) are beyond the grid limits. X and Y must be less than the grid size.\nNote that the grid is 0-indexed - (0, 0) is the top-left corner.");
        alert.setTitle("Pile Coordinates Error");
        alert.setHeaderText("Pile Coordinates Out-of-Bounds");
        alert.showAndWait();
    }

    private void showUpdateSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "All fields were updated successfully!");
        alert.setTitle("Update Successful");
        alert.setHeaderText("Update Successful");
        alert.showAndWait();
    }
}
