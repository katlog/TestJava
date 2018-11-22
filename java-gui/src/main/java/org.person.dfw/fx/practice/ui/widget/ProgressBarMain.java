package org.person.dfw.fx.practice.ui.widget;

import org.junit.Test;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ProgressBarMain {

    @Test
    public void create() {
        // 以下代码显示如何通过传递progress值来创建ProgressBar。
        ProgressBar pb = new ProgressBar(0.6);
    }


    public static class Main1 extends Application {

        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 260, 80);
            stage.setScene(scene);

            Group g = new Group();

            ProgressBar p2 = new ProgressBar();


            g.getChildren().add(p2);

            scene.setRoot(g);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }


    public static class Main2 extends Application {

        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 260, 80);
            stage.setScene(scene);

            Group g = new Group();

            ProgressBar p2 = new ProgressBar();
            p2.setProgress(0.25F);

            g.getChildren().add(p2);

            scene.setRoot(g);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**以下代码显示如何将ProgressBar与后台进程一起使用。*/
    public  static class Main extends Application {
        Task copyWorker;

        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            Group root = new Group();
            Scene scene = new Scene(root, 330, 120, Color.WHITE);

            BorderPane mainPane = new BorderPane();
            root.getChildren().add(mainPane);

            final Label label = new Label("Files Transfer:");
            final ProgressBar progressBar = new ProgressBar(0);

            final HBox hb = new HBox();
            hb.setSpacing(5);
            hb.setAlignment(Pos.CENTER);
            hb.getChildren().addAll(label, progressBar);
            mainPane.setTop(hb);

            final Button startButton = new Button("Start");
            final Button cancelButton = new Button("Cancel");
            final HBox hb2 = new HBox();
            hb2.setSpacing(5);
            hb2.setAlignment(Pos.CENTER);
            hb2.getChildren().addAll(startButton, cancelButton);
            mainPane.setBottom(hb2);
            startButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    startButton.setDisable(true);
                    progressBar.setProgress(0);
                    cancelButton.setDisable(false);
                    copyWorker = createWorker();
                    progressBar.progressProperty().unbind();
                    progressBar.progressProperty().bind(copyWorker.progressProperty());
                    copyWorker.messageProperty().addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> observable,
                                            String oldValue, String newValue) {
                            System.out.println(newValue);
                        }
                    });
                    new Thread(copyWorker).start();
                }
            });
            cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    startButton.setDisable(false);
                    cancelButton.setDisable(true);
                    copyWorker.cancel(true);
                    progressBar.progressProperty().unbind();
                    progressBar.setProgress(0);
                    System.out.println("cancelled.");
                }
            });
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public Task createWorker() {
            return new Task() {
                @Override
                protected Object call() throws Exception {
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(2000);
                        updateMessage("2000 milliseconds");
                        updateProgress(i + 1, 10);
                    }
                    return true;
                }
            };
        }
    }
}
