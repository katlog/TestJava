package name.katlog.asm._03method;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.NOP;

public class ChangeMethod {

    /**非常简单的适配器，删除方法中的 NOP指令*/
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

    /**使用1*/
    /**上面简单适配器的使用*/
    public class RemoveNopClassAdapter1 extends ClassVisitor {
        public RemoveNopClassAdapter1(ClassVisitor cv) {
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

    /**使用2*/
    /**类适配器可选择仅删除方法中的 NOP，而不移除构造器中的该指令*/
    public class RemoveNopClassAdapter2 extends ClassVisitor {
        public RemoveNopClassAdapter2(ClassVisitor cv) {
            super(ASM4, cv);
        }
        @Override
        public MethodVisitor visitMethod(int access, String name,String desc, String signature, String[] exceptions) {
            MethodVisitor mv;

            mv = cv.visitMethod(access, name, desc, signature, exceptions);
            if (mv != null && !name.equals("<init>")) {
                mv = new RemoveNopAdapter1(mv);
            }
            return mv;
        }
    }

//    public class RemoveNopClassAdapter3 extends ClassVisitor {
//        public RemoveNopClassAdapter3(ClassVisitor cv) {
//            super(ASM4, cv);
//        }
//        @Override
//        public MethodVisitor visitMethod(int access, String name,String desc, String signature, String[] exceptions) {
//            MethodVisitor mv1, mv2;
//            mv1 = cv.visitMethod(access, name, desc, signature, exceptions);
//            mv2 = cv.visitMethod(access, "_" + name, desc, signature, exceptions);
//            return new MultiMethodAdapter(mv1, mv2);
//        }
//    }

}