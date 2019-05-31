package dfw.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by fw on 2019/3/28
 */
public class TestCache {

    LoadingCache<Long, Person> cache;
    private int cacheTimeoutSeconds = 10;

    Integer counter = 1;

    @Before
    public void initialize() {
        System.out.println("初始化");

        cache = CacheBuilder.newBuilder()
                /* 回收策略：基于容量（least-recently-used eviction when a maximum size is exceeded） */
                .maximumSize(10)
                // .initialCapacity(initialCapacity)

                /* 回收策略：基于存活时间（time-based expiration of entries, measured since last access or last write） */
                .expireAfterWrite(cacheTimeoutSeconds, TimeUnit.SECONDS)
                // .expireAfterAccess(duration, unit)
                // .refreshAfterWrite(duration, unit)

                /* 回收策略：基于权重 */
                // .maximumWeight(maximumWeight)
                // .weigher(weigher)

                /* 回收策略：基于引用（keys automatically wrapped in weak references, values automatically wrapped in weak or soft references）*/
                // .weakKeys()
                // .weakValues()
                // .softValues()

                // 设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
                // .concurrencyLevel(concurrencyLevel)

                /* 缓存访问统计（accumulation of cache access statistics） */
                .recordStats()

                /* 移除监听器（notification of evicted (or otherwise removed) entries） */
                // .removalListener(listener)

                .build(new CacheLoader<Long, Person>() {

                    /* 自动加载（automatic loading of entries into the cache） */
                    @Override
                    public Person load(Long id) throws Exception {
                        System.out.println("获取值, id=" + id);

                        // 调用接口获取值
                        Person p = new Person();
                        p.setId(id);
                        p.setName("name" + counter.toString());
                        counter++;

                        return p;
                    }
                });
    }

    @Test
    public void stats() {
        try {
            /* 获值 */
            // ConcurrentMap<Long, Person> asMap = cache.asMap();

            // cache.get(key); //
            // cache.getAll(keys);

            // cache.getIfPresent(key);
            // cache.getAllPresent(keys);

            // cache.size();

            /* 存值 */
            // cache.put(key, value);
            // cache.putAll(m); // Map<? extends K, ? extends V> m

            /* 移除/删除 */
            // cache.refresh(key);
            // cache.invalidate(key);
            // cache.invalidateAll();
            // cache.invalidateAll(keys);
            // cache.cleanUp();

            /* 缓存访问统计 */
            CacheStats stats = cache.stats();

            stats.averageLoadPenalty();
            stats.evictionCount();
            stats.hitCount();
            stats.hitRate();
            stats.loadCount();
            stats.loadExceptionCount();
            stats.loadExceptionRate();
            stats.loadSuccessCount();
            stats.missCount();
            stats.missRate();
            stats.requestCount();
            stats.totalLoadTime();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2() {
        try {
            Long id = 1L;
            Person person1 = cache.get(id);

            Thread.sleep(3L * 1000L);
            Person person2 = cache.get(id);

            Thread.sleep(11L * 1000L);
            Person person3 = cache.get(id);

            System.out.println(person1);
            System.out.println(person2);
            System.out.println(person3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** load 相当于 设定了 通过key获取value的函数，等到执行get时 若cache中没有的话才会放入进入  */
    @Test
    public void load() throws ExecutionException {

        Person initPerson = new Person(0L, "init");

        LoadingCache<Integer, Person> cache1 = CacheBuilder.newBuilder()
                .build(new CacheLoader<Integer, Person>() {
                    @Override
                    public Person load(Integer integer) throws Exception {
                        return initPerson;
                    }
                });
        assertEquals(initPerson, cache1.get(1));
        assertEquals(initPerson, cache1.get(2));
        assertEquals(initPerson, cache1.get(0));

        LoadingCache<Integer, Person> cache2 = CacheBuilder.newBuilder()
                .build(new CacheLoader<Integer, Person>() {
                    @Override
                    public Person load(Integer integer) throws Exception {
                        return new Person(Long.valueOf(integer),integer+"");
                    }
                });
        assertEquals(new Person(1L, "1"), cache2.get(1));
        assertEquals(new Person(0L, "0"), cache2.get(0));


        LoadingCache<Integer, Person> cache3 = CacheBuilder.newBuilder()
                .build(new CacheLoader<Integer, Person>() {
                    @Override
                    public Person load(Integer integer) throws Exception {
                        return producePerson(integer);
                    }
                    private Person producePerson(Integer integer) {
                        System.out.println(" produce  " + integer);
                        return new Person(Long.valueOf(integer),integer+"");
                    }
                });

        assertEquals(new Person(1L, "1"), cache3.get(1));
        assertEquals(new Person(1L, "1"), cache3.get(1));
        assertEquals(new Person(1L, "1"), cache3.get(1));
        // 会发现 日志中 produce 1只执行了1次
        assertEquals(new Person(0L, "0"), cache3.get(0));
    }

    /** 显示插入值 */
    @Test
    public void put(){
    }

    @Test
    public void get() throws ExecutionException {

        Cache<Integer, Person> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build();

        // 非自动加载的方式(自动加载)
        assertEquals(new Person(1L, "1"), cache.get(1, () -> {
            System.out.println(" manual 1" );
            return new Person(1L, "1");
        }));
        assertEquals(new Person(1L, "1"), cache.get(1, () -> {
            System.out.println(" manual 2" );
            return new Person(1L, "1");
        }));
    }
    
    /** 显示清除 */
    @Test
    public void invalidate(){
        
    }

    @Test
    public void invalidateAll(){

    }

    @Test
    public void reload(){

    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
}
