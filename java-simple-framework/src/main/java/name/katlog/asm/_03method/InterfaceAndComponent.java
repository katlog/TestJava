package name.katlog.asm._03method;

import name.katlog.asm.util.AsmUtil;
import org.objectweb.asm.*;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.PUTSTATIC;

/**
 * 第3章：方法部分  接口与组件
 * Created by fw on 2021/4/9
 */
public class InterfaceAndComponent {


    /**
     * 示例1：生成方法
     */
    public static class GenerateMethod{



        public static void main(String[] args) {
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

            cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"com/method/Getter", null, "java/lang/Object", null);

            AsmUtil.addNoArgsConstructor(cw);

            cw.visitField(Opcodes.ACC_PRIVATE, "f", "I", null, null).visitEnd();

            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "getter", "()I", null,null);

            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "pkg/Bean", "f", "I");
            mv.visitInsn(IRETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();

            cw.visitEnd();

            ClassReader cr = new ClassReader(cw.toByteArray());
            cr.accept(new TraceClassVisitor(new PrintWriter(System.out)),0 );

        }

    }


    /**
     * 示例2：转换方法
     */
    public static class TransformMethod{

        /** 删除NOP指令的适配器，NOP指令不做任何事情，可以删除 */
        public class RemoveNopAdapter1 extends MethodVisitor {
            public RemoveNopAdapter1(MethodVisitor mv) {
                super(ASM4, mv);
            }
            @Override
            public void visitInsn(int opcode) {
                if (opcode != NOP) {
                    mv.visitInsn(opcode);
                }
            }
        }

        public class RemoveNopClassAdapter2 extends ClassVisitor {
            public RemoveNopClassAdapter2(ClassVisitor cv) {
                super(ASM4, cv);
            }
            @Override
            public MethodVisitor visitMethod(int access, String name,String desc, String signature, String[] exceptions) {
                MethodVisitor mv;
                mv = cv.visitMethod(access, name, desc, signature, exceptions);
                if (mv != null) {
                    mv = new RemoveNopAdapter1(mv);
                }
                return mv;
            }
        }

    }


    /**
     * 示例3：无状态转换方法
     *
     *      即，转换是局部的，不会依赖于在当前指令之前访问的指令
     *      在开头添加的代码总是相同的，而且总会被添加，对于在每个 RETURN 指令之前添加的代码也是如此。
     */
    public static class TransformMethodStateless{


        /**
         * public class C {
         *      public void m() throws Exception {
         *          Thread.sleep(100);
         *      }
         * }
         *
         * 转换为
         *
         * public class C {
         *    public static long timer;
         *    public void m() throws Exception {
         *         timer -= System.currentTimeMillis();
         *         Thread.sleep(100);
         *         timer += System.currentTimeMillis();
         *    }
         * }
         *
         *
         */
        public static class AddTimerAdapter extends ClassVisitor {
            private String owner;
            private boolean isInterface;
            public AddTimerAdapter(ClassVisitor cv) {
                super(ASM4, cv);
            }
            @Override
            public void visit(int version, int access, String name,String signature, String superName, String[] interfaces) {
                cv.visit(version, access, name, signature, superName, interfaces);
                owner = name;
                isInterface = (access & ACC_INTERFACE) != 0;
            }
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor mv = cv.visitMethod(access, name, desc, signature,exceptions);
                if (!isInterface && mv != null && !name.equals("<init>")) {
                    mv = new AddTimerMethodAdapter(mv);
                }
                return mv;
            }
            @Override
            public void visitEnd() {
                if (!isInterface) {
                    FieldVisitor fv = cv.visitField(ACC_PUBLIC + ACC_STATIC, "timer","J", null, null);
                    if (fv != null) {
                        fv.visitEnd();
                    }
                }
                cv.visitEnd();
            }
            class AddTimerMethodAdapter extends MethodVisitor {
                public AddTimerMethodAdapter(MethodVisitor mv) {
                    super(ASM4, mv);
                }
                @Override
                public void visitCode() {
                    mv.visitCode();
                    mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
                    mv.visitMethodInsn(INVOKESTATIC, "java/lang/System","currentTimeMillis", "()J");
                    mv.visitInsn(LSUB);
                    mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
                }
                @Override
                public void visitInsn(int opcode) {
                    if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
                        mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
                        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System","currentTimeMillis", "()J");
                        mv.visitInsn(LADD);
                        mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
                    }
                    mv.visitInsn(opcode);
                }
                @Override
                public void visitMaxs(int maxStack, int maxLocals) {
                    mv.visitMaxs(maxStack + 4, maxLocals);
                }
            }
        }

    }

    /**
     * 示例4：有状态转换方法
     */
    public static class TransformMethodStateful{

    }
}
