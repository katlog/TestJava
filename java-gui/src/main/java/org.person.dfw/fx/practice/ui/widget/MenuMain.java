package org.person.dfw.fx.practice.ui.widget;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.Test;

public class MenuMain {
    /**
     * 菜单是桌面应用程序选择选项的标准方法。
     * 菜单和菜单项可以具有用于选择选项的键组合，称为键盘快捷键。
     * 创建菜单和菜单项
     *
     * 我们必须创建一个菜单栏 javafx.scene.control.MenuBar 对象来保存 javafx.scene.control.Menu 对象。
     * 菜单对象可以包含Menu和javafx.scene.control.MenuItem对象。菜单可以包含其他菜单作为子菜单。MenuItems是Menu对象内的子选项。
     * 以下代码显示如何创建菜单栏并添加菜单和菜单项。
     */
    public static class Main1 extends Application {

        @Override
        public void start(Stage primaryStage) {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);

            MenuBar menuBar = new MenuBar();
            menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
            root.setTop(menuBar);

            // File menu - new, save, exit
            Menu fileMenu = new Menu("File");
            MenuItem newMenuItem = new MenuItem("New");
            MenuItem saveMenuItem = new MenuItem("Save");
            MenuItem exitMenuItem = new MenuItem("Exit");
            exitMenuItem.setOnAction(actionEvent -> Platform.exit());

            fileMenu.getItems().addAll(newMenuItem, saveMenuItem,
                    new SeparatorMenuItem(), exitMenuItem);

            Menu webMenu = new Menu("Web");
            CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
            htmlMenuItem.setSelected(true);
            webMenu.getItems().add(htmlMenuItem);

            CheckMenuItem cssMenuItem = new CheckMenuItem("CSS");
            cssMenuItem.setSelected(true);
            webMenu.getItems().add(cssMenuItem);

            Menu sqlMenu = new Menu("SQL");
            ToggleGroup tGroup = new ToggleGroup();
            RadioMenuItem mysqlItem = new RadioMenuItem("MySQL");
            mysqlItem.setToggleGroup(tGroup);

            RadioMenuItem oracleItem = new RadioMenuItem("Oracle");
            oracleItem.setToggleGroup(tGroup);
            oracleItem.setSelected(true);

            sqlMenu.getItems().addAll(mysqlItem, oracleItem,
                    new SeparatorMenuItem());

            Menu tutorialManeu = new Menu("Tutorial");
            tutorialManeu.getItems().addAll(
                    new CheckMenuItem("Java"),
                    new CheckMenuItem("JavaFX"),
                    new CheckMenuItem("Swing"));

            sqlMenu.getItems().add(tutorialManeu);

            menuBar.getMenus().addAll(fileMenu, webMenu, sqlMenu);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }

