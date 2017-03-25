package view.TableTab;


import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class GeneralSettingsMenuView extends TitledPane{

    public GeneralSettingsMenuView(){
        this.setText("General Settings");
        VBox paneVBox = new VBox();
        this.setContent(paneVBox);
    }
}
