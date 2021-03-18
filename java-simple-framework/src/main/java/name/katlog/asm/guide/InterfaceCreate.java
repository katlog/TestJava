package name.katlog.asm.guide;

import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.*;

/**
 * package pkg;
 * public interface Comparable extends Mesurable {
 *      int LESS = -1;
 *      int EQUAL = 0;
 *      int GREATER = 1;
 *      int compareTo(Object o);
 * }
 */
public class InterfaceCreate {

    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pkg/Comparable", null, "java/lang/Object", new String[]{"pkg/Mesurable"});
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, -1).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, 0).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, 1).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class c = myClassLoader.defineClass("pkg.Comparable", b);
    }

    static class MyClassLoader extends ClassLoader {
        public Class defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }

   static class StubClassLoader extends ClassLoader {
        @Override
        protected Class findClass(String name)throws ClassNotFoundException {
            if (name.endsWith("_Stub")) {
                ClassWriter cw = new ClassWriter(0);
                // ... 生成class的过程
                byte[] b = cw.toByteArray();
                return defineClass(name, b, 0, b.length);
            }
            return super.findClass(name);
        }
    }
}

