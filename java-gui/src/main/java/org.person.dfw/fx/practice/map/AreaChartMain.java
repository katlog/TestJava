package org.person.dfw.fx.practice.map;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

public class AreaChartMain {

    /**区域图是另一种类型的双轴图表。*/

    public static class Main1 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {

            Group root = new Group();
            final NumberAxis xAxis = new NumberAxis(1, 12, 1);

            final NumberAxis yAxis = new NumberAxis();
            final StackedAreaChart<Number,Number> stackedAreaChart = new StackedAreaChart<Number,Number>(xAxis,yAxis);
            final XYChart.Series<Number,Number> series1 = new XYChart.Series<Number,Number>();

            xAxis.setLabel("Month");
            yAxis.setLabel("Value");

            stackedAreaChart.setTitle("StackedAreaChart");
            series1.setName("XYChart.Series 1");

            series1.getData().add(new XYChart.Data(1, 100));
            series1.getData().add(new XYChart.Data(2, 200));
            series1.getData().add(new XYChart.Data(10, 150));

            XYChart.Series<Number,Number> series2 = new XYChart.Series();
            series2.setName("XYChart.Series 2");

            series2.getData().add(new XYChart.Data(1, 50));
            series2.getData().add(new XYChart.Data(2, 200));
            series2.getData().add(new XYChart.Data(10, 260));

            stackedAreaChart.getData().addAll(series1, series2);

            root.getChildren().addAll(stackedAreaChart);

            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.show();
        }
    }

    /**
     * 创建区域图
     *
     * 要创建区域图，请使用AreaChart对象和使用XYChart.Series类创建一个或多个数据系列，并将数据分配给图表。
     */
    public static class Main extends Application {

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("");
            Group root = new Group();

            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Month");
            yAxis.setLabel("Value");

            final AreaChart<String,Number> areaChart = new AreaChart<String,Number>(xAxis,yAxis);

            areaChart.setTitle("AreaChart");
            XYChart.Series series = new XYChart.Series();
            series.setName("XYChart.Series");

            series.getData().add(new XYChart.Data("January", 100));
            series.getData().add(new XYChart.Data("February", 200));
            series.getData().add(new XYChart.Data("March", 50));

            areaChart.getData().add(series);

            root.getChildren().add(areaChart);

            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.show();
        }

    }


    /**向AreaChart添加更多系列*/
    public static class Main3 extends Application {

        @Override public void start(Stage stage) {
            stage.setTitle("Area Chart Sample");
            final NumberAxis xAxis = new NumberAxis(1, 30, 1);
            final NumberAxis yAxis = new NumberAxis(-5, 27, 5);
            final AreaChart<Number,Number> ac =
                    new AreaChart<Number,Number>(xAxis,yAxis);
            xAxis.setForceZeroInRange(true);

            ac.setTitle("Temperature Monitoring (in Degrees C)");

            XYChart.Series series1 = new XYChart.Series();
            series1.setName("March");
            series1.getData().add(new XYChart.Data(0, -2));
            series1.getData().add(new XYChart.Data(3, -4));
            series1.getData().add(new XYChart.Data(6, 0));
            series1.getData().add(new XYChart.Data(9, 5));

            XYChart.Series series2 = new XYChart.Series();
            series2.setName("April");
            series2.getData().add(new XYChart.Data(0, 4));
            series2.getData().add(new XYChart.Data(3, 10));
            series2.getData().add(new XYChart.Data(6, 15));
            series2.getData().add(new XYChart.Data(9, 8));

            XYChart.Series series3 = new XYChart.Series();
            series3.setName("May");
            series3.getData().add(new XYChart.Data(0, 20));
            series3.getData().add(new XYChart.Data(3, 15));
            series3.getData().add(new XYChart.Data(6, 13));
            series3.getData().add(new XYChart.Data(9, 12));

            Scene scene  = new Scene(ac,800,600);
            //scene.getStylesheets().add("areachartsample/Chart.css");
            ac.setHorizontalZeroLineVisible(true);
            ac.getData().addAll(series1, series2, series3);
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**创建堆叠区域图*/
    public static class Main4 extends Application {
        final NumberAxis xAxis = new NumberAxis(1, 10, 1);
        final NumberAxis yAxis = new NumberAxis();
        final StackedAreaChart<Number, Number> sac =
                new StackedAreaChart<>(xAxis, yAxis);

        @Override
        public void start(Stage stage) {
            sac.setTitle("title");
            XYChart.Series<Number, Number> seriesApril =
                    new XYChart.Series<>();
            seriesApril.setName("April");
            seriesApril.getData().add(new XYChart.Data(1, 4));
            seriesApril.getData().add(new XYChart.Data(3, 10));
            seriesApril.getData().add(new XYChart.Data(6, 15));
            XYChart.Series<Number, Number> seriesMay =
                    new XYChart.Series<>();
            seriesMay.setName("May");
            seriesMay.getData().add(new XYChart.Data(1, 23));
            seriesMay.getData().add(new XYChart.Data(7, 26));
            seriesMay.getData().add(new XYChart.Data(10, 26));
            Scene scene = new Scene(sac, 800, 600);
            sac.getData().addAll(seriesApril, seriesMay);
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
