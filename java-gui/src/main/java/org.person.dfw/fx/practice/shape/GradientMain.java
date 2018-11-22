package org.person.dfw.fx.practice.shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * JavaFX教程 - JavaFX渐变颜色
 *
 *
 * 我们可以使用径向渐变使形状看起来三维。
 * 梯度绘制可以在两种或更多种颜色之间内插，这给出形状的深度。
 * JavaFX提供两种类型的渐变：径向渐变( RadialGradient )和线性渐变( LinearGradient )。
 * 要在JavaFX中创建渐变颜色，我们需要设置五个属性。
     * 设置开始第一个停止颜色的起点。
     * 将终点设置为终止停止颜色。
     * 设置proportional属性以指定是使用标准屏幕坐标还是单位平方坐标。
     * 将循环方法设置为使用三个枚举：NO_CYCLE，REFLECT或REPEAT。
     * 设置Stop颜色数组。
 * 通过将proportional属性设置为false，我们可以基于标准屏幕（x，y）坐标将渐变轴设置为具有起点和终点。
 * 通过将proportional属性设置为true，梯度轴线开始点和结束点将被表示为单位平方坐标。起点和终点的x，y坐标必须在0.0和1.0之间（double）。
 */
public class GradientMain {

    /***
     * 线性梯度
     *
     * 要创建线性渐变绘制，我们为开始点和结束点指定startX，startY，endX和endY。起点和终点坐标指定渐变图案开始和停止的位置。
     * 下表列出了LinearGradient属性
     */
    public static class LinearGradientMain extends Application {
        @Override
        public void start(Stage stage) {
            VBox box = new VBox();
            final Scene scene = new Scene(box,300, 250);
            scene.setFill(null);
            Stop[] stops = new Stop[] { new Stop(0, Color.BLACK), new Stop(1, Color.RED)};
            LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

            Rectangle r1 = new Rectangle(0, 0, 100, 100);
            r1.setFill(lg1);

            box.getChildren().add(r1);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**径向梯度
     */
    public static class RadialGradientMain extends Application {
        static int dx = 1;
        static int dy = 1;

        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(final Stage primaryStage) {
            primaryStage.setTitle("Animation");
            Group root = new Group();
            Scene scene = new Scene(root, 400, 300, Color.WHITE);

            primaryStage.setScene(scene);
            addBouncyBall(scene);
            primaryStage.show();
        }
        private void addBouncyBall(final Scene scene) {
            final Circle ball = new Circle(100, 100, 20);

            RadialGradient gradient1 = new RadialGradient(0,
                    .1,
                    100,
                    100,
                    20,
                    false,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, Color.RED),
                    new Stop(1, Color.BLACK));

            ball.setFill(gradient1);


            final Group root = (Group) scene.getRoot();
            root.getChildren().add(ball);

        }
    }


    /**半透明渐变*/
    public static class LinearGradientMain1 extends Application {
        @Override
        public void start(Stage stage) {
            VBox box = new VBox();
            final Scene scene = new Scene(box,300, 250);
            scene.setFill(null);
            // A rectangle filled with a linear gradient with a translucent color.
            Rectangle rectangle = new Rectangle();
            rectangle.setX(50);
            rectangle.setY(50);
            rectangle.setWidth(100);
            rectangle.setHeight(70);

            LinearGradient linearGrad = new LinearGradient(
                    0,   // start X
                    0,   // start Y
                    0,   // end X
                    1, // end Y
                    true, // proportional
                    CycleMethod.NO_CYCLE, // cycle colors
                    // stops
                    new Stop(0.1f, Color.rgb(25, 200, 0, .4)),
                    new Stop(1.0f, Color.rgb(0, 0, 0, .1)));
            rectangle.setFill(linearGrad);

            box.getChildren().add(rectangle);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /***
     * 反射循环渐变
     *
     * 以下代码使用在对角线方向上的绿色和黑色创建具有渐变的重复图案的矩形。
     * 开始X，Y和结束X，Y值设置在对角线位置，循环方法设置为反映CycleMethod.REFLECT 。
     * CycleMethod.REFLECT使梯度图案在停止颜色之间重复或循环。
     */
    public static class LinearGradientMain2 extends Application {
        @Override
        public void start(Stage stage) {
            VBox box = new VBox();
            final Scene scene = new Scene(box, 300, 250);
            scene.setFill(null);
            // A rectangle filled with a linear gradient with a translucent color.
            Rectangle rectangle = new Rectangle();
            rectangle.setX(50);
            rectangle.setY(50);
            rectangle.setWidth(100);
            rectangle.setHeight(70);

            LinearGradient cycleGrad = new LinearGradient(50, // start X
                    50, // start Y
                    70, // end X
                    70, // end Y
                    false, // proportional
                    CycleMethod.REFLECT, // cycleMethod
                    new Stop(0f, Color.rgb(21, 25, 0, .784)), new Stop(1.0f, Color.rgb(0,
                    210, 0, .784)));
            rectangle.setFill(cycleGrad);

            box.getChildren().add(rectangle);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }


}
