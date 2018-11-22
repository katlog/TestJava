package org.person.dfw.fx.practice.shape;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;

public class TextMain {

    /***
     * JavaFX教程 - JavaFX文本
     *
     *
     * 另一个基本的JavaFX节点是Text节点，它允许我们在场景图上显示测试。
     * 要创建 Text 节点，请使用 javafx.scene.text.Text 类。
     * 所有JavaFX场景节点都从 javafx.scene.Node 扩展，并且它们继承了许多功能，例如缩放，翻译或旋转的功能。
     * Text节点的直接父对象是 javafx.scene.shape.Shape 类。我们可以在两个文本之间执行几何操作，如减法，相交或联合。您还可以使用文本剪辑视口区域。
     */
    public static class TextMain1 extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Drawing Text");
            Group root = new Group();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);
            int x = 100;
            int y = 100;
            int red = 30;
            int green = 40;
            int blue = 50;

            Text text = new Text(x, y, "JavaFX 2.0");

            text.setFill(Color.rgb(red, green, blue, .99));
            text.setRotate(60);
            root.getChildren().add(text);


            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**旋转文本*/
    public static class TextMain2 extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Drawing Text");
            Group root = new Group();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);
            int x = 100;
            int y = 100;
            int red = 30;
            int green = 40;
            int blue = 50;

            Text text = new Text(x, y, "JavaFX 2.0");

            text.setFill(Color.rgb(red, green, blue, .99));
            text.setRotate(60);
            root.getChildren().add(text);


            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**
     * 文本字体
     *
     * JavaFX的Font API使您能够更改字体样式和字体大小。
     */
    public static class TextMain3 extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("");
            Group root = new Group();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);

            Group g = new Group();


            Text t = new Text();
            t.setCache(true);
            t.setX(10.0);
            t.setY(70.0);
            t.setFill(Color.RED);
            t.setText("JavaFX");
            t.setFont(Font.font(null, FontWeight.BOLD, 32));
            g.getChildren().add(t);



            root.getChildren().add(g);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**具有CHOCOLATE颜色和Font.SERIF的文本*/
    public static class TextMain4 extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Title");

            final Circle circ = new Circle(40, 40, 30);
            final Group root = new Group(circ);

            final Scene scene = new Scene(root, 800, 400, Color.BEIGE);

            final Text text1 = new Text(25, 25, "w3cschool.cn");
            text1.setFill(Color.CHOCOLATE);
            text1.setFont(Font.font(java.awt.Font.SERIF, 25));
            root.getChildren().add(text1);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**
     * 文本效果
     *
     * DropShadow 对象基于相对于Text节点的x，y偏移量定位。您可以设置阴影的颜色。
     * 以下代码显示如何使用DropShadow绘制文本。
     */
    public static class TextMain5 extends Application {
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

            Text t = new Text();
            t.setEffect(ds);
            t.setCache(true);
            t.setX(10.0);
            t.setY(70.0);
            t.setFill(Color.RED);
            t.setText("JavaFX drop shadow...");
            t.setFont(Font.font(null, FontWeight.BOLD, 32));
            g.getChildren().add(t);



            root.getChildren().add(g);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /***
     * 使用0.7f调用setFraction()方法基本上指定了我们希望显示70％的反射。
     * 以下代码显示如何使用在文本的反射作用。
     *
     * 反射值范围从零(0％)到一(100％)。
     * 我们还可以通过setTopOffset()方法设置不透明节点部分和反射部分之间的空间。顶部偏移默认为零。
     */
    public static class TextMain6 extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("");
            Group root = new Group();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);

            Text t = new Text();
            t.setX(10.0);
            t.setY(50.0);
            t.setCache(true);
            t.setText("Reflections on JavaFX...");
            t.setFill(Color.RED);
            t.setFont(Font.font(null, FontWeight.BOLD, 30));

            Reflection r = new Reflection();
            r.setFraction(0.7);

            t.setEffect(r);

            root.getChildren().add(t);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**以下代码显示如何使用行分隔符对文本执行换行。*/
    public static class TextMain7 extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Keyboard");
            Group root = new Group();
            Scene scene = new Scene(root, 530, 300, Color.WHITE);

            final StringProperty statusProperty = new SimpleStringProperty();

            InnerShadow iShadow = InnerShadowBuilder.create()
                    .offsetX(3.5f)
                    .offsetY(3.5f)
                    .build();
            final Text status = TextBuilder.create()
                    .effect(iShadow)
                    .x(100)
                    .y(50)
                    .fill(Color.LIME)
                    .font(Font.font(null, FontWeight.BOLD, 35))
                    .translateY(50)
                    .build();
            status.textProperty().bind(statusProperty);
            statusProperty.set("Line\nLine2\nLine");
            root.getChildren().add(status);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }


    /**以下代码显示如何设置文本换行宽度。*/
    public static class TextMain8 extends Application {
        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 300, 150);
            stage.setScene(scene);
            stage.setTitle("Sample");

            Text t = new Text(10, 50, "This is a test");
            t.setWrappingWidth(200);
            t.setText("First row Second row Second row Second row Second row Second row ");
            t.setFont(new Font(20));

            root.getChildren().add(t);


            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
