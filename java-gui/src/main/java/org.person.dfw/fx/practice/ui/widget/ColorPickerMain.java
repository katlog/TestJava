package org.person.dfw.fx.practice.ui.widget;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;

public class ColorPickerMain {


    @Test
    public void create() {
        // 以下代码使用空构造函数和创建颜色选择器控件颜色选择器控件使用默认颜色，即WHITE。
        ColorPicker colorPicker1 = new ColorPicker();
        // 我们还可以提供颜色常量作为当前选择的颜色。
        ColorPicker colorPicker2 = new ColorPicker(Color.BLUE);
        // 我们还可以提供网络颜色值作为当前选择的颜色
        ColorPicker colorPicker3 = new ColorPicker(Color.web("#EEEEEE"));
    }


    /**
     * 颜色选择器控件使用户能够从可用范围中选择颜色，或通过指定RGB或HSB组合设置附加颜色。
     * JavaFX ColorPicker控件具有颜色选择器，调色板和自定义颜色对话框窗口。
     *
     * 自定义颜色
     *
     * getCustomColors()方法返回创建的自定义颜色Color对象的ObservableList。
     * ObservableList<Color> customColors = colorPicker.getCustomColors();
     * colorPicker.setValue(customColors.get(index));
     */
    public static class Main extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new HBox(20), 400, 100);
            HBox box = (HBox) scene.getRoot();
            final ColorPicker colorPicker = new ColorPicker();
            colorPicker.setValue(Color.RED);

            final Text text = new Text("Color picker:");
            text.setFill(colorPicker.getValue());

            colorPicker.setOnAction((ActionEvent t) -> {
                text.setFill(colorPicker.getValue());
            });

            box.getChildren().addAll(colorPicker, text);

            stage.setScene(scene);
            stage.show();
        }
    }
}
