package org.person.dfw.fx.practice.property;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.Test;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class PropertyMain {

    /**
     * 读/写属性
     *
     * 读/写属性是可以读取和修改的属性值。
     * 例如， SimpleStringProperty 类创建一个字符串属性，该属性对包装的字符串值是可读写的。
     * 以下代码演示了一个 SimpleStringProperty 类的实例，并通过set()方法修改该属性。
     */
    @Test
    public  void simpleStringProperty() {
        StringProperty password  = new SimpleStringProperty("w3cschool.cn");
        password.set("example.com");
        System.out.println("Modified StringProperty "  + password.get() );
    }

    /**
     * 只读属性
     *
     * 要创建只读属性，请使用以 ReadOnly 作为前缀的包装类。
     * 创建只读属性需要两个步骤。
         * 实例化只读包装类
         * 调用方法getReadOnlyProperty()返回一个真正的只读属性对象
     */
    @Test
    public  void readOnlyStringWrapper() {
        ReadOnlyStringWrapper userName = new ReadOnlyStringWrapper("www.w3cschool.cn");
        ReadOnlyStringProperty readOnlyUserName  = userName.getReadOnlyProperty();
    }

    /**
     * JavaFX JavaBean
     *
     * 以下代码显示了如何创建JavaFX JavaBean。当构建基于Swing的应用程序时，我们使用getter和setter创建JavaBean。
     * 然后我们必须通过Swing模型类在UI逻辑中获取和设置数据。通过使用JavaFX属性创建JavaFX JavaBean，JavaFX将执行数据绑定，并完成域模型类和UI控件之间的数据交换作业。
     */
    class User {
        private final static String USERNAME_PROP_NAME = "userName";
        private final ReadOnlyStringWrapper userName;
        private final static String PASSWORD_PROP_NAME = "password";
        private StringProperty password;

        public User() {
            userName = new ReadOnlyStringWrapper(this, USERNAME_PROP_NAME,"fake user");
            password = new SimpleStringProperty(this, PASSWORD_PROP_NAME, "");
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

        public final void setPassword(String password) {
            this.password.set(password);
        }

        public StringProperty passwordProperty() {
            return password;
        }
    }

    /**
     * 属性更改事件
     *
     * 属性可以通知值更改事件处理程序，以在属性更改时进行响应。
     * JavaFX属性对象包含一个addListener()方法，它接受两种类型的功能接口：ChangeListener和invalidationListener。
     * 所有JavaFX属性都是ObservableValue和Observable接口的后代，它们分别为ChangeListener和invalidationListener提供了addListener()方法。
     * 以下代码显示如何创建要注册到属性的ChangeListener。随着属性的值改变，将调用change()方法。
     */
    public static class Main1 {
        public static void main(String[] args) {
            SimpleIntegerProperty xProperty = new SimpleIntegerProperty(0);

            // Adding a change listener with anonymous inner class
            xProperty.addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number oldVal,
                                    Number newVal) {
                    System.out.println("old value:"+oldVal);
                    System.out.println("new value:"+newVal);
                }
            });

            // Adding a change listener with lambda expression
            xProperty.addListener((ObservableValue<? extends Number> ov, Number oldVal,
                                   Number newVal) -> {
                System.out.println("old value:"+oldVal);
                System.out.println("new value:"+newVal);
            });
        }
    }


    /**
     * 以下代码显示如何创建一个invalidationListener以向属性注册。随着属性的值更改，将调用invalidated()方法。
     *
     *  ChangeListener和invalidationListener之间的区别。
         * 使用ChangeListener，我们将获得Observable（ObservableValue），旧值和新值。
         * 使用invalidationListener只获取Observable对象（属性）。
     */
    public static class Main2 {
        public static void main(String[] args) {
            SimpleIntegerProperty xProperty = new SimpleIntegerProperty(0);

            // Adding a invalidation listener (anonymous inner class)
            xProperty.addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable o) {
                    System.out.println(o.toString());
                }
            });

            // Adding a invalidation listener (lambda expression)
            xProperty.addListener((Observable o) -> {
                System.out.println(o.toString());
            });

        }
    }

}
