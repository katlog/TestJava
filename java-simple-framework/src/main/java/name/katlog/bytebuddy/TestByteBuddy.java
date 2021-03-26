package name.katlog.bytebuddy;

import com.alibaba.fastjson.JSON;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.DefaultMethodCall;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.commons.collections.iterators.CollatingIterator;
import org.junit.Assert;
import org.junit.Test;

import java.beans.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * Created by fw on 2021/3/22
 */
public class TestByteBuddy {



    /** 添加类方法 */
    @Test
    public void modifyMethod() throws IllegalAccessException, InstantiationException {

        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(TestByteBuddy.class.getClassLoader())
                .getLoaded();

        String str = dynamicType.newInstance().toString();
        System.out.println(str);
    }

    public static class Hi {
        public static void main(String[] args) {
            System.out.println("Byte-buddy Hi HelloWorld ");
        }

    }

    /**
     * 生成类HelloWorld，void main方法被代理到Hi类对应方法
     *
     *  public static void main(String[] args) {
     *     Hi.main(var0);
     *  }
     *
     * */
    @Test
    public void generateClass(){
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("name.katlog.bytebuddy.HelloWorld")
                .defineMethod("main", void.class, Modifier.PUBLIC + Modifier.STATIC)
                .withParameter(String[].class, "args")
                .intercept(MethodDelegation.to(Hi.class))
                .make();

        // 输出类字节码
        outputClazz(dynamicType.getBytes());
    }

    @Test
    public void test_make() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        // 创建类
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                // 类名信息
                .name("name.katlog.bytebuddy.HelloWorld")
                // 定义方法
                .defineMethod("main", void.class, Modifier.PUBLIC + Modifier.STATIC)
                // 设置参数
                .withParameter(String[].class, "args")
                .intercept(MethodDelegation.to(Hi.class))
                .defineField("str", String.class, Modifier.PUBLIC)
                .make();

        // 加载类
        Class<?> clazz = dynamicType.load(TestByteBuddy.class.getClassLoader())
                .getLoaded();

        // 输出类字节码
        outputClazz(dynamicType.getBytes());

        // 反射调用
        clazz.getMethod("main", String[].class).invoke(clazz.newInstance(), (Object) new String[1]);
    }

    @Test
    public void test_helloWorld() throws IllegalAccessException, InstantiationException {
        DynamicType.Unloaded<Object> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("name.katlog.bytebuddy.HelloWorld")
                .method(named("toString")).intercept(FixedValue.value("Hello World!"))
                .make();

        // 输出类字节码
        outputClazz(dynamicType.getBytes());

        String toString = dynamicType.load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();

        System.out.println(toString);

    }



    private static void outputClazz(byte[] bytes) {
        FileOutputStream out = null;
        try {
            String pathName = TestByteBuddy.class.getResource("/").getPath() + "ByteBuddyHelloWorld.class";
            out = new FileOutputStream(new File(pathName));
            System.out.println("类输出路径：" + pathName);
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Moo{

    }

    @Test
    public void addField() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Moo moo = new ByteBuddy()
                .subclass(Moo.class)
                .defineField("name", String.class)
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .newInstance();

        System.out.println("moo = " + moo);
    }

    public interface NameInterceptor {
        String getName();
        void setName(String name);
    }

    /**
     * 添加字段，再添加对应的方法
     * */
    @Test
    public void addFileld2() throws IllegalAccessException, InstantiationException, IntrospectionException {
        Moo moo = new ByteBuddy()
                .subclass(Moo.class)
                .defineField("name", String.class, Modifier.PUBLIC)
                .implement(NameInterceptor.class).intercept(FieldAccessor.ofBeanProperty())
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .newInstance();

        // 打印新生成的类信息
        BeanInfo beanInfo = Introspector.getBeanInfo(moo.getClass());
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            System.out.println("propertyDescriptor = " + propertyDescriptor);
        }
        for (MethodDescriptor methodDescriptor : beanInfo.getMethodDescriptors()) {
            System.out.println("methodDescriptor = " + methodDescriptor);
        }

    }
}
