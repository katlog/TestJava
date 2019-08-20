package org.person.dfw.util.collections;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class CollectionUtilTest {

    @Test
    public void split() {
        List<List<Integer>> split = CollectionUtil.split(IntStream.rangeClosed(1, 20).boxed().collect(toList()), 6);

        assertEquals(4, split.size());
        assertArrayEquals(new Integer[]{1,2,3,4,5,6}, split.get(0).toArray(new Integer[0]));
        assertArrayEquals(new Integer[]{7,8,9,10,11,12}, split.get(1).toArray(new Integer[0]));
        assertArrayEquals(new Integer[]{13,14,15,16,17,18}, split.get(2).toArray(new Integer[0]));
        assertArrayEquals(new Integer[]{19,20}, split.get(3).toArray(new Integer[0]));
    }


    @Test
    public void split_1() {
        List<List<Integer>> split = CollectionUtil.split(IntStream.rangeClosed(1, 20).boxed().collect(toList()), 6,integer -> ++integer);

        assertEquals(4, split.size());
        assertArrayEquals(new Integer[]{2,3,4,5,6,7}, split.get(0).toArray(new Integer[0]));
        assertArrayEquals(new Integer[]{8,9,10,11,12,13}, split.get(1).toArray(new Integer[0]));
        assertArrayEquals(new Integer[]{14,15,16,17,18,19}, split.get(2).toArray(new Integer[0]));
        assertArrayEquals(new Integer[]{20,21}, split.get(3).toArray(new Integer[0]));
    }

    @Test
    public void splitBatchFun() {
        List<List<Integer>> split = CollectionUtil.splitBatchFun(IntStream.rangeClosed(1, 20).boxed().collect(toList()), 6,
                integers -> integers.stream().map(integer -> ++integer).collect(Collectors.toList()));

        assertEquals(4, split.size());
        assertArrayEquals(new Integer[]{2,3,4,5,6,7}, split.get(0).toArray(new Integer[0]));
        assertArrayEquals(new Integer[]{8,9,10,11,12,13}, split.get(1).toArray(new Integer[0]));
        assertArrayEquals(new Integer[]{14,15,16,17,18,19}, split.get(2).toArray(new Integer[0]));
        assertArrayEquals(new Integer[]{20,21}, split.get(3).toArray(new Integer[0]));
    }

}