package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        /* For now, going to do without the fxml files. Will make things nicer afterwards */
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1500,1000));

        /* create BorderPane */
        BorderPane borderPane = new BorderPane();

        /* Top BorderPane
         *
         * Will contain two drop down menu buttons - File and Play.
         * Will not change based on tab selected.*/
        Rectangle topRectangle = new Rectangle(1500, 40, Color.DARKSEAGREEN);
        topRectangle.setStroke(Color.BLACK);
        borderPane.setTop(topRectangle);

        /* Centre BorderPane
        * User will drag and drop objects into this area when in Game Creation Mode.
        * If Table Tab selected, the TableGrid will be displayed.  User will drag Piles into the area.
        * If Editor Tab selected, Game Rules (sequence of Events and Actions) will be displayed.  User
        * can drag Events and Actions into this area. */
        GridPane grid = new GridPane();
        borderPane.setCenter(grid);

        // Right BorderPane
        /*
        * User will select and modify objects through lists and the interface in this area.
        * If Table Tab selected, lists of game objects and their settings will be displayed.
        * If Editor Tab selected, lists of game Events and Actions will be displayed. */
        Rectangle rightRectangle = new Rectangle(200, 1000, Color.ALICEBLUE);
        rightRectangle.setStroke(Color.ALICEBLUE);
        borderPane.setRight(rightRectangle);


        root.getChildren().addAll(borderPane);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        initialize(primaryStage);
        primaryStage.show();
    }
}
