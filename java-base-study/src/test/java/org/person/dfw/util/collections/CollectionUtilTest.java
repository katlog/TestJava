package org.person.dfw.util.collections;

import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class CollectionUtilTest {

    @Test
    public void split() {
        List<List<Integer>> split = CollectionUtil.split(IntStream.rangeClosed(1, 20).boxed().collect(toList()), 6);

        assertEquals(4, split.size());
    }
}