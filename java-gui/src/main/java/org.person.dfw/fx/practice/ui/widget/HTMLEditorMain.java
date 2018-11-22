package org.person.dfw.fx.practice.ui.widget;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class HTMLEditorMain {

    /**HTMLEditor控件是一个富文本编辑器*/
    public static class Main1 extends Application {

        @Override
        public void start(Stage stage) {
            HTMLEditor htmlEditor = new HTMLEditor();
            htmlEditor.setPrefHeight(245);
            Scene scene = new Scene(htmlEditor);
            stage.setScene(scene);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }

    /**
     * HTML内容
     *
     * 要将内容设置为HTMLEditor类，请使用setHtmlText方法。
     */
    public static class Main2 extends Application {

        @Override
        public void start(Stage stage) {
            HTMLEditor htmlEditor = new HTMLEditor();
            htmlEditor.setPrefHeight(245);
            String INITIAL_TEXT = "Lorem ipsum dolor sit "
                    + "amet, consectetur adipiscing elit. Nam tortor felis, pulvinar "
                    + "aliquam sagittis gravida eu dolor. Etiam sit amet ipsum "
                    + "sem.";
            htmlEditor.setHtmlText(INITIAL_TEXT);
            Scene scene = new Scene(htmlEditor);
            stage.setScene(scene);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }

    /**
     * 格式
     *
     * 我们可以使用此字符串中的HTML标记为最初渲染的内容应用特定的格式。
     */

    public  static  class Main3 extends Application {

        @Override
        public void start(Stage stage) {
            HTMLEditor htmlEditor = new HTMLEditor();
            htmlEditor.setPrefHeight(245);
            String INITIAL_TEXT = "Lorem ipsum dolor sit "
                    + "amet, consectetur adipiscing elit. <i>Nam tortor felis</i>, pulvinar "
                    + "<UL><li>a</li><li>a</li><li>a</li></UL>"
                    + "aliquam sagittis gravida <b>eu dolor</b>. Etiam sit amet ipsum "
                    + "sem.";
            htmlEditor.setHtmlText(INITIAL_TEXT);
            Scene scene = new Scene(htmlEditor);
            stage.setScene(scene);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }

    /**获取HTML内容*/
    public static class Main4 extends Application {

        @Override
        public void start(Stage stage) {
            HTMLEditor htmlEditor = new HTMLEditor();
            htmlEditor.setPrefHeight(245);
            String INITIAL_TEXT = "Lorem ipsum dolor sit "
                    + "amet, consectetur adipiscing elit. <i>Nam tortor felis</i>, pulvinar "
                    + "<UL><li>a</li><li>a</li><li>a</li></UL>"
                    + "aliquam sagittis gravida <b>eu dolor</b>. Etiam sit amet ipsum "
                    + "sem.";
            htmlEditor.setHtmlText(INITIAL_TEXT);

            Button showHTMLButton = new Button("Produce HTML Code");

            showHTMLButton.setOnAction((ActionEvent arg0) -> {
                System.out.println(htmlEditor.getHtmlText());
            });

            VBox vbox = new VBox();
            vbox.getChildren().addAll(htmlEditor,showHTMLButton);
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }
}
