package view.TableTab;


import javafx.scene.control.Accordion;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class TableTabView extends Tab{
    BorderPane tableTabBorderPane;

    /* initialize the tab*/
    public TableTabView(){
        this.setText("Table");
        tableTabBorderPane = new BorderPane();

        /* center (main portion) will be a grid */
        GridPane tableGridPane = new GridPane();
        tableTabBorderPane.setCenter(tableGridPane);

        /* right side will contain menus */
        PileSettingsMenuView pileSettingsMenu = new PileSettingsMenuView();
        CardRestrictionsMenuView cardRestrictionSettingsMenu = new CardRestrictionsMenuView();
        GeneralSettingsMenuView generalSettingsMenu = new GeneralSettingsMenuView();

        Accordion tableTabAccordian = new Accordion();
        tableTabAccordian.getPanes().addAll(generalSettingsMenu, pileSettingsMenu, cardRestrictionSettingsMenu);

        tableTabBorderPane.setRight(tableTabAccordian);
        this.setContent(tableTabBorderPane);

    }

    public void setTabCentrePane(){

    }

}
