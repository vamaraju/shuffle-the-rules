import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

        heightValue = new TextField();
        heightValue.setMaxSize(20,50);

        widthValue = new TextField();
        widthValue.setMaxSize(20,50);

        blockSizeValue = new TextField();
        blockSize.setMaxSize(20,50);




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

    @Override
    public String toString() {
        return "TableGridPropertiesView{" +
                "heightValue=" + heightValue +
                ", widthValue=" + widthValue +
                ", blockSizeValue=" + blockSizeValue +
                '}';
    }
}
