package name.katlog.collection.set;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import name.katlog.collection.queue.TestQueue;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by fw on 2019/6/21
 */
public class TestTreeSet {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person implements Comparable<Person>{
        String name;
        int age;

        @Override
        public int compareTo(Person o) {
            return age-o.age;
        }
    }

    @Test
    public void common(){
        TreeSet<String> set = new TreeSet<>();
        set.add("1");
        set.add("3");
        set.add("4");
        set.add("2");

        assertEquals("1", set.first());

        TreeSet<Person> personSet = new TreeSet<>();
        personSet.add(new Person("di", 10));
        personSet.add(new Person("li", 10));

        personSet.add(new Person("ka", 9));
        personSet.add(new Person("di", 9));
        personSet.add(new Person("li", 9));

        personSet.add(new Person("di", 7));
        personSet.add(new Person("li", 7));


        Person first = personSet.first();
        System.out.println("first = " + first);
    }
}
