package name.katlog.collection.queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 *  add offer
 *  remove poll
 *  element peek
 *
 */
public class TestQueue {

    /**
     * 相当于 addLast 返回 boolean
     * */
    @Test
    public void add(){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        assertEquals(1, (int) queue.element());
        assertArrayEquals(new Integer[]{1, 2, 3}, queue.toArray());

    }

    /**
     * 等同于 offerLast最终调用addLast 返回boolean
     *
     * */
    @Test
    public void offer(){

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(1, (int) queue.element());
        assertArrayEquals(new Integer[]{1, 2, 3}, queue.toArray());
    }


    /** 会删除 */
    @Test
    public void poll(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);


        assertEquals(1, (int) queue.poll());
        assertArrayEquals(new Integer[]{2, 3}, queue.toArray());
    }

    /**
     *
     * 相当于 peekFirst
     * */
    @Test
    public void peek(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);


        assertEquals(1, (int) queue.peek());
        assertArrayEquals(new Integer[]{1, 2, 3}, queue.toArray());
    }

    /**
     *  不会删除 相当于 getFirst
     *
     * */
    @Test
    public void element(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        assertEquals(1, (int) queue.element());
        assertArrayEquals(new Integer[]{1, 2, 3}, queue.toArray());
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person implements Comparable<Person>{
        int id;
        String name;
        int age;

        @Override
        public int compareTo(Person o) {
            return age-o.age;
        }
    }
    @Test
    public void priority(){

        List<Person> personList = new LinkedList<>();
        IntStream.rangeClosed(11,21)
                .forEach(value -> {
                    personList.add(new Person(value, "li" + value, value));
                });

        personList.add(new Person(11, "", 12));
        Map<Integer, PriorityQueue<Person>> collect = personList.stream()
                .collect(Collectors.groupingBy(Person::getId, Collectors.toCollection(PriorityQueue::new)));

        System.out.println("collect = " + collect);
    }
}
