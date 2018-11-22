package org.person.dfw.fx.practice.theme;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;

public class CSSMain {
    /**
     * JavaFX应用程序支持主题通过CSS设置。
     * 主题可以转换整个应用程序的外观，而不改变其功能。
     * 在JavaFX中，您可以创建，修改或使用现有主题来应用程序。
     */

    /**
     * 应用主题
     *
     * setUserAgentStylesheet(String URL)方法接受表示JavaFX CSS文件的有效URL字符串。
     * CSS文件捆绑在jar应用程序内，或者它们可以驻留在本地文件系统或远程Web服务器上。
     * 要从类路径加载CSS文件，请调用getClass()。getResource（“path / some_file.css”）。toExternalForm()方法。它会找到CSS文件并产生一个URL String。
     * 下面的代码加载sample.css文件作为当前的外观。
     * sample.css文件和Java类位于同一目录中，因此文件名前面不需要有路径。
         * Application.setUserAgentStylesheet(getClass().getResource("sample.css")
         * .toExternalForm());
     */
    public static class Main extends Application {
        public static void main(String[] args) {
            Application.launch(args);

        }

        @Override
        public void start(Stage primaryStage) {

            //setUserAgentStylesheet()方法将样式全局应用于应用程序拥有的所有场景。
            Application.setUserAgentStylesheet(getClass().getResource("sample.css")
                    .toExternalForm());


            Group root = new Group();
            Scene scene = new Scene(root, 300, 250);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**
     * 预定义皮肤
     * <p>
     * JavaFX 8目前包含两个样式表，Caspian和Modena，它们用作默认的跨平台外观和感觉皮肤。
     * 因为两个样式表是预定义的，您可以使用setUserAgentStylesheet（）方法在它们之间轻松切换。
     * 以下代码显示如何在Caspian和Modena外观样式表之间切换。
     */
    @Test
    public void toggle() {
        // Switch  to JavaFX 2.x"s CASPIAN  Look and  Feel.
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);

        // Switch  to JavaFX 8"s Modena Look and  Feel.
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);

        /**
         * 我们可以从jfxrt.jar文件中提取CSS文件caspian.css和modena.css，或者查看位于http://openjdk.java.net的JavaFX源代码。
         * 当调用setUserAgentStylesheet(null)时，默认样式表(Modena)将被自动加载并设置为当前外观。
         * 对于JavaFX 2.x，默认样式表将是Caspian。
         */
    }


    /**
     * 场景主题
     *
     * 通过调用scene.getStylesheets().add()方法，我们可以为单个场景及其子节点设置样式。
     * 我们将传递一个表示JavaFX CSS文件的URL字符串。
     * 以下代码调用setUserAgentStylesheet(null)加载Modena作为外观，然后通过调用getStylesheets()。add()方法设置场景的其他样式。
     *
     *
         * Application.setUserAgentStylesheet(null); // defaults to Modena
         *
         * // apply   custom  look  and  feel to the   scene.
         * scene.getStylesheets()
         * .add(getClass().getResource("my_cool_skin.css")
         * .toExternalForm());
     */

    /**
     * CSS样式
     *
     * 类似于HTML5使用CSS样式表的方式，在场景图上有与Node对象相关联的选择器或样式类。
     * 所有JavaFX场景图形节点分别有setId()，getStyleClass()。add()和setStyle()方法来应用可以改变节点的背景颜色，边框，笔画等的样式。
     * 选择器在场景图上定位JavaFX节点，然后我们可以使用CSS样式定义对它们进行样式化。
     * 两种类型的选择器类型是 id 和 class 。
     * id选择器是在场景节点上设置的唯一字符串名称。
     * 类选择器是一个字符串名称，可以作为标记添加到任何JavaFX节点。
     * 类选择器与Java类的概念无关。类选择器用于对节点进行分组以将它们与一个CSS样式定义一起设置样式。
     */


    public static class Login extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("JavaFX Welcome");

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));


            Text scenetitle = new Text("Welcome");
            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            scenetitle.setId("welcome-text");
            grid.add(scenetitle, 0, 0, 2, 1);

            Label userName = new Label("User Name:");
            grid.add(userName, 0, 1);

            TextField userTextField = new TextField();
            grid.add(userTextField, 1, 1);

            Label pw = new Label("Password:");
            grid.add(pw, 0, 2);

            PasswordField pwBox = new PasswordField();
            grid.add(pwBox, 1, 2);

            Button btn = new Button("Sign in");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn, 1, 4);

            final Text actiontarget = new Text();
            actiontarget.setFill(Color.FIREBRICK);
            actiontarget.setId("actiontarget");

            grid.add(actiontarget, 1, 6);

            btn.setOnAction(e -> {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Sign in button pressed");
            });

            Scene scene = new Scene(grid, 500, 375);
            primaryStage.setScene(scene);

            scene.getStylesheets().add(CSSMain.class.getResource("/gui/fx/theme/login.css").toExternalForm());
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
