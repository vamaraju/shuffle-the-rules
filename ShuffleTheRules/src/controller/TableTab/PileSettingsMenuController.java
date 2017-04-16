/*
* Requirements mandating inclusion:
* */

package controller.TableTab;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import model.*;
import model.Piles.BasicPile;
import model.Piles.Deck;
import model.Piles.Pile;
import model.Piles.PileType;
import view.TableTab.PileSettingsMenuView;
import view.TableTab.TableGridElement;
import view.TableTab.TableGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        view.getPileTypeComboBox().getItems().addAll(PileType.GENERAL, PileType.DECK);
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

        if (runAllValidations()) {
            TableGridPosition gridPosition = new TableGridPosition(Integer.parseInt(view.getXCoordinateTextFieldValue()), Integer.parseInt(view.getYCoordinateTextFieldValue()));
            Pile pile = generatePileFromInputs();
            tableGridView.updateElement(gridPosition, pile);
            showAddSuccessAlert();
        }
    }

    public void onUpdatePileButtonClick(Event e){
        TableGridView tableGridView = GameView.getInstance().getTableTab().getTableGridView();
        TableGridElement clickedElement = tableGridView.getClickedElement();

        if (clickedElement != null && emptyFieldValidation() && numberFieldValidation() && numCardsMinMaxValidation() && startingPileSizeValidation() && gridCoordinatesValidation()) {
            TableGridPosition oldGridPosition = clickedElement.getPosition();
            TableGridPosition newGridPosition = new TableGridPosition(Integer.parseInt(view.getXCoordinateTextFieldValue()), Integer.parseInt(view.getYCoordinateTextFieldValue()));
            Pile oldPile = clickedElement.getPile();
            Pile newPile = generatePileFromInputs();

            tableGridView.removeElement(oldGridPosition, oldPile);
            tableGridView.removeElement(newGridPosition);
            tableGridView.updateElement(newGridPosition, newPile);

            showUpdateSuccessAlert();
        }

        tableGridView.setClickedElement(null);
    }

    public void onDeletePileButtonClick(Event e){

    }

    private Pile generatePileFromInputs() {
        String name = view.getPileNameTextFieldValue();
        int minCards = Integer.parseInt(view.getMinCardsTextFieldValue());
        int maxCards = Integer.parseInt(view.getMaxCardsTextFieldValue());
        int startingCards = Integer.parseInt(view.getStartingCardsTextFieldValue());
        String viewablePlayers = view.getViewableByComboBoxValue();

        switch (view.getPileTypeComboBoxValue()) {
            case GENERAL:
                return new Pile(name, minCards, maxCards, startingCards, view.getCardOrientationComboBoxValue(), viewablePlayers);
            case DECK:
                return new Deck(name, minCards, maxCards, startingCards, view.getCardOrientationComboBoxValue(), viewablePlayers);
            default:
                return new Pile(name, minCards, maxCards, startingCards, view.getCardOrientationComboBoxValue(), viewablePlayers);
        }
    }

    private boolean runAllValidations() {
        if (!emptyFieldValidation()) {return false;}
        if (!numberFieldValidation()) {return false;}
        if (!numCardsMinMaxValidation()) {return false;}
        if (!startingPileSizeValidation()) {return false;}
        if (!gridCoordinatesValidation()) {return false;}
        if (!pileExistsValidation()) {return false;}
        return true;
    }

    private boolean emptyFieldValidation() {
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
        return true;
    }

    private boolean numberFieldValidation() {
        if (!view.getMinCardsTextFieldValue().matches("[0-9]*") ||
                !view.getMaxCardsTextFieldValue().matches("[0-9]*") ||
                !view.getStartingCardsTextFieldValue().matches("[0-9]*") ||
                !view.getXCoordinateTextFieldValue().matches("[0-9]*") ||
                !view.getYCoordinateTextFieldValue().matches("[0-9]*")) {
            showNumberErrorAlert();
            return false;
        }
        return true;
    }

    private boolean numCardsMinMaxValidation() {
        if (Integer.parseInt(view.getMinCardsTextFieldValue()) > Integer.parseInt(view.getMaxCardsTextFieldValue())) {
            showNumCardsMinMaxErrorAlert();
            return false;
        }
        return true;
    }

    private boolean startingPileSizeValidation() {
        if (Integer.parseInt(view.getStartingCardsTextFieldValue()) > Integer.parseInt(view.getMaxCardsTextFieldValue()) ||
                Integer.parseInt(view.getStartingCardsTextFieldValue()) < Integer.parseInt(view.getMinCardsTextFieldValue())) {
            showStartingPileSizeErrorAlert();
            return false;
        }
        return true;
    }

    private boolean gridCoordinatesValidation() {
        if ((Integer.parseInt(view.getXCoordinateTextFieldValue()) > GameView.getInstance().getTableTab().getTableGridView().getTableGrid().getNumCols()-1) ||
                (Integer.parseInt(view.getYCoordinateTextFieldValue()) > GameView.getInstance().getTableTab().getTableGridView().getTableGrid().getNumRows()-1)) {
            showGridCoordinatesErrorAlert();
            return false;
        }
        return true;
    }

    private boolean pileExistsValidation() {
        TableGridPosition inputGridPosition = new TableGridPosition(Integer.parseInt(view.getXCoordinateTextFieldValue()), Integer.parseInt(view.getYCoordinateTextFieldValue()));
        TableGrid tableGrid = GameView.getInstance().getTableTab().getTableGridView().getTableGrid();
        for (Map.Entry<Pile, TableGridPosition> existingPile : tableGrid.getPileMap().entrySet()) {
            if (existingPile.getKey().getName().equals(view.getPileNameTextFieldValue())) {
                showPileExistsErrorAlert(existingPile.getKey(), existingPile.getValue());
                return false;
            }
            if (inputGridPosition.equals(existingPile.getValue())) {
                showGridPositionFilledErrorAlert(inputGridPosition, existingPile.getKey());
                return false;
            }
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

    private void showPileExistsErrorAlert(Pile existingPile, TableGridPosition position) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "A pile with the specified name '" + existingPile.getName() + "' already exists on the grid at position " + position + ". Please enter a unique name for this pile.");
        alert.setTitle("Pile Name Exists Error");
        alert.setHeaderText("Pile Name Already Exists In Grid");
        alert.showAndWait();
    }

    private void showGridPositionFilledErrorAlert(TableGridPosition position, Pile existingPile) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The specified grid position " + position + " is already filled with a pile: '" + existingPile.getName() + "'. Please enter a unique grid position, or click on the grid element (pile) to update/delete it.");
        alert.setTitle("Grid Position Filled Error");
        alert.setHeaderText("Grid Location Already Contains A Pile");
        alert.showAndWait();
    }

    private void showAddSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Pile has been added successfully!");
        alert.setTitle("Add Successful");
        alert.setHeaderText("Add Successful");
        alert.showAndWait();
    }

    private void showUpdateSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "All fields were updated successfully! The pile has been updated.");
        alert.setTitle("Update Successful");
        alert.setHeaderText("Update Successful");
        alert.showAndWait();
    }
}
