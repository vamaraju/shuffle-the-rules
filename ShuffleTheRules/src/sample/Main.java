package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    private void initialize(Stage primaryStage){

        primaryStage.setTitle("Shuffle the Rules");
        primaryStage.setResizable(false);

        /* For now, going to do without the fxml files. Will make things nicer afterwards */
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        /* The main layout to organize GameCreation Mode window.*/
        BorderPane borderPane = new BorderPane();
        primaryStage.setScene(new Scene(borderPane, 1500,1000));


        /* Top BorderPane
         *
         * Will contain two drop down menu buttons - File and Play.
         * Will not change based on tab selected.*/

        /* Menu Bar - has two menus - File and Play. */
        MenuBar applicationMenuBar = new MenuBar();

        final Menu fileMenu = new Menu("File");
        final Menu playMenu = new Menu("Play");
        applicationMenuBar.getMenus().addAll(fileMenu, playMenu);

        /* File menu items */
        MenuItem newMenuItem = new MenuItem("New Game File");
        MenuItem loadMenuItem = new MenuItem("Load Game File");
        MenuItem saveMenuItem = new MenuItem("Save Game File");
        MenuItem validateMenuItem = new MenuItem("Validate Game File");
        MenuItem exitMenuItem = new MenuItem("Exit");

        fileMenu.getItems().addAll(newMenuItem, loadMenuItem, saveMenuItem, validateMenuItem, exitMenuItem);

        /* Play meny items*/
        MenuItem hostMenuItem = new MenuItem("Host Game");
        MenuItem joinMenuItem = new MenuItem("Join Game");

        playMenu.getItems().addAll(hostMenuItem, joinMenuItem);

        borderPane.setTop(applicationMenuBar);


        /* Centre BorderPane
        *
        * Contains a tab pane which will switch between the Table View and Editor View*/
        /* TabPane is a control*/
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.TOP);
        /* don't want users to be able to close tabs */
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab tableTab = new Tab("Table");
        Rectangle rightRectangle = new Rectangle(1500, 1000, Color.ALICEBLUE);
        rightRectangle.setStroke(Color.BLACK);
        tableTab.setContent(rightRectangle);

        Tab editorTab = new Tab("Editor");
        Rectangle leftRectangle = new Rectangle(1500, 1000, Color.FIREBRICK);
        leftRectangle.setStroke(Color.BLACK);
        editorTab.setContent(leftRectangle);

        tabPane.getTabs().addAll(tableTab, editorTab);
        borderPane.setCenter(tabPane);

        borderPane.setCenter(tabPane);




    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        initialize(primaryStage);
        primaryStage.show();
    }
}
