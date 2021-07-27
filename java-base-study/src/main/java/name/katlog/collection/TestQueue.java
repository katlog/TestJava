package name.katlog.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by fw on 2020/8/10
 */
public class TestQueue {


    /** 会删除 */
    @Test
    public void poll(){
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(2);
        queue.add(1);
        queue.add(3);

        System.out.println("queue = " + queue);

        int poll = queue.poll();
        System.out.println("poll = " + poll);
        System.out.println("queue = " + queue);
    }

    /** 不会删除 */
    @Test
    public void element(){
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(2);
        queue.add(1);
        queue.add(3);

        System.out.println("queue = " + queue);

        int element = queue.element();
        System.out.println("element = " + element);
        System.out.println("queue = " + queue);

        element = queue.element();
        System.out.println("element = " + element);
        System.out.println("queue = " + queue);
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
