/*
* Requirements mandating inclusion:
* */
package view.TableTab;


import controller.TableTab.GeneralSettingsMenuController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;


public class GeneralSettingsMenuView extends TitledPane{

    private GeneralSettingsMenuController controller;

    private TextField minNumPlayersInput;
    private TextField maxNumPlayersInput;

    private Button updateButton;

    public GeneralSettingsMenuView(){
        initialize();
    }

    public void initialize(){

        this.controller = new GeneralSettingsMenuController(this);
        this.setText("General Settings");

        /* organize fields in GridPane */
        GridPane generalMenuContent = new GridPane();
        generalMenuContent.setHgap(2);
        generalMenuContent.setVgap(4);

        Label players = new Label("Number of Players");
        generalMenuContent.add(players,1,1,4,1);

        /* min */
        Label minNumPlayers = new Label("min:");
        minNumPlayersInput = new TextField();
        minNumPlayersInput.setMaxSize(50, 20);

        generalMenuContent.add(minNumPlayers,2,2,1,1);
        generalMenuContent.add(minNumPlayersInput,3,2,1,1);

        /* max */

        Label maxNumPlayers = new Label("max:");
        maxNumPlayersInput = new TextField();
        maxNumPlayersInput.setMaxSize(50, 20);

        generalMenuContent.add(maxNumPlayers,2,3,1,1);
        generalMenuContent.add(maxNumPlayersInput,3,3,1,1);

        /* Purposely put in update because changing number of players could cause a lot of conflicts
           if objects which are related to those players have been created */
        updateButton = new Button("Update");
        generalMenuContent.add(updateButton,1,5,2,2);

        this.setContent(generalMenuContent);
        this.updateButton.setOnAction(controller::onUpdateButtonClick);
    }

    public Button getUpdateButton(){
        return updateButton;
    }

    public String getMinNumPlayersInput() {
        return minNumPlayersInput.getText();
    }

    public String getMaxNumPlayersInput() {
        return maxNumPlayersInput.getText();
    }

    public void setMinNumPlayersInput(String minNumPlayersInput) {
        this.minNumPlayersInput.setText(minNumPlayersInput);
    }

    public void setMaxNumPlayersInput(String maxNumPlayersInput) {
        this.maxNumPlayersInput.setText(maxNumPlayersInput);
    }
}
