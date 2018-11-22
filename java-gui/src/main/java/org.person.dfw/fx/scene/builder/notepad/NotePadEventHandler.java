package org.person.dfw.fx.scene.builder.notepad;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

public class NotePadEventHandler {

    @FXML
    private AnchorPane layoutPane;
    @FXML
    private TextArea  fileContent;
    private File result;

    @FXML
    private void onMenuOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        result = fileChooser.showOpenDialog(layoutPane.getScene().getWindow());
        if (result != null) {
            fileContent.setText(FileTools.readFile(result));
        }
    }

    @FXML
    private void onMenuSave(ActionEvent event) {
        if(result != null){
            FileTools.writeFile(result, fileContent.getText());
        }
    }

    @FXML
    private void onMenuClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void onMenuDelete(ActionEvent event) {
        fileContent.replaceSelection("");
    }

    @FXML
    private void onMenuAbout(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "JavaFX记事本是一款使用JavaFX开发的记事本。" ,"关于",  JOptionPane.PLAIN_MESSAGE);
    }

    @FXML
    private void onContextSelectAll(ActionEvent event) {
        fileContent.selectAll();
    }
}
