package name.fw.thread.concurrent.atom;

import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;

public class TestLongAdder {

    @Test
    public void sun(){
        LongAdder adder = new LongAdder();
        // 线程安全
        adder.add(10);
    }
}
