package name.dfw.jvm.item08;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GrandFather {
    void thinking() {
        System.out.println("i am grandfather");
    }

    public static void main(String[] args) {
        new Son().thinking();
    }
}

class Father extends GrandFather {
    void thinking() {
        System.out.println("i am father");
    }
}

class Son extends Father {
    void thinking() {
        try {
            Class<? super Father> grandFatherClass = Father.class.getSuperclass();
            Method thinking = grandFatherClass.getDeclaredMethod("thinking", null);
            thinking.invoke(Father.class.newInstance());
            thinking.invoke(grandFatherClass.newInstance());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //请读者在这里填入适当的代码(不能修改其他地方的代码)
    //实现调用祖父类的thinking()方法,打印"i am grandfather"
    }
}

