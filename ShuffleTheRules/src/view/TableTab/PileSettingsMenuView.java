package view.TableTab;



import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class PileSettingsMenuView extends TitledPane{
    private PileSettingsView pileSettingsView;

    public PileSettingsMenuView(){
        initialize();

    }

    public void initialize(){
         /* Menu title */
        this.setText("Pile Settings");

        /* Sub menu titles. Sub menu titles stand out compared
        *  to the text inside. */
        // TODO maybe add in later. will look into where the resources are



        Label pileLabel = new Label("Add or Update a Pile");
        pileLabel.setTextAlignment(TextAlignment.CENTER);
        pileLabel.setUnderline(true);

        pileSettingsView = new PileSettingsView();

        VBox pileMenuContent = new VBox(10);
        pileMenuContent.getChildren().addAll(pileLabel, pileSettingsView);

        this.setContent(pileMenuContent);


    }

    public PileSettingsView getPileSettingsView() {
        return pileSettingsView;
    }
}
