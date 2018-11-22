package org.person.dfw.fx.practice.ui.widget;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
public class TreeTableViewMain {

    /**
     * 分类
     *
     * 我们可以通过点击列标题对数据进行排序。
     * 设置列的降序排序模式
     * aColumn.setSortType(TreeTableColumn.SortType.DESCENDING);
     * 设置列的升序排序模式
     * aColumn.setSortType(TreeTableColumn.SortType.ASCENDING);
     * 将排序模式应用于所有树项目
     * treeTableView.setSortMode(TreeSortMode.ALL_DESCENDANTS);
     * 仅将排序模式应用于第一级节点
     * treeTableView.setSortMode(TreeSortMode.ONLY_FIRST_LEVEL);
     */

    /**
     * 管理选择模式
     *
     * TreeTableView类的默认选择模型是SelectionMode.SINGLE。
     * 要启用树项目和单元格的多重选择，请使用setSelectionModel和setCellSelectionEnabled方法的组合。
     * 启用单元格的多重选择
     * treeTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     * treeeTableView.getSelectionModel().setCellSelectionEnabled(true);
     */

    public static class Main1 extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }
        @Override
        public void start(Stage stage) {
            final Scene scene = new Scene(new Group(), 200, 400);
            Group sceneRoot = (Group) scene.getRoot();

            TreeItem<String> childNode1 = new TreeItem<>("Node 1");
            TreeItem<String> childNode2 = new TreeItem<>("Node 2");
            TreeItem<String> childNode3 = new TreeItem<>("Node 3");

            TreeItem<String> root = new TreeItem<>("Root");
            root.setExpanded(true);

            root.getChildren().setAll(childNode1, childNode2, childNode3);

            TreeTableColumn<String, String> column = new TreeTableColumn<>("Column");
            column.setPrefWidth(150);

            column.setCellValueFactory((CellDataFeatures<String, String> p) -> new ReadOnlyStringWrapper(
                    p.getValue().getValue()));

            TreeTableView<String> treeTableView = new TreeTableView<>(root);
            treeTableView.getColumns().add(column);
            sceneRoot.getChildren().add(treeTableView);
            stage.setScene(scene);
            stage.show();
        }
    }

    public static class Main2 extends Application {

        List<Employee> employees = Arrays.<Employee> asList(new Employee(
                "Ethan Williams", "ethan.williams@example.com"), new Employee(
                "Emma Jones", "emma.jones@example.com"), new Employee("Michael Brown",
                "michael.brown@example.com"), new Employee("Anna Black",
                "anna.black@example.com"), new Employee("Rodger York",
                "roger.york@example.com"), new Employee("Susan Collins",
                "susan.collins@example.com"));

        final TreeItem<Employee> root = new TreeItem<>(new Employee(
                "Sales Department", ""));

        public static void main(String[] args) {
            Application.launch(Main2.class, args);
        }

        @Override
        public void start(Stage stage) {
            root.setExpanded(true);
            employees.stream().forEach((employee) -> {
                root.getChildren().add(new TreeItem<>(employee));
            });
            Scene scene = new Scene(new Group(), 400, 400);
            Group sceneRoot = (Group) scene.getRoot();

            TreeTableColumn<Employee, String> empColumn = new TreeTableColumn<>(
                    "Employee");
            empColumn.setPrefWidth(150);
            empColumn
                    .setCellValueFactory((
                            CellDataFeatures<Employee, String> param) -> new ReadOnlyStringWrapper(
                            param.getValue().getValue().getName()));

            TreeTableColumn<Employee, String> emailColumn = new TreeTableColumn<>(
                    "Email");
            emailColumn.setPrefWidth(190);
            emailColumn
                    .setCellValueFactory((
                            CellDataFeatures<Employee, String> param) -> new ReadOnlyStringWrapper(
                            param.getValue().getValue().getEmail()));

            TreeTableView<Employee> treeTableView = new TreeTableView<>(root);
            treeTableView.getColumns().setAll(empColumn, emailColumn);
            sceneRoot.getChildren().add(treeTableView);
            stage.setScene(scene);
            stage.show();
        }

        public class Employee {

            private SimpleStringProperty name;
            private SimpleStringProperty email;

            public SimpleStringProperty nameProperty() {
                if (name == null) {
                    name = new SimpleStringProperty(this, "name");
                }
                return name;
            }

            public SimpleStringProperty emailProperty() {
                if (email == null) {
                    email = new SimpleStringProperty(this, "email");
                }
                return email;
            }

            private Employee(String name, String email) {
                this.name = new SimpleStringProperty(name);
                this.email = new SimpleStringProperty(email);
            }

            public String getName() {
                return name.get();
            }

            public void setName(String fName) {
                name.set(fName);
            }

            public String getEmail() {
                return email.get();
            }

            public void setEmail(String fName) {
                email.set(fName);
            }
        }
    }

    /**
     * 改变视觉外观
     *
     * treeTableView.setTableMenuButtonVisible(true)启用表格菜单按钮，以便用户可以切换表列的可见性。 该方法将“+”按钮添加到表头。
     *
     * 我们可以通过使用TreeTableView类的setShowRoot方法显示或隐藏根树项目。
     * treeTableView.setShowRoot(false);
     */
    public static class Main extends Application {

        List<Employee> employees = Arrays.<Employee> asList(new Employee(
                "Ethan Williams", "ethan.williams@example.com"), new Employee(
                "Emma Jones", "emma.jones@example.com"), new Employee("Michael Brown",
                "michael.brown@example.com"), new Employee("Anna Black",
                "anna.black@example.com"), new Employee("Rodger York",
                "roger.york@example.com"), new Employee("Susan Collins",
                "susan.collins@example.com"));

        final TreeItem<Employee> root = new TreeItem<>(new Employee(
                "Sales Department", ""));

        public static void main(String[] args) {
            Application.launch(Main.class, args);
        }

        @Override
        public void start(Stage stage) {
            root.setExpanded(true);
            employees.stream().forEach((employee) -> {
                root.getChildren().add(new TreeItem<>(employee));
            });
            Scene scene = new Scene(new Group(), 400, 400);
            Group sceneRoot = (Group) scene.getRoot();

            TreeTableColumn<Employee, String> empColumn = new TreeTableColumn<>(
                    "Employee");
            empColumn.setPrefWidth(150);
            empColumn
                    .setCellValueFactory((
                            CellDataFeatures<Employee, String> param) -> new ReadOnlyStringWrapper(
                            param.getValue().getValue().getName()));

            TreeTableColumn<Employee, String> emailColumn = new TreeTableColumn<>(
                    "Email");
            emailColumn.setPrefWidth(190);
            emailColumn
                    .setCellValueFactory((
                            CellDataFeatures<Employee, String> param) -> new ReadOnlyStringWrapper(
                            param.getValue().getValue().getEmail()));

            TreeTableView<Employee> treeTableView = new TreeTableView<>(root);
            treeTableView.getColumns().setAll(empColumn, emailColumn);

            treeTableView.setTableMenuButtonVisible(true);


            sceneRoot.getChildren().add(treeTableView);
            stage.setScene(scene);
            stage.show();
        }

        public class Employee {

            private SimpleStringProperty name;
            private SimpleStringProperty email;

            public SimpleStringProperty nameProperty() {
                if (name == null) {
                    name = new SimpleStringProperty(this, "name");
                }
                return name;
            }

            public SimpleStringProperty emailProperty() {
                if (email == null) {
                    email = new SimpleStringProperty(this, "email");
                }
                return email;
            }

            private Employee(String name, String email) {
                this.name = new SimpleStringProperty(name);
                this.email = new SimpleStringProperty(email);
            }

            public String getName() {
                return name.get();
            }

            public void setName(String fName) {
                name.set(fName);
            }

            public String getEmail() {
                return email.get();
            }

            public void setEmail(String fName) {
                email.set(fName);
            }
        }
    }

}
