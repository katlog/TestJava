package name.katlog.collection.stream;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import name.katlog.util.JsonUtil;
import net.sf.json.util.JSONUtils;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;

/**
 * Created by fw on 2018/12/24
 */
public class TestStream {

    public static final Consumer<Integer> PRINTLN = System.out::println;


    @Test
    public void _construct(){

    }

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

    /**
     * collect三个参数，可替代Collector对象
     *
     * */
    @Test
    public void collect(){
        HashSet<Object> set = Arrays.asList(4, 5, 13, 3, 54, 14, 56, 721)
                .stream().collect(HashSet::new, HashSet::add, HashSet::addAll);
    }

    @Test
    public void collect_toList(){

        List<Integer> list = Arrays.asList(4, 5, 13, 3, 54, 14, 56, 721);
        List<Integer> collect = list.stream().filter(i -> i == -1).collect(toList());

        List<Long> list1 = list.stream()
                .map(String::valueOf)
                .map(Long::valueOf)
                .filter(aLong -> aLong > 10)
                .limit(10)
                .collect(Collectors.toList());

        /** collect 不会返回 null */
        assertNotNull(collect);
        assertEquals(0, collect.size());
    }

    /** --------------------  toMap  ----------------------------  */

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
    /** --------------------  toMap  ----------------------------  */


    /** --------------------  groupBy  ----------------------------  */

    @Data
    @AllArgsConstructor
    private static class Person implements Comparable<Person>{

        private int age;
        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public int compareTo(Person o) {
            return this.age - o.getAge();
        }
    }

    /**
     *
     *  普通的单参数groupingBy(f)（其中f是分类函数）实际上是groupingBy(f, toList())的简便写法。
     *
     *  三个参数groupingBy(Fun classifier,Supplier<Map> map,Collector collector)
     *      分类函数
     *      生成Map的函数，默认HashMap
     *      生成流的Collector，默认Collectors.toList()，对应到map中的value上
     *
     * */
    @Test
    public void collect_groupBy_toList(){
        List<String> list = Lists.newArrayList("1", "2", "3", "4", "1", "2", "1");

        // map1和map2是等价的
        Map<String, List<String>> map1 = list.stream().collect(groupingBy(Function.identity()));

        Map<String, List<String>> map2 = list.stream().collect(groupingBy(Function.identity(), toList()));

    }

    @Test
    public void collect_groupBy_treeMap(){

        // {1=[1, 1, 1], 2=[2, 2], 3=[3], 4=[4]}
        TreeMap<String, List<String>> tree = Lists.newArrayList("1", "2", "3", "4", "1", "2", "1")
                .stream()
                .collect(groupingBy(Function.identity(), TreeMap::new, toList()));

        Map.Entry<String, List<String>> firstEntry = tree.firstEntry();
        assertEquals("1", firstEntry.getKey());
        assertArrayEquals(new String[]{"1","1","1"}, firstEntry.getValue().toArray());

        Map.Entry<String, List<String>> lastEntry = tree.lastEntry();
        assertEquals("4", lastEntry.getKey());
        assertArrayEquals(new String[]{"4"}, lastEntry.getValue().toArray());


        //
        TreeMap<Person, List<Person>> personTree = Lists.newArrayList(new Person(10, "ka"), new Person(9, "ka"), new Person(11, "ka")
                , new Person(7, "li"), new Person(11, "li")
                , new Person(17, "lin"))
                .stream()
                .collect(groupingBy(Function.identity(), TreeMap::new, toList()));

        System.out.println("personTree = " + personTree);
    }

    /**
     * mapping(Function<> mapper, Collector<> downstream)
     *      mapper 函数对流中的元素做变换
     *      downstream 则将变换的结果对象收集起来
     */
    @Test
    public void collect_groupingBy_mapping(){

        List<Person> peoples = Lists.newArrayList(new Person(10, "ka"), new Person(9, "ka"), new Person(11, "ka")
                , new Person(7, "li"), new Person(11, "li")
                , new Person(17, "lin"));
        Map<Person, TreeSet<Person>> map = peoples
                .stream()
                .collect(groupingBy(Function.identity(), mapping(
                        Function.identity(), toCollection(TreeSet::new))));

        // 根据 Person#name 判重
        TreeSet<Person> kaTree = map.get(new Person(1, "ka"));
        assertNotNull(kaTree);
        assertEquals(9, kaTree.first().getAge());

        // map映射到name，并以LinkedList的形式收集起来
        Map<Person, LinkedList<String>> map1 = peoples.stream().collect(groupingBy(Function.identity()
                , mapping(Person::getName, toCollection(LinkedList::new))));

    }

