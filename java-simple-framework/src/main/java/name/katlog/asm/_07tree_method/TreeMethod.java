package name.katlog.asm._07tree_method;

import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.util.Iterator;
import java.util.List;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.ILOAD;

public class TreeMethod {

    @Test
    public void generaterMethod() {
        MethodNode mn = new MethodNode();
        InsnList il = mn.instructions;
        il.add(new VarInsnNode(ILOAD, 1));
        LabelNode label = new LabelNode();
        il.add(new JumpInsnNode(IFLT, label));
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new VarInsnNode(ILOAD, 1));
        il.add(new FieldInsnNode(PUTFIELD, "pkg/Bean", "f", "I"));
        LabelNode end = new LabelNode();
        il.add(new JumpInsnNode(GOTO, end));
        il.add(label);
        il.add(new FrameNode(F_SAME, 0, null, 0, null));
        il.add(new TypeInsnNode(NEW, "java/lang/IllegalArgumentException"));
        il.add(new InsnNode(DUP));
        il.add(new MethodInsnNode(INVOKESPECIAL,"java/lang/IllegalArgumentException", "<init>", "()V"));
        il.add(new InsnNode(ATHROW));
        il.add(end);
        il.add(new FrameNode(F_SAME, 0, null, 0, null));
        il.add(new InsnNode(RETURN));
        mn.maxStack = 2;
        mn.maxLocals = 2;

    }

    class ClassTransformer {
        protected ClassTransformer ct;
        public ClassTransformer(ClassTransformer ct) {
            this.ct = ct;
        }
        public void transform(ClassNode cn) {
            if (ct != null) {
                ct.transform(cn);
            }
        }
    }

    public class AddTimerTransformer extends ClassTransformer {
        public AddTimerTransformer(ClassTransformer ct) {
            super(ct);
        }
        @Override
        public void transform(ClassNode cn) {
            for (MethodNode mn : (List<MethodNode>) cn.methods) {
                if ("<init>".equals(mn.name) || "<clinit>".equals(mn.name)) {
                    continue;
                }
                InsnList insns = mn.instructions;
                if (insns.size() == 0) {
                    continue;
                }
                Iterator<AbstractInsnNode> j = insns.iterator();
                while (j.hasNext()) {
                    AbstractInsnNode in = j.next();
                    int op = in.getOpcode();
                    if ((op >= IRETURN && op <= RETURN) || op == ATHROW) {
                        //① 即，这些修改与对 Iterator.next 的调用交织在一起。多线程并发是不受支持的。
                        InsnList il = new InsnList();
                        il.add(new FieldInsnNode(GETSTATIC, cn.name, "timer", "J"));
                        il.add(new MethodInsnNode(INVOKESTATIC, "java/lang/System","currentTimeMillis", "()J"));
                        il.add(new InsnNode(LADD));
                        il.add(new FieldInsnNode(PUTSTATIC, cn.name, "timer", "J"));
                        insns.insert(in.getPrevious(), il);
                    }
                }
                InsnList il = new InsnList();
                il.add(new FieldInsnNode(GETSTATIC, cn.name, "timer", "J"));
                il.add(new MethodInsnNode(INVOKESTATIC, "java/lang/System","currentTimeMillis", "()J"));
                il.add(new InsnNode(LSUB));
                il.add(new FieldInsnNode(PUTSTATIC, cn.name, "timer", "J"));
                insns.insert(il);
                mn.maxStack += 4;
            }
            int acc = ACC_PUBLIC + ACC_STATIC;
            cn.fields.add(new FieldNode(acc, "timer", "J", null, null));
            super.transform(cn);
        }
    }


}
