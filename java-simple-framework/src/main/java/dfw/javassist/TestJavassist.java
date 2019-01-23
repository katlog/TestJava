package dfw.javassist;

import javassist.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by fw on 2019/1/11
 */
public class TestJavassist {

    /** 读取和输出字节码 */
    @Test
    public void test1() throws CannotCompileException, IOException, NotFoundException {

        ClassPool pool = ClassPool.getDefault();
        // 会从classpath中查询该类
        CtClass cc = pool.get("dfw.javassist.TestJavassist");
        //设置.Rectangle的父类
        cc.setSuperclass(pool.get("test.Point"));
        //输出.Rectangle.class文件到该目录中
        cc.writeFile("c://");
        //输出成二进制格式
        //byte[] b=cc.toBytecode();
        //输出并加载class 类，默认加载到当前线程的ClassLoader中，也可以选择输出的ClassLoader。
        //Class clazz=cc.toClass();

    }

    /** 新增class */
    @Test
    public void test2(){
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("Point");
        //新增方法
        // cc.addMethod(m);
        //新增Field
        // cc.addField(f);
    }





}
