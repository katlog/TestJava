package dfw.asm._01introduction;

import dfw.asm.util.AsmUtil;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class ASMGenerateSimpleClass {

    /**
     * 创建一个run方法，里面只有一个输出
     * public void run(){
     *      System.out.println(message);
     * }
     *
     * @return
     * @throws Exception
     */
    static byte[] createClass(String className, String message) throws Exception {

        //    /**
        //     * package com.agent.my3;
        //
        //     public class Tester {
        //        public void run(){
        //            System.out.println("This is my first ASM test");
        //        }
        //     }
        //     *
        //     * */
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        //注意，这里需要把classname里面的.改成/，如com.asm.Test改成com/asm/Test
        //声明一个类，使用JDK1.8版本，public的类，父类是java.lang.Object，没有实现任何接口
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className.replace('.', '/'), null, "java/lang/Object", null);

        AsmUtil.addNoArgsConstructor(cw);

        createVoidMethod(message, cw);

        return cw.toByteArray();
    }

    private static void createVoidMethod(String message, ClassWriter cw) {

        //创建run方法
        //()V表示函数，无参数，无返回值
        MethodVisitor runMethod = cw.visitMethod(Opcodes.ACC_PUBLIC, "run", "()V", null, null);

        //先获取一个java.io.PrintStream对象
        runMethod.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

        //将int, float或String型常量值从常量池中推送至栈顶  (此处将message字符串从常量池中推送至栈顶[输出的内容])
        runMethod.visitLdcInsn(message);

        //执行println方法（执行的是参数为字符串，无返回值的println函数）
        runMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        runMethod.visitInsn(Opcodes.RETURN);
        runMethod.visitMaxs(1, 1);
        runMethod.visitEnd();
    }

    public static void main(String[] args) throws Exception {

        String className = "com.agent.my3.Tester";
        byte[] classData = createClass(className, "This is my first ASM test");
        Class<?> clazz = new MyClassLoader().defineClassForName(className, classData);
        clazz.getMethods()[0].invoke(clazz.newInstance());

    }
}