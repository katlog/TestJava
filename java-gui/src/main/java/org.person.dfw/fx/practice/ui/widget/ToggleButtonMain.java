package org.person.dfw.fx.practice.ui.widget;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.Test;

public class ToggleButtonMain {

    /**
     * 切换按钮具有两种状态：选择或未选择。
     * 我们通常将两个或多个切换按钮组合成一个组，并允许用户只选择一个按钮或不选择。
     */

    // 创建切换按钮
    @Test
    public void createToggleButton() {

        // 我们可以使用ToggleButton类的三个构造函数创建一个切换按钮。
        // 要创建没有任何字幕或图标的切换按钮
        ToggleButton tb = new ToggleButton();
        // 要创建带有文字说明的切换按钮
        ToggleButton tb1 = new ToggleButton("Press me");
        // 要创建带有文字说明和图标的切换按钮
        Image image = new Image(getClass().getResourceAsStream("icon.png"));
        ToggleButton tb2 = new ToggleButton("Press me", new ImageView(image));
        // setText方法可以将文本设置为ToggleButton和setGraphic方法可以将图像安装到ToggleButton。
    }

    /**切换组不强制选择至少一个按钮。单击所选的切换按钮可取消选择切换按钮。*/
    @Test
    public void toggle() {
        ToggleGroup group = new ToggleGroup();

        ToggleButton tb1 = new ToggleButton("High");
        tb1.setToggleGroup(group);
        tb1.setSelected(true);

        ToggleButton tb2 = new ToggleButton("Medium");
        tb2.setToggleGroup(group);

        ToggleButton tb3 = new ToggleButton("Low");
        tb3.setToggleGroup(group);
    }


    /**
     * 切换按钮行为
     *
     * setUserData方法将用户值与切换按钮相关联。
     * ChangeListener对象检查组中所选的切换。如果没有选择任何开关按钮，则输出默认值。
     * 如果选择其中一个切换按钮，getSelectedToggle和getUserData方法返回用户定义的值。
     */
    @Test
    public void toggleAction() {
        ToggleButton tb1 = new ToggleButton("High");
        ToggleButton tb2 = new ToggleButton("Medium");
        ToggleButton tb3 = new ToggleButton("Low");

        ToggleGroup group = new ToggleGroup();

        group.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) -> {
            if (new_toggle == null)
                System.out.println("default value");
            else
                System.out.println(group.getSelectedToggle().getUserData());
        });

    }

    /**
     * 样式切换按钮
     *
     * 我们可以将CSS样式应用于切换按钮。
     * 首先，我们在myStyle.css文件中声明切换按钮的样式。
     *
     *.toggle-button1{
     *     -fx-font: 30 arial;
     *     -fx-base: green;
     * }
     *
     * .toggle-button2{
     *     -fx-font: 25 arial;
     *     -fx-base: blue;
     * }
     *
     * .toggle-button3{
     *     -fx-font: 30 arial;
     *     -fx-base: red;
     * }
     */

    public static class cssToggleAction extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            ToggleButton tb1 = new ToggleButton("High");
            ToggleButton tb2 = new ToggleButton("Medium");
            ToggleButton tb3 = new ToggleButton("Low");
            ToggleGroup group = new ToggleGroup();

            Scene scene = new Scene(new Group());

            scene.getStylesheets().add("myStyle.css");

            tb1.getStyleClass().add("toggle-button1");
            tb2.getStyleClass().add("toggle-button2");
            tb3.getStyleClass().add("toggle-button3");
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

}
