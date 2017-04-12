package view.TableTab;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/*
* Used inside centre pane of TableTabView.
* Displays and allows modifications to be made to grid properties.
*
* Currently, viewable only because the functionality is part of the list
* of low priority requirements.
* */
public class TableGridPropertiesView extends GridPane {

    private Label width = new Label("Grid Width (x):");
    private Label height = new Label("Grid Height (y):");
    private Label blockSize = new Label("Grid Block Size:");
    private Label hideGrid = new Label("Hide Grid:");

    private TextField heightTextField;
    private TextField widthTextField;
    private TextField blockSizeTextField;
    private CheckBox hideGridCheckBox;

    public TableGridPropertiesView(){
        initialize();
    }

    public void initialize(){
        this.setHgap(10);
        this.setVgap(10);

        widthTextField = new TextField();
        widthTextField.setMaxSize(50,20);
        this.add(width,1,1);
        this.add(widthTextField,2,1);

        heightTextField = new TextField();
        heightTextField.setMaxSize(50,20);
        this.add(height,3,1);
        this.add(heightTextField,4,1);

        blockSizeTextField = new TextField();
        blockSizeTextField.setMaxSize(50,20);
        this.add(blockSize,5,1);
        this.add(blockSizeTextField,6,1);

        hideGridCheckBox = new CheckBox();
        this.add(hideGrid,7,1);
        this.add(hideGridCheckBox,8,1);
    }


    public TextField getHeightTextField() {
        return this.heightTextField;
    }

    public void setHeightTextField(TextField heightTextField) {
        this.heightTextField = heightTextField;
    }

    public TextField getWidthTextField() {
        return this.widthTextField;
    }

    public void setWidthTextField(TextField widthTextField) {
        this.widthTextField = widthTextField;
    }

    public TextField getBlockSizeTextField() {
        return this.blockSizeTextField;
    }

    public void setBlockSizeTextField(TextField blockSizeTextField) {
        this.blockSizeTextField = blockSizeTextField;
    }

    public CheckBox getHideGridCheckBox() {
        return hideGridCheckBox;
    }

    public void setHideGridCheckBox(CheckBox hideGridCheckBox) {
        this.hideGridCheckBox = hideGridCheckBox;
    }
}