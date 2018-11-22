package dfw.asm.guide.adapter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.V1_5;

public class ChangeVersionAdapter extends ClassVisitor {
    public ChangeVersionAdapter(ClassVisitor cv) {
        super(ASM4, cv);
    }
    @Override
    public void visit(int version, int access, String name,String signature, String superName, String[] interfaces) {
        cv.visit(V1_5, access, name, signature, superName, interfaces);
    }

    /**使用转换器*/
    public static void main(String[] args) {

        byte[] b1 = new byte[]{}; // ...
        ClassWriter cw = new ClassWriter(0);
        // cv 将所有事件转发给 cw
        ClassVisitor cv = new ChangeVersionAdapter(cw);
        ClassReader cr = new ClassReader(b1);
        cr.accept(cv, 0);
        byte[] b2 = cw.toByteArray();
        // b2 与 b1 表示同一个类


        // 优化

        //如果 ClassReader 和 ClassWriter 组件拥有对对方的引用
        //
        ClassReader cr1 = new ClassReader(b1);
        ClassWriter cw1 = new ClassWriter(cr1, 0);
        ChangeVersionAdapter ca = new ChangeVersionAdapter(cw1);
        cr1.accept(ca, 0);
        byte[] b12 = cw1.toByteArray();

    }

    /**使用转换后的类*/
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader l, String name, Class c, ProtectionDomain d, byte[] b)throws IllegalClassFormatException {
                ClassReader cr = new ClassReader(b);
                ClassWriter cw = new ClassWriter(cr, 0);
                ClassVisitor cv = new ChangeVersionAdapter(cw);
                cr.accept(cv, 0);
                return cw.toByteArray();
            }
        });
    }
}