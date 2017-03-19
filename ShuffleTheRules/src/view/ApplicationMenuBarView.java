package view;

import javafx.scene.control.*;
import model.ApplicationMenuBar;

public class ApplicationMenuBarView {

    /* GUI MenuBar*/
    private MenuBar menuBar;

    /* ApplicationMenuBar Model*/

    /* initializing this class will create the application menu bar GUI element*/
    public ApplicationMenuBarView(){

        menuBar = new MenuBar();

        /* Menu Bar - has two menus - File and Play. */
        final Menu fileMenu = new Menu("File");
        final Menu playMenu = new Menu("Play");
        menuBar.getMenus().addAll(fileMenu, playMenu);

        /* File menu items */
        MenuItem newMenuItem = new MenuItem("New Game File");
        MenuItem loadMenuItem = new MenuItem("Load Game File");
        MenuItem saveMenuItem = new MenuItem("Save Game File");
        MenuItem validateMenuItem = new MenuItem("Validate Game File");
        MenuItem exitMenuItem = new MenuItem("Exit");

        fileMenu.getItems().addAll(newMenuItem, loadMenuItem, saveMenuItem, validateMenuItem, exitMenuItem);

        /* Play menu items*/
        MenuItem hostMenuItem = new MenuItem("Host Game");
        MenuItem joinMenuItem = new MenuItem("Join Game");

        playMenu.getItems().addAll(hostMenuItem, joinMenuItem);
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
