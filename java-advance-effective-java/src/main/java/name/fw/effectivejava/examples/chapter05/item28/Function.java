// Generic Function interface - Page 122
package name.fw.effectivejava.examples.chapter05.item28;

interface Function<T> {
	T apply(T arg1, T arg2);
}
