// Program to process marker annotations - Page 171
package name.fw.effectivejava.examples.chapter06.item35;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {
	public static void main(String[] args) throws Exception {
		int tests = 0;
		int passed = 0;
		Class testClass = Class.forName(args[0]);
		for (Method m : testClass.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Test.class)) {
				tests++;
				try {
					m.invoke(null);
					
					passed++;
				} catch (InvocationTargetException wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					System.out.println(m + " failed: " + exc);
				} catch (Exception exc) {
					System.out.println("INVALID @Test: " + m);
				}
			}

			if (m.isAnnotationPresent(ExceptionTest1.class)) {
				tests++;
				try {
					m.invoke(null);
					System.out.printf("Test %s failed: no exception%n", m);
				} catch (Throwable wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					Class<? extends Exception>[] excTypes = m.getAnnotation(
							ExceptionTest1.class).value();
					int oldPassed = passed;
					for (Class<? extends Exception> excType : excTypes) {
						if (excType.isInstance(exc)) {
							passed++;
							break;
						}
					}
					if (passed == oldPassed)
						System.out.printf("Test %s failed: %s %n", m, exc);
				}
			}
			// Array ExceptionTest processing code - Page 174
			if (m.isAnnotationPresent(ExceptionTest.class)) {
			    tests++;
			    try {
			        m.invoke(null);
			        System.out.printf("Test %s failed: no exception%n", m);
			    } catch (InvocationTargetException wrappedExc) {
			        Throwable exc = wrappedExc.getCause();
			        Class<? extends Exception> excType = m.getAnnotation(
			            ExceptionTest.class).value();
			        if (excType.isInstance(exc)) {
			            passed++;
                    }else {
                        
                        System.out.printf("Test %s failed: %s ,got %s%n", m,excType.getName(), exc);
                    }
			    }catch (Exception exc) {
			        System.out.println("INVALID @Test: " + m);
                }
			}
		}
		System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
	}
}
