package org.person.dfw.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Created by fw on 2018/12/24
 */
public class TestStream {

    public static final Consumer<Integer> PRINTLN = System.out::println;

    @Test
    public void sorted() {
        List<Integer> list = Arrays.asList(4, 5, 13, 3, 54, 14, 56, 721);

        list.stream()
                .sorted()
                .forEach(PRINTLN);

        // list.stream()
        /** [NPE] */
        //         .sorted(null)
        //         .forEach(PRINTLN);

        list.stream()
                .sorted((o1, o2) -> 1)
                .forEach(PRINTLN);
    }
}