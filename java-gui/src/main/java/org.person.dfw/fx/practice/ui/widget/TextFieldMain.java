package org.person.dfw.fx.practice.ui.widget;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import org.junit.Test;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class TextFieldMain {

    @Test
    public void create() {
        // 创建文本字段
        //
        // 我们可以使用TextField类的构造函数来创建文本字段。
        // TextField只是一个带有光标的文本输入框，通常我们需要一个Label控件来告诉文本字段的目的。以下代码创建一个Label控件以标记对应的文本字段是用于名称输入。然后它创建一个TextField对象。之后，它使用HBox布局Label和TextField。
        Label label1 = new Label("Name:");
        TextField textField = new TextField ();
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(10);

        // 使用预定义文本创建文本域
        TextField textField1 = new TextField("w3cschool.cn");
    }


    /**
     * TextField中的文本
     *
     * 要从文本字段获取值，请调用getText方法。
     * 从TextInput的setPrefColumnCount方法设置文本字段的大小。通过设置一次可以显示的最大字符数。
     * 我们可以使用提示字幕通知用户文本字段的用途。setPromptText方法定义显示在文本字段中的字符串。无法通过getText方法获取提示文本。
     * 以下代码显示如何设置TextField的提示文本
     */
    public static class Main1 extends Application {

        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 300, 150);
            stage.setScene(scene);
            stage.setTitle("Text Field Sample");

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(5);
            grid.setHgap(5);

            scene.setRoot(grid);

            final TextField name = new TextField();
            name.setPromptText("Enter your first name.");
            name.setPrefColumnCount(10);
            name.getText();
            GridPane.setConstraints(name, 0, 0);
            grid.getChildren().add(name);


            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }


    /**
     * 以下列表有一些有用的方法，我们可以使用它们在文本字段中进行文本编辑。
         * copy() - 将所选文本设置为剪贴板。
         * cut() - 将所选文本设置为剪贴板并删除当前选择。
         * selectAll() - 选择文本输入中的所有文本。
         * paste() - 将剪贴板中的内容设置为此文本并替换当前选择
     */
    public static class Main2 extends Application {
        public static void main(String[] args) {
            launch(args);
        }
        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group(), 450, 250);

            TextField notification = new TextField ();
            notification.setText("Label");

            notification.clear();

            GridPane grid = new GridPane();
            grid.setVgap(4);
            grid.setHgap(10);
            grid.setPadding(new Insets(5, 5, 5, 5));
            grid.add(new Label("To: "), 0, 0);
            grid.add(notification, 1, 0);

            Group root = (Group) scene.getRoot();
            root.getChildren().add(grid);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**以下代码显示如何将字符串值从TextField绑定到Stage Title。*/
    public static class Main3 extends Application {
        StringProperty title = new SimpleStringProperty();

        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage stage) {
            TextField titleTextField;
            titleTextField = new TextField();
            titleTextField.setText("Stage Coach");
            titleTextField.setPrefColumnCount(15);

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(new Label("title:"));
            hBox.getChildren().add(titleTextField);

            Scene scene  = new Scene(hBox,270,270);
            title.bind(titleTextField.textProperty());

            stage.setScene(scene);
            stage.titleProperty().bind(title);


            stage.show();
        }
    }


    /**以下代码显示了如何将ContextMenu添加到TextField。*/
    public static class Main4 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group(), 450, 250);

            TextField notification = new TextField();

            final ContextMenu contextMenu = new ContextMenu();
            contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent e) {
                    System.out.println("showing");
                }
            });
            contextMenu.setOnShown(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent e) {
                    System.out.println("shown");
                }
            });

            MenuItem item1 = new MenuItem("About");
            item1.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    System.out.println("About");
                }
            });
            MenuItem item2 = new MenuItem("Preferences");
            item2.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    System.out.println("Preferences");
                }
            });
            contextMenu.getItems().addAll(item1, item2);

            notification.setContextMenu(contextMenu);
            GridPane grid = new GridPane();
            grid.setVgap(4);
            grid.setHgap(10);
            grid.setPadding(new Insets(5, 5, 5, 5));
            grid.add(new Label("To: "), 0, 0);
            grid.add(notification, 1, 0);

            Group root = (Group) scene.getRoot();
            root.getChildren().add(grid);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**覆盖replaceText和replaceSelection以创建自定义的TextField*/
    public static class Main5 extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            Group root = new Group();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);

            TextField field = new TextField() {
                @Override
                public void replaceText(int start, int end, String text) {
                    if (!text.matches("[a-z]")) {
                        super.replaceText(start, end, text);
                    }
                }

                @Override
                public void replaceSelection(String text) {
                    if (!text.matches("[a-z]")) {
                        super.replaceSelection(text);
                    }
                }
            };

            root.getChildren().add(field);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

}
