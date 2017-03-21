package view;


import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class TabView {
    private TabPane tabPane;

    public TabView(){
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.TOP);
        /* don't want users to be able to close tabs */
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

    }



    public TabPane getTabPane(){
        return this.tabPane;
    }


}
