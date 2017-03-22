package view;


import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class TableTabView extends Tab{


    /* initialize the tab*/
    public TableTabView(){
        this.setText("Table");
        BorderPane tableTabBorderPane = new BorderPane();

        /* center (main portion) will be a grid */
        GridPane tableGridPane = new GridPane();
        tableTabBorderPane.setCenter(tableGridPane);

        /* right side will contain menus */
        TitledPane pileSettingsMenu = new TitledPane("Pile Settings", new Button("Add Pile"));
        TitledPane cardRestrictionSettingsMenu = new TitledPane("Card Restrictions", new Button("Change"));
        TitledPane generalSettingsMenu = new TitledPane("General Game Settings", new Button("Change"));

        Accordion tableTabAccordian = new Accordion();
        tableTabAccordian.getPanes().addAll(pileSettingsMenu, cardRestrictionSettingsMenu, generalSettingsMenu);

        tableTabBorderPane.setRight(tableTabAccordian);
        this.setContent(tableTabBorderPane);

    }

    public void setTabCentrePane(){

    }

}
