package name.fw.thread.concurrent.atom;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestAtomicInteger {

    @Test
    public void accumulateAndGet(){
        AtomicInteger ai = new AtomicInteger(5);
        int i = ai.accumulateAndGet(1, (left, right) -> right);
        System.out.println("i = " + i);
    }

    /**  只用一次 unsafe.compareAndSwapInt */
    @Test
    public void compareAndSet(){
        AtomicInteger atomicInteger = new AtomicInteger(3);

        assertFalse(atomicInteger.compareAndSet(2, 4));
    }


    /**  循环判断  compareAndSwapInt  */
    @Test
    public void increment(){
        AtomicInteger atomicInteger = new AtomicInteger(2);

        assertEquals(3, atomicInteger.incrementAndGet());
    }

}
