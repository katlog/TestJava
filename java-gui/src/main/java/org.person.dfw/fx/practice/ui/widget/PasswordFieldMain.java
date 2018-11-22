package org.person.dfw.fx.practice.ui.widget;

import org.junit.Test;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PasswordFieldMain {

    /**
     * 创建密码字段
     *
     * 以下代码使用来自PasswordField类的默认构造函数创建一个密码字段，然后为密码字段设置提示消息文本。
     * 提示消息在字段中显示为灰色文本，并为用户提供该字段是什么的提示，而不使用标签控件。
     *
     * PasswordField类有setText方法来为控件设置文本字符串。对于密码字段，指定的字符串由echo字符隐藏。默认情况下，echo字符是一个点。
     * 密码字段中的值可以通过getText方法获取。
     */
    @Test
    public void create() {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Your password");
    }

    public static class Main extends Application {

        final Label message = new Label("");

        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 260, 80);
            stage.setScene(scene);
            stage.setTitle("Password Field Sample");

            VBox vb = new VBox();
            vb.setPadding(new Insets(10, 0, 0, 10));
            vb.setSpacing(10);
            HBox hb = new HBox();
            hb.setSpacing(10);
            hb.setAlignment(Pos.CENTER_LEFT);

            Label label = new Label("Password");
            final PasswordField pb = new PasswordField();

            pb.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    if (!pb.getText().equals("abc")) {
                        message.setText("Your password is incorrect!");
                        message.setTextFill(Color.web("red"));
                    } else {
                        message.setText("Your password has been confirmed");
                        message.setTextFill(Color.web("black"));
                    }
                    pb.setText("");
                }
            });

            hb.getChildren().addAll(label, pb);
            vb.getChildren().addAll(hb, message);

            scene.setRoot(vb);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
