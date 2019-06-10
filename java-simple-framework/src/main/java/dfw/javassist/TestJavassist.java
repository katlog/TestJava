package dfw.javassist;

import javassist.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by fw on 2019/1/11
 */
public class TestJavassist {

    class Point{}
    static class Graphic{}

    /** 读取和输出字节码
     *  Javassist的加载是依靠ClassPool类，输出方式支持三种。
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
     * Javassist会冻结CtClass Object，对CtClass object的修改将不允许 */
    @Test
    public void defrost(){
    }





}
