package view.Gameplay;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.TableTab.TableGridView;


public class GameplayView extends BorderPane {


    private Button endTurn = new Button("End Turn");
    private Button sortHand = new Button("Sort Hand");


    public GameplayView(){
        initialize();
    }

    public void initialize(){

        /* top will be the menu bar */

        /* centre pane will be the table */
        TableGridView table = new TableGridView();

        this.setCenter(table);


        /* right pane will be where text is displayed for Events and Actions */



        /* bottom pane will be where hand and buttons are displayed */

        HBox handAndButtons = new HBox();


        GridPane buttonsPane = new GridPane();
        buttonsPane.add(endTurn,1,1,1,1);
        buttonsPane.add(sortHand,1,2,1,1);

        handAndButtons.getChildren().addAll(buttonsPane);
    }


}