    /**
     * collectingAndThen(Collector<> downstream, Function<R,RR> finisher)
     *     downstream 要转换的收集器
     *     finisher 及转换函数
     *     返回另一个收集器。
     *
     *  这个收集器相当于旧收集器的一个包装，collect操作的最后一步就是将返回值用转换函数做一个映射
     * */
    @Test
    public void collect_collectingAndThen(){
        Map<Integer, Person> collect = Lists.newArrayList(new Person(10, "ka"), new Person(9, "ka"), new Person(11, "ka")
                , new Person(7, "li"), new Person(11, "li")
                , new Person(17, "lin"))
                .stream()
                .collect(groupingBy(Person::getAge,
                                    collectingAndThen(maxBy(Comparator.comparing(Person::getName)), Optional::get)));
    }

    /** --------------------  groupBy  ----------------------------  */


    @Test
    public void create() {
        // Arrays.asList(1, 4, 9).stream()

    }


    @Test
    public void debug(){
        java.util.stream.Stream<Integer> headStream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19).stream();

        java.util.stream.Stream<Integer> firstStream = headStream
                .map(i -> i * 3);

        java.util.stream.Stream<Integer> secondStream = firstStream
                .map(i -> i - 1);

        java.util.stream.Stream<Integer> lastStream = secondStream
                .filter(i -> i > 30);

        Set<Integer> set = lastStream
                .collect(Collectors.toSet());
    }

    @Test
    public void createStreamTest(){

        List<Integer> list = Arrays.asList(1, 3, 4, 6, 7);
        List<Object> end = MyStream.of(list)
                .map(o -> o * o)
                .map(o -> o - 1)
                .map(String::valueOf)
                .end();


    }

   static class MyStream<T,R>{
        @Setter(AccessLevel.PRIVATE)
        MyStream next;
        @Setter(AccessLevel.PRIVATE)
        MyStream pervious;
        @Setter(AccessLevel.PRIVATE)

        MyStream head;
       Wrapper<T,R> func;

        static interface Wrapper<IN,OUT>{
            OUT accept(IN in);
        }

        abstract class BaseWapper<IN,OUT> implements Wrapper<IN,OUT>{



            protected final Wrapper<IN,OUT> downstream;

            public BaseWapper(Wrapper<IN,OUT> downstream) {
                this.downstream = Objects.requireNonNull(downstream);
            }

        }




        @Setter(AccessLevel.PRIVATE)
        List<T> srouce;

       public MyStream(Wrapper<T, R> func) {
           this.func = func;
       }


       public MyStream(MyStream next, MyStream pervious, MyStream head, Wrapper<T, R> func) {
           this.next = next;
           this.pervious = pervious;
           this.head = head;
           this.func = func;
       }

       public MyStream() {
       }

       static <T,R> MyStream<T,R> of(List<T> list) {
           MyStream<T,R> stream = new MyStream<>();
           stream.setSrouce(list);
           stream.setHead(stream);
           stream.setPervious(null);
           return stream;
        }

       MyStream<T,R> map(Function<T,R> function){



           MyStream<T,R> current = new MyStream<>(null, this, head, new BaseWapper<T, R>(null) {
               @Override
               public R accept(T t) {
                   return function.apply(t);
               }
           });
           this.setNext(current);
           return current;
       }

       List<R> end(){
           List<R> end = new ArrayList<>();
           for (Object t : head.srouce) {
               MyStream curr = head.next;
               Object o = t;
               while (curr != null && curr.func != null ) {
                   curr.func.accept(o);
                   Object e = curr.func.accept((T) o);
                   curr = curr.next;
                   o = e;
               }
               end.add((R) o);
           }
           return end;
       }
    }

    /** 可把流转换成并行流 */
    @Test
    public void parallel(){
        Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(10_000)
                .parallel()   // ←─将流转换为并行流
                .reduce(0L, Long::sum);

        System.out.println("sum = " + sum);
    }

    /** 可把并行流变成顺序流 */
    @Test
    public void sequential(){
        
    }
}
