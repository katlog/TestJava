package name.katlog.asm.util;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public final class AsmUtil {

    private AsmUtil() {
    }




    public static void addNoArgsConstructor(ClassWriter cw) {

        //初始化一个无参的构造函数
        MethodVisitor constructor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);

        //这里请看截图
        constructor.visitVarInsn(Opcodes.ALOAD, 0);

        //执行父类的init初始化
        constructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);

        //从当前方法返回void
        constructor.visitInsn(Opcodes.RETURN);
        constructor.visitMaxs(1, 1);
        constructor.visitEnd();
    }
}
