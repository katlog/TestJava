
/**  
 * @Title: TestReflect.java
 * @Package: org.person.dfw.refelct
 * @author: 丰伟
 * @date: 2017年4月12日 上午9:13:10
 * @version: V1.0  
 */ 
package org.person.dfw.refelct;

import org.junit.Test;
import sun.misc.Contended;
import sun.net.www.protocol.file.FileURLConnection;

import javax.annotation.Tainted;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @moudle: TestReflect 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年4月12日 上午9:13:10
 *
 */
public class TestReflect {
    @Contended
    @Tainted
    class ReflectDemo{

        public class PublicInnerClass{}
        private class PrivateInnerClass{}
        protected class ProtectedInnerClass{}
        // 内部类无法再嵌套接口：inner class can not have static declarations
    }

    /**
     * Class的方法测试
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws NoSuchFieldException
     * https://blog.csdn.net/u014112608/article/details/73526554
     */ 
    @Test public void clazz() throws NoSuchMethodException, SecurityException, NoSuchFieldException
            , ClassNotFoundException, IllegalAccessException, InstantiationException {
        
        /**3种获取 class的方式*/
        Class<String> stringClass = String.class;                                    // 1、
        List<String> list = new ArrayList<String>();
        Class<? extends List> listClass = list.getClass();                         // 2、getClass方式
        Class clazz2 = Class.forName("java.lang.Object");                       // 3、Class.forName方式
        Class<ReflectDemo> demoClass = ReflectDemo.class;



        /**1、获取包package*/
        System.out.println("此类的包："+stringClass.getPackage().toString());

        System.out.println(Arrays.toString(Math.class.getTypeParameters()));
        System.out.println(int[].class.getComponentType().getName());

        /**类的‘修饰’*/

        /**类上的全部注解*/
        demoClass.getAnnotations();
//        assertArrayEquals(demoClass.getAnnotations(),new Annotation[] {Convert.class.newInstance(),Table.class.newInstance()});
        /**类上的注解*/

        demoClass.getAnnotatedInterfaces();
        demoClass.getAnnotatedSuperclass();
        demoClass.getAnnotationsByType(Contended.class);

        /**2、类本身 */
        
        /**2.1 类名*/
        /**2.1.1、普通类名 */
        assertEquals(stringClass.getName(), "java.lang.String");                // 看方法注释里的例子
        assertEquals(stringClass.getSimpleName(), "String");
        assertEquals(stringClass.getCanonicalName(), "java.lang.String");       // 类精确名，匿名类、数组(元素没类精确名) 返回null
        System.out.println("ArrayList<E>的类全名"+listClass.getName());
        System.out.println("ArrayList<E>的类简单名"+listClass.getSimpleName());
        /**2.1.2  数组类名*/
        System.out.println(String[].class.getClasses());
        /**2.1.2.1 数组类型*/
        System.out.println(String[].class.getComponentType().getName());
        
        /**2.2   类修饰符  public final*/
        assertTrue(Modifier.isPublic(stringClass.getModifiers()));
        assertTrue(Modifier.isFinal(stringClass.getModifiers()));
        assertEquals(Modifier.toString(stringClass.getModifiers()), "public final");
        
        System.out.println("String的修饰符："+Modifier.toString(stringClass.getModifiers()));
        
        
        /**2.3  父类*/
        /**2.3.1 类的父类*/
        assertEquals(stringClass.getSuperclass(), Object.class);

        
        /**2.4  类实现的接口*/
        assertArrayEquals(stringClass.getInterfaces(), new Object[]{Serializable.class, Comparable.class, CharSequence.class});
        Class<?>[] interfaces = stringClass.getInterfaces();
        for (Class<?> class1 : interfaces) {
			System.out.println(class1);
		}
        /**2.5  类注解*/
        /** @Link{https://blog.csdn.net/u014112608/article/details/73526554]*/
        System.out.println("全部注解："+Arrays.toString(stringClass.getAnnotations()));
        System.out.println("此类的全部注解："+Arrays.toString(stringClass.getDeclaredAnnotations()));
        assertNotNull(demoClass.getAnnotation(Contended.class));                          // 特定注解

        /**3  类结构*/
        /**3.1 构造器*/
        System.out.println("全部构造器："+Arrays.toString(stringClass.getConstructors()));
        System.out.println("此类的全部构造器："+Arrays.toString(stringClass.getDeclaredConstructors()));
        System.out.println("特定构造器："+stringClass.getDeclaredConstructor(byte[].class,int.class));
        /**3.2 方法*/
        System.out.println("全部方法："+Arrays.toString(stringClass.getMethods()));
        System.out.println("此类的全部方法："+Arrays.toString(stringClass.getDeclaredMethods()));
        System.out.println("特定方法："+stringClass.getDeclaredMethod("toString"));
        System.out.println("特定方法："+stringClass.getDeclaredMethod("compareTo",String.class));  //【此种方式有问题，不晓得在哪】
        /**3.3 变量*/ 
        System.out.println("全部变量："+Arrays.toString(stringClass.getFields()));
        System.out.println("此类的全部变量："+Arrays.toString(stringClass.getDeclaredFields()));
        System.out.println("特定变量："+stringClass.getField("CASE_INSENSITIVE_ORDER"));
//        System.out.println("特定变量："+stringClass.getField("hash"));							// 访问私有变量会报 错java.lang.NoSuchFieldException

    }



    
    class Generic1<T extends Cloneable&Serializable,E>{};
	class Generic2<T extends Cloneable,R extends Generic1>{};
    
