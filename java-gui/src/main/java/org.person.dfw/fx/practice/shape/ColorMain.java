package org.person.dfw.fx.practice.shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorMain {

    /***
     * 在JavaFX中，我们可以对对象应用颜色（Paint）。
     * 在JavaFX中，所有形状都可以填充简单的颜色和渐变颜色。
     *
     * RGB颜色
     *
     * 当指定颜色值时，我们可以使用默认的RGB颜色空间中的颜色。
     * 要创建颜色，请使用Color.rgb()方法。此方法采用三个整数值，表示红色，绿色和蓝色组件。
     */
    public static class ColorMain1 extends Application{
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

    /**以下代码根据颜色名称创建颜色。Color.DARKBLUE*/
    public static class ColorMain2 extends Application{
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
            text1.setFill(Color.DARKBLUE);
            text1.setFont(Font.font(java.awt.Font.SERIF, 25));
            root.getChildren().add(text1);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /***
     * 颜色alpha通道
     *
     * 另一个重载方法需要三个整数值和第四个double类型值，即alpha通道。
     * 第四个值设置颜色的不透明度。此值介于零(0)和一(1)之间。
     */
    public static class ColorMain3 extends Application{
        public static void main(String[] args) {
            Application.launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Text Fonts");
            Group root = new Group();
            Scene scene = new Scene(root, 550, 250, new Color(0,0,1,1.0));

            Text text = new Text(50, 100, "JavaFX 2.0 from Java2s.com");
            Font sanSerif = Font.font("Dialog", 30);
            text.setFont(sanSerif);
            text.setFill(Color.RED);
            root.getChildren().add(text);


            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /***
     * HSB颜色
     *
     * 我们还可以通过指定色调，饱和度和亮度(HSB)来创建颜色。要使用HSB创建颜色，请使用 Color.hsb()方法。
     */
    public static class ColorMain4 extends Application{
        public static void main(String[] args) {
            Application.launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Text Fonts");
            Group root = new Group();
            Scene scene = new Scene(root, 550, 250,Color.hsb(270,1.0,1.0,1.0));

            Text text = new Text(50, 100, "JavaFX 2.0 from Java2s.com");
            Font sanSerif = Font.font("Dialog", 30);
            text.setFont(sanSerif);
            text.setFill(Color.RED);
            root.getChildren().add(text);


            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /***
     * Web颜色
     *
     * 以下代码显示了如何从Web值创建颜色。
     *  Color c = Color.web("#0000FF",1.0);// blue as a hex web value, explict alpha
     *  Color c = Color.web("#0000FF");// blue as a hex web value, implict alpha
     *  Color c = Color.web("0000FF",1.0);// blue as a hex web value, explict alpha
     *  Color c = Color.web("0000FF");// blue as a hex web value, implict alpha
     *
     *  要使用RGB十六进制值作为CSS指定颜色值，我们可以使用Color.web()方法。
     */
    public static class ColorMain5 extends Application{
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setTitle("Label Sample");
            stage.setWidth(400);
            stage.setHeight(180);

            HBox hbox = new HBox();

            Label label1 = new Label("Search");
            label1.setTextFill(Color.web("#0076a3"));

            hbox.setSpacing(10);
            hbox.getChildren().add((label1));
            ((Group) scene.getRoot()).getChildren().add(hbox);

            stage.setScene(scene);
            stage.show();
        }
    }

}
