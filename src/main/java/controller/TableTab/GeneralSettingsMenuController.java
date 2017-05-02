/*
* Requirements mandating inclusion:
* */

package controller.TableTab;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.Alert;
import model.GameCreation;
import model.GameSettings;
import model.Piles.Hand;
import model.Player;
import view.TableTab.GeneralSettingsMenuView;

import java.util.ArrayList;
import java.util.List;


public class GeneralSettingsMenuController {

    private GeneralSettingsMenuView view;
    private GameSettings gameSettings = GameCreation.getInstance().getGameSettings();
    private List<Player> players = GameCreation.getInstance().getPlayers();

    public GeneralSettingsMenuController(GeneralSettingsMenuView view){
        this.view = view;
    }

    public void onUpdateButtonClick(Event e) {
        if (runValidationChecks()) {
            Player selectedPlayer = view.getPlayerComboBoxValue();
            if (selectedPlayer != null) {
                selectedPlayer.setName(view.getPlayerNameTextFieldValue());
            }
            resetPlayers(Integer.parseInt(view.getMaxPlayersTextFieldValue()));
            updatePlayersComboBox();

            gameSettings.setMinPlayers(Integer.parseInt(view.getMinPlayersTextFieldValue()));
            gameSettings.setMaxPlayers(Integer.parseInt(view.getMaxPlayersTextFieldValue()));

            int minHandSize = Integer.parseInt(view.getMinHandSizeTextFieldValue());
            int maxHandSize = Integer.parseInt(view.getMaxHandSizeTextFieldValue());
            int startingHandSize = Integer.parseInt(view.getStartingHandSizeTextFieldValue());

            for (Player p : players) {
                p.setHand(new Hand(minHandSize, maxHandSize, startingHandSize));
            }

            showUpdateSuccessAlert();
        }
    }

    public void onMaxPlayersChanged(ObservableValue observable, Object oldMaxNumPlayers, Object newMaxNumPlayers) {
        if (((String) newMaxNumPlayers).matches("[0-9]*")) {
            resetPlayers(Integer.parseInt((String) newMaxNumPlayers));
            updatePlayersComboBox();
        }
    }

    public void resetPlayers(int numPlayers) {
        int initialSize =  players.size();
        if (numPlayers < players.size()) {
            for (int i = numPlayers; i < initialSize; i++) {
                players.remove(numPlayers);
            }
        } else if (numPlayers > players.size()) {
            for (int i = initialSize; i < numPlayers; i++) {
                players.add(new Player(i+1));
            }
        }
    }

    public void onPlayerSelected(ObservableValue observable, Object oldPlayer, Object newPlayer) {
        Player selectedPlayer = (Player) newPlayer;
        if (selectedPlayer != null) {
            view.getPlayerNameTextField().setText(selectedPlayer.getName());
            view.getTurnNumberLabel().setText(Integer.toString(selectedPlayer.getTurnNum()));
        }
    }

    public void updatePlayersComboBox() {
        view.getPlayerComboBox().getItems().clear();
        view.getPlayerComboBox().getItems().addAll(players);
        view.getPlayerNameTextField().setText("");
        view.getTurnNumberLabel().setText("");
    }

    public void onPlayerNameChanged(ObservableValue observable, Object oldPlayerName, Object newPlayerName) {
        view.getPlayerComboBoxValue().setName((String) newPlayerName);
    }

