package org.person.dfw.fx.practice.ui.widget;
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ImageMain {
    /**
     * 使用javafx.scene.image.Image从本地文件系统或远程Web服务器加载图像。
     * 使用javafx.scene.image.ImageView节点显示图像。
     */

    public static class Main1 extends Application {
        @Override
        public void start(Stage primaryStage) {
            try {
                File file = new File("C:/Users/abc/myphoto.jpg");
                String localUrl = file.toURI().toURL().toString();
                // don"t load in the background
                Image localImage = new Image(localUrl, false);

                String remoteUrl = "/attachments/jimg/Firefox.png";
                // load in the background
                Image remoteImage = new Image(remoteUrl, true);

                System.out.println(localUrl);
                System.out.println(remoteUrl);

            } catch (MalformedURLException ex) {
                // error
            }
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**
     * 显示图像
     *
     * ImageView对象是一个可以显示图像的JavaFX Node对象。它可以有效果，执行变换和缩放图像。
     * 当ImageView节点应用特殊效果(如图像模糊)时，图像的像素数据被复制，计算并显示在ImageView节点上。
     * 以下代码显示如何创建ImageView对象。
         * Image  image  = new Image(url, true);
         * ImageView imageView = new ImageView(image);
     */
    public static class Main2 extends Application {

        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Title");
            Group root = new Group();
            Scene scene = new Scene(root, 600, 330, Color.WHITE);

            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(5));
            gridpane.setHgap(10);
            gridpane.setVgap(10);

            final ImageView imv = new ImageView();
            final Image image2 = new Image(Main2.class.getResourceAsStream("button.png"));
            imv.setImage(image2);

            final HBox pictureRegion = new HBox();

            pictureRegion.getChildren().add(imv);
            gridpane.add(pictureRegion, 1, 1);


            root.getChildren().add(gridpane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**下面的代码显示了如何旋转ImageView。*/
    public static class Main3 extends Application {

        @Override
        public void start(Stage stage) {
            stage.setTitle("HTML");
            stage.setWidth(500);
            stage.setHeight(500);
            Scene scene = new Scene(new Group());
            VBox root = new VBox();

            final ImageView selectedImage = new ImageView();
            Image image1 = new Image(Main3.class.getResourceAsStream("a.jpg"));
            selectedImage.setImage(image1);

            selectedImage.setRotate(90);

            root.getChildren().addAll(selectedImage);

            scene.setRoot(root);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**ImageView的setFitWidth*/
    public static class Main4 extends Application {

        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Title");
            Group root = new Group();
            Scene scene = new Scene(root, 600, 330, Color.WHITE);

            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(5));
            gridpane.setHgap(10);
            gridpane.setVgap(10);

            final ImageView imv = new ImageView();
            imv.setFitWidth(100);
            final Image image2 = new Image(Main4.class.getResourceAsStream("button.png"));
            imv.setImage(image2);

            final HBox pictureRegion = new HBox();

            pictureRegion.getChildren().add(imv);
            gridpane.add(pictureRegion, 1, 1);


            root.getChildren().add(gridpane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    /**setPreserveRatio for ImageView*/
    public static class Main5 extends Application {

        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Title");
            Group root = new Group();
            Scene scene = new Scene(root, 600, 330, Color.WHITE);

            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(5));
            gridpane.setHgap(10);
            gridpane.setVgap(10);

            final ImageView imv = new ImageView();
            imv.setPreserveRatio(true);
            final Image image2 = new Image(Main5.class.getResourceAsStream("button.png"));
            imv.setImage(image2);

            final HBox pictureRegion = new HBox();

            pictureRegion.getChildren().add(imv);
            gridpane.add(pictureRegion, 1, 1);


            root.getChildren().add(gridpane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

    }

    /**JavaFX图像缩放*/
    public static class Main6 extends Application {

        @Override
        public void start(Stage stage) throws Exception {
            ImageView imageView = new ImageView();
            ScrollPane scrollPane = new ScrollPane();
            DoubleProperty zoomProperty = new SimpleDoubleProperty(200);

            zoomProperty.addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable arg0) {
                    imageView.setFitWidth(zoomProperty.get() * 2);
                    imageView.setFitHeight(zoomProperty.get() * 3);
                }
            });
            scrollPane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
                @Override
                public void handle(ScrollEvent event) {
                    if (event.getDeltaY() > 0) {
                        zoomProperty.set(zoomProperty.get() * 1.2);
                    } else if (event.getDeltaY() < 0) {
                        zoomProperty.set(zoomProperty.get() / 1.1);
                    }
                }
            });
            imageView.setImage(new Image("http://yourImageURL"));
            imageView.preserveRatioProperty().set(true);
            scrollPane.setContent(imageView);
            stage.setScene(new Scene(scrollPane, 400, 300));
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

}
