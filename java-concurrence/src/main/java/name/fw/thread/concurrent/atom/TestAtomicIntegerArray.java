package name.fw.thread.concurrent.atom;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

import static org.junit.Assert.assertEquals;

public class TestAtomicIntegerArray {

    @Test
    public void get(){
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1, 2, 3});
        atomicIntegerArray.addAndGet(1, 1);

        assertEquals(3, atomicIntegerArray.get(1));

        boolean b = atomicIntegerArray.compareAndSet(1, 2, 4);
        System.out.println("b = " + b);
    }
}
