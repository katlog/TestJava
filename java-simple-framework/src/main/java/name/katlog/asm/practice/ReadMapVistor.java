package name.katlog.asm.practice;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fw on 2019/12/21
 */
public class ReadMapVistor {

    static class Person{
        private String name;
        private int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    static class RetrunMap{

        public Map<String,Object> map(){
            HashMap<String, Object> result = new HashMap<>();

            result.put("first", Arrays.asList(1,2));
            result.put("second", new HashMap(1) {{
                put(1, 1);
            }});
            result.put("third", new Person("kat", 31));

            return result;
        }
    }


    class ReadMapMethodVistor extends ClassVisitor{
        public ReadMapMethodVistor(int api) {
            super(api);
        }
        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            if (!name.equals("<init>") && mv != null) {
                // 为方法添加计时功能
                mv = new AddTimeMethodAdapter(mv);
            }
            return mv;
        }
    }


    class AddTimeMethodAdapter extends MethodVisitor {

        public AddTimeMethodAdapter(MethodVisitor mv) {
            super(Opcodes.ASM5, mv);
        }


        @Override
        public void visitInsn(int opcode) {
            if (opcode == Opcodes.INVOKESPECIAL) {

            }

            // if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
            //         || opcode == Opcodes.ATHROW) {
            //     mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
            //     mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
            //             "currentTimeMillis", "()J", isInterface);
            //     mv.visitInsn(Opcodes.LADD);
            //     mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
            // }
            // mv.visitInsn(opcode);
        }

    }
}
