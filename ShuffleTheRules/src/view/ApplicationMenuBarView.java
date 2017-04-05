package view;

import javafx.scene.control.*;
import model.ApplicationMenuBar;
import controller.ApplicationMenuBarController;

public class ApplicationMenuBarView {

    /* GUI MenuBar*/
    private MenuBar menuBar;

    /* ApplicationMenuBar Controller*/
    private ApplicationMenuBarController controller;

    /* initializing this class will create the application menu bar GUI element*/
    public ApplicationMenuBarView(){

        menuBar = new MenuBar();
        controller = new ApplicationMenuBarController();

        /* Menu Bar - has two menus - File and Play. */
        final Menu fileMenu = new Menu("File");
        final Menu playMenu = new Menu("Play");
        menuBar.getMenus().addAll(fileMenu, playMenu);

        /* File menu items */
        MenuItem newMenuItem = new MenuItem("New Game");
        MenuItem saveMenuItem = new MenuItem("Save Game");
        MenuItem loadMenuItem = new MenuItem("Load Game");
        MenuItem validateMenuItem = new MenuItem("Validate Game");
        MenuItem exitMenuItem = new MenuItem("Exit");

        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, loadMenuItem, validateMenuItem, exitMenuItem);

        /* Play menu items*/
        MenuItem hostMenuItem = new MenuItem("Host Game");
        MenuItem joinMenuItem = new MenuItem("Join Game");

        playMenu.getItems().addAll(hostMenuItem, joinMenuItem);

        newMenuItem.setOnAction(controller::onNewGameClick);
        saveMenuItem.setOnAction(controller::onSaveGameClick);
        loadMenuItem.setOnAction(controller::onLoadGameClick);
    }

    public void newFile(){

    }

    public void saveFile(){

    }

    public void loadFile(){

    }

    public void validateFile(){

    }

    public void exit(){

    }

    public void hostGame(){

    }

    public void joinGame(){

    }

    public MenuBar getMenuBar(){
        return this.menuBar;
    }
}
