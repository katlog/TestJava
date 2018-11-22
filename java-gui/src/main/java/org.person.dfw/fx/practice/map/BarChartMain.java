package org.person.dfw.fx.practice.map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;

public class BarChartMain {

    public static class Main1 extends Application {

        @Override
        public void start(final Stage stage) {
            stage.setTitle("");
            stage.setWidth(500);
            stage.setHeight(500);
            Scene scene = new Scene(new Group());

            VBox root = new VBox();
            NumberAxis lineYAxis = new NumberAxis(0,100,10);
            CategoryAxis lineXAxis = new CategoryAxis();

            BarChart barChart =     new BarChart(lineXAxis,lineYAxis);

            lineYAxis.setLabel("Sales");
            lineXAxis.setLabel("Products");

            root.getChildren().addAll(barChart);
            scene.setRoot(root);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**水平条形图*/
    public static class Main2 extends Application {

        final static String itemA = "A";
        final static String itemB = "B";
        final static String itemC = "F";
        @Override
        public void start(Stage stage) {
            final NumberAxis xAxis = new NumberAxis();
            final CategoryAxis yAxis = new CategoryAxis();
            final BarChart<Number, String> bc = new BarChart<Number, String>(xAxis, yAxis);
            bc.setTitle("Summary");
            xAxis.setLabel("Value");
            xAxis.setTickLabelRotation(90);
            yAxis.setLabel("Item");

            XYChart.Series series1 = new XYChart.Series();
            series1.setName("2003");
            series1.getData().add(new XYChart.Data(2, itemA));
            series1.getData().add(new XYChart.Data(20, itemB));
            series1.getData().add(new XYChart.Data(10, itemC));

            XYChart.Series series2 = new XYChart.Series();
            series2.setName("2004");
            series2.getData().add(new XYChart.Data(50, itemA));
            series2.getData().add(new XYChart.Data(41, itemB));
            series2.getData().add(new XYChart.Data(45, itemC));

            XYChart.Series series3 = new XYChart.Series();
            series3.setName("2005");
            series3.getData().add(new XYChart.Data(45, itemA));
            series3.getData().add(new XYChart.Data(44, itemB));
            series3.getData().add(new XYChart.Data(18, itemC));

            Timeline tl = new Timeline();
            tl.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                    new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent actionEvent) {
                            for (XYChart.Series<Number, String> series : bc.getData()) {
                                for (XYChart.Data<Number, String> data : series.getData()) {
                                    data.setXValue(Math.random() * 100);
                                }
                            }
                        }
                    }));
            tl.setCycleCount(Animation.INDEFINITE);
            tl.play();

            Scene scene = new Scene(bc, 800, 600);
            bc.getData().addAll(series1, series2, series3);
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**动画条形图*/
    public static class Main3 extends Application {

        final static String itemA = "A";
        final static String itemB = "B";
        final static String itemC = "F";
        @Override
        public void start(Stage stage) {
            final NumberAxis xAxis = new NumberAxis();
            final CategoryAxis yAxis = new CategoryAxis();
            final BarChart<Number, String> bc = new BarChart<Number, String>(xAxis, yAxis);
            bc.setTitle("Summary");
            xAxis.setLabel("Value");
            xAxis.setTickLabelRotation(90);
            yAxis.setLabel("Item");

            XYChart.Series series1 = new XYChart.Series();
            series1.setName("2003");
            series1.getData().add(new XYChart.Data(2, itemA));
            series1.getData().add(new XYChart.Data(20, itemB));
            series1.getData().add(new XYChart.Data(10, itemC));

            XYChart.Series series2 = new XYChart.Series();
            series2.setName("2004");
            series2.getData().add(new XYChart.Data(50, itemA));
            series2.getData().add(new XYChart.Data(41, itemB));
            series2.getData().add(new XYChart.Data(45, itemC));

            XYChart.Series series3 = new XYChart.Series();
            series3.setName("2005");
            series3.getData().add(new XYChart.Data(45, itemA));
            series3.getData().add(new XYChart.Data(44, itemB));
            series3.getData().add(new XYChart.Data(18, itemC));

            Timeline tl = new Timeline();
            tl.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                    new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent actionEvent) {
                            for (XYChart.Series<Number, String> series : bc.getData()) {
                                for (XYChart.Data<Number, String> data : series.getData()) {
                                    data.setXValue(Math.random() * 100);
                                }
                            }
                        }
                    }));
            tl.setCycleCount(Animation.INDEFINITE);
            tl.play();

            Scene scene = new Scene(bc, 800, 600);
            bc.getData().addAll(series1, series2, series3);
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**StackedBarChart演示*/
    public static class Main4 extends Application {

        public static void main(String[] args) {
            launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            Group root = new Group();

            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Month");
            xAxis.setCategories(FXCollections.<String> observableArrayList(Arrays.asList(
                    "January",
                    "February",
                    "March")));
            yAxis.setLabel("Value");

            final StackedBarChart<String,Number> stackedBarChart = new StackedBarChart<String,Number>(xAxis,yAxis);
            stackedBarChart.setTitle("StackedBarChart");

            XYChart.Series<String,Number> series1 = new XYChart.Series();
            series1.setName("XYChart.Series 1");

            series1.getData().add(new XYChart.Data("January", 100));
            series1.getData().add(new XYChart.Data("February", 200));
            series1.getData().add(new XYChart.Data("March", 50));

            XYChart.Series<String,Number> series2 = new XYChart.Series();
            series2.setName("XYChart.Series 2");

            series2.getData().add(new XYChart.Data("January", 150));
            series2.getData().add(new XYChart.Data("February", 100));
            series2.getData().add(new XYChart.Data("March", 60));

            stackedBarChart.getData().addAll(series1, series2);

            root.getChildren().addAll(stackedBarChart);

            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.show();
        }
    }

}
