package org.person.dfw.fx.scene.builder.notepad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NotePad extends Application {

    public static void main(String[] args) {
        Application.launch(NotePad.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NotePad.fxml"));


        Scene scene = new Scene(root, 600, 400);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle("JavaFX记事本");
        stage.show();
    }

}