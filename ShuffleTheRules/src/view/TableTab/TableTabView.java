package view.TableTab;


import javafx.scene.control.Accordion;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class TableTabView extends Tab{

    private BorderPane tableTabBorderPane;
    private PileSettingsMenuView pileSettingsMenu;
    private CardRestrictionsMenuView cardRestrictionSettingsMenu;
    private GeneralSettingsMenuView generalSettingsMenu;
    private Accordion tableTabAccordian;
    private TableGridView tableGridView;

    public TableTabView(){
        initialize();

    }

    public void initialize(){
        this.setText("Table");
        tableTabBorderPane = new BorderPane();

        /* center (main portion) will be a grid */
        tableGridView = new TableGridView();
        tableTabBorderPane.setCenter(tableGridView);

        /* right side will contain menus */
        pileSettingsMenu = new PileSettingsMenuView();
        cardRestrictionSettingsMenu = new CardRestrictionsMenuView();
        generalSettingsMenu = new GeneralSettingsMenuView();

        tableTabAccordian = new Accordion();
        tableTabAccordian.getPanes().addAll(generalSettingsMenu, pileSettingsMenu, cardRestrictionSettingsMenu);

        tableTabBorderPane.setRight(tableTabAccordian);

        this.setContent(tableTabBorderPane);
    }



}
