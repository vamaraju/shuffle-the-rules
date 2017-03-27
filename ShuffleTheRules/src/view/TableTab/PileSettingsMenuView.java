package view.TableTab;


import javafx.application.Application;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.text.TextAlignment;

public class PileSettingsMenuView extends TitledPane{

    public PileSettingsMenuView(){


    }

    public void initialize(){
        /* Menu title */
        this.setText("Pile Settings");

        /* Sub menu titles. Sub menu titles stand out compared
        *  to the text inside. */
        // TODO maybe add in later. will look into where the resources are



        Label addNewPileLabel = new Label("Add New Pile");
        addNewPileLabel.setTextAlignment(TextAlignment.CENTER);
        addNewPileLabel.setUnderline(true);

        Label updatePileLabel = new Label("Update Pile");
        updatePileLabel.setTextAlignment(TextAlignment.CENTER);
        updatePileLabel.setUnderline(true);


        NewPileView newPileView = new NewPileView();
        UpdatePileView updatePileView = new UpdatePileView();

        VBox pileMenuContent = new VBox(10);
        pileMenuContent.getChildren().addAll(addNewPileLabel,newPileView, updatePileLabel, updatePileView);

        this.setContent(pileMenuContent);
    }
}
