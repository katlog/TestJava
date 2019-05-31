package name.katlog.jvm.item08;

/**
 * @moudle: TestStaticDispatch
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年7月31日 上午9:56:42
 * 体现的是重载：一个类的多个方法名相同的不同签名的方法间的选择
 */
public class TestStaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {

        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {

        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {

        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {

        Human man = new Man();
        Human woman = new Woman();
        TestStaticDispatch sr = new TestStaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);

        //实际类型变化
        Human man1 = new Man();
        man1 = new Woman();
        //静态类型变化
        sr.sayHello((Man) man1);
        sr.sayHello((Woman) man1);

    }
}
