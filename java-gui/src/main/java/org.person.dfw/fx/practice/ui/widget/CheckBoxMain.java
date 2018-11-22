package org.person.dfw.fx.practice.ui.widget;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.junit.Test;

public class CheckBoxMain {


    @Test
    public void createCheckBox() {

        // 我们可以使用CheckBox中的构造函数来创建CheckBox的对象。
        // 创建不带标题的复选框
        CheckBox checkBox1 = new CheckBox();

        // 要创建带有字符串标题的复选框
        CheckBox checkBox = new CheckBox("Second");
        // 创建复选框后，我们可以更改其文本并将其选中。
        checkBox.setText("First");
        checkBox.setSelected(true);
    }

    /**
     * 复选框状态
     *
     * 可用CheckBox来表示三个状态:
         * Yes
         * No
         * Not Applicable
     * “不适用"状态是调用不确定。如果复选框不在不确定中，可以选择或不选择。选择表示是，未选择表示否。
     * 我们可以选择通过设置支持不确定CheckBox对象的allowIndeterminate属性。
     * 如果设置为true，则复选框应该循环选择所有三个状态:选中，取消选择和未定义。
     * 如果设置为false，复选框将循环选择和取消选择的状态。
     */

    public static class Main extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setTitle("Tooltip Sample");
            stage.setWidth(300);
            stage.setHeight(150);
            final CheckBox cb = new CheckBox("checkBox");
            final Tooltip tooltip = new Tooltip("$ tooltip");
            tooltip.setFont(new Font("Arial", 16));
            cb.setTooltip(tooltip);
            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                                    Boolean old_val, Boolean new_val) {
                    System.out.println(cb.isSelected());
                }
            });

            // cb.selectedProperty().addListener((ov, old_val, new_val) -> System.out.println(cb.isSelected()));

            ((Group) scene.getRoot()).getChildren().add(cb);

            stage.setScene(scene);
            stage.show();
        }
    }
}
