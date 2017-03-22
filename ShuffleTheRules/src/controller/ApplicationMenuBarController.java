package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.event.Event;

public class ApplicationMenuBarController {


    public void newFile(Event e){
        // Dummy code for spawning a dialog box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + "non_existent_file" + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //do stuff
        }
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

}
