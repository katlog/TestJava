package org.person.dfw.fx.practice.ui.widget;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Test;

public class ButtonMain {

    /**
     * 当用户单击按钮时，JavaFX Button类可以触发事件。
     * Button类扩展标记的类，它可以显示文本，图像，或两者兼而有之。
     *
     * 以下代码显示了如何向Button添加单击操作侦听器。
     */
    public static  class Main1 extends Application {
        public static void main(String[] args) {
            launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Hello World!");
            Button btn = new Button();
            btn.setText("Say Hello World");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Hello World!");
                }
            });

            StackPane root = new StackPane();
            root.getChildren().add(btn);
            primaryStage.setScene(new Scene(root, 300, 250));
            primaryStage.show();
        }
    }

    /**
     * 创建按钮
     *
     * 我们使用以下构造函数在JavaFX中创建一个Button。
     * 创建带有空文本标题的按钮。
     * Button button = new Button();
     * 创建具有指定文本的按钮。
     * Button button = new Button("OK");
     * 要创建带有文本和图标的按钮。
     */
    @Test
    public void createButton() {
        Image imageOk = new Image(getClass().getResourceAsStream("OK.png"));
        Button button = new Button("OK", new ImageView(imageOk));
    }

    /**
     * 按钮内容
     *
     * 创建JavaFX Button对象后，我们可以使用以下方法设置文本并安装图标。
         * setText(String text) - 设置按钮的文本标题
         * setGraphic(Node graphic) - 设置图标
     * 除了ImageView对象，我们可以使用javafx.scene.shape包中的形状作为Button中的图形元素。
     * setGraphicTextGap方法设置文本和图形内容之间的差距。
     * 以下代码将图像安装到按钮。
     */
    @Test
    public void buttonContent() {
        Image okImage = new Image(getClass().getResourceAsStream("OK.png"));
        Button button = new Button("OK", new ImageView(okImage));
        button.setGraphic(new ImageView(okImage));
    }

    /**
     * 按钮效果
     *
     * 我们可以将javafx.scene.effect包中的效果应用到按钮。
     * 以下代码将DropShadow效果应用于按钮。
     */
    @Test
    public void buttonEffect() {
        DropShadow shadow = new DropShadow();
        Button button = new Button("OK");
        button.setEffect(shadow);
        button.setEffect(null);//remove the effect
    }


    /**以下代码显示了如何为Button设置阴影效果。*/
    public static  class Main2 extends Application {
        DropShadow shadow = new DropShadow();
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setTitle("Button Sample");
            stage.setWidth(300);
            stage.setHeight(190);

            VBox vbox = new VBox();
            vbox.setLayoutX(20);
            vbox.setLayoutY(20);

            final Button button1 = new Button("Accept");

            button1.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            button1.setEffect(shadow);
                        }
                    });

            button1.addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            button1.setEffect(null);
                        }
                    });

            vbox.getChildren().add(button1);
            vbox.setSpacing(10);
            ((Group) scene.getRoot()).getChildren().add(vbox);

            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * 按钮样式
     *
     * 我们可以使用CSS样式来改变按钮的外观和感觉。
     * 我们在单独的CSS文件中定义样式，并通过使用getStyleClass方法应用CSS文件。
     * 下面的代码是一个CSS文件，它改变了按钮的字体和颜色。
     */
    public static class Main3 extends Application {

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setWidth(300);
            stage.setHeight(190);

            VBox vbox = new VBox();
            vbox.setLayoutX(20);
            vbox.setLayoutY(20);

            Button button1 = new Button("Accept");
            button1.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");


            vbox.getChildren().add(button1);
            vbox.setSpacing(10);
            ((Group)scene.getRoot()).getChildren().add(vbox);

            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * 按钮鼠标事件
     *
     * 下面的代码显示了如何处理Button的鼠标输入和输出事件。
     */
    public static class Main4 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setWidth(300);
            stage.setHeight(190);

            VBox vbox = new VBox();
            vbox.setLayoutX(20);
            vbox.setLayoutY(20);

            final Button button1 = new Button("OK");

            button1.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            System.out.println("mouse entered");
                        }
                    });

            button1.addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            System.out.println("mouse out");
                        }
                    });

            vbox.getChildren().add(button1);
            ((Group) scene.getRoot()).getChildren().add(vbox);

            stage.setScene(scene);
            stage.show();
        }
    }
}
