package org.person.dfw.fx.practice.ui.widget;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.stage.Stage;
import org.junit.Test;

public class ScrollBarMain {


    @Test
    public void create() {
        // 以下代码使用其默认构造函数创建滚动条。
        ScrollBar sc = new ScrollBar();
        // setMin和setMax方法定义滚动条表示的最小值和最大值。
        // setValue方法设置滚动的当前值，也设置拇指的位置。
        sc.setMin(0);
        sc.setMax(100);
        sc.setValue(50);

    }

    /**
     * 滚动条有四个区域：
         * thumb
         * 右按钮或向下按钮
         * 左按钮或向上按钮
         * track
     */
    public static class Main1 extends Application {
        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 500, 200);
            stage.setScene(scene);

            ScrollBar s1 = new ScrollBar();

            root.getChildren().add(s1);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }


    /**
     * 当用户移动缩略图时，滚动条的值会更改。
     * 默认情况下，滚动条水平定向。我们可以使用 setOrientation 方法设置垂直方向。
     * 我们可以单击左右按钮的水平滚动条或向上和向下按钮垂直滚动条滚动一个单位增量。UNIT_INCREMENT属性设置此值。
     * 单击轨道可使滚动条移动块增量。BLOCK_INCREMENT属性定义此值。
     */
    public static class Main2 extends Application {
        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 500, 200);
            stage.setScene(scene);

            ScrollBar s1 = new ScrollBar();
            s1.setMax(500);
            s1.setMin(0);
            s1.setValue(100);
            s1.setUnitIncrement(30);
            s1.setBlockIncrement(35);

            s1.setOrientation(Orientation.VERTICAL);

            root.getChildren().add(s1);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }

    /**以下代码为滚动事件从滚动条添加事件处理程序。*/
    public static class Main4 extends Application {
        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 500, 200);
            stage.setScene(scene);

            ScrollBar s1 = new ScrollBar();

            s1.valueProperty().addListener((ObservableValue<? extends Number> ov,
                                            Number old_val, Number new_val) -> {
                System.out.println(-new_val.doubleValue());
            });
            root.getChildren().add(s1);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }
}
