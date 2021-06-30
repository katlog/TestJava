package name.katlog.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @moudle: TestGenricReflect
 * @version:v1.0
 * @author: katlog
 * @date: 2017年4月13日 上午10:21:26
 *
 */
public class TestGenericReflect {
	
	class CalssA<K,T,V extends Comparable<String>>{									//extends在类型参数的声明中使用
		
		public <M extends CalssA> M extendsGet(){return (M) new CalssA();}				//extends在类型参数的声明中使用
		
		//通过通配符接收任意指定泛型类型的对象
		public void set1(ClassB b){};
		public void set2(ClassB<?> b){};
		public void set3(ClassB<? extends CalssA> b){};
		public void set4(ClassB<? super CalssA> b){};
		public void set5(ClassB<? extends CalssA> b){
			ClassB<? super CalssA> b1 = (ClassB<? super CalssA>) b;
		};
	}
	
	class ClassB<T>{};
	
	@Test public void _demo(){
		TypeVariable<Class<CalssA>>[] typeParameters = CalssA.class.getTypeParameters();
		System.out.println(Arrays.toString(typeParameters));  // [K, T, V]
	}

	class ClassC extends ClassB<CalssA> {}

    @Test
    public void get() {
        ClassB<ClassC> b = new ClassB<>();

    }


    @Test public <E,T> void _common(){
		
		ArrayList<ArrayList> al1 = new ArrayList<ArrayList>();
		
		TypeVariable<?>[] typeParameters = al1.getClass().getTypeParameters();
		System.out.println(Arrays.toString(typeParameters));
		
		ArrayList<E> al2 = new ArrayList<>();
		ArrayList<String> al3 = new ArrayList<>();
		ArrayList<? extends Number> al4 = new ArrayList<>();
		ArrayList<E[]> al5 = new ArrayList<>();

	}

	class ParamClass{}

    interface AAA<T>{}
    class A implements AAA<ParamClass>{}

    class BBB<D,R>{}

    class B  <D> extends BBB<D,String> {
    }

    /** 类的父类、接口泛型信息 */
    @Test
    public void main() {

        Type[] genericInterfaces = A.class.getGenericInterfaces();
        System.out.println(Arrays.toString(genericInterfaces));

        Type genericSuperclass = B.class.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }



    /**
     * 泛型变量类型
     */
    @Test
    public void genericFieldType() throws NoSuchFieldException, SecurityException{
        Field field = MyFieldClass.class.getField("stringList");

        Type genericFieldType = field.getGenericType();

        if(genericFieldType instanceof ParameterizedType){
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for(Type fieldArgType : fieldArgTypes){
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println("fieldArgClass = " + fieldArgClass);
            }
        }
    }

    
    /**
     * 泛型方法返回类型
     */
    @Test
    public void genericMethodRetrunType() throws NoSuchMethodException, SecurityException{
        Method method = MyMethodClass.class.getMethod("getStringList", null);

        Type returnType = method.getGenericReturnType();

        if(returnType instanceof ParameterizedType){
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            for(Type typeArgument : typeArguments){
                Class typeArgClass = (Class) typeArgument;
                System.out.println("typeArgClass = " + typeArgClass);
            }
        }
    }
    
    /**
     * 泛型方法参数类型
     */
    @Test
    public void genericMethodParamType() throws NoSuchMethodException, SecurityException{
        Method method = MyMethodClass.class.getMethod("setStringList", List.class);

        Type[] genericParameterTypes = method.getGenericParameterTypes();

        for(Type genericParameterType : genericParameterTypes){
            if(genericParameterType instanceof ParameterizedType){
                ParameterizedType aType = (ParameterizedType) genericParameterType;
                Type[] parameterArgTypes = aType.getActualTypeArguments();
                for(Type parameterArgType : parameterArgTypes){
                    Class parameterArgClass = (Class) parameterArgType;
                    System.out.println("parameterArgClass = " + parameterArgClass);
                }
            }
        }
    }
}
//region fieldclass
class MyFieldClass<D>  {
    public List<D> dlist = new ArrayList<>();
    public List<String> stringList = new ArrayList<String>();
  }
//endregion

//region  methodclass 
class MyMethodClass {
    protected List<String> stringList = new ArrayList<String>();

    public List<String> getStringList() {
        return this.stringList;
    }

    public void setStringList(List<String> list){
        this.stringList = list;
    }
}
//endregion methodclass 

//region  parmclass 
class MyParamClass {
    protected List<String> stringList = new ArrayList<String>();

    public void setStringList(List<String> list) {
        this.stringList = list;
    }
}
//endregion parmclass