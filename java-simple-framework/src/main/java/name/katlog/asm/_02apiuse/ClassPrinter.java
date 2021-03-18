package name.katlog.asm._02apiuse;

import org.objectweb.asm.*;

import javax.print.attribute.Attribute;
import java.io.IOException;

/**子用来打印class字节码内容，这里以java.lang.runnable为例*/
public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(Opcodes.ASM5);
    }

    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public void visitSource(String source, String debug) {
    }

    public void visitOuterClass(String owner, String name, String desc) {
    }

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return null;
    }

    public void visitAttribute(Attribute attr) {
    }

    public void visitInnerClass(String name, String outerName, String innerName, int access) {
    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println("filed:\t\t    " + desc + " " + name);
        return null;
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("method:\t\t " + name + desc);
        return null;
    }

    public void visitEnd() {
        System.out.println("}");
    }

    public static void main(String args[]) throws IOException {
        // ClassReader作为字节码生产者，ClassPrinter作为字节码消费者
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader("java.lang.String");
        cr.accept(cp, 0);
    }
}
