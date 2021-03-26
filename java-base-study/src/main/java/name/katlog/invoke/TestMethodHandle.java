package name.katlog.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Random;

/**
 * MethodHandle 属于 JDK7 新加入
 */
public class TestMethodHandle {

    static class ClassA{
        public void println(String s) {
            System.out.println("im class A , " + s);
        }
    }

    static class ClassB{
        public void println(String s) {
            System.out.println("im class B , " + s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object o = new Random().nextBoolean()? new ClassA():new ClassB();
        getPrintlnMH(o).invoke("test");
    }

    private static MethodHandle getPrintlnMH(Object obj) throws NoSuchMethodException, IllegalAccessException {
        MethodType mt = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(obj.getClass(), "println", mt).bindTo(obj);
    }
}
