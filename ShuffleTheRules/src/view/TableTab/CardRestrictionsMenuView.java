package view.TableTab;


import controller.TableTab.CardRestrictionsMenuController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PlayingCard;

import java.util.ArrayList;
import java.util.List;

public class CardRestrictionsMenuView extends TitledPane{

    private CardRestrictionsMenuController controller;

    private ChoiceBox availableCards;

    public CardRestrictionsMenuView(){
        initialize();
    }

    private Button updateButton;

    private ImageView heartImageView = new ImageView(new Image("assets/playing_cards/suit/heart.png"));
    private ImageView spadeImageView = new ImageView(new Image("assets/playing_cards/suit/spade.png"));
    private ImageView clubImageView = new ImageView(new Image("assets/playing_cards/suit/club.png"));
    private ImageView diamondImageView = new ImageView(new Image("assets/playing_cards/suit/diamond.png"));


    private TextField heartCount;
    private TextField spadeCount;
    private TextField clubCount;
    private TextField diamondCount;


    public void initialize(){

        this.controller = new CardRestrictionsMenuController(this);
        this.setText("Card Restrictions");


        GridPane cardRestrictionMenuContent = new GridPane();
        cardRestrictionMenuContent.setHgap(2);
        cardRestrictionMenuContent.setVgap(4);

        Label selectCard = new Label("Select Card");
        availableCards = new ChoiceBox(FXCollections.observableArrayList(PlayingCard.values()));

        cardRestrictionMenuContent.add(selectCard,1,1,2,1);
        cardRestrictionMenuContent.add(availableCards,3,1,2,1);

        /* suit count area */

        /* heart */
        heartCount = new TextField();
        heartCount.setMaxSize(50, 20);
        heartImageView.setPreserveRatio(true);
        heartImageView.setFitHeight(50);
        heartImageView.setFitWidth(50);

        cardRestrictionMenuContent.add(heartImageView,1,2,1,1);
        cardRestrictionMenuContent.add(heartCount,2,2,1,1);

        /* spade */
        spadeCount = new TextField();
        spadeCount.setMaxSize(50, 20);
        spadeImageView.setPreserveRatio(true);
        spadeImageView.setFitHeight(50);
        spadeImageView.setFitWidth(50);

        cardRestrictionMenuContent.add(spadeImageView,1,3,1,1);
        cardRestrictionMenuContent.add(spadeCount,2,3,1,1);

        /* club */
        clubCount = new TextField();
        clubCount.setMaxSize(50, 20);
        clubImageView.setPreserveRatio(true);
        clubImageView.setFitHeight(50);
        clubImageView.setFitWidth(50);

        cardRestrictionMenuContent.add(clubImageView,1,4,1,1);
        cardRestrictionMenuContent.add(clubCount,2,4,1,1);

        /* diamond */
        diamondCount = new TextField();
        diamondCount.setMaxSize(50, 20);
        diamondImageView.setPreserveRatio(true);
        diamondImageView.setFitHeight(50);
        diamondImageView.setFitWidth(50);

        cardRestrictionMenuContent.add(diamondImageView,1,5,1,1);
        cardRestrictionMenuContent.add(diamondCount,2,5,1,1);

        updateButton = new Button("Update");
        cardRestrictionMenuContent.add(updateButton,1,7,2,2);
        this.setContent(cardRestrictionMenuContent);

        this.updateButton.setOnAction(controller::onUpdateButtonClick);

        availableCards.getSelectionModel().selectedItemProperty().addListener(controller::updateDisplayedSuitCounts);


    }

    public Button getUpdateButton() { return updateButton; }

    public ChoiceBox getAvailableCards() {
        return availableCards;
    }

    public String getHeartCount() {
        return heartCount.getText();
    }

    public void setHeartCount(String heartCount) {
        this.heartCount.setText(heartCount);
    }

    public String getSpadeCount() {
        return spadeCount.getText();
    }

    public void setSpadeCount(String spadeCount) {
        this.spadeCount.setText(spadeCount);
    }

    public String getClubCount() {
        return clubCount.getText();
    }

    public void setClubCount(String clubCount) {
        this.clubCount.setText(clubCount);
    }

    public String getDiamondCount() {
        return diamondCount.getText();
    }

    public void setDiamondCount(String diamondCount) {
        this.diamondCount.setText(diamondCount);
    }
}


