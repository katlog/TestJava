package org.person.dfw.fx.practice.map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ScatterChartMain {


    public static class Main1 extends Application {


        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            ScatterChart scatterChart = new ScatterChart(xAxis, yAxis);
            scatterChart.setData(getChartData());
            scatterChart.setTitle("speculations");
            primaryStage.setTitle("ScatteredChart example");

            StackPane root = new StackPane();
            root.getChildren().add(scatterChart);
            primaryStage.setScene(new Scene(root, 400, 250));
            primaryStage.show();
        }

        private ObservableList<Series<String, Double>> getChartData() {
            double aValue = 1.56;
            double cValue = 1.06;
            ObservableList<Series<String, Double>> answer = FXCollections.observableArrayList();
            Series<String, Double> aSeries = new Series<String, Double>();
            Series<String, Double> cSeries = new Series<String, Double>();
            aSeries.setName("a");
            cSeries.setName("C");

            for (int i = 2011; i < 2021; i++) {
                aSeries.getData().add(new XYChart.Data(Integer.toString(i), aValue));
                aValue = aValue + Math.random() - .5;
                cSeries.getData().add(new XYChart.Data(Integer.toString(i), cValue));
                cValue = cValue + Math.random() - .5;
            }
            answer.addAll(aSeries, cSeries);
            return answer;
        }
    }


    public static class Main2 extends Application {

        @Override public void start(Stage stage) {
            stage.setTitle("Scatter Chart Sample");
            final NumberAxis xAxis = new NumberAxis(0, 10, 1);
            final NumberAxis yAxis = new NumberAxis(-100, 500, 100);
            final ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
            xAxis.setLabel("Age (years)");
            yAxis.setLabel("Returns to date");
            sc.setTitle("Investment Overview");

            Series series1 = new Series();

            series1.setName("Option 1");
            series1.getData().add(new XYChart.Data(4.2, 193.2));
            series1.getData().add(new XYChart.Data(2.8, 33.6));
            series1.getData().add(new XYChart.Data(6.2, 24.8));

            sc.setPrefSize(500, 400);
            sc.getData().addAll(series1);
            Scene scene  = new Scene(new Group());
            final VBox vbox = new VBox();
            final HBox hbox = new HBox();

            final Button add = new Button("Add Series");
            add.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    if (sc.getData() == null)
                        sc.setData(FXCollections.<Series<Number,
                                Number>>observableArrayList());
                    Series<Number, Number> series =
                            new Series<Number, Number>();
                    series.setName("Option "+(sc.getData().size()+1));
                    for (int i=0; i<100; i++) series.getData().add(
                            new ScatterChart.Data<Number,
                                    Number>(Math.random()*100, Math.random()*500));
                    sc.getData().add(series);
                }
            });

            final Button remove = new Button("Remove Series");
            remove.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    if (!sc.getData().isEmpty())
                        sc.getData().remove((int)(Math.random()*(sc.getData().size()-1)));
                }
            });

            final DropShadow shadow = new DropShadow();
            shadow.setOffsetX(2);
            shadow.setColor(Color.GREY);
            sc.setEffect(shadow);

            hbox.setSpacing(10);
            hbox.getChildren().addAll(add, remove);

            vbox.getChildren().addAll(sc, hbox);
            hbox.setPadding(new Insets(10, 10, 10, 50));

            ((Group)scene.getRoot()).getChildren().add(vbox);
            stage.setScene(scene);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }
}
