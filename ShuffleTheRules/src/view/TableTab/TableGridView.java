package view.TableTab;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class TableGridView extends VBox {

    public TableGridView(){
        intialize();
    }

    public void intialize(){
        /* This area will contain the GUI elements which allow a user to modify the
        *  TableGrid*/
        /* Currently, viewable only because the functionality is part of the list
        *  of low priority requirements. */
        VBox tableGridControls = new VBox();

        HBox gridPropertiesTop = new HBox(50);
        Label height = new Label("Height:");
        Label width = new Label("Width:");
        Label showGrid = new Label("Hide/Show");
        gridPropertiesTop.getChildren().addAll(height,width,showGrid);

        HBox gridPropertiesBottom = new HBox(10);
        Label blockSize = new Label("Block Size:");
        gridPropertiesBottom.getChildren().addAll(blockSize);

        tableGridControls.getChildren().addAll(gridPropertiesTop, gridPropertiesBottom, new Separator());

        this.getChildren().addAll(tableGridControls);
    }
}
