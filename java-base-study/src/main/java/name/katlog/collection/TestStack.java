package name.katlog.collection;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Created by fw on 2021/7/19
 */
public class TestStack {

    /** add方法返回true或false */
    @Test
    public void add(){
        Stack<String> stack = new Stack<>();

        boolean add = stack.add("1");
        assertTrue(add);

        stack.add("2");
        stack.add("3");

        assertEquals("[1, 2, 3]", stack.toString());
    }

    /** push方法返回插入的元素 */
    @Test
    public void push(){
        Stack<String> stack = new Stack<>();

        String push = stack.push("1");
        assertEquals("1", push);

        stack.push("2");
        stack.push("3");

        assertEquals("[1, 2, 3]", stack.toString());
    }

    /** peek不会删除栈顶元素 */
    @Test
    public void peek(){
        Stack<String> stack = new Stack<>();

        stack.push("1");
        stack.push("2");
        stack.push("3");

        String peek = stack.peek();
        assertEquals("3", peek);
        peek = stack.peek();
        assertEquals("3", peek);
    }

    /** pop会删除栈顶元素 */
    @Test
    public void pop(){
        Stack<String> stack = new Stack<>();

        stack.push("1");
        stack.push("2");
        stack.push("3");

        String pop = stack.pop();
        assertEquals("3", pop);
        pop = stack.peek();
        assertEquals("2", pop);
    }
}
