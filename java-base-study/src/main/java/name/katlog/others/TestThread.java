package name.katlog.others;

import org.junit.Test;

/**
 * Created by fw on 2019/6/24
 */
public class TestThread {

    @Test
    public void sleep() throws InterruptedException {
        long before = System.currentTimeMillis();

        int i = 0;
        while (i++<10000) {
            Thread.sleep(1);
        }

        long after = System.currentTimeMillis();

        long diff = after - before;
        System.out.println("diff = " + diff);
    }
}
