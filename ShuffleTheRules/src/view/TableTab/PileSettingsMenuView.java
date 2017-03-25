package view.TableTab;


import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.text.TextAlignment;

public class PileSettingsMenuView extends TitledPane{

    public PileSettingsMenuView(){
        /* Menu title */
        this.setText("Pile Settings");

        /* Sub menu titles. Sub menu titles stand out compared
        *  to the text inside. */


        Label addNewPileLabel = new Label("Add New Pile");
        addNewPileLabel.setTextAlignment(TextAlignment.CENTER);
        addNewPileLabel.setUnderline(true);
        Label updatePileLabel = new Label("Add New Pile");
        updatePileLabel.setTextAlignment(TextAlignment.CENTER);
        updatePileLabel.setUnderline(true);

        NewPileView newPileView = new NewPileView();
        UpdatePileView updatePileView = new UpdatePileView();

        VBox menuSeparator = new VBox();
        menuSeparator.getChildren().addAll(addNewPileLabel,newPileView, updatePileLabel, updatePileView);

        this.setContent(menuSeparator);

    }
}
