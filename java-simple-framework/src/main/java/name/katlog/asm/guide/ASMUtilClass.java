package name.katlog.asm.guide;

import org.junit.Test;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.objectweb.asm.Opcodes.*;

/**
 * Created by dell on 2018/5/3
 */
public class ASMUtilClass {

    @Test
    public void typeUtil() throws NoSuchMethodException {

        // TYPE的构造
        Type intType = Type.INT_TYPE;

        Type intType1 = Type.getType(int.class);
        assertEquals(intType,intType1);

        Type v = Type.getType("Ljava/lang/Void;");
        Type v1 = Type.getType(Void.class);
        assertEquals(v,v1);

        // 获取内部名
        String stringInternalName = Type.getType(String.class).getInternalName();
        assertEquals("java/lang/String",stringInternalName);

        // 获取描述符
        String stringDescriptor = Type.getType(String.class).getDescriptor();
        assertEquals("Ljava/lang/String;",stringDescriptor);
        String methodDescriptor = Type.getMethodDescriptor(this.getClass().getMethod("typeUtil", null));
        assertEquals("()V",methodDescriptor);

        // 获取参数类型
        Type[] types = Type.getArgumentTypes("(I)V");
        assertArrayEquals(new Type[]{Type.INT_TYPE},types);

        // 获取返回类型
        Type returnType = Type.getReturnType("(I)V");
        assertEquals(Type.VOID_TYPE ,returnType);
    }

    @Test
    public void traceClassVisitor() {

        ClassWriter cw = new ClassWriter(0);

        PrintWriter printWriter = new PrintWriter(System.out);
        TraceClassVisitor cv = new TraceClassVisitor(cw, printWriter);

        generatorComparable(cv);
        byte[] bytes = cw.toByteArray();

    }

    private void generatorComparable(ClassVisitor cv) {

        cv.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pkg/Comparable", null, "java/lang/Object", new String[]{"pkg/Mesurable"});
        cv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, new Integer(-1)).visitEnd();
        cv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, new Integer(0)).visitEnd();
        cv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, new Integer(1)).visitEnd();
        cv.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        cv.visitEnd();
    }

    @Test
    public void CheckClassAdapter() {

        ClassWriter cw = new ClassWriter(0);
        PrintWriter printWriter = new PrintWriter(System.out);
        TraceClassVisitor tcv = new TraceClassVisitor(cw, printWriter);
        CheckClassAdapter cv = new CheckClassAdapter(tcv);

        generatorComparable(cv);

        byte b[] = cw.toByteArray();
    }

    @Test
    public void asmifier() {

    }

}
