package name.fw.thread.thread;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.sf.json.util.JSONUtils;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


    @Data@AllArgsConstructor
    class Person{
        String name;
        int age;
    }

    @Test
    public void set_whenGc() throws InterruptedException {

      Thread t1=  new Thread(() -> {
            ThreadLocal<Person> th2 = new ThreadLocal<>();
            Person value2 = new Person("LI",18);
            th2.set(value2);

            threadMap(Thread.currentThread());

            // 没有gc的时候 暂不回收key
            th2 = null;
            threadMap(Thread.currentThread());

            /**
             * 哈希表条目使用\作为键。但是，由于不使用引用队列，因此只有当表开始耗尽空间时，才保证删除过时的条目
             * */
            // gc 会把没有强引用的key回收 但 value和entry都还在
            value2 = null;
            System.gc();
            threadMap(Thread.currentThread());



        });

      t1.start();
      t1.join();

      threadMap(t1);

      Thread.sleep(100);
    }

    @Test
    public void _whenGc1() throws InterruptedException {

        Thread t1=  new Thread(() -> {

            List<Person> personList = IntStream.rangeClosed(1, 17).mapToObj(value -> new Person("LI" + value, value))
                    .collect(Collectors.toList());

            ThreadLocal<Person> th2 = new ThreadLocal<>();
            Person value2 = new Person("LI",18);
            th2.set(value2);
            th2.set(new Person("1", 1));

            threadMap(Thread.currentThread());

            // 没有gc的时候 暂不回收key
            threadMap(Thread.currentThread());

            /**
             * 哈希表条目使用\作为键。但是，由于不使用引用队列，因此只有当表开始耗尽空间时，才保证删除过时的条目
             * */
            // gc 会把没有强引用的key回收 但 value和entry都还在
            System.gc();
            threadMap(Thread.currentThread());



        });

        t1.start();

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
                    System.out.println("key:" + key + ",value:" + value + "\t,entry:" + entry);
                }
            }

            System.out.println(thread.getName()+"-------------print local end" );

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
