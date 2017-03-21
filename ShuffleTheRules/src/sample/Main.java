package sample;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import view.ApplicationMenuBarView;
import view.EditorTabView;
import view.TabView;
import view.TableTabView;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    private void initialize(Stage primaryStage){

        primaryStage.setTitle("Shuffle the Rules");
        primaryStage.setResizable(false);


        /* The main layout to organize GameCreation Mode window.*/
        BorderPane rootBorderPane = new BorderPane();
        primaryStage.setScene(new Scene(rootBorderPane, 1500,1000));


        /* Top BorderPane
         *
         * Will contain a MenuBar with two drop down menu buttons - File and Play.
         * Will not change based on tab selected.*/

        ApplicationMenuBarView applicationMenuBarView = new ApplicationMenuBarView();
        rootBorderPane.setTop(applicationMenuBarView.getMenuBar());


        /* Centre BorderPane
        *
        * Contains a tab pane which will switch between the Table View and Editor View*/
        //TabView tabView = new TabView();
        //TableTabView tableTabView = new TableTabView();
        //EditorTabView editorTabView = new EditorTabView();

        //tabView.addTab(tableTabView.getTab());
        //tabView.addTab(editorTabView.getTab());

                /* Centre BorderPane
        *
        * Contains a tab pane which will switch between the Table View and Editor View*/
        /* TabPane is a control*/
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.TOP);
        /* don't want users to be able to close tabs */
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);


        /* Table Tab ***********************************************/
        Tab tableTab = new Tab("Table");
        BorderPane tableTabBorderPane = new BorderPane();

        /* center (main portion) will be a grid */
        GridPane tableGridPane = new GridPane();
        tableTabBorderPane.setCenter(tableGridPane);

        /* right side will contain menus */
        TitledPane pileSettingsMenu = new TitledPane("Pile Settings", new Button("Add Pile"));
        TitledPane cardRestrictionSettingsMenu = new TitledPane("Card Restrictions", new Button("Change"));
        TitledPane playerSettingsMenu = new TitledPane("Player Settings", new Button("Change"));
        TitledPane generalSettingsMenu = new TitledPane("General Settings", new Button("Change"));

        Accordion tableTabAccordian = new Accordion();
        tableTabAccordian.getPanes().addAll(pileSettingsMenu, cardRestrictionSettingsMenu, playerSettingsMenu, generalSettingsMenu);

        tableTabBorderPane.setRight(tableTabAccordian);
        tableTab.setContent(tableTabBorderPane);



        /* Editor Tab ************************************************/
        Tab editorTab = new Tab("Editor");
        BorderPane editorTabBorderPane = new BorderPane();

         /* center (main portion) will be a grid */
        GridPane editorGridPane = new GridPane();
        editorTabBorderPane.setCenter(editorGridPane);

        /* right side will contain menus */
        TitledPane eventsMenu = new TitledPane("Events", new Button("Add Event"));
        TitledPane actionsMenu = new TitledPane("Actions", new Button("Add Action"));

        Accordion editorTabAccordian = new Accordion();
        editorTabAccordian.getPanes().addAll(eventsMenu, actionsMenu);

        editorTabBorderPane.setRight(editorTabAccordian);
        editorTab.setContent(editorTabBorderPane);

        tabPane.getTabs().addAll(tableTab, editorTab);


        rootBorderPane.setCenter(tabPane);


        //rootBorderPane.setCenter(tabView.getTabPane());

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        initialize(primaryStage);
        primaryStage.show();
    }
}
