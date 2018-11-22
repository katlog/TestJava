package org.person.dfw.fx.practice.web;

import javafx.scene.web.WebEvent;
import org.junit.Test;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
public class WebViewMain {
    /**
     * JavaFX提供了一个GUI WebView(javafx.scene.web.WebView)节点，以将HTML5内容呈现到场景图形上。
     * WebView节点是一个小型浏览器。
     * 我们可以使用以下代码加载网页并显示它。
     */
    @Test
    public void loadHtml() {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("http://mySite.com");

    }


    public static class Main extends Application {
        @Override
        public void start(final Stage stage) {
            stage.setWidth(400);
            stage.setHeight(500);
            Scene scene = new Scene(new Group());


            final WebView browser = new WebView();
            final WebEngine webEngine = browser.getEngine();

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(browser);

            webEngine.getLoadWorker().stateProperty()
                    .addListener(new ChangeListener<State>() {
                        @Override
                        public void changed(ObservableValue ov, State oldState, State newState) {

                            if (newState == State.SUCCEEDED) {
                                stage.setTitle(webEngine.getLocation());
                            }

                        }
                    });
            webEngine.load("http://www.w3cschool.cn");

            scene.setRoot(scrollPane);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }


    /**
     * WebEvents
     * <p>
     * JavaFX Web API也遵循事件驱动的编程模型。
     * 网页中的以下JavaScript将弹出一个包含浏览器窗口消息的警报对话框。
     * <script>
     * alert("JavaFX is  Awesome");
     * </script>
     * 当在JavaFX WebView节点中执行代码时，不会弹出本机对话框窗口。但是，OnAlert事件作为javafx.scene.web.WebEvent对象引发。
     * 我们可以处理这些事件。要设置处理程序，请使用带有类型为WebEvent的入站参数的setOnAlert()方法。
     * browser.getEngine().setOnAlert((WebEvent<String> wEvent) -> {
     * System.out.println("Alert Event  -  Message:  " + wEvent.getData());
     * });
     */

    public static class Main2 extends Application {
        @Override
        public void start(final Stage stage) {
            stage.setWidth(400);
            stage.setHeight(500);
            Scene scene = new Scene(new Group());

            final WebView browser = new WebView();
            final WebEngine webEngine = browser.getEngine();

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(browser);

            browser.getEngine().setOnAlert((WebEvent<String> wEvent) -> {
                System.out.println("Alert Event  -  Message:  " + wEvent.getData());
            });

            webEngine.load("http://www.w3cschool.cn");

            scene.setRoot(scrollPane);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**
     * 管理网络历史记录
     */
    public static class Main3 extends Application {
        @Override
        public void start(final Stage stage) {
            stage.setWidth(400);
            stage.setHeight(500);
            Scene scene = new Scene(new Group());

            final WebView browser = new WebView();
            final WebEngine webEngine = browser.getEngine();

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(browser);

            browser.getEngine().setOnAlert((WebEvent<String> wEvent) -> {
                System.out.println("Alert Event  -  Message:  " + wEvent.getData());
            });

            webEngine.load("http://www.w3cschool.cn");


            final WebHistory history = webEngine.getHistory();
            history.getEntries().addListener((ListChangeListener<Entry>) c -> {
                c.next();
                for (Entry e : c.getRemoved()) {
                    System.out.println(e.getUrl());
                }
                for (Entry e : c.getAddedSubList()) {
                    System.out.println(e.getUrl());
                }
            }
            );

            history.go(0);
            scene.setRoot(scrollPane);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**
     * 打印HTML内容
     *
     * 以下代码显示如何从WebEngine进行打印。
     *     PrinterJob job = PrinterJob.createPrinterJob();
     *     if (job != null) {
     *         webEngine.print(job);
     *         job.endJob();
     *     }
     */
    public static class Main4 extends Application {
        @Override
        public void start(final Stage stage) {
            stage.setWidth(400);
            stage.setHeight(500);
            Scene scene = new Scene(new Group());

            VBox root = new VBox();

            final WebView browser = new WebView();
            final WebEngine webEngine = browser.getEngine();

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(browser);

            webEngine.getLoadWorker().stateProperty()
                    .addListener((ov, oldState, newState) -> {
                        if (newState == State.SUCCEEDED) {
                            PrinterJob job = PrinterJob.createPrinterJob();
                            if (job != null) {
                                webEngine.print(job);
                                job.endJob();
                            }
                        }
                    });
            webEngine.load("http://www.w3cschool.cn");

            root.getChildren().addAll(scrollPane);
            scene.setRoot(root);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**使用WebView显示HTML*/
    public static class Main5 extends Application {

        @Override
        public void start(Stage stage) {
            stage.setTitle("HTML");
            stage.setWidth(500);
            stage.setHeight(500);
            Scene scene = new Scene(new Group());

            VBox root = new VBox();

            final WebView browser = new WebView();
            final WebEngine webEngine = browser.getEngine();

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(browser);
            webEngine.loadContent("<b>asdf</b>");

            root.getChildren().addAll(scrollPane);
            scene.setRoot(root);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    /**使用CSS更改WebView背景*/
    public static class Main6 extends Application {

        @Override
        public void start(Stage stage) {
            stage.setTitle("HTML");
            stage.setWidth(500);
            stage.setHeight(500);
            Scene scene = new Scene(new Group());

            VBox root = new VBox();

            final WebView browser = new WebView();
            final WebEngine webEngine = browser.getEngine();


            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setStyle("-fx-background-color: white");

            scrollPane.setContent(browser);
            webEngine.loadContent("<b>asdf</b>");


            root.getChildren().addAll(scrollPane);
            scene.setRoot(root);

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
