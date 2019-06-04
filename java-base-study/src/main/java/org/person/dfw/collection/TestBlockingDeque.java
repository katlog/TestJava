package org.person.dfw.collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class TestBlockingDeque {

    private BlockingDeque deque;

    @Before
    public void before() {
        deque = new LinkedBlockingDeque(Arrays.asList(1,2,3,4,5,6,7,8,9));
    }

    @Test
    public void peek() {
        Object peek = deque.peek();
        Assert.assertEquals(1,peek);

        Object peek1 = deque.peek();
        Assert.assertEquals(1,peek1);
    }

    public static void main(String [] args) {
        Queue<String> queue = new LinkedList<String>();

        //追加元素
        queue.offer("one");
        queue.offer("two");
        queue.offer("three");
        queue.offer("four");
        System.out.println(queue);

        //从队首取出元素并删除
        String poll = queue.poll();
        System.out.println(poll);
        System.out.println(queue);

        //从队首取出元素但是不删除
        String peek = queue.peek();
        System.out.println(peek);
        System.out.println(queue);

        //遍历队列，这里要注意，每次取完元素后都会删除，整个
        //队列会变短，所以只需要判断队列的大小即可
        while(queue.size() > 0) {
            System.out.println(queue.poll());
        }
    }

}
