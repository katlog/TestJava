package org.person.dfw.fx.practice.ui.widget;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Test;

public class RadionButtonMain {
    /**
     * 单选按钮通常组合在一起以允许用户进行单选，即用户只能在单选按钮列表中选择一个项目。例如，当选择鞋子尺寸时，我们通常从列表中选择一个尺寸。
     * 选择或取消选择单选按钮。
     * 以下代码显示，当放置在ToggleGroup中时，只能选择一个RadioButton。
     */
    public static class Main1 extends Application {
        @Override
        public void start(Stage stage) {
            HBox root = new HBox();
            Scene scene = new Scene(root, 300, 150);
            stage.setScene(scene);
            stage.setTitle("");

            ToggleGroup group = new ToggleGroup();
            RadioButton button1 = new RadioButton("select first");
            button1.setToggleGroup(group);
            button1.setSelected(true);
            RadioButton button2 = new RadioButton("select second");
            button2.setToggleGroup(group);

            root.getChildren().add(button1);
            root.getChildren().add(button2);

            scene.setRoot(root);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**
     * 创建单选按钮
     *
     * javafx.scene.control包中的RadioButton类有两个构造函数。
     * 要为其标签创建一个带有空字符串的单选按钮，然后再设置标签。
     *
     */
    @Test
    public void createRadioButton() {
        RadioButton rb = new RadioButton();
    //setting a text label
        rb.setText("Size 9");

        //要创建具有指定标签的单选按钮
        RadioButton rb2 = new RadioButton("Size 9");
        //带有true参数的setSelected()方法可以显式选择单选按钮。
        //isSelected 方法返回用户是否选择了特定单选按钮。
        //setGraphic 方法可以为RadioButton安装一个图像。
        Image image = new Image(getClass().getResourceAsStream("Size.jpg"));
        RadioButton rb1 = new RadioButton("Size 9");
        rb1.setGraphic(new ImageView(image));

    }

    /**
     * 单选按钮组
     * <p>
     * 单选按钮通常用在组中。
     * 我们可以添加单选按钮到ToggleGroup对象，它将管理它们，使得一次只能选择一个单选按钮。
     * 下面的代码创建一个切换组和三个单选按钮，然后将每个单选按钮添加到切换组，并指定应该选择哪个按钮。
     */
    @Test
    public void radioButtonGroup() {
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Size 9");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Size 10");
        rb2.setToggleGroup(group);

        RadioButton rb3 = new RadioButton("Size 11");
        rb3.setToggleGroup(group);
    }

    /**
     * 单选按钮事件
     *
     * 我们通过ToggleGroup处理单选按钮选择的事件。更改侦听器添加到ToggleGroup。
     * 为每个单选按钮分配了用户数据。
     * ChangeListener对象检查组中的选定项目。所选单选按钮从getSelectedToggle方法返回。然后我们通过调用getUserData方法获取用户数据。
     *
     * 单选按钮焦点
     *
     * 在单选按钮组中，默认情况下第一个按钮最初具有焦点。
     * 当使用setSelected方法选择单选按钮时，我们还应该使用requestFocus函数将焦点更改为所选单选按钮。
     * rb.setSelected(true);
     * rb.requestFocus();
     */
    public static class Main3 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setWidth(250);
            stage.setHeight(150);

            final ToggleGroup group = new ToggleGroup();

            RadioButton rb1 = new RadioButton("A");
            rb1.setToggleGroup(group);
            rb1.setUserData("A");

            RadioButton rb2 = new RadioButton("B");
            rb2.setToggleGroup(group);
            rb2.setUserData("B");

            RadioButton rb3 = new RadioButton("C");
            rb3.setToggleGroup(group);
            rb3.setUserData("C");

            group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                    if (group.getSelectedToggle() != null) {
                        System.out.println(group.getSelectedToggle().getUserData().toString());
                    }
                }
            });

            HBox hbox = new HBox();
            VBox vbox = new VBox();

            vbox.getChildren().add(rb1);
            vbox.getChildren().add(rb2);
            vbox.getChildren().add(rb3);
            vbox.setSpacing(10);

            hbox.getChildren().add(vbox);
            hbox.setSpacing(50);
            hbox.setPadding(new Insets(20, 10, 10, 20));

            ((Group) scene.getRoot()).getChildren().add(hbox);
            stage.setScene(scene);
            stage.show();
        }
    }
}
