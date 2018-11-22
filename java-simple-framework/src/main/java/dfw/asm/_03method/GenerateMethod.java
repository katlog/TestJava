package dfw.asm._03method;

/**
 * Created by dell on 2018/5/5
 */

import dfw.asm.util.AsmUtil;
import org.junit.Test;
import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

/**接口和组件*/
public class GenerateMethod {

    @Test
    /**生成方法*/
    public void generateSetter() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"com/method/Getter", null, "java/lang/Object", null);

        AsmUtil.createNoArgsConstructor(cw);

        cw.visitField(Opcodes.ACC_PRIVATE, "f", "I", null, null).visitEnd();

        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "getter", "()I", null,null);

        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "pkg/Bean", "f", "I");
        mv.visitInsn(IRETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        cw.visitEnd();

        write2File(cw,"/Users/fan/Getter.class");

    }

    private void write2File(ClassWriter cw,String path) {
        // 将cw转换成字节数组写到文件里面去
        byte[] data = cw.toByteArray();
        File file = new File(path);
        try {
            FileOutputStream fout = new FileOutputStream(file,false);
            fout.write(data);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**生成方法*/
    public void generateCheckIfAndSet() {
        ClassVisitor cv = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "<init>", "()V", null,null);

        mv.visitCode();
        mv.visitVarInsn(ILOAD, 1);
        Label label = new Label();
        mv.visitJumpInsn(IFLT, label);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(ILOAD, 1);
        mv.visitFieldInsn(PUTFIELD, "pkg/Bean", "f", "I");
        Label end = new Label();
        mv.visitJumpInsn(GOTO, end);
        mv.visitLabel(label);
        mv.visitFrame(F_SAME, 0, null, 0, null);
        mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL,"java/lang/IllegalArgumentException", "<init>", "()V");
        mv.visitInsn(ATHROW);
        mv.visitLabel(end);
        mv.visitFrame(F_SAME, 0, null, 0, null);
        mv.visitInsn(RETURN);
        mv.visitMaxs(2, 2);
        mv.visitEnd();

        cv.visitEnd();
    }


}
