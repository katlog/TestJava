package org.person.dfw.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SnowFlakeIdWorkTest {

    @Test
    public void nextId() {
        long l = SnowFlakeIdWork.getInstance().nextId();
        System.out.println("l = " + l);
    }

    @Test
    public void split(){
        SnowFlakeIdWork.getInstance().nextId();
        SnowFlakeIdWork.getInstance().nextId();
        SnowFlakeIdWork.getInstance().nextId();
        long l = SnowFlakeIdWork.getInstance().nextId();
        long[] split = SnowFlakeIdWork.split(l);
        System.out.println("split = " + Arrays.toString(split));

        System.out.println("split = " + Arrays.toString(SnowFlakeIdWork.split(1012400433719853056L)));
        System.out.println("split = " + Arrays.toString(SnowFlakeIdWork.split(1012441828497702912L)));
    }
}