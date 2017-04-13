package view.Gameplay;

import controller.GameplayController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.GameplayMessage;
import view.TableTab.TableGridView;



public class GameplayView extends BorderPane {


    private Button endTurnButton = new Button("End Turn");
    private Button sortHandButton = new Button("Sort Hand");


    private GameplayMessageView gameplayMessageView;

    public GameplayView(){
        initialize();
    }

    public void initialize(){

        /* top will be the menu bar */

        /* centre pane will be the table */
        /* need to pass in TableGrid values*/
        TableGridView table = new TableGridView();
        table.enableBackgroundImage();

        this.setCenter(table);


        /* right pane will be where text is displayed for Events and Actions */
        gameplayMessageView = new GameplayMessageView();
        this.setRight(gameplayMessageView);

        /* bottom pane will be where hand and buttons are displayed */

        HBox handAndButtons = new HBox();


        GridPane buttonsPane = new GridPane();
        buttonsPane.add(endTurnButton,2,1,2,2);
        buttonsPane.add(sortHandButton,2,3,2,2);

        /* buttons will be disabled unless condition satisified */
        endTurnButton.setDisable(true);
        sortHandButton.setDisable(true);

        handAndButtons.getChildren().addAll(buttonsPane);
        this.setBottom(handAndButtons);
    }

    public Button getEndTurnButton() {
        return endTurnButton;
    }

    public Button getSortHandButton() {
        return sortHandButton;
    }

    public GameplayMessageView getGameplayMessageView(){
        return this.gameplayMessageView;
    }
}
