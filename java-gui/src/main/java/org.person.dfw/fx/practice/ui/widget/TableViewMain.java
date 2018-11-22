package org.person.dfw.fx.practice.ui.widget;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.junit.Test;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class TableViewMain {
    /**
     * 我们可以使用来自JavaFX API的TableView，TableColumn和TableCell类以表格形式表示数据。
     * 通过实现数据模型和应用单元工厂来填充表中的数据。
     * 表类可以按列排序数据，并在必要时调整列大小。
     */

    @Test
    public void create() {
        // 表控件是通过实例化TableView类创建的。
        TableView table = new TableView();
        table.setEditable(true);
        // 然后使用TableColumn类创建三个列。TableView类的getColumns方法将创建的列添加到表中。
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        // 我们可以通过调用setVisible方法隐藏列。
        // aColumn.setVisible(false).
    }

    public static class Main1 extends Application {
        public static void main(String[] args) {
            launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            TableView table = new TableView();
            table.setEditable(true);

            TableColumn firstNameCol = new TableColumn("First Name");
            TableColumn lastNameCol = new TableColumn("Last Name");
            TableColumn emailCol = new TableColumn("Email");

            table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
            emailCol.setVisible(false);

            StackPane root = new StackPane();
            root.getChildren().add(table);
            primaryStage.setScene(new Scene(root, 200, 250));
            primaryStage.show();
        }
    }

    /**
     * 嵌套标头
     *
     * 使用JavaFX表视图，我们可以轻松创建嵌套列。
     * 假设我们要将两个子列添加到地址列。
        * TableColumn cityCol = new TableColumn("City");
        * TableColumn stateCol = new TableColumn("State");
     * 然后我们将新创建的列添加到地址列。
     * addressCol.getColumns().addAll(cityCol, stateCol);
     */
    public static class Main2 extends Application {
        public static void main(String[] args) {
            launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            TableView table = new TableView();
            table.setEditable(true);

            TableColumn firstNameCol = new TableColumn("First Name");
            TableColumn lastNameCol = new TableColumn("Last Name");
            TableColumn addressCol = new TableColumn("Email");

            table.getColumns().addAll(firstNameCol, lastNameCol, addressCol);

            TableColumn cityCol = new TableColumn("City");
            TableColumn stateCol = new TableColumn("State");

            addressCol.getColumns().addAll(cityCol, stateCol);

            StackPane root = new StackPane();
            root.getChildren().add(table);
            primaryStage.setScene(new Scene(root, 200, 250));
            primaryStage.show();
        }
    }

    /**
     * 添加新行
     *
     * 以下代码显示如何向表视图中添加数据。 创建JavaFX JavaBean以保存单个行的值。 表中的每一行代表一个名字为姓氏的人。 JavaFX JavaBean称为Person，它有两个字段，名字和姓氏。 Person为这两个值提供了可绑定的属性。
     * 在UI逻辑中，它使用ObservableList来保存表视图的值。 ObservableList中的每个元素都是一个Person对象。
     * 在按钮事件处理程序中，它创建一个新的具有硬编码的名字和姓氏的人，然后添加到ObservableList。
     */
    public static class Main3 extends Application {

        private final TableView<Person> table = new TableView<>();
        private final ObservableList<Person> data =
                FXCollections.observableArrayList(new Person("A", "B"));
        final HBox hb = new HBox();

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setWidth(450);
            stage.setHeight(550);


            TableColumn firstNameCol = new TableColumn("First Name");
            firstNameCol.setMinWidth(100);
            firstNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("firstName"));

            TableColumn lastNameCol = new TableColumn("Last Name");
            lastNameCol.setMinWidth(100);
            lastNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("lastName"));

            table.setItems(data);
            table.getColumns().addAll(firstNameCol, lastNameCol);

            final Button addButton = new Button("Add");
            addButton.setOnAction((ActionEvent e) -> {
                data.add(new Person("Z","X"));
            });

            hb.getChildren().addAll(addButton);
            hb.setSpacing(3);

            final VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(table, hb);

            ((Group) scene.getRoot()).getChildren().addAll(vbox);

            stage.setScene(scene);
            stage.show();
        }

        public static class Person {

            private final SimpleStringProperty firstName;
            private final SimpleStringProperty lastName;

            private Person(String fName, String lName) {
                this.firstName = new SimpleStringProperty(fName);
                this.lastName = new SimpleStringProperty(lName);
            }

            public String getFirstName() {
                return firstName.get();
            }

            public void setFirstName(String fName) {
                firstName.set(fName);
            }

            public String getLastName() {
                return lastName.get();
            }

            public void setLastName(String fName) {
                lastName.set(fName);
            }
        }
    }

    /**
     * TableView列排序
     *
     * TableView类具有列的内置排序功能。
     * 我们可以通过点击列标题对数据进行排序。第一次单击启用升序排序，第二次点击使得排序顺序降序，第三个点击禁用排序。
     * 默认情况下，不应用排序。
     * 我们可以排序多个列，并在排序操作中指定每个列的优先级。
     * 要对多个列进行排序，请在按住Shift键的同时单击列标题。
     * 我们可以使用 setSortType 方法设置排序首选项我们可以指定升序或降序类型。
     * 以下代码设置排序的降序类型
        * aColumn.setSortType(TableColumn.SortType.DESCENDING);
     * 我们可以通过从TableView.sortOrder observable列表中添加和删除TableColumn实例来指定要排序的列。
     * TableView.sortOrder observable列中的列的顺序表示排序优先级。 列表中零索引的项目的优先级高于列表中的第一个项目。
     * 要禁止对列进行排序，请调用该列上的setSortable(false)方法。
     */
    public static class Main4 extends Application {

        private final TableView<Person> table = new TableView<>();
        private final ObservableList<Person> data =
                FXCollections.observableArrayList(new Person("A", "B"),
                        new Person("D", "E"));


        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setWidth(450);
            stage.setHeight(550);


            TableColumn firstNameCol = new TableColumn("First Name");
            firstNameCol.setMinWidth(100);
            firstNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("firstName"));

            TableColumn lastNameCol = new TableColumn("Last Name");
            lastNameCol.setMinWidth(100);
            lastNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("lastName"));

            table.setItems(data);
            table.getColumns().addAll(firstNameCol, lastNameCol);

            firstNameCol.setSortType(TableColumn.SortType.DESCENDING);
            lastNameCol.setSortable(false);

            final VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(table);

            ((Group) scene.getRoot()).getChildren().addAll(vbox);

            stage.setScene(scene);
            stage.show();
        }

        public static class Person {

            private final SimpleStringProperty firstName;
            private final SimpleStringProperty lastName;

            private Person(String fName, String lName) {
                this.firstName = new SimpleStringProperty(fName);
                this.lastName = new SimpleStringProperty(lName);
            }

            public String getFirstName() {
                return firstName.get();
            }

            public void setFirstName(String fName) {
                firstName.set(fName);
            }

            public String getLastName() {
                return lastName.get();
            }

            public void setLastName(String fName) {
                lastName.set(fName);
            }
        }
    }

    /**
     * 编辑表中的数据
     *
     * 我们可以在表视图中编辑数据。通过调用setEditable方法，我们可以启用表内容的编辑。
     * setCellFactory方法安装在TextFieldTableCell类中定义的文本字段为每个表单元格。
     * setOnEditCommit方法更新表单元格，并且数据绑定需要考虑设置数据回到JavaFX JavaBean，它用作表视图的下划线数据模型。
     */
    public static class Main extends Application {

        private final TableView<Person> table = new TableView<>();
        private final ObservableList<Person> data =
                FXCollections.observableArrayList(
                        new Person("A", "B"),
                        new Person("C", "D"));
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setWidth(450);
            stage.setHeight(550);

            table.setEditable(true);

            TableColumn<Person, String> firstNameCol =
                    new TableColumn<>("First Name");
            firstNameCol.setMinWidth(100);
            firstNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("firstName"));

            firstNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
            firstNameCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Person, String> t) -> {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setFirstName(t.getNewValue());
                    });


            TableColumn<Person, String> lastNameCol =
                    new TableColumn<>("Last Name");
            lastNameCol.setMinWidth(100);
            lastNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("lastName"));
            lastNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
            lastNameCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Person, String> t) -> {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setLastName(t.getNewValue());
                    });
            table.setItems(data);
            table.getColumns().addAll(firstNameCol, lastNameCol);

            final VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll( table);

            ((Group) scene.getRoot()).getChildren().addAll(vbox);

            stage.setScene(scene);
            stage.show();
        }

        public  class Person {
            private final SimpleStringProperty firstName;
            private final SimpleStringProperty lastName;

            private Person(String fName, String lName) {
                this.firstName = new SimpleStringProperty(fName);
                this.lastName = new SimpleStringProperty(lName);
            }

            public String getFirstName() {
                return firstName.get();
            }

            public void setFirstName(String fName) {
                firstName.set(fName);
            }

            public String getLastName() {
                return lastName.get();
            }

            public void setLastName(String fName) {
                lastName.set(fName);
            }
        }
    }

    /**
     * 表编辑提交事件
     *
     * 当运行上面的代码并双击编辑单元格，我们必须按Enter键提交编辑。 如果我们只是双击并键入一些值，并单击单元格外，commit事件不会运行，旧的值将被填充。
     * 我们可以重新定义焦点更改上的commit事件。
     */
    public static class Main5 extends Application {

        private final TableView<Person> table = new TableView<>();
        private final ObservableList<Person> data = FXCollections
                .observableArrayList(new Person("A", "B"), new Person("C", "D"));

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory = (
                    TableColumn<Person, String> p) -> new EditingCell();

            Scene scene = new Scene(new Group());
            stage.setWidth(450);
            stage.setHeight(550);

            table.setEditable(true);

            TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
            firstNameCol.setMinWidth(100);
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            firstNameCol.setCellFactory(cellFactory);

            firstNameCol.setOnEditCommit((TableColumn.CellEditEvent<Person, String> t) -> {
                ((Person) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setFirstName(t.getNewValue());
            });

            TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
            lastNameCol.setMinWidth(100);
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            table.setItems(data);
            table.getColumns().addAll(firstNameCol, lastNameCol);

            final VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(table);

            ((Group) scene.getRoot()).getChildren().addAll(vbox);

            stage.setScene(scene);
            stage.show();
        }

        class EditingCell extends TableCell<Person, String> {

            private TextField textField;

            public EditingCell() {
            }

            @Override
            public void startEdit() {
                if (!isEmpty()) {
                    super.startEdit();
                    createTextField();
                    setText(null);
                    setGraphic(textField);
                    textField.selectAll();
                }
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();

                setText((String) getItem());
                setGraphic(null);
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
                        setGraphic(null);
                    }
                }
            }

            private void createTextField() {
                textField = new TextField(getString());
                textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
                textField.focusedProperty()
                        .addListener(
                                (ObservableValue<? extends Boolean> arg0, Boolean arg1,
                                 Boolean arg2) -> {
                                    if (!arg2) {
                                        commitEdit(textField.getText());
                                    }
                                });
            }

            private String getString() {
                return getItem() == null ? "" : getItem().toString();
            }
        }

        public static class Person {
            private final SimpleStringProperty firstName;
            private final SimpleStringProperty lastName;

            private Person(String fName, String lName) {
                this.firstName = new SimpleStringProperty(fName);
                this.lastName = new SimpleStringProperty(lName);
            }

            public String getFirstName() {
                return firstName.get();
            }

            public void setFirstName(String fName) {
                firstName.set(fName);
            }

            public String getLastName() {
                return lastName.get();
            }

            public void setLastName(String fName) {
                lastName.set(fName);
            }
        }
    }

    /**
     * 将数据映射添加到表中
     *
     * 我们可以使用MapValueFactory类将Map数据添加到表中。
     */
    public static class Main6 extends Application {

        public static final String Column1MapKey = "A";
        public static final String Column2MapKey = "B";

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setTitle("Table View Sample");
            stage.setWidth(300);
            stage.setHeight(500);

            final Label label = new Label("Student IDs");
            label.setFont(new Font("Arial", 20));

            TableColumn<Map, String> firstDataColumn = new TableColumn<>("Class A");
            TableColumn<Map, String> secondDataColumn = new TableColumn<>("Class B");

            firstDataColumn.setCellValueFactory(new MapValueFactory(Column1MapKey));
            firstDataColumn.setMinWidth(130);
            secondDataColumn.setCellValueFactory(new MapValueFactory(Column2MapKey));
            secondDataColumn.setMinWidth(130);

            TableView tableView = new TableView<>(generateDataInMap());

            tableView.setEditable(true);
            tableView.getSelectionModel().setCellSelectionEnabled(true);
            tableView.getColumns().setAll(firstDataColumn, secondDataColumn);
            Callback<TableColumn<Map, String>, TableCell<Map, String>>
                    cellFactoryForMap = (TableColumn<Map, String> p) ->
                    new TextFieldTableCell(new StringConverter() {
                        @Override
                        public String toString(Object t) {
                            return t.toString();
                        }
                        @Override
                        public Object fromString(String string) {
                            return string;
                        }
                    });
            firstDataColumn.setCellFactory(cellFactoryForMap);
            secondDataColumn.setCellFactory(cellFactoryForMap);

            final VBox vbox = new VBox();

            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(label, tableView);

            ((Group) scene.getRoot()).getChildren().addAll(vbox);

            stage.setScene(scene);

            stage.show();
        }

        private ObservableList<Map> generateDataInMap() {
            int max = 10;
            ObservableList<Map> allData = FXCollections.observableArrayList();
            for (int i = 1; i < max; i++) {
                Map<String, String> dataRow = new HashMap<>();

                String value1 = "A" + i;
                String value2 = "B" + i;

                dataRow.put(Column1MapKey, value1);
                dataRow.put(Column2MapKey, value2);

                allData.add(dataRow);
            }
            return allData;
        }
    }

}