    @Test public void generic(){

		Generic2<Cloneable, Generic1> generic2 = new Generic2<>();
		Class<? extends Generic2> class1 = generic2.getClass();
		TypeVariable<?>[] typeParameters = class1.getTypeParameters();
		
		for (TypeVariable<?> typeVariable : typeParameters) {
			
			if (typeVariable instanceof ParameterizedType) {
				
			}/*else if (typeParameters instanceof GenericArrayType) {
				
			}*/
			
			
			System.out.println(typeVariable);
			Type[] bounds = typeVariable.getBounds();
			for (Type type : bounds) {
				System.out.println(type);
			}
		}
		
    }
    
    
    
    @Test public void getResource(){
    	URL url = this.getClass().getResource("resource.xml"); //加载的是当前目录【包下】
//    	URL url = this.getClass().getResource("/resource.xml"); //加载的是classPath下的
    	FileURLConnection openConnection;
		try {
			openConnection = (FileURLConnection) url.openConnection();
			InputStream inputStream = openConnection.getInputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = inputStream.read(b))!=-1) {
				System.out.println(new String(b));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 类路径相关的测试
     * <p>date : 2017年4月18日 上午9:40:05</p>
     * @throws MalformedURLException 
     */ 
    @Test
    public void classPath() throws MalformedURLException{
        
        String classPath = "file:/C:/Users/lenovo/git/TestJava/TestJava/target/classes/";
        String packagePath = classPath+"org/person/dfw/refelct/";
        
        /**不以'/'开头时，默认是从此类所在的包下取资源*/
        assertEquals(TestReflect.class.getResource(""), new URL(packagePath));//file:/C:/Users/lenovo/git/TestJava/TestJava/target/classes/org/person/dfw/refelct/
        /**以'/'开头时，则是从项目的ClassPath根下获取资源配【技巧/相当于Linux下根目录感觉】*/
        assertEquals(TestReflect.class.getResource("/"), new URL(classPath));//file:/C:/Users/lenovo/git/TestJava/TestJava/target/classes/
        
        /**user.dir项目文件路径*/
        System.out.println(System.getProperty("user.dir"));//C:\Users\lenovo\git\TestJava\TestJava
        
        //class.getResource("/") == class.getClassLoader().getResource("")
        
        /**不能以'/'开头时，指类加载器的加载范围，在资源加载的过程中，使用的逐级向上委托的形式加载的*/
        System.out.println(TestReflect.class.getClassLoader().getResource(""));//file:/C:/Users/lenovo/git/TestJava/TestJava/target/classes/
        /**'/'表示Boot ClassLoader中的加载范围，因为这个类加载器是C++实现的，所以加载范围为null*/
        System.out.println(TestReflect.class.getClassLoader().getResource("/"));

    }
    
    /**
     * Java 反射机制通过 java.lang.reflect.Array 这个类来处理数组。不要把这个类与 Java 集合套件（Collections suite）
     * 中的 java.util.Arrays 混淆， java.util.Arrays 是一个提供了遍历数组，将数组转化为集合等工具方法的类
     * @throws ClassNotFoundException 
     */ 
    @Test public void array() throws ClassNotFoundException{
        /**创建数组*/
        int[] intArray = (int[]) Array.newInstance(int.class, 3);
        /**访问数组*/
        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 456);
        Array.set(intArray, 2, 789);

        System.out.println("intArray[0] = " + Array.get(intArray, 0));
        System.out.println("intArray[1] = " + Array.get(intArray, 1));
        System.out.println("intArray[2] = " + Array.get(intArray, 2));
        
        /**获取数组的Class对象*/
        //1、直接获取
        Class stringArrayClass1 = String[].class;
        //2、forName获取
        Class stringArrayClass2 = Class.forName("[Ljava.lang.String;");
        Class intArray2 = Class.forName("[I");  //获取基本类型时
        //3、基本数据类型获取【有问题】
        try {
            Class intClass1 = Class.forName("I");
            Class intClass2 = Class.forName("int");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        /**获取数组的成员类型 getComponentType */
        String[] strings = new String[3];
        Class stringArrayClass = strings.getClass();
        Class stringArrayComponentType = stringArrayClass.getComponentType();
        System.out.println("数组成员类型:"+stringArrayComponentType);
    }


    enum  EnumDemo {
        A,B,C;
    }
    /**枚举相关*/
    @Test
    public void enums() {
        Class<EnumDemo> enumClass = EnumDemo.class;
        assertArrayEquals(enumClass.getEnumConstants(),new Enum[]{EnumDemo.A,EnumDemo.B,EnumDemo.C});
    }


    static class Son extends Fathter implements Serializable{}
    static class Fathter{}

    /** 父子类关系 */
    @Test
    public void classRelation() {
        assertTrue(Fathter.class.isAssignableFrom(Son.class));
        assertTrue(Serializable.class.isAssignableFrom(Son.class));
    }

    /** 个对象强制转换成此 Class 对象所表示的类或接口 */
    @Test
    public void cast(){
        Son son = new Son();
        Fathter fathter =son;
        Class<Son> targetClass = Son.class;

        // 反射转换
        assertEquals(son, targetClass.cast(fathter));
        // 手动转化
        assertEquals(son, (Son) fathter);
    }

    @Test
    /** 是否是原始类型 */
    public void isPrimitive() {

        assertTrue(int.class.isPrimitive());
        assertTrue(long.class.isPrimitive());
        assertTrue(short.class.isPrimitive());
        assertTrue(double.class.isPrimitive());
        assertTrue(float.class.isPrimitive());
        assertTrue(char.class.isPrimitive());
        assertTrue(boolean.class.isPrimitive());


        assertFalse(Integer.class.isPrimitive());
        assertFalse(Long.class.isPrimitive());
        assertFalse(Short.class.isPrimitive());

    }
    
}
