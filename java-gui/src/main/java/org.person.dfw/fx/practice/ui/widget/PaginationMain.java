package org.person.dfw.fx.practice.ui.widget;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.junit.Test;

public class PaginationMain {

    /**
     * 分页控件用于浏览多个页面。 我们典型地使用对网页的分页控制，例如博客。
     * 在博客页面的底部，我们可以看到一个矩形区域，作为一个数字列表来指示页面索引，以及一个下一个/上一个按钮来链接到下一个/上一个页面。
     */

    @Test
    public void create() {
        // 分页控件由页面内容和页面导航区域组成。
        // 创建具有不确定页计数和当前页索引等于零的分页控件
        Pagination pagination1 = new Pagination();
        // 要创建一个5页的分页控件，当前页索引等于零
        Pagination pagination2 = new Pagination(5);
        // 要创建一个5页的分页控件，当前所选索引等于2
        Pagination pagination3 = new Pagination(5, 2);
    }


    public static class Main extends Application {
        private Pagination pagination;

        public static void main(String[] args) throws Exception {
            launch(args);
        }

        public int itemsPerPage() {
            return 8;
        }

        public VBox createPage(int pageIndex) {
            VBox box = new VBox(5);
            int page = pageIndex * itemsPerPage();
            for (int i = page; i < page + itemsPerPage(); i++) {
                VBox element = new VBox();
                Hyperlink link = new Hyperlink("Item " + (i + 1));
                link.setVisited(true);
                Label text = new Label("Search results\nfor " + link.getText());
                element.getChildren().addAll(link, text);
                box.getChildren().add(element);
            }
            return box;
        }

        @Override
        public void start(final Stage stage) throws Exception {
            pagination = new Pagination(28, 0);
            pagination.setStyle("-fx-border-color:red;");
            pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));

            AnchorPane anchor = new AnchorPane();
            AnchorPane.setTopAnchor(pagination, 10.0);
            AnchorPane.setRightAnchor(pagination, 10.0);
            AnchorPane.setBottomAnchor(pagination, 10.0);
            AnchorPane.setLeftAnchor(pagination, 10.0);
            anchor.getChildren().addAll(pagination);
            Scene scene = new Scene(anchor);
            stage.setScene(scene);
            stage.setTitle("PaginationSample");
            stage.show();
        }
    }

    /**将分段文本添加到分页控件*/
    public static class Main2 extends Application {

        private Pagination pagination;
        final String[] textPages = new String[]{
                "this is a test 1",
                "this is a test 2",
                "this is a test 3",
                "this is a test 4",
                "this is a test 5",
                "this is a test 6",
                "this is a test 7",
                "this is a test 8",
                "this is a test 8",
        };

        public static void main(String[] args) throws Exception {
            launch(args);
        }

        public int itemsPerPage() {
            return 1;
        }

        public VBox createPage(int pageIndex) {
            VBox box = new VBox(5);
            int page = pageIndex * itemsPerPage();
            for (int i = page; i < page + itemsPerPage(); i++) {
                TextArea text = new TextArea(textPages[i]);
                text.setWrapText(true);
                box.getChildren().add(text);
            }
            return box;
        }

        @Override
        public void start(final Stage stage) throws Exception {
            pagination = new Pagination(28, 0);
            pagination.setPageFactory((Integer pageIndex) -> {
                if (pageIndex >= textPages.length) {
                    return null;
                } else {
                    return createPage(pageIndex);
                }
            });

            AnchorPane anchor = new AnchorPane();
            AnchorPane.setTopAnchor(pagination, 10.0);
            AnchorPane.setRightAnchor(pagination, 10.0);
            AnchorPane.setBottomAnchor(pagination, 10.0);
            AnchorPane.setLeftAnchor(pagination, 10.0);
            anchor.getChildren().addAll(pagination);
            Scene scene = new Scene(anchor, 400, 250);
            stage.setScene(scene);
            stage.setTitle("PaginationSample");
            stage.show();
        }
    }

    /**添加未确定大小的内容*/
    public static class Main3 extends Application {

        private Pagination pagination;
        String[] fonts = new String[]{};

        public static void main(String[] args) throws Exception {
            launch(args);
        }

        public int itemsPerPage() {
            return 15;
        }

        public VBox createPage(int pageIndex) {
            VBox box = new VBox(5);
            int page = pageIndex * itemsPerPage();
            for (int i = page; i < page + itemsPerPage(); i++) {
                Label font = new Label(fonts[i]);
                box.getChildren().add(font);
            }
            return box;
        }

        @Override
        public void start(final Stage stage) throws Exception {
            fonts = Font.getFamilies().toArray(fonts);
            pagination = new Pagination(fonts.length/itemsPerPage(), 0);
            pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));

            AnchorPane anchor = new AnchorPane();
            AnchorPane.setTopAnchor(pagination, 10.0);
            AnchorPane.setRightAnchor(pagination, 10.0);
            AnchorPane.setBottomAnchor(pagination, 10.0);
            AnchorPane.setLeftAnchor(pagination, 10.0);
            anchor.getChildren().addAll(pagination);
            Scene scene = new Scene(anchor, 400, 450);
            stage.setScene(scene);
            stage.show();
        }
    }


}
