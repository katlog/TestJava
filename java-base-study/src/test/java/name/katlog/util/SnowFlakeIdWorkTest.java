package name.katlog.util;

import org.junit.Test;

import java.util.Arrays;

public class SnowFlakeIdWorkTest {

    @Test
    public void nextId() {
        long l = SnowFlakeIdWork.getInstance().nextId();
        System.out.println("l = " + l);
    }

    @Test
    public void split(){
        long l = SnowFlakeIdWork.getInstance().nextId();
        String [] split = SnowFlakeIdWork.splitFormat(0, l);
        System.out.println("splitBatchFun = " + Arrays.toString(split));

        System.out.println("splitBatchFun = " + Arrays.toString(SnowFlakeIdWork.splitFormat(0,1104000353513795584L)));
        // System.out.println("splitBatchFun = " + Arrays.toString(SnowFlakeIdWork.split(1104000353513795584L)));
        System.out.println("splitBatchFun = " + Arrays.toString(SnowFlakeIdWork.splitFormat(0, 1012441828497702912L)));
    }
}