package view.TableTab;


import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CardRestrictionsMenuView extends TitledPane{
    private List<String> cardList = new ArrayList<String>();
    private ChoiceBox availableCards;

    public CardRestrictionsMenuView(){
        initialize();
    }

    private Button updateButton;

    private ImageView heartImageView = new ImageView(getClass().getResource("../../assets/playing_cards/suits/heart.png").toExternalForm());
    private ImageView spadeImageView = new ImageView(getClass().getResource("../../assets/playing_cards/suits/spade.png").toExternalForm());
    private ImageView clubImageView = new ImageView(getClass().getResource("../../assets/playing_cards/suits/club.png").toExternalForm());
    private ImageView diamondImageView = new ImageView(getClass().getResource("../../assets/playing_cards/suits/diamond.png").toExternalForm());

    /* http://stackoverflow.com/questions/33626858/add-border-around-vbox-in-javafx */
    String greyLayoutBorder = "-fx-border-color: rgba(0,0,0,0.32);\n" +
            "-fx-border-insets: 0;\n" +
            "-fx-border-width: 1;\n" +
            "-fx-border-style: solid;\n";

    private TextField heartCount;
    private TextField spadeCount;
    private TextField clubCount;
    private TextField diamondCount;


    public void initialize(){
        this.setText("Card Restrictions");

        VBox cardRestrictionMenuContent = new VBox();

        HBox cardSelection = new HBox(10);

        Label selectCard = new Label("Select Card");
        availableCards = new ChoiceBox();
        availableCards.getItems().addAll(cardList);
        cardSelection.getChildren().addAll(selectCard, availableCards);

        /* suit count area */

        VBox suitSettingsVBox = new VBox(20);

        HBox heartHBox = new HBox(10);
        heartCount = new TextField();
        heartImageView.setPreserveRatio(true);
        heartImageView.setFitHeight(50);
        heartImageView.setFitWidth(50);
        heartHBox.getChildren().addAll(heartImageView,heartCount);

        HBox spadeHBox = new HBox(10);
        spadeCount = new TextField();
        spadeImageView.setPreserveRatio(true);
        spadeImageView.setFitHeight(50);
        spadeImageView.setFitWidth(50);
        spadeHBox.getChildren().addAll(spadeImageView, spadeCount);

        HBox clubHBox = new HBox(10);
        clubCount = new TextField();
        clubImageView.setPreserveRatio(true);
        clubImageView.setFitHeight(50);
        clubImageView.setFitWidth(50);
        clubHBox.getChildren().addAll(clubImageView, clubCount);

        HBox diamondHBox = new HBox(10);
        diamondCount = new TextField();
        diamondImageView.setPreserveRatio(true);
        diamondImageView.setFitHeight(50);
        diamondImageView.setFitWidth(50);
        diamondHBox.getChildren().addAll(diamondImageView, diamondCount);

        suitSettingsVBox.getChildren().addAll(heartHBox, spadeHBox, clubHBox, diamondHBox);
        updateButton = new Button("Update");
        cardRestrictionMenuContent.getChildren().addAll(cardSelection, suitSettingsVBox, updateButton);
        this.setContent(cardRestrictionMenuContent);
    }

    public void drawCardSettingsDisplay(){

    }


    public void drawCardSettingsDisplay(String card){

    }

    public void loadCardSettings(){
        /* load settings for card chosen in ChoiceBox*/
    }



    public void updateCardList(List<String> list){
        cardList = list;
        /* should probably change to use a listener */
        this.availableCards.getItems().addAll(cardList);
    }

    public Button getUpdateButton() { return updateButton; }

    public List<String> getCardList() {
        return cardList;
    }

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


