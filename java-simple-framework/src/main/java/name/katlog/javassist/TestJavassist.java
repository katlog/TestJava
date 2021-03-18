package name.katlog.javassist;

import javassist.*;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import javassist.util.proxy.Proxy;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Created by fw on 2019/1/11
 */
public class TestJavassist {

    class Point{}
    static class Graphic{}

    /** 读取和输出字节码
     *  Javassist的加载是依靠ClassPool类，输出方式支持三种。
     *      writeFile toByteCode toClass
     * */
    @Test
    public void write() throws CannotCompileException, IOException, NotFoundException {

        ClassPool pool = ClassPool.getDefault();
        // 会从classpath中查询该类
        CtClass cc = pool.get("dfw.javassist.TestJavassist$Graphic");
        //设置父类
        cc.setSuperclass(pool.get("dfw.javassist.TestJavassist$Point"));

        //1、输出.TestJavassist.class文件到该目录中
        cc.writeFile("c://");
        //2、输出成二进制格式
        //byte[] b=cc.toBytecode();
        //3、输出并加载class 类，默认加载到当前线程的ClassLoader中，也可以选择输出的ClassLoader。
        Class clazz=cc.toClass();
        System.out.println("clazz = " + clazz);

    }

    /** 新增class
     *   对Class的修改主要是依赖于CtClass类。
     * */
    @Test
    public void makeClass() throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("Point");

        //新增方法
        CtMethod ctMethod = CtMethod.make("public String getStr(){ return \"aaaa\";}", cc);
        cc.addMethod(ctMethod);
        //新增Field
        CtField ctField = CtField.make("public static int i = 10;", cc);
        cc.addField(ctField);

        cc.writeFile("c://");
    }

    /**  当CtClass 调用writeFile()、toClass()、toBytecode() 这些方法的时候，
     *      Javassist会冻结CtClass Object，对CtClass object的修改将不允许，此时可用 defrost
     *      */
    @Test
    public void defrost() throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeInterface("www.javassist.FakeInterface");
        ctClass.addMethod(CtMethod.make("void make();",ctClass));
        ctClass.toBytecode();

        ctClass.defrost();

        ctClass.addMethod(CtMethod.make("void make1();",ctClass));
        ctClass.toBytecode();
    }

    /**
     * ClassPool.getDefault() 方法的搜索Classpath 只是搜索JVM的同路径下的class。
     *      当一个程序运行在JBoss或者Tomcat下，ClassPool Object 可能找到用户的classes。
     *      Javassist 提供了四种动态加载classpath的方法
     *  */
    @Test
    public void insertClassPath() throws NotFoundException, IOException {

        //默认加载方式如pool.insertClassPath(new ClassClassPath(this.getClass()));
        ClassPool pool = ClassPool.getDefault();
        //从file加载classpath
        pool.insertClassPath("/usr/local/javalib");

        //从URL中加载
        ClassPath cp = new URLClassPath("www.javassist.org", 80, "/java/", "org.javassist.");
        pool.insertClassPath(cp);

        //从byte[] 中加载
        byte[] b = new byte[]{};
        pool.insertClassPath(new ByteArrayClassPath("ClassFromByte", b));

        //可以从输入流中加载class
        InputStream ins = null;
        CtClass ctClass = pool.makeClass(ins);
    }








    @Test
    public void getProxy() throws InstantiationException, IllegalAccessException {

        Object proxy = getProxy(Point.class);
        System.out.println("proxy = " + proxy);
    }

    public Object getProxy(Class<?> type) throws IllegalAccessException, InstantiationException {
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(type);
        f.setFilter(new MethodFilter() {
            public boolean isHandled(Method m) {
                // ignore finalize()
                return !m.getName().equals("finalize");
            }
        });

        Class c = f.createClass();
        MethodHandler mi = new MethodHandler() {
            public Object invoke(Object self, Method m, Method proceed,
                                 Object[] args) throws Throwable {
                System.out.println("method name: " + m.getName()+" exec");
                return proceed.invoke(self, args);  // execute the original method.
            }
        };
        Object proxy = c.newInstance();
        ((Proxy)proxy).setHandler(mi);
        return proxy;
    }


    class RayTest{
        public RayTest() {
        }
    }

    /**
     * javassist 生成动态代理可以使用两种方式，一种使用代理工厂创建，和普通的JDK动态代理和 CGLIB
     * 类似，另一种则可以使用 动态代码创建
     */
    // 代理工厂创建动态代理
    @Test
    public void testJavassistFactoryProxy() throws Exception {
        // 创建代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();

        // 设置被代理类的类型
        proxyFactory.setSuperclass(RayTest.class);

        // 创建代理类的class
        Class proxyClass = proxyFactory.createClass();

        // 创建对象
        RayTest proxyTest = (RayTest)proxyClass.newInstance();

        ((ProxyObject) proxyTest).setHandler(new MethodHandler() {
            // 真实主题
            RayTest test = new RayTest();

            @Override
            public Object invoke(Object self, Method thisMethod,
                                 Method proceed, Object[] args) throws Throwable {
                String before = "before ";
                Object str = thisMethod.invoke(test, args);
                String after = " after";
                return before + str + after;
            }
        });

        System.out.println("proxyTest = " + proxyTest);
    }


    static class Ray{ String exe(){ return "";} }

    // 动态代码创建的例子
    // 下面例子使用 Javassist 的 API成功组织出代理类的一个子类，可以看出 添加构造函数，添加属性，
    // 添加方法，内容 都是通过字符串类型完成即可。 通过 Javassist 强大的字节生成能力可以达到动态
    // 增加类和实现动态代理的功能.
    @Test
    public void testJavassistDefineClass() throws Exception  {
        // 创建类池，true 表示使用默认路径
        ClassPool classPool = new ClassPool(true);

        String className = Ray.class.getName();
        // 创建一个类
        CtClass ctClass = classPool.makeClass(className + "JavassistProxy");

        // 添加超类
        // 设置 XxxProxy 的父类是 Xx.
        ctClass.setSuperclass(classPool.get(Ray.class.getName()));

        // 添加默认构造函数
        ctClass.addConstructor(CtNewConstructor.defaultConstructor(ctClass));

        // 添加属性
        ctClass.addField(CtField.make("public " + className + " real = new " +
                className + "();", ctClass));

        // 添加方法，里面进行动态代理 logic
        ctClass.addMethod(CtNewMethod.make("public String exe() { return \"before \" + real.exe() + \" after\";}",
                ctClass));

        Ray rayTest = ((Class<Ray>) ctClass.toClass()).newInstance();
    }





}
