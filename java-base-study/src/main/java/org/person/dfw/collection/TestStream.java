package org.person.dfw.collection;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.junit.Assert.*;

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

        System.out.println("-----------------");
        list.stream()
                .sorted((Comparator.comparingInt(o -> (int) o).reversed()))
                .forEach(PRINTLN);
    }

    @Test
    public void collect_toList(){

        List<Integer> list = Arrays.asList(4, 5, 13, 3, 54, 14, 56, 721);
        List<Integer> collect = list.stream().filter(i -> i == -1).collect(toList());

        /** collect 不会返回 null */
        assertNotNull(collect);
        assertEquals(0, collect.size());
    }

    @Test
    public void collect_ToMap(){
        Map<String, String> stringMap = IntStream.rangeClosed(1, 100).mapToObj(Long::valueOf).collect(Collectors.toMap(o -> o + "", o -> o + ""));
        System.out.println("stringMap = " + stringMap);
    }

    /** 重复的key会报错 */
    @Test(expected = IllegalStateException.class)
    public void collect_ToMap_Exception(){
        Lists.newArrayList("1", "2", "3", "1").stream().collect(toMap(Function.identity(), s -> s + "-value"));
    }

    /** key重复时 处理两个value的措施 */
    @Test
    public void collect_ToMap_Exception_resolve(){
        Map<String, String> stringMap = Lists.newArrayList("1", "2", "3", "1").stream().collect(toMap(Function.identity(), s->s + "-value", (s, s2) -> s + s2));
        assertEquals("1-value1-value", stringMap.get("1"));
    }

    @Test
    public void create() {
        // Arrays.asList(1, 4, 9).stream()

    }
}
