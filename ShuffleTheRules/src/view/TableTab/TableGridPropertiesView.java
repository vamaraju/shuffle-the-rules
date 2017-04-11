package view.TableTab;

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

    private Label height = new Label("Height:");
    private Label width = new Label("Width:");
    private Label showGrid = new Label("Hide/Show");
    private Label blockSize = new Label("Block Size:");

    private TextField heightValue;
    private TextField widthValue;
    private TextField blockSizeValue;

    public TableGridPropertiesView(){
        initialize();
    }

    public void initialize(){

        this.setHgap(2);
        this.setVgap(4);

        heightValue = new TextField();
        heightValue.setMaxSize(50,20);
        this.add(height,1,1,1,1);
        this.add(heightValue,2,1,1,1);

        widthValue = new TextField();
        widthValue.setMaxSize(50,20);
        this.add(width,4,1,1,1);
        this.add(widthValue,5,1,1,1);

        blockSizeValue = new TextField();
        blockSizeValue.setMaxSize(50,20);
        this.add(blockSize,1,2,1,1);
        this.add(blockSizeValue,2,2,1,1);
    }

    public TextField getHeightValue() {
        return this.heightValue;
    }

    public void setHeightValue(TextField heightValue) {
        this.heightValue = heightValue;
    }

    public TextField getWidthValue() {
        return this.widthValue;
    }

    public void setWidthValue(TextField widthValue) {
        this.widthValue = widthValue;
    }

    public TextField getBlockSizeValue() {
        return this.blockSizeValue;
    }

    public void setBlockSizeValue(TextField blockSizeValue) {
        this.blockSizeValue = blockSizeValue;
    }


}
