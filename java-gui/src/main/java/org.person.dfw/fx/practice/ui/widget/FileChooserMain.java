package org.person.dfw.fx.practice.ui.widget;

import org.junit.Test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class FileChooserMain {
    /**
     * FileChooser允许用户导航文件系统并选择一个文件或文件夹。
     * FileChooser类位于javafx.stage包中。
     */

    /**
     * 打开文件
     *
     * 文件选择器可用作打开文件对话框，用于选择单个文件或多个文件，或作为文件保存对话框。
     * 以下代码创建一个FileChooser对象并设置其标题，然后显示到用户。
     */
    @Test
    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        // fileChooser.showOpenDialog(stage);
    }

    /**
     * 扩展过滤器
     *
     * 我们可以设置扩展过滤器来确定在文件选择器中打开哪些文件
     */
    @Test
    public void extendFilter() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    /**
     * 保存文件
     * <p>
     * FileChooser API允许用户指定文件名及其文件夹用于由应用程序保存的文件。
     * showSaveDialog方法打开保存对话框窗口。
     */
    public void save() {
        FileChooser fileChooser1 = new FileChooser();
        fileChooser1.setTitle("Save Image");
        // System.out.println(pic.getId());
        // File file = fileChooser1.showSaveDialog(stage);
        // System.out.println(file);
    }

    public static class Main1 extends Application {
        public static void main(String[] args) {
            launch(args);
        }
        @Override
        public void start(final Stage primaryStage) {
            Group root = new Group();

            Button buttonLoad = new Button("Load");
            buttonLoad.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent arg0) {
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                    fileChooser.getExtensionFilters().add(extFilter);
                    File file = fileChooser.showOpenDialog(primaryStage);
                    System.out.println(file);
                }
            });
            VBox vBox = VBoxBuilder.create()
                    .children(buttonLoad)
                    .build();
            root.getChildren().add(vBox);
            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.show();
        }

    }
}
