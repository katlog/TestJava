package name.fw.thread.thread;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.sf.json.util.JSONUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestThreadLocal {


    /**  thread local的使用 */
    @Test
    public void set() throws InterruptedException {
        ThreadLocal<String> th1 = new ThreadLocal<>();
        th1.set("main");

        // 有值
        assertEquals("main", th1.get());

        // 空值
        new Thread(() -> {
            assertNull(th1.get());
        }).start();
    }

    @Test
    public void withInitial(){
        // 延迟产生所以都有
        ThreadLocal<String> th1 = ThreadLocal.withInitial(() -> "value");

        // 有值
        System.out.println(Thread.currentThread().getName() + "thread th1. = " + th1.get());

        // 也有值
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +" thread th1 = " + th1.get()
            );
        }).start();
    }


    @Data@AllArgsConstructor
    class Person{
        String name;
        int age;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


    @Test
    public void _whenGc() throws InterruptedException {

        Thread t1=  new Thread(() -> {

            ThreadLocal<Person> th2 = new ThreadLocal<>();
            Person person = new Person("1", 1);
            th2.set(person);

            // 没有gc的时候 暂不回收key
            // ①
            threadMap(Thread.currentThread());
            th2 = null;
            person = null;
            // ①
            threadMap(Thread.currentThread());

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // gc 会把没有强引用的key回收 但 value和entry都还在
            // key使用WeakReference(非必须对象)类型，只要有gc就会被回收
            System.out.println("set null and begin to gc....");
            System.gc();
            // ②
            threadMap(Thread.currentThread());

            // 保证主线程执行threadMap时t1线程还没执行完，否则，t1中的threadLocals字段为空了
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        t1.start();

        Thread.sleep(100);

        // ③
        threadMap(t1);

        Thread.sleep(100);
    }



    private void threadMap(Thread thread){
        // 找thread的threadLocalMap中的key（ThreadLocal类型）和value
        System.out.println(thread.getName()+"-------------print local begin" );
        try {

            // thread locals
            Field field = Thread.class.getDeclaredField("threadLocals");
            field.setAccessible(true);
            Object threadLocals = field.get(thread);
            if (threadLocals == null) {
                return;
            }
            Class<?> threadLocalsClass = threadLocals.getClass();

            // entry table
            Field tableFiled = threadLocalsClass.getDeclaredField("table");
            tableFiled.setAccessible(true);
            Object table = tableFiled.get(threadLocals);

            // entry elem
            for (int i = 0; i < Array.getLength(table); i++) {
                Object entry = Array.get(table, i);
                if (entry != null) {
                    // value
                    Class<?> entryClass = entry.getClass();
                    Field valueField = entryClass.getDeclaredField("value");
                    valueField.setAccessible(true);
                    Object value = valueField.get(entry);

                    // key
                    Field referentField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                    referentField.setAccessible(true);
                    Object key = referentField.get(entry);
                    System.out.println("key:" + key + ",\t\tvalue:" + value + "\t\t,entry:" + entry);
                }
            }

            System.out.println(thread.getName()+"-------------print local end" );

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
