package org.person.dfw.fx.scene.builder.contacts.view;

import javafx.fxml.FXML;
import org.person.dfw.fx.scene.builder.contacts.controller.MainApp;

public class RootLayoutController {

    private MainApp mainApp;

    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void handleShowBirthdayStatistics() {
        mainApp.showBirthdayStatistics();
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
