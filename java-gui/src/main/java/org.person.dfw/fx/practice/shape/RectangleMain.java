package org.person.dfw.fx.practice.shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RectangleMain {

    /**要在JavaFX中绘制一个矩形，我们可以使用 javafx.scene.shape.Rectangle 类。*/
    public static class RectangleMain1 extends Application{
        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("");
            Group root = new Group();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);


            Rectangle r = new Rectangle();
            r.setX(50);
            r.setY(50);
            r.setWidth(200);
            r.setHeight(100);

            root.getChildren().add(r);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }


    /**Rectangle 类实现圆弧宽度和圆弧高度。我们可以使用这些功能来绘制圆角矩形*/
    public static class RectangleMain2 extends Application{
        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            Group group = new Group();

            Rectangle rect = new Rectangle(20,20,200,200);

            rect.setArcHeight(15);
            rect.setArcWidth(15);

            rect.setStroke(Color.BLACK);
            group.getChildren().add(rect);

            Scene scene = new Scene(group, 300, 200);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public static class RectangleMain3 extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("");
            Group root = new Group();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);

            Group g = new Group();

            DropShadow ds = new DropShadow();
            ds.setOffsetY(3.0);
            ds.setColor(Color.color(0.4, 0.4, 0.4));

            Ellipse ellipse = new Ellipse();
            ellipse.setCenterX(50.0f);
            ellipse.setCenterY(50.0f);
            ellipse.setRadiusX(50.0f);
            ellipse.setRadiusY(25.0f);
            ellipse.setEffect(ds);

            g.getChildren().add(ellipse);

            root.getChildren().add(g);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }



}
