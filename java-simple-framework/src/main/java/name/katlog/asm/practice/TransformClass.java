package name.katlog.asm.practice;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TransformClass {
    public static void main(String[] args) {
        try {
            ClassReader cr = new ClassReader("com/asm5/C");
            // ClassWriter extends ClassVisitor
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassVisitor classAdapter = new AddTimeClassAdapter(cw);
            // 使给定的访问者访问Java类的ClassReader
            cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
            byte[] data = cw.toByteArray();
            File file = new File("/Users/fan/Computer/Java/ASMTest/bin/com/asm5/C.class");
            FileOutputStream fout = new FileOutputStream(file);
            fout.write(data);
            fout.close();
            System.out.println("success!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class AddTimeClassAdapter extends ClassVisitor {
        private String owner;
        private boolean isInterface;

        public AddTimeClassAdapter(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        // visit the head of the class
        @Override
        public void visit(int version, int access, String name, String signature,
                          String superName, String[] interfaces) {
            cv.visit(version, access, name, signature, superName, interfaces);
            // the internal name of the class
            owner = name;
            // 判断类是否是接口
            isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc,
                                         String signature, String[] exceptions) {
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            if (!name.equals("<init>") && !isInterface && mv != null) {
                // 为方法添加计时功能
                mv = new AddTimeMethodAdapter(mv);
            }
            return mv;
        }

        @Override
        public void visitEnd() {
            // 添加字段
            if (!isInterface) {
                FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                        "timer", "J", null, null);
                if (fv != null) {
                    fv.visitEnd();
                }
            }
            cv.visitEnd();
        }

        class AddTimeMethodAdapter extends MethodVisitor {

            public AddTimeMethodAdapter(MethodVisitor mv) {
                super(Opcodes.ASM5, mv);
            }

            // Starts the visit of the _03method's code, if any(i.e. non abstract _03method)
            @Override
            public void visitCode() {
                mv.visitCode();
                // Visits a field instruction
                mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
                // Visits a _03method instruction
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
                        "currentTimeMillis", "()J", isInterface);
                // Visits a zero operand instruction
                mv.visitInsn(Opcodes.LSUB);
                mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
            }

            @Override
            public void visitInsn(int opcode) {
                if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
                        || opcode == Opcodes.ATHROW) {
                    mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
                            "currentTimeMillis", "()J", isInterface);
                    mv.visitInsn(Opcodes.LADD);
                    mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
                }
                mv.visitInsn(opcode);
            }

            @Override
            public void visitMaxs(int maxStack, int maxLocal) {
                mv.visitMaxs(maxStack + 4, maxLocal);
            }
        }
    }
}