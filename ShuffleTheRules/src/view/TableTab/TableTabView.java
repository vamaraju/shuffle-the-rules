package view.TableTab;


import javafx.scene.control.Accordion;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class TableTabView extends Tab{

    private BorderPane tableTabBorderPane;
    private PileSettingsMenuView pileSettingsMenu;
    private CardRestrictionsMenuView cardRestrictionSettingsMenu;
    private GeneralSettingsMenuView generalSettingsMenu;
    private Accordion tableTabAccordian;
    private TableGridView tableGridView;
    private TableGridPropertiesView tableGridPropertiesView;

    public TableTabView(){
        initialize();

    }

    public void initialize(){
        this.setText("Table");
        tableTabBorderPane = new BorderPane();

        /* center (main portion) will be a grid */
        VBox centrePaneVBox = new VBox();

        tableGridView = new TableGridView();
        tableGridPropertiesView = new TableGridPropertiesView();

        centrePaneVBox.getChildren().addAll(tableGridPropertiesView, new Separator(), tableGridView);
        tableTabBorderPane.setCenter(centrePaneVBox);

        /* right side will contain menus */
        pileSettingsMenu = new PileSettingsMenuView();
        cardRestrictionSettingsMenu = new CardRestrictionsMenuView();
        generalSettingsMenu = new GeneralSettingsMenuView();

        tableTabAccordian = new Accordion();
        tableTabAccordian.getPanes().addAll(generalSettingsMenu, pileSettingsMenu, cardRestrictionSettingsMenu);

        tableTabBorderPane.setRight(tableTabAccordian);

        this.setContent(tableTabBorderPane);
    }


    public BorderPane getTableTabBorderPane() {
        return tableTabBorderPane;
    }

    public PileSettingsMenuView getPileSettingsMenu() {
        return pileSettingsMenu;
    }

    public CardRestrictionsMenuView getCardRestrictionSettingsMenu() {
        return cardRestrictionSettingsMenu;
    }

    public GeneralSettingsMenuView getGeneralSettingsMenu() {
        return generalSettingsMenu;
    }

    public Accordion getTableTabAccordian() {
        return tableTabAccordian;
    }

    public TableGridView getTableGridView() {
        return tableGridView;
    }
}