    private boolean runValidationChecks() {
        if ((view.getMinPlayersTextFieldValue().isEmpty() || view.getMinPlayersTextFieldValue() == null) ||
                (view.getMaxPlayersTextFieldValue().isEmpty() || view.getMaxPlayersTextFieldValue() == null) ||
                //(view.getPlayerComboBoxValue() == null) ||
                //(view.getPlayerNameTextFieldValue().isEmpty() || view.getPlayerNameTextFieldValue() == null) ||
                //(view.getTurnNumberComboBoxValue() == null) ||
                (view.getMinHandSizeTextFieldValue().isEmpty() || view.getMinHandSizeTextFieldValue() == null) ||
                (view.getMaxHandSizeTextFieldValue().isEmpty() || view.getMaxHandSizeTextFieldValue() == null) ||
                (view.getStartingHandSizeTextFieldValue().isEmpty() || view.getStartingHandSizeTextFieldValue() == null)) {
            showEmptyFieldErrorAlert();
            return false;
        }

        if (!view.getMinPlayersTextFieldValue().matches("[0-9]*") ||
                !view.getMaxPlayersTextFieldValue().matches("[0-9]*") ||
                !view.getMinHandSizeTextFieldValue().matches("[0-9]*") ||
                !view.getMaxHandSizeTextFieldValue().matches("[0-9]*") ||
                !view.getStartingHandSizeTextFieldValue().matches("[0-9]*")) {
            showNumberErrorAlert();
            return false;
        }

        if (Integer.parseInt(view.getMinPlayersTextFieldValue()) > Integer.parseInt(view.getMaxPlayersTextFieldValue())) {
            showPlayerMinMaxErrorAlert();
            return false;
        }

        if (Integer.parseInt(view.getMinHandSizeTextFieldValue()) > Integer.parseInt(view.getMaxHandSizeTextFieldValue())) {
            showHandSizeMinMaxErrorAlert();
            return false;
        }

        if (Integer.parseInt(view.getStartingHandSizeTextFieldValue()) > Integer.parseInt(view.getMaxHandSizeTextFieldValue()) ||
                Integer.parseInt(view.getStartingHandSizeTextFieldValue()) < Integer.parseInt(view.getMinHandSizeTextFieldValue())) {
            showStartingHandSizeErrorAlert();
            return false;
        }

        if (view.getPlayerComboBoxValue() != null) {
            if (view.getPlayerNameTextFieldValue().isEmpty() || view.getPlayerNameTextFieldValue() == null) {
                showEmptyPlayerNameErrorAlert();
                return false;
            }
        }

        return true;
    }

    private void showEmptyFieldErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "One or more required fields above are empty. Please enter a value in each field in the 'Number of Players' and 'Hand Size' sections.");
        alert.setTitle("Empty Field Error");
        alert.setHeaderText("A Field Is Empty");
        alert.showAndWait();
    }

    private void showEmptyPlayerNameErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The Player Name is not specified. Please enter a value in the 'Player Name' field for the selected Player. Player Name cannot be empty.");
        alert.setTitle("Player Name Empty Error");
        alert.setHeaderText("Player Name Is Empty");
        alert.showAndWait();
    }

    private void showNumberErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "One or more number fields contain an invalid character (a non-number). Please enter a positive integer in all number fields.");
        alert.setTitle("Number Field Error");
        alert.setHeaderText("Number Field Contains A Non-Number");
        alert.showAndWait();
    }

    private void showPlayerMinMaxErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The minimum number of players must be less than or equal to the maximum number of players.");
        alert.setTitle("Min/Max Players Error");
        alert.setHeaderText("Min Players Greater Than Max Players");
        alert.showAndWait();
    }

    private void showHandSizeMinMaxErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The minimum hand size must be less than or equal to the maximum hand size.");
        alert.setTitle("Min/Max Hand Size Error");
        alert.setHeaderText("Max Hand Size Less Than Min Hand Size");
        alert.showAndWait();
    }

    private void showStartingHandSizeErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "The starting hand size must be in between (or equal to) the minimum and maximum hand size.");
        alert.setTitle("Starting Hand Size Error");
        alert.setHeaderText("Starting Hand Size Invalid");
        alert.showAndWait();
    }

    private void showUpdateSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "All fields were updated successfully!");
        alert.setTitle("Update Successful");
        alert.setHeaderText("Update Successful");
        alert.showAndWait();
    }

}
