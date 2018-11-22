package org.person.dfw.fx.practice.shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

public class LineMain  {

    /**以下代码创建一个Line对象，并使用setter方法设置开始和结束坐标。*/
    public static class LineMain1 extends Application {

        @Override
        public void start(Stage stage) {
            VBox box = new VBox();
            final Scene scene = new Scene(box, 300, 250);
            scene.setFill(null);

            Line line = new Line();
            line.setStartX(0.0f);
            line.setStartY(0.0f);
            line.setEndX(100.0f);
            line.setEndY(100.0f);

            box.getChildren().add(line);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**以下代码设置更多的线属性，包括笔触颜色，笔触宽度和线帽。*/
    public static class LineMain2 extends Application {
        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Drawing Lines");

            Group root = new Group();
            Scene scene = new Scene(root, 300, 150, Color.GRAY);

            Line redLine = new Line(10, 10, 200, 10);

            redLine.setStroke(Color.RED);
            redLine.setStrokeWidth(10);
            redLine.setStrokeLineCap(StrokeLineCap.BUTT);

            redLine.getStrokeDashArray().addAll(15d, 5d, 15d, 15d, 20d);
            redLine.setStrokeDashOffset(10);

            root.getChildren().add(redLine);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }

}
