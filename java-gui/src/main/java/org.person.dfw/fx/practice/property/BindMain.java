package org.person.dfw.fx.practice.property;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BindMain {

   static class User {

        private SimpleStringProperty firstName = new SimpleStringProperty();
        private SimpleStringProperty lastName = new SimpleStringProperty();

        public User(String fn, String ln) {
            firstName.setValue(fn);
            lastName.setValue(ln);
        }

        public final String getFirstName() {
            return firstName.getValue();
        }

        public StringProperty firstNameProperty() {
            return firstName;
        }

        public final void setFirstName(String firstName) {
            this.firstName.setValue(firstName);
        }

        public final String getLastName() {
            return lastName.getValue();
        }

        public StringProperty lastNameProperty() {
            return lastName;
        }

        public final void setLastName(String lastName) {
            this.lastName.setValue(lastName);
        }
    }

    /**
     * 双向绑定
     *
     * 双向绑定绑定相同类型的属性，并同步两侧的a值。
     * 当与bindBidirectional()方法双向绑定时，需要两个属性都必须是可读/可写的。
     * 以下代码显示如何在firstName属性和字符串属性变量之间进行双向绑定
     */
    public  static class Main1 {
        public static void main(String[] args) {
            User contact = new User("Jame", "Bind");
            StringProperty fname = new SimpleStringProperty();
            fname.bindBidirectional(contact.firstNameProperty());

            contact.firstNameProperty().set("new value");
            fname.set("New First Name");

            System.out.println("firstNameProperty = "
                    + contact.firstNameProperty().get());
            System.out.println("fname = " + fname.get());

        }
    }

    /**
     * 高级绑定
     *
     * 我们还可以使用JavaFX fluent API来绑定属性。流利的API使用类似英语的方法名称对属性执行操作。
     * 例如，multiply()，divide()，subtract()，isEqualTo()，isNotEqualTo()，concat()。请注意，方法名称中没有get或set。当链接流畅的API在一起，我们可以编写代码，如同我们正在写句子，例如 width().multiply(height()).divide(2)。
     * 以下代码显示如何创建表示计算矩形面积的公式的属性。
     * 它通过使用 javafx.beans.binding.IntegerExpression 接口中的fluent API来执行高级绑定。
     * 该代码使用 multiply()方法，该方法返回包含计算值的NumberBinding。
     * 这种绑定是延迟评估的，这意味着乘法不会发生，除非我们通过 get()或 getValue()方法调用属性的值。
     */
    public static class Main2 {
        public static void main(String[] args) {
            // Area = width * height
            IntegerProperty width = new SimpleIntegerProperty(10);
            IntegerProperty height = new SimpleIntegerProperty(10);
            NumberBinding area = width.multiply(height);
            System.out.println(area.getValue());
        }
    }

    /**
     * 低级绑定
     *
     * 当对 NumberBinding 类进行子类化时，我们使用低级绑定，例如Double类型的DoubleBinding类。
     * 在DoubleBinding类的子类中，我们重写其 computeValue()方法，以便我们可以使用运算符（如* 和 - ）来制定复杂的数学方程。
     * 高级绑定使用诸如multiply()，subtract()等方法低级绑定使用诸如*和 - 之类的运算符。
     * 以下代码显示如何为公式创建低级别绑定以计算矩形的面积。
     */
    public static class Main3 {
        public static void main(String[] args) {
            DoubleProperty width = new SimpleDoubleProperty(2);
            DoubleProperty height = new SimpleDoubleProperty(2);
            DoubleBinding area = new DoubleBinding() {
                {
                    super.bind(width, height); // initial bind
                }

                @Override
                protected double computeValue() {
                    return width.get() * height.get();
                }
            };
            System.out.println(area.get());
        }
    }

    /***
     * UI控件和域模型之间的绑定
     *
     * 在JavaFX中，UI控件和域模型之间的数据绑定很容易。以下代码显示如何创建登录对话框并绑定用户域对象。
     * 首先，我们定义域对象，它是描述用户名和密码的JavaFX JavaBean。
     */
    public static class Main extends Application {
        private final static String MY_PASS = "asdf";
        private final static BooleanProperty accessGranted = new SimpleBooleanProperty(false);
        @Override
        public void start(Stage primaryStage) {
            User1 user = new User1();
            Group root = new Group();
            Scene scene = new Scene(root, 320, 100);
            primaryStage.setScene(scene);

            Text userName = new Text();
            userName.textProperty().bind(user.userNameProperty());

            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");
            user.passwordProperty().bind(passwordField.textProperty());

            // user hits the enter key
            passwordField.setOnAction(actionEvent -> {
                if (accessGranted.get()) {
                    System.out.println("granted access:"+ user.getUserName());
                    System.out.println("password:"+ user.getPassword());
                    Platform.exit();
                } else {
                    primaryStage.setTitle("no access");
                }
            });

            passwordField.textProperty().addListener((obs, ov, nv) -> {
                boolean granted = passwordField.getText().equals(MY_PASS);
                accessGranted.set(granted);
                if (granted) {
                    primaryStage.setTitle("");
                }
            });
            VBox formLayout = new VBox(4);
            formLayout.getChildren().addAll(userName, passwordField);
            formLayout.setLayoutX(12);
            formLayout.setLayoutY(12);

            root.getChildren().addAll(formLayout);
            primaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }
     static class User1 {
        private final ReadOnlyStringWrapper userName;
        private StringProperty password;
        public User1() {
            userName = new ReadOnlyStringWrapper(this, "userName", "ABC");
            password = new SimpleStringProperty(this, "password", "");
        }

        public final String getUserName() {
            return userName.get();
        }
        public ReadOnlyStringProperty userNameProperty() {
            return userName.getReadOnlyProperty();
        }

        public final String getPassword() {
            return password.get();
        }
        public StringProperty passwordProperty() {
            return password;
        }
    }
}
