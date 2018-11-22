package org.person.dfw.fx.practice.map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LineChartMain {

    public static class Main1 extends Application {

        @Override
        public void start(Stage stage) {
            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Number of Month");
            final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(
                    xAxis, yAxis);

            lineChart.setTitle("Line Chart");
            Series<Number, Number> series = new Series<Number, Number>();
            series.setName("My Data");
            // populating the series with data
            series.getData().add(new XYChart.Data<Number, Number>(1, 23));
            series.getData().add(new XYChart.Data<Number, Number>(2, 114));
            series.getData().add(new XYChart.Data<Number, Number>(3, 15));
            series.getData().add(new XYChart.Data<Number, Number>(4, 124));

            Scene scene = new Scene(lineChart, 800, 600);
            lineChart.getData().add(series);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /***
     * 折线图创建
     *
     * 对于每个图表，我们可以设置标题及其相对于图表的位置。标题可以位于图表的顶部，右侧，左侧或底部。
     * 我们可以以相同的方式指定图表图例的位置。
     */
    public static class Main2 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            LineChart lineChart = new LineChart(xAxis, yAxis);
            lineChart.setData(getChartData());
            lineChart.setTitle("Chart");

            StackPane root = new StackPane();
            root.getChildren().add(lineChart);
            primaryStage.setScene(new Scene(root, 400, 250));
            primaryStage.show();
        }

        private ObservableList<Series<String, Double>> getChartData() {
            double aValue = 10;
            double cValue = 20;
            ObservableList<Series<String, Double>> answer = FXCollections
                    .observableArrayList();
            Series<String, Double> aSeries = new Series<String, Double>();
            Series<String, Double> cSeries = new Series<String, Double>();
            aSeries.setName("a");
            cSeries.setName("C");

            for (int i = 2001; i < 2021; i++) {
                aSeries.getData().add(new XYChart.Data(Integer.toString(i), aValue));
                aValue = aValue + Math.random()*100 -50;
                cSeries.getData().add(new XYChart.Data(Integer.toString(i), cValue));
                cValue = cValue + Math.random()*100 -50 ;
            }
            answer.addAll(aSeries, cSeries);
            return answer;
        }
    }

    /**
     * 折线图的类别
     *
     * 下面的代码显示了如何使用CategoryAxis类而不是NumberAxis类以在线形图中呈现非数字数据。
     */
    public static class Main3 extends Application {

        @Override
        public void start(Stage stage) {
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Month");

            final LineChart<String, Number> lineChart = new LineChart<String, Number>(
                    xAxis, yAxis);

            lineChart.setTitle("My Chart");

            Series<String, Number> series = new Series<String, Number>();
            series.setName("My data");

            series.getData().add(new XYChart.Data<String, Number>("Jan", 23));
            series.getData().add(new XYChart.Data<String, Number>("Feb", 114));
            series.getData().add(new XYChart.Data<String, Number>("Mar", 15));
            series.getData().add(new XYChart.Data<String, Number>("Apr", 24));
            series.getData().add(new XYChart.Data<String, Number>("May", 134));

            Scene scene = new Scene(lineChart, 800, 600);
            lineChart.getData().add(series);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**将系列添加到折线图*/
    public static class Main extends Application {

        @Override
        public void start(Stage stage) {
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Month");
            final LineChart<String, Number> lineChart = new LineChart<String, Number>(
                    xAxis, yAxis);

            lineChart.setTitle("My Chart");

            Series<String, Number> series1 = new Series<String, Number>();
            series1.setName("Portfolio 1");

            series1.getData().add(new XYChart.Data<String, Number>("Jan", 23));
            series1.getData().add(new XYChart.Data<String, Number>("Feb", 14));
            series1.getData().add(new XYChart.Data<String, Number>("Mar", 15));

            Series<String, Number> series2 = new Series<String, Number>();
            series2.setName("Portfolio 2");
            series2.getData().add(new XYChart.Data<String, Number>("Jan", 33));
            series2.getData().add(new XYChart.Data<String, Number>("Feb", 34));
            series2.getData().add(new XYChart.Data<String, Number>("Mar", 25));
            series2.getData().add(new XYChart.Data<String, Number>("Apr", 44));

            Series<String, Number> series3 = new Series<String, Number>();
            series3.setName("Portfolio 3");
            series3.getData().add(new XYChart.Data<String, Number>("Jan", 44));
            series3.getData().add(new XYChart.Data<String, Number>("Feb", 35));
            series3.getData().add(new XYChart.Data<String, Number>("Mar", 36));
            series3.getData().add(new XYChart.Data<String, Number>("Apr", 33));
            series3.getData().add(new XYChart.Data<String, Number>("May", 31));

            Scene scene = new Scene(lineChart, 800, 600);
            lineChart.getData().addAll(series1, series2, series3);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }


}
