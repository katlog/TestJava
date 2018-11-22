package org.person.dfw.fx.practice.ui.widget;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TreeViewMain {
    public static class Main1 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            TreeItem<String> rootItem = new TreeItem<>("Root");
            rootItem.setExpanded(true);

            TreeItem<String> item = new TreeItem<>("A");
            rootItem.getChildren().add(item);

            item = new TreeItem<>("B");
            rootItem.getChildren().add(item);

            TreeView<String> tree = new TreeView<>(rootItem);
            StackPane root = new StackPane();
            root.getChildren().add(tree);
            primaryStage.setScene(new Scene(root, 300, 250));
            primaryStage.show();
        }
    }

    /**
     * 创建树视图
     *
     * 当创建树视图时，通常从构建根的TreeItem对象开始。 然后将根添加到树视图，并创建其他树项目对象并将它们添加到根。
     * 可通过setGraphic方法或使用它的构造函数来创建一个图形图标到树项目。 图标的建议大小为16x16。
     * 对根项目调用的setExpanded方法定义树视图项目的初始外观。
     * 以下代码从本地文件系统创建动态树。
     */
    public static  class Main extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        private TreeItem<File> createNode(final File f) {
            return new TreeItem<File>(f) {
                private boolean isLeaf;
                private boolean isFirstTimeChildren = true;
                private boolean isFirstTimeLeaf = true;

                @Override
                public ObservableList<TreeItem<File>> getChildren() {
                    if (isFirstTimeChildren) {
                        isFirstTimeChildren = false;
                        super.getChildren().setAll(buildChildren(this));
                    }
                    return super.getChildren();
                }

                @Override

                public boolean isLeaf() {
                    if (isFirstTimeLeaf) {
                        isFirstTimeLeaf = false;
                        File f = (File) getValue();
                        isLeaf = f.isFile();
                    }
                    return isLeaf;
                }

                private ObservableList<TreeItem<File>> buildChildren(
                        TreeItem<File> TreeItem) {
                    File f = TreeItem.getValue();
                    if (f == null) {
                        return FXCollections.emptyObservableList();
                    }
                    if (f.isFile()) {
                        return FXCollections.emptyObservableList();
                    }
                    File[] files = f.listFiles();
                    if (files != null) {
                        ObservableList<TreeItem<File>> children = FXCollections
                                .observableArrayList();
                        for (File childFile : files) {
                            children.add(createNode(childFile));
                        }
                        return children;
                    }
                    return FXCollections.emptyObservableList();
                }
            };
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group(), 300, 300);
            VBox vbox = new VBox();

            TreeItem<File> root = createNode(new File("c:/"));
            TreeView treeView = new TreeView<File>(root);

            vbox.getChildren().add(treeView);
            ((Group) scene.getRoot()).getChildren().add(vbox);

            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * 用户域模型
     *
     * 以下代码显示了如何使用用户域模型数据与TreeView。
     * 创建Employee类以捕获用户域模型，并且代码使用循环将所有员工添加到树视图。
     */
    public static class Main4 extends Application {
        List<Employee> employees = Arrays.<Employee>asList(
                new Employee("a1", "A"),
                new Employee("a2", "A"),
                new Employee("e1", "E"));
        private final Node rootIcon =  new ImageView(new Image(getClass().getResourceAsStream("root.png")));
        TreeItem<String> rootNode = new TreeItem<String>("Root",rootIcon);

        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage stage) {
            rootNode.setExpanded(true);
            for (Employee employee : employees) {
                TreeItem<String> empLeaf = new TreeItem<String>(employee.getName());
                boolean found = false;
                for (TreeItem<String> depNode : rootNode.getChildren()) {
                    if (depNode.getValue().contentEquals(employee.getDepartment())){
                        depNode.getChildren().add(empLeaf);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    TreeItem depNode = new TreeItem(employee.getDepartment());
                    rootNode.getChildren().add(depNode);
                    depNode.getChildren().add(empLeaf);
                }
            }
            stage.setTitle("Tree View Sample");
            VBox box = new VBox();
            final Scene scene = new Scene(box, 400, 300);
            scene.setFill(Color.LIGHTGRAY);

            TreeView<String> treeView = new TreeView<String>(rootNode);
            treeView.setShowRoot(true);
            treeView.setEditable(true);
            box.getChildren().add(treeView);
            stage.setScene(scene);
            stage.show();
        }
        public static class Employee {

            private final SimpleStringProperty name;
            private final SimpleStringProperty department;

            private Employee(String name, String department) {
                this.name = new SimpleStringProperty(name);
                this.department = new SimpleStringProperty(department);
            }

            public String getName() {
                return name.get();
            }

            public void setName(String fName) {
                name.set(fName);
            }

            public String getDepartment() {
                return department.get();
            }

            public void setDepartment(String fName) {
                department.set(fName);
            }
        }
    }

    /**新树项目按需*/
    public static class Main5 extends Application {
        List<Employee> employees = Arrays.<Employee>asList(
                new Employee("Jacob Smith", "Accounts Department"),
                new Employee("Judy Mayer", "IT Support"),
                new Employee("Gregory Smith", "IT Support"));
        TreeItem<String> rootNode =
                new TreeItem<>("MyCompany Human Resources");

        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage stage) {
            rootNode.setExpanded(true);
            for (Employee employee : employees) {
                TreeItem<String> empLeaf = new TreeItem<>(employee.getName());
                boolean found = false;
                for (TreeItem<String> depNode : rootNode.getChildren()) {
                    if (depNode.getValue().contentEquals(employee.getDepartment())){
                        depNode.getChildren().add(empLeaf);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    TreeItem depNode = new TreeItem(employee.getDepartment());
                    rootNode.getChildren().add(depNode);
                    depNode.getChildren().add(empLeaf);
                }
            }

            stage.setTitle("Tree View Sample");
            VBox box = new VBox();
            final Scene scene = new Scene(box, 400, 300);
            scene.setFill(Color.LIGHTGRAY);

            TreeView<String> treeView = new TreeView<>(rootNode);
            treeView.setEditable(true);
            treeView.setCellFactory((TreeView<String> p) ->
                    new TextFieldTreeCellImpl());

            box.getChildren().add(treeView);
            stage.setScene(scene);
            stage.show();
        }

        private final class TextFieldTreeCellImpl extends TreeCell<String> {

            private TextField textField;
            private final ContextMenu addMenu = new ContextMenu();

            public TextFieldTreeCellImpl() {
                MenuItem addMenuItem = new MenuItem("Add Employee");
                addMenu.getItems().add(addMenuItem);
                addMenuItem.setOnAction((ActionEvent t) -> {
                    TreeItem newEmployee =
                            new TreeItem<>("New Employee");
                    getTreeItem().getChildren().add(newEmployee);
                });
            }

            @Override
            public void startEdit() {
                super.startEdit();

                if (textField == null) {
                    createTextField();
                }
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();

                setText((String) getItem());
                setGraphic(getTreeItem().getGraphic());
            }

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (isEditing()) {
                        if (textField != null) {
                            textField.setText(getString());
                        }
                        setText(null);
                        setGraphic(textField);
                    } else {
                        setText(getString());
                        setGraphic(getTreeItem().getGraphic());
                        if (
                                !getTreeItem().isLeaf()&&getTreeItem().getParent()!= null
                                ){
                            setContextMenu(addMenu);
                        }
                    }
                }
            }

            private void createTextField() {
                textField = new TextField(getString());
                textField.setOnKeyReleased((KeyEvent t) -> {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                });

            }

            private String getString() {
                return getItem() == null ? "" : getItem().toString();
            }
        }

        public static class Employee {

            private final SimpleStringProperty name;
            private final SimpleStringProperty department;

            private Employee(String name, String department) {
                this.name = new SimpleStringProperty(name);
                this.department = new SimpleStringProperty(department);
            }

            public String getName() {
                return name.get();
            }

            public void setName(String fName) {
                name.set(fName);
            }

            public String getDepartment() {
                return department.get();
            }

            public void setDepartment(String fName) {
                department.set(fName);
            }
        }
    }

    /**单元工厂可以向TreeView中的单个TreeItem添加具有特殊功能的TreeCell。*/
    public static class Main6 extends Application {
        List<Employee> employees = Arrays.<Employee> asList(new Employee(
                        "Jacob Smith", "Accounts Department"), new Employee("Isabella Johnson",
                        "Accounts Department"), new Employee("Mike Graham", "IT Support"),
                new Employee("Judy Mayer", "IT Support"), new Employee("Gregory Smith",
                        "IT Support"));
        TreeItem<String> rootNode;

        public static void main(String[] args) {
            Application.launch(args);
        }

        public Main6() {
            this.rootNode = new TreeItem<>("MyCompany Human Resources");
        }

        @Override
        public void start(Stage stage) {
            rootNode.setExpanded(true);
            for (Employee employee : employees) {
                TreeItem<String> empLeaf = new TreeItem<>(employee.getName());
                boolean found = false;
                for (TreeItem<String> depNode : rootNode.getChildren()) {
                    if (depNode.getValue().contentEquals(employee.getDepartment())) {
                        depNode.getChildren().add(empLeaf);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    TreeItem<String> depNode = new TreeItem<>(employee.getDepartment());
                    rootNode.getChildren().add(depNode);
                    depNode.getChildren().add(empLeaf);
                }
            }

            stage.setTitle("Tree View Sample");
            VBox box = new VBox();
            final Scene scene = new Scene(box, 400, 300);
            scene.setFill(Color.LIGHTGRAY);

            TreeView<String> treeView = new TreeView<>(rootNode);
            treeView.setEditable(true);
            treeView.setCellFactory((TreeView<String> p) -> new TextFieldTreeCellImpl());

            box.getChildren().add(treeView);
            stage.setScene(scene);
            stage.show();
        }

        private final class TextFieldTreeCellImpl extends TreeCell<String> {

            private TextField textField;

            public TextFieldTreeCellImpl() {
            }

            @Override
            public void startEdit() {
                super.startEdit();

                if (textField == null) {
                    createTextField();
                }
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                setText((String) getItem());
                setGraphic(getTreeItem().getGraphic());
            }

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (isEditing()) {
                        if (textField != null) {
                            textField.setText(getString());
                        }
                        setText(null);
                        setGraphic(textField);
                    } else {
                        setText(getString());
                        setGraphic(getTreeItem().getGraphic());
                    }
                }
            }

            private void createTextField() {
                textField = new TextField(getString());
                textField.setOnKeyReleased((KeyEvent t) -> {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                });
            }
            private String getString() {
                return getItem() == null ? "" : getItem().toString();
            }
        }

        public static class Employee {

            private final SimpleStringProperty name;
            private final SimpleStringProperty department;

            private Employee(String name, String department) {
                this.name = new SimpleStringProperty(name);
                this.department = new SimpleStringProperty(department);
            }

            public String getName() {
                return name.get();
            }

            public void setName(String fName) {
                name.set(fName);
            }

            public String getDepartment() {
                return department.get();
            }

            public void setDepartment(String fName) {
                department.set(fName);
            }
        }
    }


    /**
     * 树单元编辑
     *
     * 我们可以使用以下树单元编辑器。
         * CheckBoxTreeCell
         * ChoiceBoxTreeCell
         * ComboBoxTreeCell
         * TextFieldTreeCell
     * 有些类扩展了TreeCell实现来渲染单元格内的特定控件。
     * 下面的代码创建一个带有复选框树项目的树视图。
     */
    public static class Main7 extends Application {
        public static void main(String[] args) {
            launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<>("A");
            rootItem.setExpanded(true);

            TreeView tree = new TreeView(rootItem);
            tree.setEditable(true);

            tree.setCellFactory(CheckBoxTreeCell.forTreeView());

            CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<>("a");
            rootItem.getChildren().add(checkBoxTreeItem);

            checkBoxTreeItem = new CheckBoxTreeItem<>("b");
            rootItem.getChildren().add(checkBoxTreeItem);

            tree.setRoot(rootItem);

            StackPane root = new StackPane();
            root.getChildren().add(tree);
            primaryStage.setScene(new Scene(root, 300, 250));
            primaryStage.show();
        }
    }
}
