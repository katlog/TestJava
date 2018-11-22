package org.person.dfw.fx.practice.ui.widget;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.File;

public class HyperlinkMain {


    @Test
    public void create() {
        /**以下代码使用默认构造函数创建超链接对象。然后它设置一个URL作为文本标题，最后添加点击事件处理程序。*/

        Hyperlink link = new Hyperlink();
        link.setText("http://www.w3cschool.cn");
        link.setOnAction((ActionEvent e) -> {
            System.out.println("This link is clicked");
        });

        /**
         * setText实例方法定义超链接的文本标题。
         * 超链接类扩展了Labeled类，我们可以为超链接设置字体和填充。
         * 以下代码将图像添加到超链接控件。
         */
        Hyperlink hpl = new Hyperlink("www.w3cschool.cn");
        Image image1 = new Image(new File("a.jpg").toURI().toString(), 0, 100, false, false);
        hpl.setGraphic(new ImageView(image1));
    }

    public static class Main extends Application {

        @Override
        public void start(Stage stage) {
            stage.setTitle("HTML");
            stage.setWidth(500);
            stage.setHeight(500);
            Scene scene = new Scene(new Group());
            VBox root = new VBox();
            Hyperlink link = new Hyperlink("www.w3cschool.cn");


            root.getChildren().addAll(link);
            scene.setRoot(root);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**更改超链接的字体*/
    public static class Main2 extends Application {

        @Override
        public void start(Stage stage) {
            stage.setTitle("HTML");
            stage.setWidth(500);
            stage.setHeight(500);
            Scene scene = new Scene(new Group());
            VBox root = new VBox();

            Hyperlink hpl = new Hyperlink("w3cschool.cn");

            hpl.setFont(Font.font("Arial", 14));

            root.getChildren().addAll(hpl);

            scene.setRoot(root);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
