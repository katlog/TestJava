package name.katlog.collection.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by fw on 2021/8/25
 */
public class TestTreeMap {

    @Data
    @AllArgsConstructor
    private static class Person implements Comparable<Person>{
        private int age;
        private String name;

        @Override
        public int compareTo(Person o) {
            return age - o.getAge();
        }
    }

    @Test
    public void firstKey(){
        TreeMap<String,Integer> map = new TreeMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        assertEquals("1", map.firstKey());

        map.remove("1");
        assertEquals("2", map.firstKey());

        TreeMap<Person,Integer> personMap = new TreeMap<>();
        personMap.put(new Person(10,"li"), 10);
        personMap.put(new Person(9,"li"), 10);

        // 第2个
        personMap.put(new Person(8,"ka"), 10);
        personMap.put(new Person(8, "li"), 110);

        // 第1个
        personMap.put(new Person(7,"li"), 10);
        // 值会被替换
        personMap.put(new Person(7,"di"), 17);

        // first entry
        Map.Entry<Person, Integer> firstEntry = personMap.firstEntry();
        assertEquals(7, firstEntry.getKey().getAge());
        assertEquals("li", firstEntry.getKey().getName());

        assertEquals(17, firstEntry.getValue(), 1.000);

    }
}
