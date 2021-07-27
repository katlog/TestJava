package name.katlog.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.Assert.*;

/** 双端队列接口 */
public class TestDeque {

    private Deque<String> deque;

    @Before
    public void before() {
        deque = new LinkedList<>(Arrays.asList("1","2","3","4"));
    }


    /** 添加元素：容量受限时抛异常 */
    @Test
    public void add(){
        deque.add("5");
        deque.add("6");

        assertArrayEquals(new String[]{"1","2","3","4", "5", "6"}, deque.toArray());
    }

    @Test(expected = IllegalStateException.class)
    public void add_error(){
        Deque<Integer> deque = new LinkedBlockingDeque<>(3);
        deque.add(1);
        deque.add(2);
        deque.add(3);
        assertArrayEquals(new Integer[]{1, 2, 3}, deque.toArray());

        // 会报错
        deque.add(4);
    }

    /** 添加元素：容量受限时不会抛异常，会返回false */
    @Test
    public void offer(){
        deque.offer("5");
        deque.offer("6");

        assertArrayEquals(new String[]{"1","2","3","4", "5", "6"}, deque.toArray());
    }

    @Test
    public void offer_success(){
        Deque<Integer> deque1 = new LinkedBlockingDeque<>(3);
        deque1.offer(1);
        deque1.offer(2);
        deque1.offer(3);
        assertArrayEquals(new Integer[]{1, 2, 3}, deque1.toArray());

        deque1.offer(4);
        assertArrayEquals(new Integer[]{1, 2, 3}, deque1.toArray());
    }

    /** 取出元素：不删除，等同于peekFirst */
    @Test
    public void peek() {
        assertEquals("1", deque.peek());

        assertEquals("1", deque.peek());
    }

    @Test
    public void peekLast(){
        assertEquals("4", deque.peekLast());

        assertEquals("4", deque.peekLast());
    }

    /** 取出元素：会删除 */
    @Test
    public void poll(){
        assertEquals("1", deque.poll());

        assertEquals("2", deque.poll());
    }

    /** 等同于：removeFirst */
    @Test
    public void remove(){
        assertEquals("1", deque.remove());

        assertEquals("2", deque.remove());
    }

    @Test
    public void removeLast(){
        assertEquals("4", deque.removeLast());

        assertEquals("3", deque.removeLast());
    }
}
