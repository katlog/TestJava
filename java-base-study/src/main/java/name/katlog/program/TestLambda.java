package name.katlog.program;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;


/**
 * @moudle: TestLambda 
 * @version:v1.0
 * @Description: 
 * @author: chensl
 * @date: 2017年2月16日 上午10:28:40
 *
 */
public class TestLambda {
	
	/**函数式接口*/
	@FunctionalInterface interface Action{
		void run(String param);
	}
	
	private void fun(Action action){
	}

	@Test public void _common(){
		fun(a->System.out.println(a));
	}
	
	/**
	 * java中，Lambda表达式是SAM类型，SAM类型是一个具有单一抽象方法的接口，即标注了@FunctionalInterface
	 * 		【java8接口可以包含非抽象方法了-即default/defender方法】
	 */
	@Test public void demo(){
		
		Runnable r =()->System.out.println("hello Lambda!");						//Runnable接口就是SAM类型

		Comparator<Integer> cmp=(x, y)->(x < y)?-1:((x > y)?1:0);					//Comparator接口也是SAM类型
	}
	
	/** Lambda 表达式是 模拟函数式编程的一个语法糖
	 * Lambda 的基本结构为 (arguments) -> body，有如下几种情况：
	 * 		1.参数类型可推导时，不需要指定类型，如 (a) -> System.out.println(a)
	 * 		2.当只有一个参数且类型可推导时，不强制写 (), 如 a -> System.out.println(a)
	 * 		3.参数指定类型时，必须有括号，如 (int a) -> System.out.println(a)
	 * 		4.参数可以为空，如 () -> System.out.println(“hello”)
	 * Java Lambda 表达式以函数式接口为基础
	 * 		只有一个方法（函数）的接口，这类接口的目的是为了一个单一的操作，也就相当于一个单一的函数了
	 * 		常见的接口如：Runnable, Comparator 都是函数式接口，并且都标注了注解 @FunctionalInterface 
	 */
	@Test public void demo1() {
		
		//Before Java 8:  
		new Thread(new Runnable() {  
			@Override  
			public void run() {  
				System.out.println("Before Java8, too much code for too little to do");  
			}  
		}).start();  
		//Java 8 way:  
		new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();  
		

		//例3 用Lambda表达式进行List迭代 
		//Prior Java 8 :  
		List<String> features = Arrays.asList("Lambdas", "Default Method", "MyStream API", "Date and Time API");
		for (String feature : features) {  
		    System.out.println(feature);  
		}  
		// Java 8 :  用匿名类的方式
		features.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
			
		});
		//In Java 8:  参数类型可推导时，不需要指定类型
		features.forEach(n -> System.out.println(n));  
		// 用java8的方法引用更好，方法引用由::(双冒号)操作符来完成
		features.forEach(System.out::println);
		
	}
	
	/**包java.util.function。其中包含了许多类来支持java函数式编程。
	 * 其中之一是Predicate接口，用此接口和lamb表达式就可以更少的代码为API方法添加更多的动态行为
	 */
	@Test public void  predicate(){
	    List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
	    
	    System.out.println("Languages which starts with J :");  
	    filter(languages, (str)->( (String) str ).startsWith("J"));  
	    System.out.println("Languages which ends with a ");  
	    filter(languages, (str)->( (String) str ).endsWith("a"));  
	    System.out.println("Print all languages :");  
	    filter(languages, (str)->true);  
	    System.out.println("Print no language : ");  
	    filter(languages, (str)->false);  
	    System.out.println("Print language whose length greater than 4:");  
	    filter(languages, (str)->( (String) str ).length() > 4);  
	}
	
	public static void filter(List<String> names, Predicate condition) {  
	    for(String name: names) {  
	        if(condition.test(name)) {  
	            System.out.println(name + " ");  
	        }  
	    }  
	} 
	
/*	public static void filter(List names, Predicate condition) {  
	    names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {  
	        System.out.println(name + " ");  
	    });  
	}*/  
	
	@Test public void mapAreduce(){
		// Without lambda expressions:  
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);  
		for (Integer cost : costBeforeTax) {  
		    double price = cost + .12*cost;  
		    System.out.println(price);  
		}   
		// With Lambda expression:  
		costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println); 
		
		// Old way
		double total = 0;  
		for (Integer cost : costBeforeTax) {  
		    double price = cost + .12*cost;  
		    total = total + price;  
		}  
		System.out.println("Total : " + total);  
		// New way:  
		double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();  
		System.out.println("Total : " + bill);  
	}
	
//	@Test public void demo2(){
//		//创建一个长度大于两个字符的字符串List  
//		List<String> filtered = strList.stream().filter(x -> x.length()>  
//		2).collect(Collectors.toList());  
//		System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);  
//	}
	
	//给每个List元素应用函数 
	@Test public void demo3(){
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy","U.K.","Canada");  
		String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));  
		System.out.println(G7Countries);  
	}
	
	//复制不同值到子列表 
	@Test public void demo4(){
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);  
		List<Integer> distinct = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());  
		System.out.printf("Original List : %s, Square Without duplicates : %s %n", numbers, distinct);  
	}
	
	//计算List中元素的最大，最小，和以及平均值 
	@Test public void demo5(){
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);  
		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();  
		System.out.println("Highest prime number in List : " + stats.getMax());  
		System.out.println("Lowest prime number in List : " + stats.getMin());  
		System.out.println("Sum of all prime numbers : " + stats.getSum());  
		System.out.println("Average of all prime numbers : " + stats.getAverage());  
	}

	/** 测试lambda表达式是不是一个对象 */
	@Test
	public void _isLambdaObject(){

		// 3次生成的同一个对象【可以用装饰器添加状态】
		for (int i = 0; i < 3; i++) {
			Action action = param -> {};
			print(action);
		}

		// 3次生成的3个对象
		for (int i = 0; i < 3; i++) {
			print(new Action() {
				@Override
				public void run(String param) {
				}
			});
		}
	}

	private void print(Action action){
		System.out.println("action = " + action);
		System.out.println("action = " + action.hashCode());
	}
}
