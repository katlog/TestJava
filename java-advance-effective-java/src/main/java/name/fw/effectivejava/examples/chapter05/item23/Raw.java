package name.fw.effectivejava.examples.chapter05.item23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Raw {
	// Uses raw type (List) - fails at runtime! - Page 112
	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>();
		unsafeAdd(strings, new Integer(42));
		String s = strings.get(0); // Compiler-generated cast
		
		
		Set<String> s1 = new HashSet();
		Set<String> s2 = new HashSet();
		rawNumElementsInCommon(s1, s2);
		numElementsInCommon(s1, s2);
		
		Set o = new HashSet();
        // Legitimate user of raw type - instanceof opertator
        if(o instanceof Set){             // Raw type
            Set<?> m = (Set<?>)o;    //Wildcard type
        }

        Set<Lark> exaltation = new HashSet();
	}

	private static  void unsafeAdd(List list, Object o) {
//    private static  void unsafeAdd(List<?> list, Object o) {
//	private static  void unsafeAdd(List<Object> list, Object o) {
//  private static  void unsafeAdd(List<? super Object> list, Object o) {
//  private static  void unsafeAdd(List<? super Comparable> list, String o) {
		list.add(o);
	}

	// Use of raw type for unknown element type - don't do this! - Page 113
	static int rawNumElementsInCommon(Set s1, Set s2) {
		int result = 0;
		for (Object o1 : s1)
			if (s2.contains(o1))
				result++;
		

		return result;
	}

	// Unbounded wildcard type - typesafe and flexible - Page 113
	static int numElementsInCommon(Set<?> s1, Set<?> s2) {
		int result = 0;
		for (Object o1 : s1)
			if (s2.contains(o1))
				result++;
		return result;
	}
	
	

    private Object[] elementData;
    private int size;
	
    // Adding local variable to reduce scope @SuppressWarnigs
    public <T> T[] toArray(T[] a) {
        if (a.length < size){
            // This cast is correct because the array we're creating
            // is of the same type as the one passed in, which is T[]:
            @SuppressWarnings("unchecked")
            T[] result = (T[]) Arrays.copyOf(elementData, size, a.getClass());
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

}
class Lark{}