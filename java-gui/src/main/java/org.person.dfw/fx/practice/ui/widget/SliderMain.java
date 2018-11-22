package org.person.dfw.fx.practice.ui.widget;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.web.WebView;
import org.junit.Test;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SliderMain {

    @Test
    public void create() {
        // 以下代码使用默认的空构造函数创建一个Slider，然后设置滑块控件的值。
        // setMin和setMax分别定义滑块上的最小值和最大值。
        // setValue方法设置滑块的当前值。并且当前值应该小于最大值和大于最小值。setValue方法在轨道上移动缩略图。
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(200);
        slider.setValue(140);
        // setShowTickMarks和setShowTickLabels定义滑块的视觉外观。并启用标记和标签。
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        // 主要刻度标记之间的单位距离通过setMajorTickUnit方法设置为50。
        // 通过setMinorTickCount方法将任意两个主刻度之间的次刻度数指定为5。
        // setSnapToTicks方法保持滑块的值与刻度标记对齐。
        // setBlockIncrement方法定义当用户点击轨道时拇指移动的距离。
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
    }


    /***
     * 滑块可以显示和与一系列数值进行交互。
     * 滑块控件有两件事：一个轨道和一个可拖动的拇指。
     * 在轨道上，我们可以选择包括指示范围的数值的刻度线和标签。
     * 以下代码显示了如何创建一个滑块，其范围（或范围）从0到1，而值的默认值为.5
     */
    public static class Main1 extends Application {

        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Slider Sample");


            Slider slider = new Slider(0, 1, 0.5);
            root.getChildren().add(slider);

            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }

    /**滑块值属性更改监听器*/
    public static class Main2 extends Application {

        final Slider opacityLevel = new Slider(0, 1, 1);
        final Label opacityCaption = new Label("Opacity Level:");
        final Label opacityValue = new Label(Double.toString(opacityLevel.getValue()));

        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Slider Sample");


            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(10);
            grid.setHgap(70);

            scene.setRoot(grid);

            GridPane.setConstraints(opacityCaption, 0, 1);
            grid.getChildren().add(opacityCaption);


            opacityLevel.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov,
                                    Number old_val, Number new_val) {
                    System.out.println(new_val.doubleValue());
                    opacityValue.setText(String.format("%.2f", new_val));
                }
            });

            GridPane.setConstraints(opacityLevel, 1, 1);
            grid.getChildren().add(opacityLevel);


            GridPane.setConstraints(opacityValue, 2, 1);
            grid.getChildren().add(opacityValue);

            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }


    public static class Main3 extends Application {

        private final AudioClip coffeeClip;
        private final AudioClip jobClip;
        private final AudioClip meetingClip;
        private Slider volumeSlider;
        private Slider rateSlider;
        private Slider balanceSlider;

        public static void main(String[] args) {
            Main3.launch(args);
        }

        public Main3() {
            coffeeClip = new AudioClip(getClipResourceString("resources/coffee.mp3"));
            jobClip = new AudioClip(getClipResourceString("resources/job.mp3"));
            meetingClip = new AudioClip(getClipResourceString("resources/meeting.mp3"));
        }

        @Override
        public void start(Stage primaryStage) {
            final GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setHgap(10);
            grid.setVgap(5);

            createControls(grid);
            createClipList(grid);

            final Scene scene = new Scene(grid, 640, 380);
            scene.getStylesheets().add(getClass().getResource("media.css").toString());

            primaryStage.setTitle("AudioClip Example");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        private void createControls(GridPane grid) {
            final Label volumeLabel = new Label("Volume");
            final Label rateLabel = new Label("Rate");
            final Label balanceLabel = new Label("Balance");

            GridPane.setHalignment(volumeLabel, HPos.CENTER);
            GridPane.setHalignment(rateLabel, HPos.CENTER);
            GridPane.setHalignment(balanceLabel, HPos.CENTER);

            volumeSlider = new Slider(0.0, 1.0, 1.0);
            rateSlider = new Slider(0.25, 2.5, 1.0);
            balanceSlider = new Slider(-1.0, 1.0, 0.0);

            GridPane.setHgrow(volumeSlider, Priority.ALWAYS);
            GridPane.setHgrow(rateSlider, Priority.ALWAYS);
            GridPane.setHgrow(balanceSlider, Priority.ALWAYS);

            grid.add(volumeLabel, 0, 2);
            grid.add(volumeSlider, 0, 3);
            grid.add(rateLabel, 1, 2);
            grid.add(rateSlider, 1, 3);
            grid.add(balanceLabel, 2, 2);
            grid.add(balanceSlider, 2, 3);
        }

        private void createClipList(GridPane grid) {
            final VBox vbox = new VBox(30);
            vbox.setAlignment(Pos.TOP_CENTER);

            final Label clipLabel = new Label("Code Monkey To-Do List:");
            clipLabel.setId("clipLabel");


            final Button getUpButton = new Button("Get Up, Get Coffee");
            getUpButton.setPrefWidth(300);
            getUpButton.setOnAction(createPlayHandler(coffeeClip));

            final Button goToJobButton = new Button("Go to Job");
            goToJobButton.setPrefWidth(300);
            goToJobButton.setOnAction(createPlayHandler(jobClip));

            final Button meetingButton = new Button("Have Boring Meeting");
            meetingButton.setPrefWidth(300);
            meetingButton.setOnAction(createPlayHandler(meetingClip));

            final Hyperlink link = new Hyperlink("About Code Monkey...");
            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    WebView wv = new WebView();
                    wv.getEngine().load("http://www.jonathancoulton.com/2006/04/14/" +
                            "thing-a-week-29-code-monkey/");

                    Scene scene = new Scene(wv, 720, 480);

                    Stage stage = new Stage();
                    stage.setTitle("Code Monkey");
                    stage.setScene(scene);
                    stage.show();
                }
            });

            vbox.getChildren().addAll(clipLabel, getUpButton, goToJobButton,
                    meetingButton, link);

            GridPane.setHalignment(vbox, HPos.CENTER);
            GridPane.setHgrow(vbox, Priority.ALWAYS);
            GridPane.setVgrow(vbox, Priority.ALWAYS);
            grid.add(vbox, 0, 0, GridPane.REMAINING, 1);
        }

        private String getClipResourceString(String clipName) {
            return getClass().getResource(clipName).toString();
        }

        private EventHandler<ActionEvent> createPlayHandler(final AudioClip clip) {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    clip.play(volumeSlider.getValue(), balanceSlider.getValue(),
                            rateSlider.getValue(), 0, 0);
                }
            };
        }
    }
}
