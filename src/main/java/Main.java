/*
* Requirements mandating inclusion:
*
* All requirements require this file.
* Without this file, the application would not run.
* */
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.GameView;
import view.ApplicationMenuBarView;
import view.EditorTab.EditorTabView;
import view.TableTab.TableTabView;


/*
* This class sets up the Game Creation Application window.
* In Game Creation Mode, the ApplicationMenuBar will always be shown.
* The TabPane will always be shown, but the content in the tabs themselves will
* change dynamically.
* */
public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    private void initializeStage(Stage primaryStage){

        primaryStage.setTitle("Shuffle The Rules");

        /* The main layout to organize GameCreation Mode window.*/
        BorderPane rootBorderPane = new BorderPane();
        primaryStage.setScene(new Scene(rootBorderPane, 1050, 600));
        primaryStage.setResizable(true);


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
        GameView.getInstance().setEditorTab(editorTab);
        GameView.getInstance().setTableTab(tableTab);
        
        tabPane.getTabs().addAll(tableTab, editorTab);

        rootBorderPane.setCenter(tabPane);
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        initializeStage(primaryStage);
        primaryStage.show();

    }

}
