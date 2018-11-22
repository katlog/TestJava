package org.person.dfw.fx.practice.ui.widget;
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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class ProgressIndicatorMain {

    public static class Main1 extends Application {

        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 260, 80);
            stage.setScene(scene);

            Group g = new Group();

            ProgressIndicator p1 = new ProgressIndicator();


            g.getChildren().add(p1);

            scene.setRoot(g);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**
     * 我们可以使用空构造函数创建没有参数的进度指示器。然后我们可以使用setProgress方法分配值。
     * 如果无法确定进度，我们可以在不确定模式下设置进度控制，直到确定任务的长度。
     * 以下代码显示了如何创建一个ProgressIndicator，完成25％。
     */
    public static class Main2 extends Application {

        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 260, 80);
            stage.setScene(scene);

            Group g = new Group();

            ProgressIndicator p1 = new ProgressIndicator();
            p1.setProgress(0.25F);

            g.getChildren().add(p1);

            scene.setRoot(g);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**进度条显示器和后台进程*/
    public static class Main3 extends Application {
        Task copyWorker;
        public static void main(String[] args) {
            Application.launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Background Processes");
            Group root = new Group();
            Scene scene = new Scene(root, 330, 120, Color.WHITE);

            BorderPane mainPane = new BorderPane();
            root.getChildren().add(mainPane);

            final Label label = new Label("Files Transfer:");
            final ProgressIndicator progressIndicator = new ProgressIndicator(0);

            final HBox hb = new HBox();
            hb.setSpacing(5);
            hb.setAlignment(Pos.CENTER);
            hb.getChildren().addAll(label, progressIndicator);
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
                    progressIndicator.setProgress(0);
                    cancelButton.setDisable(false);
                    copyWorker = createWorker();

                    progressIndicator.progressProperty().unbind();
                    progressIndicator.progressProperty().bind(copyWorker.progressProperty());

                    copyWorker.messageProperty().addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
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
                    progressIndicator.progressProperty().unbind();
                    progressIndicator.setProgress(0);
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
