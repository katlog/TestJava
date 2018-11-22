package org.person.dfw.fx.practice.map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class PieChartMain {

    /**表示圆圈中的数据的图表，每个切片表示百分比。*/
    public static class Main1 extends Application {

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            PieChart pieChart = new PieChart();
            pieChart.setData(getChartData());
            primaryStage.setTitle("PieChart");

            StackPane root = new StackPane();
            root.getChildren().add(pieChart);
            primaryStage.setScene(new Scene(root, 400, 250));
            primaryStage.show();
        }

        private ObservableList<Data> getChartData() {
            ObservableList<Data> answer = FXCollections.observableArrayList();
            answer.addAll(new Data("java", 17.56),
                    new Data("JavaFx", 31.37));
            return answer;
        }
    }

    /**
     * 创建饼图
     *
     * 要创建饼图，我们需要PieChart类。
     * 饼图数据包裹在PieChart.Data对象中。
     * 每个PieChart.Data对象有两个字段:饼图扇区的名称及其对应的值。
     */
    public static class Main2 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            PieChart pieChart = new PieChart();
            pieChart.setData(getChartData());
            pieChart.setTitle("Title");
            pieChart.setLegendSide(Side.LEFT);
            pieChart.setClockwise(false);
            pieChart.setLabelsVisible(false);

            StackPane root = new StackPane();
            root.getChildren().add(pieChart);
            primaryStage.setScene(new Scene(root, 300, 250));
            primaryStage.show();
        }

        private ObservableList<Data> getChartData() {
            ObservableList<Data> answer = FXCollections.observableArrayList();
            answer.addAll(new Data("java", 17),
                    new Data("JavaFx", 31),
                    new Data("Swing", 10),
                    new Data("IO", 20),
                    new Data("NIO", 21)
            );
            return answer;
        }
    }

    /**
     * 处理饼图的事件
     *
     * 以下代码创建一个EventHandler对象来处理落入特定图表切片的MOUSE_PRESSED事件。
     */
    public static class Main3 extends Application {

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setTitle("Imported Fruits");
            stage.setWidth(500);
            stage.setHeight(500);

            ObservableList<Data> pieChartData =
                    FXCollections.observableArrayList(
                            new Data("Grapefruit", 13),
                            new Data("Oranges", 25),
                            new Data("Plums", 10),
                            new Data("Pears", 22),
                            new Data("Apples", 30));

            final PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Imported Fruits");
            final Label caption = new Label("");
            caption.setTextFill(Color.DARKORANGE);
            caption.setStyle("-fx-font: 24 arial;");

            for (final Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        new EventHandler<MouseEvent>() {
                            @Override public void handle(MouseEvent e) {
                                caption.setTranslateX(e.getSceneX());
                                caption.setTranslateY(e.getSceneY());
                                caption.setText(String.valueOf(data.getPieValue())
                                        + "%");
                            }
                        });
            }

            ((Group) scene.getRoot()).getChildren().addAll(chart, caption);
            stage.setScene(scene);
            //scene.getStylesheets().add("piechartsample/Chart.css");
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
