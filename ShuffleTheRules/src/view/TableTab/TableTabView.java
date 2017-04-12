package view.TableTab;


import controller.TableTab.TableTabController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;


public class TableTabView extends Tab {

    private TableTabController controller;

    private BorderPane tableTabBorderPane;
    private PileSettingsMenuView pileSettingsMenu;
    private CardRestrictionsMenuView cardRestrictionSettingsMenu;
    private GeneralSettingsMenuView generalSettingsMenu;
    private Accordion tableTabAccordian;
    private TableGridView tableGridView;
    private TableGridPropertiesView tableGridPropertiesView;
    private ScrollPane tableGridScrollPane;

    public TableTabView(){
        initialize();
    }

    public void initialize(){
        controller = new TableTabController(this);

        this.setText("Table");
        tableTabBorderPane = new BorderPane();

        /* center (main portion) will be a grid */
        VBox centrePaneVBox = new VBox();

        tableGridPropertiesView = new TableGridPropertiesView();
        tableGridView = new TableGridView();

        tableGridScrollPane = new ScrollPane(tableGridView);
        tableGridScrollPane.setFitToWidth(true);
        tableGridScrollPane.setFitToHeight(true);
        tableGridScrollPane.setStyle("-fx-focus-color: transparent;");

        centrePaneVBox.getChildren().addAll(tableGridPropertiesView, new Separator(), this.tableGridScrollPane);
        tableTabBorderPane.setCenter(centrePaneVBox);

        /* right side will contain menus */
        pileSettingsMenu = new PileSettingsMenuView();
        cardRestrictionSettingsMenu = new CardRestrictionsMenuView();
        generalSettingsMenu = new GeneralSettingsMenuView();

        tableTabAccordian = new Accordion();
        tableTabAccordian.getPanes().addAll(generalSettingsMenu, pileSettingsMenu, cardRestrictionSettingsMenu);

        tableTabBorderPane.setRight(tableTabAccordian);

        this.setContent(tableTabBorderPane);

        getGridHideCheckBox().setOnAction(controller::onGridHideCheckboxClick);
        getGridUpdateButton().setOnAction(controller::onGridUpdateButtonClick);
    }

    public BorderPane getTableTabBorderPane() {
        return tableTabBorderPane;
    }

    public ScrollPane getTableGridScrollPane() {
        return tableGridScrollPane;
    }

    public ObservableList<Node> getTableGridChildren() {
        return this.tableGridView.getChildren();
    }

    public CheckBox getGridHideCheckBox() {
        return tableGridPropertiesView.getGridHideCheckBox();
    }

    public Button getGridUpdateButton() {
        return tableGridPropertiesView.getUpdateButton();
    }

    public HashMap<String, TextField> getGridTextFields() {
        return tableGridPropertiesView.getTextFields();
    }

    public TextField getGridTextField(String textFieldName) {
        return tableGridPropertiesView.getTextFields().get(textFieldName);
    }

    public TextField getGridWidthSetting(String textFieldName) {
        return tableGridPropertiesView.getTextFields().get(textFieldName);
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

    public TableGridPropertiesView getTableGridPropertiesView() {
        return tableGridPropertiesView;
    }
}
