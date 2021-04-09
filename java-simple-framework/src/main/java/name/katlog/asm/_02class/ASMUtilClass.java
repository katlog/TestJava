package name.katlog.asm._02class;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;
import static org.objectweb.asm.Opcodes.*;

/**
 * Created by dell on 2018/5/3
 *
 *     在org.objectweb.asm.util 包中提供了几个工具，这些工具在开发类生成器或适配器时可能非常有用，但在运行时不需要它们
 */
public class ASMUtilClass {

    /**
     * 一个Type 对象表示一种Java 类型， 既可由类型描述符构造，也可由 Class 对象构建
     *
     *      获取内部名、描述符用
     */
    @Test
    public void Type() throws NoSuchMethodException {

        // TYPE的构造
        Type intType = Type.INT_TYPE;

        Type intType1 = Type.getType(int.class);
        assertEquals(intType,intType1);

        Type v = Type.getType("Ljava/lang/Void;");
        Type v1 = Type.getType(Void.class);
        assertEquals(v,v1);

        // 获取内部名 getInternalName
        String stringInternalName = Type.getType(String.class).getInternalName();
        assertEquals("java/lang/String",stringInternalName);

        // 获取描述符 getDescriptor
        String stringDescriptor = Type.getType(String.class).getDescriptor();
        assertEquals("Ljava/lang/String;",stringDescriptor);
        // Type 对象还可表示方法类型
        String methodDescriptor = Type.getMethodDescriptor(this.getClass().getMethod("Type", null));
        assertEquals("()V",methodDescriptor);

        // 获取参数类型 getArgumentTypes
        Type[] types = Type.getArgumentTypes("(I)V");
        assertArrayEquals(new Type[]{Type.INT_TYPE},types);

        // 获取返回类型 getReturnType
        Type returnType = Type.getReturnType("(I)V");
        assertEquals(Type.VOID_TYPE ,returnType);
    }


    @Test
    public void TraceClassVisitor() {

        ClassWriter cw = new ClassWriter(0);

        TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));

        generatorComparable(cv);
        byte[] bytes = cw.toByteArray();
        assertNotNull(bytes);
    }

    private void generatorComparable(ClassVisitor cv) {

        cv.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pkg/Comparable", null, "java/lang/Object", new String[]{"pkg/Mesurable"});
        cv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, new Integer(-1)).visitEnd();
        cv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, new Integer(0)).visitEnd();
        cv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, new Integer(1)).visitEnd();
        cv.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        cv.visitEnd();
    }

    /**
     * 验证其对方法的调用顺序是否适当，参数是否有效，然后才会委托给下一个访问器
     */
    @Test
    public void CheckClassAdapter() {

        ClassWriter cw = new ClassWriter(0);
        PrintWriter printWriter = new PrintWriter(System.out);
        TraceClassVisitor tcv = new TraceClassVisitor(cw, printWriter);
        CheckClassAdapter cv = new CheckClassAdapter(tcv);

        generatorComparable(cv);

        byte b[] = cw.toByteArray();
    }

    /**
     * 使 TraceClassVisitor 类的每个方法都会打印用于调用它的 Java 代码。
     *
     *      类似使用ASMPlugin插件的效果
     */
    @Test
    public void ASMifier() throws IOException {
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(new TraceClassVisitor(null, new ASMifier(), new PrintWriter(
                System.out)), 0);
    }

}
