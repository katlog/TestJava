package org.person.dfw.fx.practice.shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ArcMain {

    /**以下代码显示如何绘制以50,50为中心，半径为25并从角度45延伸到角度315（270度长）的圆弧。*/
    public static class ArcMain1 extends Application{
        public static void main(String[] args) {
            Application.launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Text Fonts");

            Group g = new Group();
            Scene scene = new Scene(g, 550, 250,Color.web("0x0000FF",1.0));

            Arc arc = new Arc();
            arc.setCenterX(50.0f);
            arc.setCenterY(50.0f);
            arc.setRadiusX(25.0f);
            arc.setRadiusY(25.0f);
            arc.setStartAngle(45.0f);
            arc.setLength(270.0f);
            arc.setType(ArcType.ROUND);

            g.getChildren().add(arc);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }


    /**Circle类创建一个新的圆，其中指定的半径和中心位置以像素为单位。*/
    public static class ArcMain2 extends Application{
        public static void main(String[] args) {
            Application.launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Text Fonts");
            Group root = new Group();
            Scene scene = new Scene(root, 550, 250,Color.web("0x0000FF"));

            Circle circle = new Circle();
            circle.setCenterX(100.0f);
            circle.setCenterY(100.0f);
            circle.setRadius(50.0f);

            root.getChildren().add(circle);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**以下代码显示了如何使用Circle构造函数传递半径和中心。*/
    public static class ArcMain3 extends Application{

        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Title");

            final Circle circ = new Circle(40, 40, 30);
            final Group root = new Group(circ);
            final Scene scene = new Scene(root, 400, 300);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**圆与DropShadow*/

    /***
     * getBoundsInParent()方法返回节点的边界区域，例如其宽度和高度。
     * 对于高度和宽度的 getBoundsInParent()计算包括节点的实际尺寸加上任何效果，平移和变换。例如，具有阴影效果的形状通过包括阴影增加其宽度。
     */
    public static class ArcMain4 extends Application{

        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("");
            Group root = new Group();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);

            Group g = new Group();

            DropShadow ds1 = new DropShadow();
            ds1.setOffsetY(4.0);

            Circle c = new Circle();
            c.setEffect(ds1);
            c.setCenterX(50.0);
            c.setCenterY(125.0);
            c.setRadius(30.0);
            c.setFill(Color.RED);
            c.setCache(true);


            g.getChildren().add(c);


            root.getChildren().add(g);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }


}
