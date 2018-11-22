package name.fw.thread.object;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @moudle: TestThreadNotifyall
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年3月30日 上午11:38:14
 *
 */
public class TestThreadNotifyall {
    private List list;

    public TestThreadNotifyall() {
        list = Collections.synchronizedList(new LinkedList());
    }

    public String removeItem()
        throws InterruptedException {
        print("in removeItem() - entering");
        synchronized (list) {
            if (list.isEmpty()) { // 这里用if语句会发生危险
                print("in removeItem() - about to wait()");
                list.wait();
                print("in removeItem() - done with wait()");
            }
            // 删除元素
            String item = (String) list.remove(0);
            print("in removeItem() - leaving");
            return item;
        }
    }

    public void addItem(String item) {
        print("in addItem() - entering");
        synchronized (list) {
            // 添加元素
            list.add(item);
            print("in addItem() - just added: '" + item + "'");
            // 添加后，通知所有线程
            list.notifyAll();
            print("in addItem() - just notified");
        }
        print("in addItem() - leaving");
    }

    private static void print(String msg) {
        String name = Thread.currentThread().getName();
        System.out.println(name + ": " + msg);
    }

    public static void main(String[] args) {
        final TestThreadNotifyall en = new TestThreadNotifyall();
        Runnable runA = new Runnable() {
            public void run() {
                try {
                    String item = en.removeItem();
                    print("in run() - returned: '" + item + "'");
                } catch (InterruptedException ix) {
                    print("interrupted!");
                } catch (Exception x) {
                    print("threw an Exception!!!\n" + x);
                }
            }
        };
        Runnable runB = new Runnable() {
            public void run() {
                en.addItem("Hello!");
            }
        };
        try {
            // 启动第一个删除元素的线程
            Thread threadA1 = new Thread(runA, "threadA1");
            threadA1.start();
            Thread.sleep(1000);
            // 启动第二个删除元素的线程
            Thread threadA2 = new Thread(runA, "threadA2");
            threadA2.start();
            Thread.sleep(500);
            // 启动增加元素的线程
            Thread threadB = new Thread(runB, "threadB");
            threadB.start();
            Thread.sleep(10000); // wait 10 seconds
            threadA1.interrupt();
            threadA2.interrupt();
        } catch (InterruptedException x) {
        }
    }
}
