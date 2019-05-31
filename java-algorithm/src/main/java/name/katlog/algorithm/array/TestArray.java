
package name.katlog.algorithm.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @moudle: TestArray 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年6月30日 上午11:46:04
 * 数组的操作
 */
public class TestArray {
	int[] array = {1,2,3,4,5};
	
	/**添加不同元素*/
	@Test public void add1(){
		int[] coArray = new int[array.length+1];
		
		int n = -1;
		int a = 3;
		int b = 4;
		for (int i = 0; i < array.length; i++) {
			if (a==array[i]) {
				
			}
		}
	}
	
	@Test public void remove(){
		String[] noInterest = new String[] { "109", "111"};
		boolean contains = false;
		String areaCode = "109";
		if (areaCode !=null) {
			contains = Arrays.asList(noInterest).contains(areaCode);
		}
		System.out.println(contains);
		System.out.println((Arrays)(Object)noInterest[1]);
	}
	
	//region  sort--------方法测试 
		@Test public void sort(){
        Person[] persons = new Person[5];
        persons[0] =new Person("tom",45);
        persons[1] =new Person("jack",12);
        persons[2] =new Person("bill",21);
        persons[3] =new Person("kandy",34);
        persons[4] =new Person();
        Arrays.sort(persons);												//排序：实现了Comparator接口或
//        ListUtil.format(Style.Vertical, null, persons);
        
        Student[] students = new Student[5];
        students[0] =new Student("tom",1);
        students[1] =new Student("jack",1);
        students[2] =new Student("bill",4);
        students[3] =new Student("kandy",2);
        students[4] =new Student("lily",5);
        
        /*Arrays.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getNumber()-o2.getNumber();
			}
        	
		});*/
        Arrays.sort(students, (o1, o2)->o1.getNumber()-o2.getNumber());		//排序：增加Comparator接口参数，用lambda方式
//        ListUtil.format(Style.Vertical, null, students);
	}
	
	
	class Person implements Comparable<Person> {
		private String name;
		private int age;
		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
		public Person() {
			this("unknown", 0);
	    }
	    //重写该类的compareTo()方法，使其按照从小到大顺序排序
	    @Override public int compareTo(Person o) {
	         return age-o.age;
	         
	    }
	    @Override public String toString() {      
	        return "Person[name:"+name+",age:"+age+"]";
	    }
	}
	
	class Student {
	    private String name;
	    private int number;
	     
	    public Student(String name,int number){
	        this.name = name;
	        this.number = number;
	    }
	    @Override
	    public String toString() {      
	        return "Student[name:"+name+",number:"+number+"]";
	    }
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public int getNumber() {
	        return number;
	    }
	    public void setNumber(int number) {
	        this.number = number;
	    }
	}
	
	//endregion sort--------方法测试 

}	