    /**
     * 特殊菜单项
     *
     * 要将选中的选项或单选按钮添加到菜单，我们可以使用以下选项MenuItem类的子类。
     * 以下列表显示了可用作菜单选项的可用MenuItem子类。
         * javafx.scene.control.CheckMenuItem
         * javafx.scene.control.RadioMenuItem
         * javafx.scene.control.CustomMenuItem
         * javafx.scene.control.SeparatorMenuItem
         * javafx.scene.control.Menu
     * CheckMenuItem菜单项类似于复选框控件，允许用户选择项目。
     * RadioMenuItem菜单项类似于RadioButton控件，允许用户从项目组中仅选择一个项目。
     * 要创建自定义菜单项，我们可以使用CustomMenuItem类。
     * SeparatorMenuItem是CustomMenuItem类型的派生类显示用于分隔菜单项的视线。
     * 使用CustomMenuItem将Slider添加到MenuItem
     */
    public static class Main extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Menus");
            Group root = new Group();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);

            MenuBar menuBar = new MenuBar();

            Menu menu = new Menu("File");
            menu.getItems().add(new MenuItem("New"));
            menu.getItems().add(new MenuItem("Save"));
            menu.getItems().add(new SeparatorMenuItem());
            menu.getItems().add(new MenuItem("Exit"));

            CustomMenuItem customMenuItem = new CustomMenuItem(new Slider());
            customMenuItem.setHideOnClick(false);
            menu.getItems().add(customMenuItem);

            menuBar.getMenus().add(menu);

            menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

            root.getChildren().add(menuBar);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**
     * 菜单事件处理程序
     *
     * 要向菜单项添加事件处理程序，我们可以使用setOnAction()方法，它接收一个类型为EventHandler <ActionEvent>的功能接口，它是在选择菜单项时调用的处理程序代码。
     */
    public static class Main3 extends Application {

        @Override
        public void start(Stage primaryStage) {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);

            MenuBar menuBar = new MenuBar();
            menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
            root.setTop(menuBar);

            Menu fileMenu = new Menu("File");
            MenuItem exitMenuItem = new MenuItem("Exit");
            fileMenu.getItems().add(exitMenuItem);
            exitMenuItem.setOnAction(actionEvent -> Platform.exit());

            menuBar.getMenus().addAll(fileMenu);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }

    /**
     * 关键助记符
     *
     * 标准菜单通常具有键盘助记符，以在不使用鼠标的情况下选择菜单项。
     * 用户可以点击 Alt 键和带下划线 _ 的字母来激活菜单，然后使用箭头键导航。
     * 要向菜单添加键助记符，我们使用String值和调用构造函数在菜单或菜单项的文本中，在所选字母前面放置一个下划线字符。
     * 然后我们将true传递给setMnemonicParsing(true)方法。
     * 以下代码创建一个使用字母“F"作为助记符的文件菜单。
     */
    public static class Main4 extends Application {

        @Override
        public void start(Stage primaryStage) {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 300, 250, Color.WHITE);

            MenuBar menuBar = new MenuBar();
            menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
            root.setTop(menuBar);

            Menu fileMenu = new Menu("_File");
            fileMenu.setMnemonicParsing(true);
            MenuItem exitMenuItem = new MenuItem("Exit");
            fileMenu.getItems().add(exitMenuItem);
            exitMenuItem.setOnAction(actionEvent -> Platform.exit());

            menuBar.getMenus().addAll(fileMenu);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }

    /**
     * 键组合
     * <p>
     * 键组合是用于选择菜单选项的键击的组合。键组合称为键盘快捷键。
     * 例如，在Windows平台上，Ctrl + S的组合键可以保存文件。在Mac OS平台上，组合键为Command + S.
     * Ctrl，Command，Alt，Shift和Meta等键称为修饰键。
     * 通常，修饰符与单个字母组合使用。
     * 要创建键组合，使用KeyCode组合对象并传递击键和修饰符。
     * 以下代码创建(Ctrl或Meta)+ S的键代码组合。
     */
    @Test
    public void keyCombination() {
        MenuItem saveItem = new MenuItem("_Save");
        saveItem.setMnemonicParsing(true);
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));

        /**
         * 代码使用KeyCombination.SHORTCUT_DOWN值作为键修饰符而不是CONTROL_DOWN或META_DOWN。 SHORTCUT_DOWN的值将启用应用程序跨平台。
         * 值CONTROL_DOWN和META_DOWN是系统依赖于Windows和MacOS平台，但SHORTCUT_DOWN适用于所有平台。
         */
    }

    /**
     * 上下文菜单
     * <p>
     * 上下文菜单是当用户右键单击鼠标按钮时显示的弹出菜单。
     * 要创建上下文菜单，使用 ContextMenu 类。ContextMenu菜单有一个getItems()。add()方法来添加菜单项。
     * 以下代码显示了使用菜单项(exitItem)实例化的上下文菜单:
     */
    @Test
    public void test() {
        // ContextMenu  contextFileMenu = new ContextMenu(exitItem);

        /**
         * 要响应鼠标右键单击，请添加事件处理程序以侦听右键单击事件调用上下文菜单的show()方法。
         * 以下代码设置了一个事件处理程序来显示和隐藏上下文菜单基于右或左鼠标点击。
         * hide()方法是通过主鼠标单击(左键单击)以删除上下文菜单。
         */
        // primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED,  (MouseEvent  me) ->  {
        //     if (me.getButton() == MouseButton.SECONDARY  || me.isControlDown())  {
        //         contextFileMenu.show(root, me.getScreenX(), me.getScreenY());
        //     }  else  {
        //         contextFileMenu.hide();
        //     }
        // });
    }

}
