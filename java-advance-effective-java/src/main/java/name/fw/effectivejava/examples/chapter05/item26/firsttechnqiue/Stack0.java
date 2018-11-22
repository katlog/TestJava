// Generic stack using E[] - Pages 125-127
package name.fw.effectivejava.examples.chapter05.item26.firsttechnqiue;

import java.util.Arrays;

public class Stack0 {
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack0() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0)
			throw new EmptyStackException();
		Object result = elements[--size];
		elements[size] = null; // Eliminate obsolete reference
		return result;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void ensureCapacity() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}

	// Little program to exercise our generic Stack
//	public static void main(String[] args) {
//		Stack0<String> stack = new Stack0<String>();
//		for (String arg : args)
//			stack.push(arg);
//		while (!stack.isEmpty())
//			System.out.println(stack.pop().toUpperCase());
//	}
}
