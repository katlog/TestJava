package org.person.dfw.fx.practice.ui.widget;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import org.junit.Test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SeparatorMain {

    /**
     * Separator类表示水平或垂直分隔线。它分割元素，不产生任何动作。
     * 我们可以设计风格，应用视觉效果，并为分隔符设置动画。
     * 默认情况下，分隔符是水平的。我们可以使用setOrientation方法改变它的方向。
     * Separator类扩展了Node类。
     */

    @Test
    public void create() {
        // 创建水平分隔符
        Separator separator1 = new Separator();
        // 创建垂直分隔符
        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);
        // setMaxWidth方法定义了一个特定的宽度。
        // setValignment方法指定垂直位置。
    }

    public static class Main1 extends Application {

        Label caption = new Label("We");

        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 500, 200);
            stage.setScene(scene);

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(2);
            grid.setHgap(5);

            scene.setRoot(grid);

            caption.setFont(Font.font("Verdana", 20));

            GridPane.setConstraints(caption, 0, 0);
            GridPane.setColumnSpan(caption, 8);
            grid.getChildren().add(caption);

            final Separator sepHor = new Separator();
            sepHor.setValignment(VPos.CENTER);
            GridPane.setConstraints(sepHor, 0, 1);
            GridPane.setColumnSpan(sepHor, 7);
            grid.getChildren().add(sepHor);

            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }


}
