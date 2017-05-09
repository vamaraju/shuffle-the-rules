/*
* Requirements mandating inclusion:
*
* 3.2.1.2.3.1 User can specify number of each type and suit of Card(s).
* */

package controller.TableTab;


import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.Alert;
import model.CardSettings;
import model.GameCreation;
import model.PlayingCard;
import model.Suit;
import view.TableTab.CardRestrictionsMenuView;


public class CardRestrictionsMenuController {

    private CardRestrictionsMenuView view;
    private CardSettings cardSettings = GameCreation.getInstance().getCardSettings();


    public CardRestrictionsMenuController(CardRestrictionsMenuView view) {
        this.view = view;
    }


    public void onUpdateButtonClick(Event e) {
        if (runAllValidations()) {
            updateCardSettings();
            updateCardPool();
            showUpdateSuccessfulAlert();
        }
    }


    public void onCardSelected(ObservableValue observable, Object oldCard, Object newCard) {
        PlayingCard selectedCard = (PlayingCard) newCard;
        updateSuitTextFields(selectedCard);
    }


    public void onNumDecksChanged(ObservableValue observable, Object oldNum, Object newNum) {
        String newNumStr = (String) newNum;
        if (!newNumStr.equals("") && newNumStr.matches("[0-9]*")) {
            cardSettings.setNumDecks(Integer.parseInt((String) newNum));
            updateSuitTextFields(view.getCardComboBox().getValue());
        }
    }


    public void updateSuitTextFields(PlayingCard card) {
        if (card != null) {
            view.getClubTextField().setText(Integer.toString(cardSettings.getCount(card, Suit.CLUB)));
            view.getHeartTextField().setText(Integer.toString(cardSettings.getCount(card, Suit.DIAMOND)));
            view.getDiamondTextField().setText(Integer.toString(cardSettings.getCount(card, Suit.HEART)));
            view.getSpadeTextField().setText(Integer.toString(cardSettings.getCount(card, Suit.SPADE)));
        }
    }


    public void updateCardPool() {
        GameCreation.getInstance().setCardPool(cardSettings.generateCardPool());
    }


    public void updateCardSettings() {
        PlayingCard selectedCard = view.getCardComboBox().getValue();
        cardSettings.setNumDecks(Integer.parseInt(view.getNumDecksTextField().getText()));
        cardSettings.updateCount(selectedCard, Suit.CLUB, Integer.parseInt(view.getClubTextField().getText()));
        cardSettings.updateCount(selectedCard, Suit.DIAMOND, Integer.parseInt(view.getDiamondTextField().getText()));
        cardSettings.updateCount(selectedCard, Suit.HEART, Integer.parseInt(view.getHeartTextField().getText()));
        cardSettings.updateCount(selectedCard, Suit.SPADE, Integer.parseInt(view.getSpadeTextField().getText()));
    }


    public void populateCardComboBox() {
        view.getCardComboBox().getItems().addAll(PlayingCard.values());
    }


    public void setCardSettings(CardSettings cardSettings) {
        this.cardSettings = cardSettings;
    }


    private boolean emptyFieldValidation() {
        if ((view.getNumDecksTextField().getText() == null || view.getNumDecksTextField().getText().isEmpty()) ||
                (view.getClubTextField().getText() == null || view.getClubTextField().getText().isEmpty()) ||
                (view.getDiamondTextField().getText() == null || view.getDiamondTextField().getText().isEmpty()) ||
                (view.getHeartTextField().getText() == null || view.getHeartTextField().getText().isEmpty()) ||
                (view.getSpadeTextField().getText() == null || view.getSpadeTextField().getText().isEmpty()) ||
                (view.getCardComboBox().getValue() == null)) {
            showEmptyFieldErrorAlert();
            return false;
        }
        return true;
    }


    private boolean numberFieldValidation() {
        if (!view.getNumDecksTextField().getText().matches("[0-9]*") ||
                !view.getClubTextField().getText().matches("[0-9]*") ||
                !view.getDiamondTextField().getText().matches("[0-9]*") ||
                !view.getHeartTextField().getText().matches("[0-9]*") ||
                !view.getSpadeTextField().getText().matches("[0-9]*")) {
            showNumberErrorAlert();
            return false;
        }
        return true;
    }


    private boolean totalCardCountValidation() {
        int numDecks = Integer.parseInt(view.getNumDecksTextField().getText());
        int expectedNumCards = numDecks * 52;
        int actualNumCards = cardSettings.getTotalCount();
        if (actualNumCards > expectedNumCards) {
            showTotalCardCountErrorAlert();
            return false;
        }
        return true;
    }


    private boolean runAllValidations() {
        if (!emptyFieldValidation()) {return false;}
        if (!numberFieldValidation()) {return false;}
//        if (!totalCardCountValidation()) {return false;}
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


    private void showTotalCardCountErrorAlert() {
        int numDecks = Integer.parseInt(view.getNumDecksTextField().getText());
        int expectedNumCards = numDecks * 52;
        Alert alert = new Alert(Alert.AlertType.WARNING, "The number of decks is " + numDecks + " (" + expectedNumCards +
                " cards), but the number of cards in play (based on the specified card restrictions) is " + cardSettings.getTotalCount() + ".\n" +
                "Please update the number of decks and/or card restrictions accordingly.");
        alert.setTitle("Total Card Count Error");
        alert.setHeaderText("Total Card Count Exceeds Maximum");
        alert.showAndWait();
    }


    private void showUpdateSuccessfulAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Card Restriction Settings updated successfully!");
        alert.setTitle("Update Successful");
        alert.setHeaderText("Update Successful");
        alert.showAndWait();
    }
}
