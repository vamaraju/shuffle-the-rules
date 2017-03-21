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
        /* TabPane is a control*/
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.TOP);
        /* don't want users to be able to close tabs */
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        TableTabView tableTab = new TableTabView();
        EditorTabView editorTab = new EditorTabView();

        tabPane.getTabs().addAll(tableTab.getTab(), editorTab.getTab());


        rootBorderPane.setCenter(tabPane);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        initialize(primaryStage);
        primaryStage.show();
    }

}
