package org.person.dfw.fx.practice.shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;

public class CubicCurveMain {

    /***
     * 立方曲线
     *
     * 要创建三次曲线，请使用适当的构造函数。
     * 一个三次曲线的主要参数设置是startX，startY，controlX1(控制点1X)，controlY1(控制点1Y)，控制X2(控制点2X)和控制Y2(控制点2Y)，endX，endY。
     * startX，startY，endX和endY参数是曲线的起点和终点。controlX1，controlY1，controlX2和controlY2是控制点。
     * 控制点(控制X1，控制Y1)影响线段在起点(startX，startY)和线的中点之间。
     * 控制点(controlX2，controlY2)影响线段在线的中点和其终点(endX，endY)之间。
     * 控制点将曲线拉向自身的方向。
     */
    public static class CubicCurveMain1 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            stage.setTitle("ComboBoxSample");
            Scene scene = new Scene(new Group(), 450, 250);

            CubicCurve cubic = new CubicCurve();
            cubic.setStartX(0.0f);
            cubic.setStartY(50.0f);
            cubic.setControlX1(25.0f);
            cubic.setControlY1(0.0f);
            cubic.setControlX2(75.0f);
            cubic.setControlY2(100.0f);
            cubic.setEndX(100.0f);
            cubic.setEndY(50.0f);


            Group root = (Group) scene.getRoot();
            root.getChildren().add(cubic);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**QuadCurve 类与三次曲线类似。代替两个控制点，我们只有一个控制点为QuadCurve*/
    public static class QuadCurve1 extends Application {
        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 300, 150);
            stage.setScene(scene);
            stage.setTitle("");


            QuadCurve quad = new QuadCurve();
            quad.setStartX(0.0f);
            quad.setStartY(50.0f);
            quad.setEndX(50.0f);
            quad.setEndY(50.0f);
            quad.setControlX(25.0f);
            quad.setControlY(0.0f);

            root.getChildren().add(quad);

            scene.setRoot(root);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }



}
