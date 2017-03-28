package view.TableTab;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GeneralSettingsMenuView extends TitledPane{
    private TextField minNumPlayersInput;
    private TextField maxNumPlayersInput;



    public GeneralSettingsMenuView(){

        initialize();

    }

    public void initialize(){
        this.setText("General Settings");

        VBox generalMenuContent = new VBox();

        Label players = new Label("Number of Players");

        /* min */
        HBox minNumPlayersHBox = new HBox(4);
        Label minNumPlayers = new Label("min:");
        minNumPlayersInput = new TextField();
        minNumPlayersInput.setMaxSize(50, 20);
        minNumPlayersHBox.getChildren().addAll(minNumPlayers, minNumPlayersInput);

        /* max */
        HBox maxNumPlayersHBox = new HBox(4);
        Label maxNumPlayers = new Label("max:");
        maxNumPlayersInput = new TextField();
        maxNumPlayersInput.setMaxSize(50, 20);
        maxNumPlayersHBox.getChildren().addAll(maxNumPlayers, maxNumPlayersInput);

        generalMenuContent.getChildren().addAll(players, minNumPlayersHBox, maxNumPlayersHBox);
        this.setContent(generalMenuContent);
    }

    public TextField getMinNumPlayersInput() {
        return minNumPlayersInput;
    }

    public TextField getMaxNumPlayersInput() {
        return maxNumPlayersInput;
    }
}
