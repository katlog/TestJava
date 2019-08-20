package name.fw.thread.excutor;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by fw on 2019/8/5
 */
public class TestBlockingQueue {

    private BlockingQueue<Integer> container = new ArrayBlockingQueue<>(10);

    @Test
    public void put(){
        try {
            container.put(1);
            container.put(2);
            container.put(3);
            container.put(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
