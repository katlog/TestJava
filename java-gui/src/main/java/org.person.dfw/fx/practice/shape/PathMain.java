package org.person.dfw.fx.practice.shape;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;


public class PathMain {

    /**以下代码显示了如何创建路径。*/
    /**
     * 路径元素实际上从javafx.scene.shape.PathElement类扩展，它仅在Path对象的上下文中使用。
     * 所以你不能实例化一个LineTo类放在场景图中。具有 To 作为后缀的类是路径元素，而不是Shape节点。
     * 例如，MoveTo和LineTo对象实例是添加到Path对象的Path元素，而不是可以添加到场景的形状。
     * 上面的代码生成以下结果
     * */
    public static class PathMain1 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setTitle("Checkbox Sample");
            stage.setWidth(230);
            stage.setHeight(120);

            Path path = new Path();
            path.getElements().add(new MoveTo(0.0f, 50.0f));
            path.getElements().add(new LineTo(100.0f, 100.0f));

            VBox vbox = new VBox();
            vbox.getChildren().addAll(path);
            vbox.setSpacing(5);

            HBox root = new HBox();
            root.getChildren().add(vbox);
            root.setSpacing(40);
            root.setPadding(new Insets(20, 10, 10, 20));

            ((Group) scene.getRoot()).getChildren().add(root);

            stage.setScene(scene);
            stage.show();
        }
    }

    /**以下代码显示了如何向路径添加 QuadCurveTo 。*/
    public static class PathMain2 extends Application {
        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 300, 150);
            stage.setScene(scene);
            stage.setTitle("");

            Path path = new Path();

            MoveTo moveTo = new MoveTo();
            moveTo.setX(0.0f);
            moveTo.setY(50.0f);

            QuadCurveTo quadTo = new QuadCurveTo();
            quadTo.setControlX(25.0f);
            quadTo.setControlY(0.0f);
            quadTo.setX(50.0f);
            quadTo.setY(50.0f);

            path.getElements().add(moveTo);
            path.getElements().add(quadTo);

            root.getChildren().add(path);

            scene.setRoot(root);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**使用Path，MoveTo和CubicCurveTo创建曲线*/
    public static class PathMain3 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            stage.setTitle("ComboBoxSample");
            Scene scene = new Scene(new Group(), 450, 250);

            Path path = new Path();

            MoveTo moveTo = new MoveTo();
            moveTo.setX(0.0f);
            moveTo.setY(0.0f);

            CubicCurveTo cubicTo = new CubicCurveTo();
            cubicTo.setControlX1(0.0f);
            cubicTo.setControlY1(0.0f);
            cubicTo.setControlX2(100.0f);
            cubicTo.setControlY2(100.0f);
            cubicTo.setX(100.0f);
            cubicTo.setY(50.0f);

            path.getElements().add(moveTo);
            path.getElements().add(cubicTo);


            Group root = (Group) scene.getRoot();
            root.getChildren().add(path);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**减去两个形状以创建路径*/
    public static class PathMain4 extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Shapes");
            Group root = new Group();
            Scene scene = new Scene(root, 300, 300, Color.WHITE);

            Ellipse bigCircle = EllipseBuilder.create()
                    .centerX(100)
                    .centerY(100)
                    .radiusX(50)
                    .radiusY(75/2)
                    .strokeWidth(3)
                    .stroke(Color.BLACK)
                    .fill(Color.WHITE)
                    .build();

            Ellipse smallCircle = EllipseBuilder.create()
                    .centerX(100)
                    .centerY(100)
                    .radiusX(35/2)
                    .radiusY(25/2)

                    .build();

            Shape shape = Path.subtract(bigCircle, smallCircle);
            shape.setStrokeWidth(1);
            shape.setStroke(Color.BLACK);

            shape.setFill(Color.rgb(255, 200, 0));

            root.getChildren().add(shape);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**使用VLineTo创建垂直线*/
    public static class PathMain5 extends Application {
        @Override
        public void start(final Stage stage) {
            stage.setTitle("HTML");
            stage.setWidth(500);
            stage.setHeight(500);
            Scene scene = new Scene(new Group());

            VBox root = new VBox();

            Path path = new Path();
            path.getElements().add(new MoveTo(50.0f, 0.0f));
            path.getElements().add(new VLineTo(50.0f));

            root.getChildren().addAll(path);
            scene.setRoot(root);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

}
