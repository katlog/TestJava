// Sample uses of varargs
package name.fw.effectivejava.examples.chapter07.item42;

import java.util.Arrays;
import java.util.List;

public class Varargs {

	// Simple use of varargs - Page 197
	static int sum(int... args) {
		int sum = 0;
		for (int arg : args)
			sum += arg;
		return sum;
	}

	// The WRONG way to use varargs to pass one or more arguments! - Page 197
//    static int min(int... args) {
//        if (args.length == 0)
//            throw new IllegalArgumentException("Too few arguments");
//        int min = args[0];
//        for (int i = 1; i < args.length; i++)
//            if (args[i] < min)
//                min = args[i];
//        return min;
//    }

	// The right way to use varargs to pass one or more arguments - Page 198
	static int min(int firstArg, int... remainingArgs) {
		int min = firstArg;
		for (int arg : remainingArgs)
			if (arg < min)
				min = arg;
		return min;
	}

	public static void main(String[] args) {
		System.out.println(sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		System.out.println(min(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		
		List<String> homophones = Arrays.asList("to","too","two");
		System.out.println(homophones);
		
		// obsolete idiom to print an array!
		String[] myArray = new String[]{"1","2","3"};
		System.out.println(Arrays.asList(myArray));
		
		int[] digits = {1,4,6,7,12,3};
		System.out.println(Arrays.asList(digits));
		
	}
	
	public static <T> List<T> gather(T... args) {
        return Arrays.asList(args);
    }
}
