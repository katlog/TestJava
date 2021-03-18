package name.katlog.refelct;

import org.junit.Test;

import java.lang.reflect.*;

/**
 * Created by dell on 2018/4/20
 */
public class TestInnerClassReflect {

    private class InnerA {
        private String f = InnerA.class.getSimpleName();

        public InnerA() {
        }
    }

    private static class InnerB {
        private String f = InnerB.class.getSimpleName();

        public InnerB() {
        }
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            System.out.println("Method run of Runnable r");
        }
    };

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        Class clazz = TestInnerClassReflect.class;
        TestInnerClassReflect container = (TestInnerClassReflect) clazz.newInstance();

        // 获取全部内部类
        Class innerClazz[] = clazz.getDeclaredClasses();

        for (Class cls : innerClazz) {
            int mod = cls.getModifiers();
            String modifier = Modifier.toString(mod);

            // 静态内部类
            if (modifier.contains("static")) {
                //构造静态内部类实例
//              Constructor con1 = cls.getDeclaredConstructor();
                Object obj1 = cls.newInstance();
                Field field1 = cls.getDeclaredField("f");
                field1.setAccessible(true);
                System.out.println(field1.get(obj1));
            } else {
                // 构造成员内部类实例
                Constructor con2 = cls.getDeclaredConstructor(clazz);
                con2.setAccessible(true);
                Object obj2 = con2.newInstance(container);
                Field field2 = cls.getDeclaredField("f");
                field2.setAccessible(true);
                System.out.println(field2.get(obj2));
            }
        }
        // 获取匿名内部类实例
        Field field = clazz.getDeclaredField("r");
        field.setAccessible(true);
        Runnable r = (Runnable) field.get(container);
        r.run();
    }


    public TestInnerClassReflect() {
        // 构造方法中的匿名内部类
        InnerClass innerClass = new InnerClass() {
            @Override
            public void fun() {
                getEnclosing(this.getClass());
                /**
                 * enclosingClass=class reflect.Outer
                 * enclosingConstructor=public reflect.Outer()
                 * enclosingMethod=null
                 */
            }
        };
        innerClass.fun();
    }

    // 匿名内部类
    static InnerClass innerClass = new InnerClass() {
        public void fun() {
            getEnclosing(this.getClass());
            /**
             * enclosingClass=class reflect.Outer
             * enclosingConstructor=null
             * enclosingMethod=null
             */
        };
    };

    public static void test() {
        // 方法中的匿名内部类
        InnerClass innerClass = new InnerClass() {
            @Override
            public void fun() {
                getEnclosing(this.getClass());
                /**
                 * enclosingClass=class reflect.Outer
                 * enclosingConstructor=null
                 * enclosingMethod=public static void reflect.Outer.test()
                 */
            }
        };
        innerClass.fun();
    }


    // 内部类
    public static class InnerClass {
        public InnerClass() {    }
        public void fun() {     }
    }
    private static void getEnclosing(Class cls) {
        Class enclosingClass = cls.getEnclosingClass();
        Constructor enclosingConstructor = cls.getEnclosingConstructor();
        Method enclosingMethod = cls.getEnclosingMethod();
        System.out.println("enclosingClass=" + enclosingClass);
        System.out.println("enclosingConstructor=" + enclosingConstructor);
        System.out.println("enclosingMethod=" + enclosingMethod);

    }

    @Test
    public void innerClass() {
        System.out.println("------内部类------");
        getEnclosing(InnerClass.class);

        System.out.println("------匿名内部类------");
        innerClass.fun();

        System.out.println("------方法中的匿名内部类------");
        TestInnerClassReflect.test();

        System.out.println("------构造函数中的匿名内部类------");
        new TestReflect();
    }

}
