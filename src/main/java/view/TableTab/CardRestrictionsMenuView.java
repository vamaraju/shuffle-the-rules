/*
* Requirements mandating inclusion:
*
* 3.2.1.2.3.1 User can specify number of each type and suit of Card(s).
*
* */
package view.TableTab;


import controller.TableTab.CardRestrictionsMenuController;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.PlayingCard;
import model.Suit;
import model.TripleHashMap;


public class CardRestrictionsMenuView extends TitledPane {

    private CardRestrictionsMenuController controller;

    private GridPane cardRestrictionMenuContent;
    private TripleHashMap<String, Node, Node> gridElements;

    private Button updateButton = new Button("Update");

    public CardRestrictionsMenuView(){
        initialize();
    }

    public void initialize(){
        controller = new CardRestrictionsMenuController(this);
        this.setText("Card Restrictions");

        cardRestrictionMenuContent = new GridPane();
        cardRestrictionMenuContent.setHgap(2);
        cardRestrictionMenuContent.setVgap(4);

        gridElements = new TripleHashMap<>();

        gridElements.put("deckHeader", new Label("Deck Settings"), null);
        gridElements.put("numDecks", new Label("Num Decks:"), new TextField());

        gridElements.put("restrictionsHeader", new Label("Card Restrictions"), null);
        gridElements.put("card", new Label("Card Value:"), new ComboBox<PlayingCard>());
        gridElements.put("clubs", new ImageView(Suit.CLUB.getSuitAssetLocation()), new TextField());
        gridElements.put("diamonds", new ImageView(Suit.DIAMOND.getSuitAssetLocation()), new TextField());
        gridElements.put("hearts", new ImageView(Suit.HEART.getSuitAssetLocation()), new TextField());
        gridElements.put("spades", new ImageView(Suit.SPADE.getSuitAssetLocation()), new TextField());

        initImageViews();
        initGrid();
        boldAllHeaders();
        setTextFieldPrompts();

        updateButton.setOnAction(controller::onUpdateButtonClick);
        controller.populateCardComboBox();
        getCardComboBox().getSelectionModel().selectedItemProperty().addListener(controller::onCardSelected);
        getNumDecksTextField().textProperty().addListener(controller::onNumDecksChanged);

        this.setContent(cardRestrictionMenuContent);
    }

    private void initImageViews() {
        for (String key : gridElements.keySet()) {
            if (gridElements.getValue1(key) instanceof ImageView) {
                ImageView suitImageView = (ImageView) gridElements.getValue1(key);
                suitImageView.setPreserveRatio(true);
                suitImageView.setFitHeight(40);
                suitImageView.setFitWidth(40);
            }
        }
    }

    private void initGrid() {
        int row = 0;
        for (String key : gridElements.keySet()) {
            if (gridElements.getValue2(key) == null) {
                if (row != 0) {
                    cardRestrictionMenuContent.add(new Separator(), 0, row++);
                }
                cardRestrictionMenuContent.add(gridElements.getValue1(key), 0, row++);
            } else {
                cardRestrictionMenuContent.add(gridElements.getValue1(key), 0, row);
                cardRestrictionMenuContent.add(gridElements.getValue2(key), 1, row++);
            }
        }

        cardRestrictionMenuContent.add(updateButton, 0, row++);
    }

    public void clearCardRestrictionsInputs() {
        getCardComboBox().setValue(null);
        getClubTextField().clear();
        getDiamondTextField().clear();
        getHeartTextField().clear();
        getSpadeTextField().clear();
    }

    private void boldAllHeaders() {
        for (String key : gridElements.keySet()) {
            if (key.contains("Header")) {
                gridElements.getValue1(key).setStyle("-fx-font-weight: bold");
            }
        }
    }

    private void setTextFieldPrompts() {
        getNumDecksTextField().setPromptText("Number of Decks");
        getNumDecksTextField().setText("1");
        getClubTextField().setPromptText("Number of Clubs");
        getDiamondTextField().setPromptText("Number of Diamonds");
        getHeartTextField().setPromptText("Number of Hearts");
        getSpadeTextField().setPromptText("Number of Spades");
    }

    public CardRestrictionsMenuController getController() {
        return controller;
    }

    public TextField getNumDecksTextField() {
        return (TextField) gridElements.getValue2("numDecks");
    }

    public ComboBox<PlayingCard> getCardComboBox() {
        return (ComboBox<PlayingCard>) gridElements.getValue2("card");
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public ImageView getClubImageView() {
        return (ImageView) gridElements.getValue1("clubs");
    }

    public ImageView getDiamondImageView() {
        return (ImageView) gridElements.getValue1("diamonds");
    }

    public ImageView getHeartImageView() {
        return (ImageView) gridElements.getValue1("hearts");
    }

    public ImageView getSpadeImageView() {
        return (ImageView) gridElements.getValue1("spades");
    }

    public TextField getClubTextField() {
        return (TextField) gridElements.getValue2("clubs");
    }

    public TextField getDiamondTextField() {
        return (TextField) gridElements.getValue2("diamonds");
    }

    public TextField getHeartTextField() {
        return (TextField) gridElements.getValue2("hearts");
    }

    public TextField getSpadeTextField() {
        return (TextField) gridElements.getValue2("spades");
    }
}


