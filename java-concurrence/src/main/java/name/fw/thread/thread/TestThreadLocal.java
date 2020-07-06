package name.fw.thread.thread;

import com.alibaba.fastjson.JSON;
import net.sf.json.util.JSONUtils;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class TestThreadLocal {


    /**  thread local的使用 */
    @Test
    public void set() throws InterruptedException {
        ThreadLocal<String> th1 = new ThreadLocal<>();
        th1.set("main");

        // 有值
        System.out.println(Thread.currentThread().getName() + "thread th1. = " + th1.get());

        // 空值
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +" thread th1 = " + th1.get()
            );
        }).start();
    }

    @Test
    public void withInitial(){
        ThreadLocal<String> th1 = ThreadLocal.withInitial(() -> "value");

        // 有值
        System.out.println(Thread.currentThread().getName() + "thread th1. = " + th1.get());

        // 也有值
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +" thread th1 = " + th1.get()
            );
        }).start();
    }


    @Test
    public void set_whenGc() throws InterruptedException {

        new Thread(() -> {

            ThreadLocal<String> th1 = new ThreadLocal<>();
            th1.set("val");
            System.out.println(Thread.currentThread().getName() + "thread th1. = " + th1.get());
            System.gc();
            System.out.println(Thread.currentThread().getName() + "thread th1. = " + th1.get());

            Thread thread = Thread.currentThread();
            Object threadLocals = null;
            try {
                Field threadLocal = Thread.class.getDeclaredField("threadLocals");
                threadLocal.setAccessible(true);
                threadLocals = threadLocal.get(thread);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            Object finalThreadLocals = threadLocals;
            new Thread(() -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "thread th1. = " + th1.get());
                System.out.println("threadLocals = " +finalThreadLocals);
                    Class<?>[] declaredClasses = ThreadLocal.class.getDeclaredClasses();
                    System.out.println("declaredClasses = " + Arrays.toString(declaredClasses));
                    Object cast = declaredClasses[1].cast(finalThreadLocals);
                    System.gc();
                    System.out.println("cast = " + cast);

            }).start();

        }).start();

        Thread.sleep(100);
    }

}
